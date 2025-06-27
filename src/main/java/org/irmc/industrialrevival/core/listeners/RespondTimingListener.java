package org.irmc.industrialrevival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.irmc.industrialrevival.api.objects.events.ir.TickDoneEvent;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

public class RespondTimingListener implements Listener {
    @EventHandler
    public void onTickDone(TickDoneEvent event) {
        IRDock.getPlugin().getProfilerService().respondToTimingView(IRDock.getPlugin().getProfilerService().pullTimingViewRequest());
    }
}
