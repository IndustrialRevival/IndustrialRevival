package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class EntityPickupIRItemEvent extends EntityPickupItemEvent implements RelatedIRItem {
    private final IndustrialRevivalItem iritem;
    private final EntityPickupItemEvent originalEvent;
    public EntityPickupIRItemEvent(EntityPickupItemEvent event, IndustrialRevivalItem iritem) {
        super(event.getEntity(), event.getItem(), event.getRemaining());
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
