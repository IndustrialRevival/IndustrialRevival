package org.irmc.industrialrevival.core;

import com.tcoded.folialib.FoliaLib;
import com.tcoded.folialib.impl.ServerImplementation;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.core.command.IRCommandGenerator;
import org.irmc.industrialrevival.core.data.IDataManager;
import org.irmc.industrialrevival.core.data.MysqlDataManager;
import org.irmc.industrialrevival.core.data.SqliteDataManager;
import org.irmc.industrialrevival.core.listeners.*;
import org.irmc.industrialrevival.core.message.LanguageManager;
import org.irmc.industrialrevival.core.services.BlockDataService;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.core.services.ItemDataService;
import org.irmc.industrialrevival.core.services.ItemTextureService;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.industrialrevival.core.utils.FileUtil;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.irmc.industrialrevival.implementation.items.IRItems;
import org.jetbrains.annotations.NotNull;

public final class IndustrialRevival extends JavaPlugin implements IndustrialRevivalAddon {
    @Getter
    private static IndustrialRevival instance;

    private @Getter IRRegistry registry;
    private @Getter LanguageManager languageManager;
    private @Getter IDataManager dataManager;
    private @Getter ItemTextureService itemTextureService;
    private @Getter BlockDataService blockDataService;
    private @Getter ItemDataService itemDataService;
    private @Getter ServerImplementation foliaLibImpl;

    @Override
    public void onLoad() {
        instance = this;
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
        IRCommandGenerator.registerCommand(this);
        completeFiles();
    }

    @Override
    public void onEnable() {
        setupDataManager();

        foliaLibImpl = new FoliaLib(this).getImpl();
        languageManager = new LanguageManager(this);
        registry = new IRRegistry();

        setupServices();

        setupIndustrialRevivalItems();

        setupListeners();

        getComponentLogger().info(LanguageManager.parseToComponent("<green>Industrial Revival has been enabled!"));
    }

    private void completeFiles() {
        FileUtil.completeFile(this, "config.yml");
        FileUtil.completeLangFile(this, "language/en-US.yml");
        FileUtil.completeLangFile(this, "language/zh-CN.yml");
    }

    private void setupIndustrialRevivalItems() {
        IRItems.setup();
        IRItemGroups.setup();
    }

    private void setupServices() {
        blockDataService = new BlockDataService();
        itemTextureService = new ItemTextureService();
        itemDataService = new ItemDataService();
    }

    private void setupListeners() {
        new InteractListener().register();
        new MachineMenuListener().register();
        new ItemHandlerListener().register();
        new GuideListener().register();
        new DropListener().register();
    }

    private void setupDataManager() {
        FileConfiguration config = getConfig();
        String storageType = config.getString("storage.type", "sqlite");
        File sqliteDbFile = new File(Constants.STORAGE_FOLDER, "database.db");
        if (storageType.equalsIgnoreCase("sqlite")) {
            if (!Constants.STORAGE_FOLDER.exists()) {
                Constants.STORAGE_FOLDER.mkdirs();
            }

            this.dataManager = new SqliteDataManager(sqliteDbFile);
        } else {
            String host = config.getString("storage.mysql.host");
            int port = config.getInt("storage.mysql.port");
            String username = config.getString("storage.mysql.username");
            String password = config.getString("storage.mysql.password");
            this.dataManager = new MysqlDataManager(host + ":" + port, username, password);
        }

        try {
            dataManager.createTables();
        } catch (SQLException e) {
            getLogger().log(Level.SEVERE, "Failed to create tables in database. The plugin will not work properly.", e);
        }
    }

    @Override
    public void onDisable() {
        blockDataService.saveAllData();
        getLogger().info("IndustrialRevival has been disabled!");
        getLogger().info("Goodbye!");
    }

    @Override
    public @NotNull JavaPlugin getPlugin() {
        return this;
    }

    @Override
    public String getIssueTrackerURL() {
        return "https://github.com/IndustrialRevival/IndustrialRevival/issues";
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();

        languageManager = new LanguageManager(this);
    }
}
