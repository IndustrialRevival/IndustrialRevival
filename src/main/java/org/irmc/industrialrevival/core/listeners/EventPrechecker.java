package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockPlaceEvent;
import org.irmc.industrialrevival.utils.DataUtil;

public class EventPrechecker implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void onIRBlockPlace(IRBlockPlaceEvent event) {
        Location location = event.getBlockPlaced().getLocation();
        IRBlockData blockData = DataUtil.getBlockData(location);
        if (blockData != null) {
            event.setCancelled(true);
        }
    }
}
