package org.irmc.industrialrevival.core.data;

import com.google.gson.Gson;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
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

public abstract non-sealed class AbstractDataManager implements IDataManager {
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
            throw new RuntimeException("Failed to initialize connection pool", e);
        }
    }

    @Override
    public void handleBlockPlacing(Location loc, NamespacedKey machineId) {
        try (Connection conn = pool.getConnection()) {
            DSLContext dsl = DSL.using(conn, dialect);
            dsl.insertInto(DSL.table(DSL.name("block_record")))
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
                            DSL.val(machineId.asString()),
                            DSL.val("")
                    )
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to handle block placing", e);
        }
    }

    @Override
    public void handleBlockBreaking(Location loc) {
        try (Connection conn = pool.getConnection()) {
            DSLContext dsl = DSL.using(conn, dialect);
            dsl.deleteFrom(DSL.table(DSL.name("block_record")))
                    .where(DSL.field("world").eq(loc.getWorld().getName()))
                    .and(DSL.field("x").eq(loc.getBlockX()))
                    .and(DSL.field("y").eq(loc.getBlockY()))
                    .and(DSL.field("z").eq(loc.getBlockZ()))
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to handle block breaking", e);
        }
    }

    @Override
    public @NotNull YamlConfiguration getBlockData(@NotNull Location location) {
        String b64;
        try (Connection conn = pool.getConnection()) {
            DSLContext dsl = DSL.using(conn, dialect);
            Object tmp = dsl.select(DSL.field("data"))
                    .from(DSL.table("block_record"))
                    .where(DSL.field("world").eq(location.getWorld().getName()))
                    .and(DSL.field("x").eq(location.getBlockX()))
                    .and(DSL.field("y").eq(location.getBlockY()))
                    .and(DSL.field("z").eq(location.getBlockZ()))
                    .fetchOne(DSL.field("data"));

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
        String b64 = Base64.getEncoder().encodeToString(record.data().getBytes());
        try (Connection conn = pool.getConnection()) {
            DSLContext dsl = DSL.using(conn, dialect);
            dsl.update(DSL.table("block_record"))
                    .set(DSL.field("data"), DSL.val(b64))
                    .where(DSL.field("world").eq(location.getWorld().getName()))
                    .and(DSL.field("x").eq(location.getBlockX()))
                    .and(DSL.field("y").eq(location.getBlockY()))
                    .and(DSL.field("z").eq(location.getBlockZ()))
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update block data", e);
        }
    }

    @Override
    public List<BlockRecord> getAllBlockRecords() {
        try (Connection conn = pool.getConnection()) {
            DSLContext dsl = DSL.using(conn, dialect);
            return dsl.select(
                            DSL.field("world"),
                            DSL.field("x"),
                            DSL.field("y"),
                            DSL.field("z"),
                            DSL.field("machineId"),
                            DSL.field("data"))
                    .from(DSL.table("block_record"))
                    .fetchInto(BlockRecord.class);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get all block records", e);
        }
    }

    @Override
    public ItemStack getMenuItem(Location location, int slot) {
        try (Connection conn = pool.getConnection()) {
            DSLContext dsl = DSL.using(conn, dialect);
            Record2<Object, Object> results = dsl.select(
                            DSL.field("itemJson"), DSL.field("itemClass"))
                    .from(DSL.table("menu_items"))
                    .where(DSL.field("world").eq(location.getWorld().getName()))
                    .and(DSL.field("x").eq(location.getBlockX()))
                    .and(DSL.field("y").eq(location.getBlockY()))
                    .and(DSL.field("z").eq(location.getBlockZ()))
                    .and(DSL.field("slot").eq(slot))
                    .fetchOne();

            if (results == null) {
                return null;
            } else {
                Gson gson = new Gson();
                String json = results.getValue(0, String.class);
                String className = results.getValue(1, String.class);
                try {
                    Class<? extends ItemStack> clazz = (Class<? extends ItemStack>) Class.forName(className);
                    return gson.fromJson(json, clazz);
                } catch (ClassNotFoundException e) {
                    return gson.fromJson(json, ItemStack.class);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get menu item", e);
        }
    }

    @Override
    public GuideSettings getGuideSettings(@NotNull String playerName) {
        try (Connection conn = pool.getConnection()) {
            DSLContext dsl = DSL.using(conn, dialect);
            Record3<Object, Object, Object> results = dsl.select(
                            DSL.field("fireWorksEnabled"),
                            DSL.field("learningAnimationEnabled"),
                            DSL.field("language"))
                    .from(DSL.table("guide_settings"))
                    .where(DSL.field("username").eq(playerName))
                    .fetchOne();

            if (results == null) {
                return GuideSettings.DEFAULT_SETTINGS;
            } else {
                return new GuideSettings(
                        results.get(0, Boolean.class),
                        results.get(1, Boolean.class),
                        results.get(2, String.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get guide settings", e);
        }
    }

    @Override
    public void saveGuideSettings(@NotNull String playerName, @NotNull GuideSettings settings) {
        try (Connection conn = pool.getConnection()) {
            DSLContext dsl = DSL.using(conn, dialect);
            dsl.insertInto(DSL.table("guide_settings"))
                    .set(DSL.field("username"), DSL.val(playerName))
                    .set(DSL.field("fireWorksEnabled"), DSL.val(settings.isFireWorksEnabled()))
                    .set(DSL.field("learningAnimationEnabled"), DSL.val(settings.isLearningAnimationEnabled()))
                    .set(DSL.field("language"), DSL.val(settings.getLanguage()))
                    .onDuplicateKeyUpdate()
                    .set(DSL.field("fireWorksEnabled"), DSL.val(settings.isFireWorksEnabled()))
                    .set(DSL.field("learningAnimationEnabled"), DSL.val(settings.isLearningAnimationEnabled()))
                    .set(DSL.field("language"), DSL.val(settings.getLanguage()))
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save guide settings", e);
        }
    }

    @Override
    public @NotNull YamlConfiguration getResearchStatus(String playerName) {
        try (Connection conn = pool.getConnection()) {
            DSLContext dsl = DSL.using(conn, dialect);
            Object tmp = dsl.select(DSL.field("researchStatus"))
                    .from(DSL.table("research_status"))
                    .where(DSL.field("username").eq(playerName))
                    .fetchOne(DSL.field("researchStatus"));

            if (tmp == null) {
                return new YamlConfiguration();
            } else {
                String yaml = new String(Base64.getDecoder().decode((String) tmp));
                return YamlConfiguration.loadConfiguration(new StringReader(yaml));
            }
        } catch (SQLException e) {
            return new YamlConfiguration();
        }
    }

    @Override
    public void saveResearchStatus(String playerName, YamlConfiguration researchStatus) {
        String yaml = researchStatus.saveToString();
        String b64 = Base64.getEncoder().encodeToString(yaml.getBytes());
        try (Connection conn = pool.getConnection()) {
            DSLContext dsl = DSL.using(conn, dialect);
            dsl.insertInto(DSL.table("research_status"))
                    .set(DSL.field("username"), DSL.val(playerName))
                    .set(DSL.field("researchStatus"), DSL.val(b64))
                    .onDuplicateKeyUpdate()
                    .set(DSL.field("researchStatus"), DSL.val(b64))
                    .execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save research status", e);
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
