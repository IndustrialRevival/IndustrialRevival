package org.irmc.industrialrevival.core;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
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

    @Override
    public void onEnable() {
        instance = this;

        FileUtil.completeFile(this, "config.yml");

        // objects
        languageManager = new LanguageManager(this);
        registry = new IRRegistry();

        // listeners
        new MachineMenuListener().register();
    }

    @Override
    public void onDisable() {}
}
