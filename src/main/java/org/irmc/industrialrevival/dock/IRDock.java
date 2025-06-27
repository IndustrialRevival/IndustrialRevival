package org.irmc.industrialrevival.dock;

import com.google.common.base.Preconditions;

public class IRDock {
    private static final String PLUGIN_CLASS = "org.irmc.industrialrevival.implementation.IndustrialRevival";

    private static IIndustrialRevivalPlugin plugin;

    public static void setPlugin(IIndustrialRevivalPlugin plugin) {
        Preconditions.checkState(IRDock.plugin == null, "Plugin is already loaded");
        Preconditions.checkArgument(plugin != null, "Plugin cannot be null");
        Preconditions.checkArgument(plugin.getClass().getName().equals(PLUGIN_CLASS), "Not an valid plugin instance");
        IRDock.plugin = plugin;
    }

    public static IIndustrialRevivalPlugin getPlugin() {
        Preconditions.checkNotNull(plugin, "Plugin is not loaded");
        return plugin;
    }
}
