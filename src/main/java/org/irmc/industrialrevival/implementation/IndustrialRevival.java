package org.irmc.industrialrevival.implementation;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.tcoded.folialib.FoliaLib;
import com.tcoded.folialib.impl.PlatformScheduler;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import io.papermc.paper.plugin.configuration.PluginMeta;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.objects.ItemSettings;
import org.irmc.industrialrevival.core.command.IRCommandGenerator;
import org.irmc.industrialrevival.core.data.IRDataManager;
import org.irmc.industrialrevival.core.managers.ListenerManager;
import org.irmc.industrialrevival.core.services.BlockDataService;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.core.services.ItemDataService;
import org.irmc.industrialrevival.core.services.ItemTextureService;
import org.irmc.industrialrevival.core.services.LanguageTextService;
import org.irmc.industrialrevival.core.services.ProfilerService;
import org.irmc.industrialrevival.core.task.AnitEnderDragonTask;
import org.irmc.industrialrevival.core.task.ArmorCheckTask;
import org.irmc.industrialrevival.core.task.PostSetupTask;
import org.irmc.industrialrevival.core.world.populators.ElementOreGenerator;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItemSetup;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.industrialrevival.utils.WorldUtil;
import org.irmc.pigeonlib.enums.Language;
import org.irmc.pigeonlib.file.ConfigFileUtil;
import org.irmc.pigeonlib.language.LanguageManager;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;

public final class IndustrialRevival extends JavaPlugin implements IndustrialRevivalAddon {

    private static @Getter IndustrialRevival instance;
    private @Getter IRRegistry registry;
    private @Getter LanguageManager languageManager;
    private @Getter ListenerManager listenerManager;
    private @Getter IRDataManager dataManager;
    private @Getter ItemTextureService itemTextureService;
    private @Getter LanguageTextService languageTextService;
    private @Getter BlockDataService blockDataService;
    private @Getter ItemDataService itemDataService;
    private @Getter ProfilerService profilerService;
    private @Getter PlatformScheduler foliaLibImpl;
    private @Getter ItemSettings itemSettings;
    private @Getter ElementOreGenerator elementOreGenerator;

    public static void runSync(@Nonnull Runnable runnable) {
        getInstance().getFoliaLibImpl().runNextTick(_ -> runnable.run());
    }

    public static void runAsync(@Nonnull Runnable runnable) {
        getInstance().getFoliaLibImpl().runAsync(_ -> runnable.run());
    }

    public static @Nonnull Set<Plugin> getAddons() {
        String pluginName = instance.getName();

        return Arrays.stream(instance.getServer().getPluginManager().getPlugins())
                .filter(plugin -> {
                    PluginMeta description = plugin.getPluginMeta();
                    return description.getPluginDependencies().contains(pluginName)
                            || description.getPluginSoftDependencies().contains(pluginName);
                })
                .collect(Collectors.toSet());
    }

    @Override
    public void onLoad() {
        instance = this;

        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
        IRCommandGenerator.registerCommand(this);

        foliaLibImpl = new FoliaLib(this).getScheduler();

        completeFiles();

        itemSettings = new ItemSettings(YamlConfiguration.loadConfiguration(Constants.Files.ITEM_SETTINGS_FILE));

        setupProtocolLib();

        System.setProperty("org.jooq.no-logo", "true");
        System.setProperty("org.jooq.no-tips", "true");
    }

    @Override
    public void onEnable() {
        getLogger().info("IndustrialRevival is being enabled!");

        getLogger().info("Setting up data manager...");
        setupDataManager();

        languageManager = new LanguageManager(this, Language.ZH_CN);
        listenerManager = new ListenerManager();
        registry = new IRRegistry();

        getLogger().info("Setting up items...");
        setupIndustrialRevivalItems();

        getLogger().info("Setting up services...");
        setupServices();

        getLogger().info("Setting up listeners...");
        listenerManager.setupAll();

        getLogger().info("Setting up tasks...");
        setupTasks();

        getLogger().info("Adding block populators...");
        this.elementOreGenerator = new ElementOreGenerator();
        World overworld = Bukkit.getWorld("world");
        if (overworld != null) {
            WorldUtil.addPopulatorTo(overworld, elementOreGenerator);
        }

        getComponentLogger().info(LanguageManager.parseToComponent("<green>Industrial Revival has been enabled!"));
    }

    private void completeFiles() {
        ConfigFileUtil.completeFile(this, "config.yml");
        //ConfigFileUtil.completeLangFile(this, "language/en-US.yml");
        ConfigFileUtil.completeLangFile(this, "language/zh-CN.yml");

        if (!Constants.Files.ITEM_SETTINGS_FILE.exists()) {
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
        languageTextService = new LanguageTextService();
    }

    private void setupDataManager() {
        if (!Constants.Files.STORAGE_FOLDER.exists()) {
            Constants.Files.STORAGE_FOLDER.mkdirs();
        }

        dataManager = new IRDataManager(this);
        dataManager.init();
    }

    private void setupTasks() {
        int checkInterval = getConfig().getInt("options.armor-check-interval", 1);
        foliaLibImpl.runTimerAsync(new ArmorCheckTask(checkInterval), checkInterval, checkInterval);
        foliaLibImpl.runTimerAsync(IndustrialRevival.getInstance().getProfilerService().getTask(), checkInterval, checkInterval);
        int deEnderDragonCheckInterval = getConfig().getInt("options.anti-ender-dragon-check.interval", 20);
        int deEnderDragonCheckRadius = getConfig().getInt("options.anti-ender-dragon-check.radius", 20);
        foliaLibImpl.runTimerAsync(new AnitEnderDragonTask(deEnderDragonCheckRadius), deEnderDragonCheckInterval, deEnderDragonCheckInterval);
        foliaLibImpl.runAsync(new PostSetupTask());
    }

    private void setupProtocolLib() {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();

        manager.addPacketListener(
                new PacketAdapter(
                        this,
                        ListenerPriority.HIGHEST,
                        PacketType.Play.Client.PICK_ITEM
                ) {
                    @Override
                    public void onPacketReceiving(PacketEvent event) {

                    }
                }
        );
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
}
