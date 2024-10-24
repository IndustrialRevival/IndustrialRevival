package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@Getter
public class PrepareItemCraftIRItemEvent extends PrepareItemCraftEvent implements Cancellable {
    private final IndustrialRevivalItem iritem;
    private final PrepareItemCraftEvent originalEvent;
    @Setter
    private boolean cancelled;
    public PrepareItemCraftIRItemEvent(PrepareItemCraftEvent event, IndustrialRevivalItem iritem) {
        super(event.getInventory(), event.getView(), event.isRepair());
        this.originalEvent = event;
        this.iritem = iritem;
    }
}
