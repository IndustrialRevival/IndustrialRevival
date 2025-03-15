package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerLeftClickEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();

    public PlayerLeftClickEvent(PlayerInteractEvent originalEvent) {
        super(originalEvent.getPlayer());
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
