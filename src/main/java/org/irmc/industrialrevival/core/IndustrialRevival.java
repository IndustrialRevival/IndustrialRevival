package org.irmc.industrialrevival.core;

import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.industrialrevival.core.listeners.MachineMenuListener;
import org.irmc.industrialrevival.core.registry.IRRegistry;
import org.irmc.industrialrevival.core.utils.FileUtil;
import org.irmc.industrialrevival.core.utils.LanguageManager;

public final class IndustrialRevival extends JavaPlugin {
    private static IndustrialRevival instance;
    private IRRegistry registry;
    private LanguageManager languageManager;

    @Override
    public void onEnable() {
        instance = this;

        FileUtil.completeFile(this, "config.yml");

        //objects
        languageManager = new LanguageManager(this);
        registry = new IRRegistry();

        //listeners
        new MachineMenuListener().register();
    }

    @Override
    public void onDisable() {
    }

    public static IndustrialRevival getInstance() {
        return instance;
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }

    public IRRegistry getRegistry() {
        return registry;
    }
}
