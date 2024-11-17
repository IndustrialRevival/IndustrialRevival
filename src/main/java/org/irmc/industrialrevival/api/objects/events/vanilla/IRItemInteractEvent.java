package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class IRItemInteractEvent extends PlayerInteractEvent {
    private static final HandlerList handlers = new HandlerList();
    private final IndustrialRevivalItem iritem;
    private final PlayerInteractEvent originalEvent;

    public IRItemInteractEvent(PlayerInteractEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getPlayer(), originalEvent.getAction(), originalEvent.getItem(), originalEvent.getClickedBlock(), originalEvent.getBlockFace(), originalEvent.getHand());
        this.iritem = iritem;
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
