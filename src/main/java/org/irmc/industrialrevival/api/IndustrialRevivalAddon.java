package org.irmc.industrialrevival.api;

import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.pigeonlib.language.LanguageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

public interface IndustrialRevivalAddon {
    @NotNull JavaPlugin getPlugin();

    @Nullable String getIssueTrackerURL();

    default @NotNull String getVersion() {
        return getPlugin().getDescription().getVersion();
    }

    default @NotNull String getName() {
        return getPlugin().getDescription().getName();
    }

    default @NotNull Logger getLogger() {
        return getPlugin().getLogger();
    }
}
