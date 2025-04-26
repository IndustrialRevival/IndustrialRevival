package org.irmc.industrialrevival.api.objects.events.ir;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class IndustrialRevivalFinalizedEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    public IndustrialRevivalFinalizedEvent() {
        super(true);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
