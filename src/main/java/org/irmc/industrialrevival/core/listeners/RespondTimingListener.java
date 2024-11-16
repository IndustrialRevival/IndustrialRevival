package org.irmc.industrialrevival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.irmc.industrialrevival.api.objects.events.ir.IRTickDoneEvent;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

public class RespondTimingListener implements Listener {
    @EventHandler
    public void onTickDone(IRTickDoneEvent event) {
        IndustrialRevival.getInstance().getProfilerService().respondToTimingView(IndustrialRevival.getInstance().getProfilerService().pullTimingViewRequest());
    }
}
