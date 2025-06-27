package org.irmc.industrialrevival.core.managers;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.irmc.industrialrevival.core.listeners.BulkDensityListener;
import org.irmc.industrialrevival.core.listeners.DefaultHandler;
import org.irmc.industrialrevival.core.listeners.DropListener;
import org.irmc.industrialrevival.core.listeners.EventCreator;
import org.irmc.industrialrevival.core.listeners.EventPrechecker;
import org.irmc.industrialrevival.core.listeners.GuideListener;
import org.irmc.industrialrevival.core.listeners.HandlerCaller;
import org.irmc.industrialrevival.core.listeners.LimitedItemListener;
import org.irmc.industrialrevival.core.listeners.MachineMenuListener;
import org.irmc.industrialrevival.core.listeners.MultiBlockListener;
import org.irmc.industrialrevival.core.listeners.MultiblockTicker;
import org.irmc.industrialrevival.core.listeners.NotPlaceableListener;
import org.irmc.industrialrevival.core.listeners.PlayerJoinListener;
import org.irmc.industrialrevival.core.listeners.RespondTimingListener;
import org.irmc.industrialrevival.core.listeners.TranslateUpdater;
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
        listeners.add(new BulkDensityListener());
        listeners.add(new DefaultHandler());
        listeners.add(new DropListener());
        listeners.add(new EventCreator());
        listeners.add(new EventPrechecker());
        listeners.add(new GuideListener());
        listeners.add(new HandlerCaller());
        listeners.add(new LimitedItemListener());
        listeners.add(new MachineMenuListener());
        listeners.add(new MultiBlockListener());
        listeners.add(new NotPlaceableListener());
        listeners.add(new PlayerJoinListener());
        listeners.add(new RespondTimingListener());
        listeners.add(new UnusableItemListener());
        listeners.add(new MultiblockTicker());
        //listeners.add(new TranslateUpdater());
    }

    public void setupAll() {
        listeners.forEach(r -> Bukkit.getServer().getPluginManager().registerEvents(r, IRDock.getPlugin()));
    }

    public int getListenerCount() {
        return listeners.size();
    }
}
