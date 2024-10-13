package org.irmc.industrialrevival.api.objects.events.handler;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerRightClickEvent extends PlayerInteractEvent {
    private final HandlerList handlers = new HandlerList();
    private final PlayerInteractEvent originalEvent;

    public PlayerRightClickEvent(PlayerInteractEvent originalEvent) {
        super(
                originalEvent.getPlayer(),
                originalEvent.getAction(),
                originalEvent.getItem(),
                originalEvent.getClickedBlock(),
                originalEvent.getBlockFace(),
                originalEvent.getHand());
        this.originalEvent = originalEvent;
    }

    public static HandlerList getHandlerList() {
        return new HandlerList();
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
