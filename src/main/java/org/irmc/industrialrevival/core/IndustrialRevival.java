package org.irmc.industrialrevival.core;

import java.sql.SQLException;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.industrialrevival.core.data.IDataManager;
import org.irmc.industrialrevival.core.data.MysqlDataManager;
import org.irmc.industrialrevival.core.data.SqliteDataManager;
import org.irmc.industrialrevival.core.listeners.ItemHandlerListener;
import org.irmc.industrialrevival.core.listeners.MachineMenuListener;
import org.irmc.industrialrevival.core.message.LanguageManager;
import org.irmc.industrialrevival.core.registry.IRRegistry;
import org.irmc.industrialrevival.core.utils.FileUtil;

public final class IndustrialRevival extends JavaPlugin {
    @Getter
    private static IndustrialRevival instance;

    @Getter
    private IRRegistry registry;

    @Getter
    private LanguageManager languageManager;

    @Getter
    private IDataManager dataManager;

    @Override
    public void onEnable() {
        instance = this;

        FileUtil.completeFile(this, "config.yml");

        // objects
        languageManager = new LanguageManager(this);
        registry = new IRRegistry();

        // listeners
        new MachineMenuListener().register();
        new ItemHandlerListener().register();

        setupDataManager();
    }

    private void setupDataManager() {
        FileConfiguration config = getConfig();
        String storageType = config.getString("storage.type", "sqlite");
        if (storageType.equalsIgnoreCase("sqlite")) {
            try {
                dataManager = new SqliteDataManager();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                dataManager = new MysqlDataManager();
            } catch (Exception e) {
                getLogger().severe("Failed to connect to MySQL database, falling back to SQLite");
                try {
                    dataManager = new SqliteDataManager();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public void onDisable() {
        dataManager.close();
    }
}
