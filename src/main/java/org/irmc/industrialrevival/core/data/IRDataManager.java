package org.irmc.industrialrevival.core.data;

import com.google.common.base.Strings;
import io.github.lijinhong11.mdatabase.DatabaseConnection;
import io.github.lijinhong11.mdatabase.DatabaseParameters;
import io.github.lijinhong11.mdatabase.enums.DatabaseType;
import io.github.lijinhong11.mdatabase.impl.SQLConnections;
import io.github.lijinhong11.mdatabase.sql.conditions.Conditions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.dock.IRDock;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.Constants;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//todo: move to implementation
public class IRDataManager {
    private final DatabaseConnection connection;
    private final Logger LOGGER = IRDock.getPlugin().getLogger();

    public IRDataManager(IndustrialRevival plugin) {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("storage");
        if (section == null) {
            section = plugin.getConfig().createSection("storage");
        }

        DatabaseType type = DatabaseType.getByName(section.getString("type", "SQLITE"));
        DatabaseConnection conn;

        String host = section.getString("remote.host");
        int port = section.getInt("remote.port");
        String username = section.getString("remote.username");
        String password = section.getString("remote.password");
        String database = section.getString("remote.database");

        if (Strings.isNullOrEmpty(host)) {
            LOGGER.warning("Remote database host is not set, using sqlite instead.");
            conn = SQLConnections.sqlite(Constants.Files.SQLITE_DB_FILE.getAbsolutePath(), new DatabaseParameters());
        } else {
            switch (type) {
                case MYSQL ->
                        conn = SQLConnections.mysql(host, port, database, username, password, new DatabaseParameters());
                case MARIADB ->
                        conn = SQLConnections.mariadb(host, port, database, username, password, new DatabaseParameters());
                default ->
                        conn = SQLConnections.sqlite(Constants.Files.SQLITE_DB_FILE.getAbsolutePath(), new DatabaseParameters());
            }
        }

        connection = conn;
        connection.setDebug(true);
    }

    public void init() {
        try {
            connection.createTableByClass(BlockRecord.class);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, """
                    ========================= FATAL ERROR
                    Failed to create tables!
                    That means the plugin cannot work anymore.
                    Disabling...
                    =========================
                    """, e);

            Bukkit.getPluginManager().disablePlugin(IRDock.getPlugin());
        }
    }

    public List<BlockRecord> getAllBlockRecords() {
        try {
            return connection.selectMulti(BlockRecord.class);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to get all block records!", e);
            return new ArrayList<>();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException _) {
        }
    }

    public void saveBlockRecord(BlockRecord record) {
        try {
            connection.upsertObject(BlockRecord.class, record);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to save block record!", e);
        }
    }

    public BlockRecord getBlockRecord(Location loc) {
        try {
            return connection.selectOne(BlockRecord.class,
                    Conditions.and(
                            Conditions.eq("world", loc.getWorld().getName()),
                            Conditions.eq("x", loc.getBlockX()),
                            Conditions.eq("y", loc.getBlockY()),
                            Conditions.eq("z", loc.getBlockZ())));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to get block record!", e);
        }

        return null;
    }

    @NotNull
    public ConfigurationSection getBlockData(Location loc) {
        BlockRecord record = getBlockRecord(loc);
        if (record == null) {
            return new YamlConfiguration();
        }

        return record.getData();
    }

    public void handleBlockPlacing(@NotNull Location location, @NotNull NamespacedKey id) {
        BlockRecord record = BlockRecord.warp(location, id);

        saveBlockRecord(record);
    }

    public void handleBlockBreaking(@NotNull Location location) {
        try {
            connection.deleteObject(BlockRecord.class, Conditions.and(
                    Conditions.eq("world", location.getWorld().getName()),
                    Conditions.eq("x", location.getBlockX()),
                    Conditions.eq("y", location.getBlockY()),
                    Conditions.eq("z", location.getBlockZ())
            ));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to handle block breaking!", e);
        }
    }
}
