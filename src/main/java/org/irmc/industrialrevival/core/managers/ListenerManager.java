package org.irmc.industrialrevival.core.managers;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.irmc.industrialrevival.core.listeners.DisabledItemListener;
import org.irmc.industrialrevival.core.listeners.DropListener;
import org.irmc.industrialrevival.core.listeners.EventPrechecker;
import org.irmc.industrialrevival.core.listeners.GuideListener;
import org.irmc.industrialrevival.core.listeners.EventCreator;
import org.irmc.industrialrevival.core.listeners.HandlerCaller;
import org.irmc.industrialrevival.core.listeners.ItemHandlerListener;
import org.irmc.industrialrevival.core.listeners.LimitedItemListener;
import org.irmc.industrialrevival.core.listeners.MachineMenuListener;
import org.irmc.industrialrevival.core.listeners.NotPlaceableListener;
import org.irmc.industrialrevival.core.listeners.PlayerJoinListener;
import org.irmc.industrialrevival.core.listeners.UnusableItemListener;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {
    private final List<Listener> listeners = new ArrayList<>();

    public ListenerManager() {
        loadAll();
    }

    private void loadAll() {
        listeners.add(new EventCreator());
        listeners.add(new EventPrechecker());
        listeners.add(new HandlerCaller());
        listeners.add(new DisabledItemListener());
        listeners.add(new DropListener());
        listeners.add(new GuideListener());
        listeners.add(new ItemHandlerListener());
        listeners.add(new LimitedItemListener());
        listeners.add(new MachineMenuListener());
        listeners.add(new NotPlaceableListener());
        listeners.add(new PlayerJoinListener());
        listeners.add(new UnusableItemListener());
    }

    public void setupAll() {
        listeners.forEach(r -> Bukkit.getServer().getPluginManager().registerEvents(r, IndustrialRevival.getInstance()));
    }

    public int getListenerCount() {
        return listeners.size();
    }
}
