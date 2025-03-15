package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class EntityExplodeIRBlockEvent extends EntityEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final EntityExplodeEvent originalEvent;
    private final Location iritemLocation;
    private final IndustrialRevivalItem iritem;

    public EntityExplodeIRBlockEvent(EntityExplodeEvent originalEvent, Location iritemLocation, IndustrialRevivalItem iritem) {
        super(originalEvent.getEntity());
        this.originalEvent = originalEvent;
        this.iritemLocation = iritemLocation;
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
