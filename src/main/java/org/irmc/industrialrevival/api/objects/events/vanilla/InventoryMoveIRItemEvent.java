package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@Getter
public class InventoryMoveIRItemEvent extends InventoryMoveItemEvent {
    private final IndustrialRevivalItem iritem;
    private final InventoryMoveItemEvent originalEvent;
    public InventoryMoveIRItemEvent(InventoryMoveItemEvent event, IndustrialRevivalItem iritem) {
        super(event.getSource(), event.getItem(), event.getDestination(), event.getInitiator().equals(event.getSource()));
        this.originalEvent = event;
        this.iritem = iritem;
    }
}