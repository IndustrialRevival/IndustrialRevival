package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerRightClickEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final PlayerInteractEvent originalEvent;

    public PlayerRightClickEvent(PlayerInteractEvent originalEvent) {
        super(originalEvent.getPlayer());
        this.originalEvent = originalEvent;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
