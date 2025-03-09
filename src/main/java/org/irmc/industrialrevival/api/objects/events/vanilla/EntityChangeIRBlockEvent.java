package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class EntityChangeIRBlockEvent extends EntityEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final EntityChangeBlockEvent originalEvent;
    private final IndustrialRevivalItem iritem;

    public EntityChangeIRBlockEvent(EntityChangeBlockEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getEntity());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
