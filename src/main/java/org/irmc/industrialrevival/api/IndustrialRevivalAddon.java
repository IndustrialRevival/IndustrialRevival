package org.irmc.industrialrevival.api;

import org.bukkit.plugin.java.JavaPlugin;
import org.irmc.pigeonlib.enums.Language;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

public interface IndustrialRevivalAddon {
    default String translateKey() {
        return getPlugin().getName().toLowerCase();
    }

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

    default @NotNull Language getDefaultLanguage() {
        return Language.EN_US;
    }
}
