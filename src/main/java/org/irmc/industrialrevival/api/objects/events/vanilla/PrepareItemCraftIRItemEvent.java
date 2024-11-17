package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class PrepareItemCraftIRItemEvent extends PrepareItemCraftEvent implements Cancellable, RelatedIRItem {
    private final IndustrialRevivalItem iritem;
    private final PrepareItemCraftEvent originalEvent;
    @Setter
    private boolean cancelled;
    public PrepareItemCraftIRItemEvent(PrepareItemCraftEvent event, IndustrialRevivalItem iritem) {
        super(event.getInventory(), event.getView(), event.isRepair());
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
