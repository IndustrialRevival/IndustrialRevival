package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.irmc.industrialrevival.api.items.attributes.Tickable;
import org.irmc.industrialrevival.api.objects.events.ir.IRTickDoneEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultiblockTicker implements Listener {
    private static final Map<Location, Tickable> tickables = new ConcurrentHashMap<>();
    @EventHandler
    public void onMultiblockTick(IRTickDoneEvent event) {
        for (Location location : tickables.keySet()) {
            tickables.get(location).tick(location);
        }
    }

    public static void addTickable(Location location, Tickable tickable) {
        synchronized (tickables) {
            tickables.put(location, tickable);
        }
    }

    public static void removeTickable(Location location) {
        synchronized (tickables) {
            tickables.remove(location);
        }
    }
}
