package org.irmc.industrialrevival.core.data;

import com.google.gson.Gson;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.data.object.BlockRecord;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.JDBCUtils;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

public non-sealed class AbstractDataManager implements IDataManager {
    private final ConnectionPool pool;

    private final String url;
    private final String username;
    private final String password;
    private final SQLDialect dialect;

    public AbstractDataManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.dialect = JDBCUtils.dialect(url);

        try {
            this.pool = new ConnectionPool(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleBlockPlacing(Location loc, String machineId) {
        try {
            Connection conn = pool.getConnection();
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            DSLContext dsl = DSL.using(conn, dialect);
            dsl.insertInto(DSL.table(DSL.name("block_record")))
                    // world, x, y, z, machineId, data
                    .columns(
                            DSL.field("world"),
                            DSL.field("x"),
                            DSL.field("y"),
                            DSL.field("z"),
                            DSL.field("machineId"),
                            DSL.field("data"))
                    .values(
                            DSL.val(loc.getWorld().getName()),
                            DSL.val(loc.getBlockX()),
                            DSL.val(loc.getBlockY()),
                            DSL.val(loc.getBlockZ()),
                            DSL.val(machineId),
                            DSL.val("")
                    )
                    .execute();

            pool.releaseConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleBlockBreaking(Location loc) {
        try {
            Connection conn = pool.getConnection();
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            DSLContext dsl = DSL.using(conn, dialect);
            dsl.deleteFrom(DSL.table(DSL.name("block_record")))
                    .where(DSL.field(DSL.name("world")).eq(loc.getWorld().getName()))
                    .and(DSL.field(DSL.name("x")).eq(loc.getBlockX()))
                    .and(DSL.field(DSL.name("y")).eq(loc.getBlockY()))
                    .and(DSL.field(DSL.name("z")).eq(loc.getBlockZ()))
                    .executeAsync()
                    .toCompletableFuture()
                    .join();

            pool.releaseConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull YamlConfiguration getBlockData(@NotNull Location location) {
        String b64;
        try {
            Connection conn = pool.getConnection();
            DSLContext dsl = DSL.using(conn, dialect);
            Object tmp = dsl.select(DSL.field(DSL.name("data")))
                    .from(DSL.table(DSL.name("block_record")))
                    .where(DSL.field(DSL.name("world")).eq(location.getWorld().getName()))
                    .and(DSL.field(DSL.name("x")).eq(location.getBlockX()))
                    .and(DSL.field(DSL.name("y")).eq(location.getBlockY()))
                    .and(DSL.field(DSL.name("z")).eq(location.getBlockZ()))
                    .fetchOne(DSL.field(DSL.name("data")));

            pool.releaseConnection(conn);

            if (tmp == null) {
                return new YamlConfiguration();
            } else {
                b64 = (String) tmp;
            }
        } catch (SQLException e) {
            return new YamlConfiguration();
        }

        return YamlConfiguration.loadConfiguration(new StringReader(b64));
    }

    @Override
    public void updateBlockData(@NotNull Location location, @NotNull BlockRecord record) {
        try {
            String b64 = Base64.getEncoder().encodeToString(record.data().getBytes());
            DSLContext dsl = DSL.using(pool.getConnection(), dialect);
            dsl.update(DSL.table(DSL.name("block_record")))
                    .set(DSL.field(DSL.name("data")), DSL.val(b64))
                    .where(DSL.field(DSL.name("world")).eq(location.getWorld().getName()))
                    .and(DSL.field(DSL.name("x")).eq(location.getBlockX()))
                    .and(DSL.field(DSL.name("y")).eq(location.getBlockY()))
                    .and(DSL.field(DSL.name("z")).eq(location.getBlockZ()))
                    .executeAsync()
                    .toCompletableFuture()
                    .join();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BlockRecord> getAllBlockRecords() {
        try {
            DSLContext dsl = DSL.using(pool.getConnection(), dialect);
            return dsl.select(
                            DSL.field(DSL.name("world")),
                            DSL.field(DSL.name("x")),
                            DSL.field(DSL.name("y")),
                            DSL.field(DSL.name("z")),
                            DSL.field(DSL.name("machineId")),
                            DSL.field(DSL.name("data")))
                    .from(DSL.table(DSL.name("block_record")))
                    .fetchInto(BlockRecord.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemStack getMenuItem(Location location, int slot) {
        try {
            DSLContext dsl = DSL.using(pool.getConnection(), dialect);
            Record2<Object, Object> results = dsl.select(
                            DSL.field(DSL.name("itemJson")), DSL.field(DSL.name("itemClass")))
                    .from(DSL.table(DSL.name("menu_items")))
                    .where(DSL.field(DSL.name("world")).eq(location.getWorld().getName()))
                    .and(DSL.field(DSL.name("x")).eq(location.getBlockX()))
                    .and(DSL.field(DSL.name("y")).eq(location.getBlockY()))
                    .and(DSL.field(DSL.name("z")).eq(location.getBlockZ()))
                    .and(DSL.field(DSL.name("slot")).eq(slot))
                    .fetchOne();
            if (results == null) {
                return null;
            } else {
                Gson gson = new Gson();
                String json = results.getValue(0, String.class);
                String className = results.getValue(1, String.class);
                Class<? extends ItemStack> clazz;
                try {
                    clazz = (Class<? extends ItemStack>) Class.forName(className);
                } catch (ClassNotFoundException e) {
                    clazz = ItemStack.class;
                }
                return gson.fromJson(json, clazz);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GuideSettings getGuideSettings(@NotNull String playerName) {
        try {
            DSLContext dsl = DSL.using(pool.getConnection(), dialect);
            Record3<Object, Object, Object> results = dsl.select(
                            DSL.field(DSL.name("fireWorksEnabled")),
                            DSL.field(DSL.name("learningAnimationEnabled")),
                            DSL.field(DSL.name("language")))
                    .from(DSL.table(DSL.name("guide_settings")))
                    .where(DSL.field(DSL.name("username")).eq(playerName))
                    .fetchOne();
            if (results == null) {
                return GuideSettings.DEFAULT_SETTINGS;
            } else {
                return new GuideSettings(
                        results.get(0, Boolean.class), results.get(1, Boolean.class), results.get(2, String.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveGuideSettings(@NotNull String playerName, @NotNull GuideSettings settings) {
        try {
            DSLContext dsl = DSL.using(pool.getConnection(), dialect);
            dsl.insertInto(DSL.table(DSL.name("guide_settings")))
                    .select(DSL.select(
                            DSL.val(playerName),
                            DSL.val(settings.isFireWorksEnabled()),
                            DSL.val(settings.isLearningAnimationEnabled()),
                            DSL.val(settings.getLanguage())))
                    .bind("username", DSL.val(playerName))
                    .bind("fireWorksEnabled", DSL.val(settings.isFireWorksEnabled()))
                    .bind("learningAnimationEnabled", DSL.val(settings.isLearningAnimationEnabled()))
                    .bind("language", DSL.val(settings.getLanguage()))
                    .executeAsync()
                    .toCompletableFuture()
                    .join();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull YamlConfiguration getResearchStatus(String playerName) {
        String yaml;
        try {
            DSLContext dsl = DSL.using(pool.getConnection(), dialect);
            Object tmp = dsl.select(DSL.field(DSL.name("researchStatus")))
                    .from(DSL.table(DSL.name("research_status")))
                    .where(DSL.field(DSL.name("username")).eq(playerName))
                    .fetchOne(DSL.field(DSL.name("researchStatus")));
            if (tmp == null) {
                return new YamlConfiguration();
            } else {
                yaml = (String) tmp;
            }
        } catch (SQLException e) {
            return new YamlConfiguration();
        }

        yaml = new String(Base64.getDecoder().decode(yaml));
        return YamlConfiguration.loadConfiguration(new StringReader(yaml));
    }

    @Override
    public void saveResearchStatus(String playerName, YamlConfiguration researchStatus) {
        String yaml = researchStatus.saveToString();
        String b64 = Base64.getEncoder().encodeToString(yaml.getBytes());
        try {
            DSLContext dsl = DSL.using(pool.getConnection(), dialect);
            dsl.insertInto(DSL.table(DSL.name("research_status")))
                    .select(DSL.select(DSL.val(playerName), DSL.val(b64)))
                    .bind("username", DSL.val(playerName))
                    .bind("researchStatus", DSL.val(b64))
                    .executeAsync()
                    .toCompletableFuture()
                    .join();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTables() {
        int retryCount = 5;
        while (retryCount > 0) {
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                conn.prepareStatement(
                                "CREATE TABLE IF NOT EXISTS guide_settings (username TEXT NOT NULL, fireWorksEnabled BOOLEAN NOT NULL, learningAnimationEnabled BOOLEAN NOT NULL, language TEXT NOT NULL);")
                        .execute();
                conn.prepareStatement(
                                "CREATE TABLE IF NOT EXISTS research_status (username TEXT NOT NULL, researchStatus TEXT NOT NULL);")
                        .execute();
                conn.prepareStatement(
                                "CREATE TABLE IF NOT EXISTS block_record (world TEXT NOT NULL, x INT NOT NULL, y INT NOT NULL, z INT NOT NULL, machineId TEXT NOT NULL, data TEXT DEFAULT NULL, PRIMARY KEY (world, x, y, z));")
                        .execute();
                conn.prepareStatement(
                                "CREATE TABLE IF NOT EXISTS menu_items (world TEXT NOT NULL, x INT NOT NULL, y INT NOT NULL, z INT NOT NULL, slot INT NOT NULL, itemJson TEXT NOT NULL, itemClass TEXT NOT NULL, PRIMARY KEY (world, x, y, z));")
                        .execute();
                return;
            } catch (SQLException e) {
                if (e.getMessage().contains("SQLITE_BUSY")) {
                    retryCount--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new RuntimeException("Failed to create tables after multiple attempts.");
    }

    @Override
    public void close() {
        pool.closeAllConnections();
    }
}
