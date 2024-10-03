package org.irmc.industrialrevival.core.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.irmc.industrialrevival.core.listeners.DisabledItemListener;
import org.irmc.industrialrevival.core.listeners.DropListener;
import org.irmc.industrialrevival.core.listeners.GuideListener;
import org.irmc.industrialrevival.core.listeners.InteractListener;
import org.irmc.industrialrevival.core.listeners.ItemHandlerListener;
import org.irmc.industrialrevival.core.listeners.LimitedItemListener;
import org.irmc.industrialrevival.core.listeners.MachineMenuListener;
import org.irmc.industrialrevival.core.listeners.NotPlaceableListener;
import org.irmc.industrialrevival.core.listeners.PlayerJoinListener;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

public class ListenerManager {
    private final List<Listener> listeners = new ArrayList<>();

    public ListenerManager() {
        loadAll();
    }

    private void loadAll() {
        listeners.add(new DisabledItemListener());
        listeners.add(new DropListener());
        listeners.add(new GuideListener());
        listeners.add(new InteractListener());
        listeners.add(new ItemHandlerListener());
        listeners.add(new LimitedItemListener());
        listeners.add(new MachineMenuListener());
        listeners.add(new NotPlaceableListener());
        listeners.add(new PlayerJoinListener());
    }

    public void setupAll() {
        listeners.forEach(r -> Bukkit.getServer().getPluginManager().registerEvents(r, IndustrialRevival.getInstance()));
    }
}
