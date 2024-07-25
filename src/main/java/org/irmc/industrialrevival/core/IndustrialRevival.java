package org.irmc.industrialrevival.core;

import java.sql.SQLException;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.core.command.IRCommandGenerator;
import org.irmc.industrialrevival.core.data.IDataManager;
import org.irmc.industrialrevival.core.data.MysqlDataManager;
import org.irmc.industrialrevival.core.data.SqliteDataManager;
import org.irmc.industrialrevival.core.listeners.GuideListener;
import org.irmc.industrialrevival.core.listeners.ItemHandlerListener;
import org.irmc.industrialrevival.core.listeners.MachineMenuListener;
import org.irmc.industrialrevival.core.listeners.MobDropListener;
import org.irmc.industrialrevival.core.message.LanguageManager;
import org.irmc.industrialrevival.core.services.BlockDataService;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.core.services.ItemTextureService;
import org.irmc.industrialrevival.core.utils.FileUtil;
import org.jetbrains.annotations.NotNull;

public final class IndustrialRevival extends JavaPlugin implements IndustrialRevivalAddon {
    @Getter
    private static IndustrialRevival instance;

    @Getter
    private IRRegistry registry;

    @Getter
    private LanguageManager languageManager;

    @Getter
    private IDataManager dataManager;

    @Getter
    private ItemTextureService itemTextureService;

    private @Getter BlockDataService blockDataService;

    @Override
    public void onEnable() {
        instance = this;

        FileUtil.completeFile(this, "config.yml");

        languageManager = new LanguageManager(this);

        setupDataManager();

        setupServices();
        setupListeners();

        IRCommandGenerator.registerCommand(this);

        //new Metrics(this, ??);
    }

    private void setupServices() {
        registry = new IRRegistry();
        blockDataService = new BlockDataService();
        itemTextureService = new ItemTextureService();
    }

    private void setupListeners() {
        new GuideListener().register();
        new MachineMenuListener().register();
        new ItemHandlerListener().register();
        new MobDropListener().register();
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
        getLogger().info("IndustrialRevival is saving data and shutting down...");
        blockDataService.saveAllData();
        dataManager.close();
    }

    @Override
    public @NotNull JavaPlugin getPlugin() {
        return this;
    }

    @Override
    public String getIssueTrackerURL() {
        return "https://github.com/IndustrialRevival/IndustrialRevival/issues";
    }
}
