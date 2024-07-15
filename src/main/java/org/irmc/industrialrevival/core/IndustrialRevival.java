package org.irmc.industrialrevival.core;

import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.industrialrevival.core.utils.FileUtil;
import org.irmc.industrialrevival.core.utils.LanguageManager;

public final class IndustrialRevival extends JavaPlugin {
    private static IndustrialRevival instance;
    private LanguageManager languageManager;

    @Override
    public void onEnable() {
        instance = this;

        FileUtil.completeFile(this, "config.yml");

        languageManager = new LanguageManager(this);
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
}
