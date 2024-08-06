package org.irmc.industrialrevival.core;

import com.tcoded.folialib.FoliaLib;
import com.tcoded.folialib.impl.ServerImplementation;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.core.command.IRCommandGenerator;
import org.irmc.industrialrevival.core.data.IDataManager;
import org.irmc.industrialrevival.core.data.MysqlDataManager;
import org.irmc.industrialrevival.core.data.SqliteDataManager;
import org.irmc.industrialrevival.core.implemention.groups.IRItemGroups;
import org.irmc.industrialrevival.core.implemention.items.IRItems;
import org.irmc.industrialrevival.core.listeners.DropListener;
import org.irmc.industrialrevival.core.listeners.GuideListener;
import org.irmc.industrialrevival.core.listeners.ItemHandlerListener;
import org.irmc.industrialrevival.core.listeners.MachineMenuListener;
import org.irmc.industrialrevival.core.message.LanguageManager;
import org.irmc.industrialrevival.core.services.BlockDataService;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.core.services.ItemTextureService;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.industrialrevival.core.utils.FileUtil;
import org.jetbrains.annotations.NotNull;

public final class IndustrialRevival extends JavaPlugin implements IndustrialRevivalAddon {
    @Getter
    private static IndustrialRevival instance;

    private @Getter IRRegistry registry;
    private @Getter LanguageManager languageManager;
    private @Getter IDataManager dataManager;
    private @Getter ItemTextureService itemTextureService;
    private @Getter BlockDataService blockDataService;
    private @Getter ServerImplementation foliaLibImpl;

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
        IRCommandGenerator.registerCommand(this);
    }

    @Override
    public void onEnable() {
        instance = this;

        completeFiles();

        foliaLibImpl = new FoliaLib(this).getImpl();
        languageManager = new LanguageManager(this);
        registry = new IRRegistry();

        setupDataManager();
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
    }

    private void setupListeners() {
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

            if (!sqliteDbFile.exists()) {
                try {
                    sqliteDbFile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                dataManager = new SqliteDataManager(sqliteDbFile);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                String host = config.getString("storage.mysql.host");
                int port = config.getInt("storage.mysql.port");
                String username = config.getString("storage.mysql.username");
                String password = config.getString("storage.mysql.password");
                dataManager = new MysqlDataManager(host + ":" + port, username, password);
            } catch (Exception e) {
                getLogger().severe("Failed to connect to MySQL database, falling back to SQLite");
                try {
                    dataManager = new SqliteDataManager(sqliteDbFile);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public void onDisable() {
        blockDataService.saveAllData();
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
