package org.irmc.industrialrevival.implementation;

import com.tcoded.folialib.FoliaLib;
import com.tcoded.folialib.impl.PlatformScheduler;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import io.papermc.paper.plugin.configuration.PluginMeta;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.objects.ItemSettings;
import org.irmc.industrialrevival.core.command.IRCommandGenerator;
import org.irmc.industrialrevival.core.data.IDataManager;
import org.irmc.industrialrevival.core.data.impl.MysqlDataManager;
import org.irmc.industrialrevival.core.data.impl.PostgreSQLDataManager;
import org.irmc.industrialrevival.core.data.impl.SqliteDataManager;
import org.irmc.industrialrevival.core.managers.ListenerManager;
import org.irmc.industrialrevival.core.services.BlockDataService;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.core.services.ItemDataService;
import org.irmc.industrialrevival.core.services.ItemTextureService;
import org.irmc.industrialrevival.core.services.ProfilerService;
import org.irmc.industrialrevival.core.task.ArmorCheckTask;
import org.irmc.industrialrevival.core.task.DeEnderDragonTask;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItemSetup;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.pigeonlib.file.ConfigFileUtil;
import org.irmc.pigeonlib.language.LanguageManager;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;

public final class IndustrialRevival extends JavaPlugin implements IndustrialRevivalAddon {

    private static @Getter IndustrialRevival instance;
    private @Getter IRRegistry registry;
    private @Getter LanguageManager languageManager;
    private @Getter ListenerManager listenerManager;
    private @Getter IDataManager dataManager;
    private @Getter ItemTextureService itemTextureService;
    private @Getter BlockDataService blockDataService;
    private @Getter ItemDataService itemDataService;
    private @Getter ProfilerService profilerService;
    private @Getter PlatformScheduler foliaLibImpl;
    private @Getter ItemSettings itemSettings;

    public static void runSync(@Nonnull Runnable runnable) {
        getInstance().getFoliaLibImpl().runNextTick(_ -> runnable.run());
    }

    @Override
    public void onLoad() {
        instance = this;

        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
        IRCommandGenerator.registerCommand(this);

        foliaLibImpl = new FoliaLib(this).getScheduler();

        completeFiles();

        itemSettings = new ItemSettings(YamlConfiguration.loadConfiguration(new File(getDataFolder(), "items-settings.yml")));

        System.setProperty("org.jooq.no-logo", "true");
        System.setProperty("org.jooq.no-tips", "true");
    }

    @Override
    public void onEnable() {
        getLogger().info("IndustrialRevival is being enabled!");

        getLogger().info("Setting up data manager...");
        setupDataManager();

        languageManager = new LanguageManager(this);
        listenerManager = new ListenerManager();
        registry = new IRRegistry();

        getLogger().info("Setting up services...");
        setupServices();

        getLogger().info("Setting up items...");
        setupIndustrialRevivalItems();

        getLogger().info("Setting up listeners...");
        listenerManager.setupAll();

        getLogger().info("Setting up tasks...");
        setupTasks();

        getComponentLogger().info(LanguageManager.parseToComponent("<green>Industrial Revival has been enabled!"));
    }

    private void completeFiles() {
        //TODO: 重写一堆东西
        ConfigFileUtil.completeFile(this, "config.yml");
        //ConfigFileUtil.completeLangFile(this, "language/en-US.yml");
        ConfigFileUtil.completeLangFile(this, "language/zh-CN.yml");

        if (!new File(getDataFolder(), "items-settings.yml").exists()) {
            saveResource("items-settings.yml", false);
        }
    }

    private void setupIndustrialRevivalItems() {
        IRItemGroups.setup();
        IndustrialRevivalItemSetup.setup();
    }

    private void setupServices() {
        blockDataService = new BlockDataService();
        itemTextureService = new ItemTextureService();
        itemDataService = new ItemDataService();
        profilerService = new ProfilerService();
    }

    private void setupDataManager() {
        FileConfiguration config = getConfig();
        String storageType = config.getString("storage.type", "sqlite");
        File sqliteDbFile = new File(Constants.Files.STORAGE_FOLDER, "database.db");

        if (!Constants.Files.STORAGE_FOLDER.exists()) {
            boolean _ = Constants.Files.STORAGE_FOLDER.mkdirs();
        }

        if (storageType.equalsIgnoreCase("sqlite")) {
            this.dataManager = new SqliteDataManager(sqliteDbFile);
        } else if (storageType.equalsIgnoreCase("mysql")) {
            String host = config.getString("storage.mysql.host");
            int port = config.getInt("storage.mysql.port");
            String username = config.getString("storage.mysql.username");
            String password = config.getString("storage.mysql.password");
            this.dataManager = new MysqlDataManager(host + ":" + port, username, password);
        } else if (storageType.equalsIgnoreCase("postgres")) {
            String host = config.getString("storage.mysql.host");
            int port = config.getInt("storage.mysql.port");
            String username = config.getString("storage.mysql.username");
            String password = config.getString("storage.mysql.password");
            this.dataManager = new PostgreSQLDataManager(host + ":" + port, username, password);
        } else {
            getLogger().log(Level.SEVERE, "Unsupported storage type: " + storageType + ", using sqlite instead.");

            this.dataManager = new SqliteDataManager(sqliteDbFile);
        }

        try {
            dataManager.createTables();
        } catch (SQLException e) {
            getLogger().log(Level.SEVERE, "Failed to create tables in database. The plugin will not work properly.", e);
        }
    }

    private void setupTasks() {
        int checkInterval = getConfig().getInt("options.armor-check-interval", 1);
        foliaLibImpl.runTimerAsync(new ArmorCheckTask(checkInterval), checkInterval * 20L, checkInterval * 20L);
        foliaLibImpl.runTimerAsync(IndustrialRevival.getInstance().getProfilerService().getTask(), checkInterval * 20L, checkInterval * 20L);
        int deEnderDragonCheckInterval = getConfig().getInt("options.de-ender-dragon-check.interval", 20);
        int deEnderDragonCheckRadius = getConfig().getInt("options.de-ender-dragon-check.radius", 20);
        foliaLibImpl.runTimerAsync(new DeEnderDragonTask(deEnderDragonCheckRadius), deEnderDragonCheckInterval * 20L, deEnderDragonCheckInterval);
    }

    @Override
    public void onDisable() {
        try {
            itemSettings.getItemCfg().save(new File(getDataFolder(), "items-settings.yml"));
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Failed to save items-settings.yml", e);
        }

        if (blockDataService != null) {
            blockDataService.saveAllData();
        }

        if (dataManager != null) {
            dataManager.close();
        }

        getLogger().info("IndustrialRevival has been disabled!");
    }

    @Override
    public @Nonnull JavaPlugin getPlugin() {
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

    public @Nonnull Set<Plugin> getAddons() {
        String pluginName = instance.getName();

        return Arrays.stream(instance.getServer().getPluginManager().getPlugins())
                .filter(plugin -> {
                    PluginMeta description = plugin.getPluginMeta();
                    return description.getPluginDependencies().contains(pluginName)
                            || description.getPluginSoftDependencies().contains(pluginName);
                })
                .collect(Collectors.toSet());
    }
}
