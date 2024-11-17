package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class InventoryMoveIRItemEvent extends InventoryMoveItemEvent implements RelatedIRItem {
    private final IndustrialRevivalItem iritem;
    private final InventoryMoveItemEvent originalEvent;
    public InventoryMoveIRItemEvent(InventoryMoveItemEvent event, IndustrialRevivalItem iritem) {
        super(event.getSource(), event.getItem(), event.getDestination(), event.getInitiator().equals(event.getSource()));
        this.originalEvent = event;
        this.iritem = iritem;
    }

    private static final HandlerList handlers = new HandlerList();
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
