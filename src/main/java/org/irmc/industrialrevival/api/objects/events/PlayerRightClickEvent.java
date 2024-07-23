package org.irmc.industrialrevival.api.objects.events;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerRightClickEvent extends PlayerInteractEvent {
    public PlayerRightClickEvent(PlayerInteractEvent originalEvent) {
        super(originalEvent.getPlayer(), originalEvent.getAction(), originalEvent.getItem(), originalEvent.getClickedBlock(), originalEvent.getBlockFace(), originalEvent.getHand());
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return new HandlerList();
    }

    public static HandlerList getHandlerList() {
        return new HandlerList();
    }
}
