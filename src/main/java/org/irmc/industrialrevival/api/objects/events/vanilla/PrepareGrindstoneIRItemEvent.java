package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class PrepareGrindstoneIRItemEvent extends PrepareGrindstoneEvent implements Cancellable, RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final IndustrialRevivalItem iritem;
    private final PrepareGrindstoneEvent originalEvent;
    private boolean cancelled;

    public PrepareGrindstoneIRItemEvent(PrepareGrindstoneEvent event, IndustrialRevivalItem iritem) {
        super(event.getView(), event.getResult());
        this.originalEvent = event;
        this.iritem = iritem;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
        setResult(null);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
