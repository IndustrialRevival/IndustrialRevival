package org.irmc.industrialrevival.core.data;

import com.google.gson.Gson;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.data.object.BlockRecord;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.jetbrains.annotations.NotNull;

import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public non-sealed class AbstractDataManager implements IDataManager {
    private final ConnectionPool pool;

    private final String url;
    private final String username;
    private final String password;

    public AbstractDataManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

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
           PreparedStatement pstmt = conn.prepareStatement("INSERT INTO block_record (world, x, y, z, machineId, data) VALUES (?,?,?,?,?,?)");

           pstmt.setString(1, loc.getWorld().getName());
           pstmt.setInt(2, loc.getBlockX());
           pstmt.setInt(3, loc.getBlockY());
           pstmt.setInt(4, loc.getBlockZ());
           pstmt.setString(5, machineId);
           pstmt.setString(6, "");

           pstmt.executeUpdate();
           pstmt.close();
           pool.releaseConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleBlockBreaking(Location loc) {
        try {
            Connection conn = pool.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM block_record WHERE world =? AND x =? AND y =? AND z =?");

            pstmt.setString(1, loc.getWorld().getName());
            pstmt.setInt(2, loc.getBlockX());
            pstmt.setInt(3, loc.getBlockY());
            pstmt.setInt(4, loc.getBlockZ());
            pstmt.executeUpdate();

            pstmt.close();
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT data FROM block_record WHERE world =? AND x =? AND y =? AND z =?");
            pstmt.setString(1, location.getWorld().getName());
            pstmt.setInt(2, location.getBlockX());
            pstmt.setInt(3, location.getBlockY());
            pstmt.setInt(4, location.getBlockZ());

            ResultSet rs = pstmt.executeQuery();
            String tmp = rs.getString("data");
            rs.close();
            pstmt.close();
            pool.releaseConnection(conn);

            if (tmp == null) {
                return new YamlConfiguration();
            } else {
                b64 = tmp;
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

            Connection conn = pool.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE block_record SET data=? WHERE world =? AND x =? AND y =? AND z =?");

            pstmt.setString(1, b64);
            pstmt.setString(2, location.getWorld().getName());
            pstmt.setInt(3, location.getBlockX());
            pstmt.setInt(4, location.getBlockY());
            pstmt.setInt(5, location.getBlockZ());
            pstmt.executeUpdate();

            pstmt.close();
            pool.releaseConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BlockRecord> getAllBlockRecords() {
        try {
            List<BlockRecord> records = new ArrayList<>();

            Connection conn = pool.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM block_record");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String world = rs.getString("world");
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                int z = rs.getInt("z");
                String machineId = rs.getString("machineId");
                String data = rs.getString("data");
                records.add(new BlockRecord(world, x, y, z, machineId, data));
            }

            rs.close();
            pstmt.close();
            pool.releaseConnection(conn);
            return records;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemStack getMenuItem(Location location, int slot) {
        try {
            Connection conn = pool.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT itemJson, itemClass FROM menu_items WHERE world =? AND x =? AND y =? AND z =? AND slot =?");
            pstmt.setString(1, location.getWorld().getName());
            pstmt.setInt(2, location.getBlockX());
            pstmt.setInt(3, location.getBlockY());
            pstmt.setInt(4, location.getBlockZ());
            pstmt.setInt(5, slot);

            ResultSet rs = pstmt.executeQuery();
            String json = rs.getString("itemJson");
            String className = rs.getString("itemClass");
            rs.close();
            pstmt.close();
            pool.releaseConnection(conn);

            if (json == null) {
                return null;
            } else {
                Gson gson = new Gson();
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
            Connection conn = pool.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM guide_settings WHERE username =?");
            pstmt.setString(1, playerName);

            ResultSet rs = pstmt.executeQuery();
            boolean fireWorksEnabled = rs.getBoolean("fireWorksEnabled");
            boolean learningAnimationEnabled = rs.getBoolean("learningAnimationEnabled");
            String language = rs.getString("language");
            rs.close();
            pstmt.close();
            pool.releaseConnection(conn);

            if (language == null) {
                return GuideSettings.DEFAULT_SETTINGS;
            }

            return new GuideSettings(fireWorksEnabled, learningAnimationEnabled, language);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveGuideSettings(@NotNull String playerName, @NotNull GuideSettings settings) {
        try {
            Connection conn = pool.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO guide_settings (username, fireWorksEnabled, learningAnimationEnabled, language) VALUES (?,?,?,?) ON CONFLICT (username) DO UPDATE SET fireWorksEnabled = excluded.fireWorksEnabled, learningAnimationEnabled = excluded.learningAnimationEnabled, language = excluded.language");
            pstmt.setString(1, playerName);
            pstmt.setBoolean(2, settings.isFireWorksEnabled());
            pstmt.setBoolean(3, settings.isLearningAnimationEnabled());
            pstmt.setString(4, settings.getLanguage());
            pstmt.executeUpdate();
            pstmt.close();
            pool.releaseConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull YamlConfiguration getResearchStatus(String playerName) {
        /*
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

         */

        return new YamlConfiguration();
    }

    @Override
    public void saveResearchStatus(String playerName, YamlConfiguration researchStatus) {
        /*
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

         */
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
