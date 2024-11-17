package org.irmc.industrialrevival.api.objects.events.ir;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;


@Getter
public class IRTickDoneEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public IRTickDoneEvent() {
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
