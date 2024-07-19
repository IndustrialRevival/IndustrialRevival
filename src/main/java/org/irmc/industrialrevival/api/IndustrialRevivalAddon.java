package org.irmc.industrialrevival.api;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IndustrialRevivalAddon {
    @NotNull JavaPlugin getPlugin();

    @Nullable String getIssueTrackerURL();
}
