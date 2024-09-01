package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

public abstract class AbstractIRListener implements Listener {
    public void register() {
        Bukkit.getPluginManager().registerEvents(this, IndustrialRevival.getInstance());
    }
}
