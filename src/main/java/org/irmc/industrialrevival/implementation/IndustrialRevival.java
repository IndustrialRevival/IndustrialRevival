package org.irmc.industrialrevival.implementation;

import com.tcoded.folialib.FoliaLib;
import com.tcoded.folialib.impl.ServerImplementation;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import io.papermc.lib.PaperLib;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.MCVersion;
import org.irmc.industrialrevival.core.command.IRCommandGenerator;
import org.irmc.industrialrevival.core.data.IDataManager;
import org.irmc.industrialrevival.core.data.MysqlDataManager;
import org.irmc.industrialrevival.core.data.SqliteDataManager;
import org.irmc.industrialrevival.core.managers.ListenerManager;
import org.irmc.industrialrevival.core.services.BlockDataService;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.core.services.ItemDataService;
import org.irmc.industrialrevival.core.services.ItemTextureService;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.irmc.industrialrevival.implementation.items.IRItems;
import org.irmc.pigeonlib.file.ConfigFileUtil;
import org.irmc.pigeonlib.language.LanguageManager;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;

public final class IndustrialRevival extends JavaPlugin implements IndustrialRevivalAddon {

    private static @Getter IndustrialRevival instance;
    private @Getter MCVersion mcVersion;
    private @Getter IRRegistry registry;
    private @Getter LanguageManager languageManager;
    private @Getter ListenerManager listenerManager;
    private @Getter IDataManager dataManager;
    private @Getter ItemTextureService itemTextureService;
    private @Getter BlockDataService blockDataService;
    private @Getter ItemDataService itemDataService;
    private @Getter ServerImplementation foliaLibImpl;

    @Override
    public void onLoad() {
        instance = this;
        foliaLibImpl = new FoliaLib(this).getImpl();

        completeFiles();

        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
        IRCommandGenerator.registerCommand(this);
    }

    @Override
    public void onEnable() {
        getLogger().info("IndustrialRevival is being enabled!");

        boolean success = environmentCheck();
        if (!success) {
            onDisable();
            return;
        }

        getLogger().info("Setting up data manager...");
        setupDataManager();

        getLogger().info("Completing files...");
        completeFiles();

        languageManager = new LanguageManager(this);
        listenerManager = new ListenerManager();
        registry = new IRRegistry();

        getLogger().info("Setting up data manager...");
        setupDataManager();

        getLogger().info("Setting up services...");
        setupServices();

        getLogger().info("Setting up items...");
        setupIndustrialRevivalItems();

        getLogger().info("Setting up listeners...");
        listenerManager.setupAll();

        getComponentLogger().info(LanguageManager.parseToComponent("<green>Industrial Revival has been enabled!"));
    }

    private void completeFiles() {
        ConfigFileUtil.completeFile(this, "config.yml");
        ConfigFileUtil.completeLangFile(this, "language/en-US.yml");
        ConfigFileUtil.completeLangFile(this, "language/zh-CN.yml");
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
        if (blockDataService != null) {
            blockDataService.saveAllData();
        }
        getLogger().info("IndustrialRevival has been disabled!");
    }

    public boolean environmentCheck() {
        int major = PaperLib.getMinecraftVersion();
        int minor = PaperLib.getMinecraftPatchVersion();
        mcVersion = mcVersion.getByInt(major, minor);
        if (mcVersion == MCVersion.UNKNOWN) {
            getLogger().log(Level.SEVERE, "Unsupported Minecraft version: 1." + major + "." + minor);
            return false;
        }

        return true;
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
