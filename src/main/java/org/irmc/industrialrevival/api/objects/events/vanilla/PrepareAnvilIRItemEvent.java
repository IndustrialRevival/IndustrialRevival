package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class PrepareAnvilIRItemEvent extends PrepareAnvilEvent implements Cancellable, RelatedIRItem {
    private final IndustrialRevivalItem iritem;
    private final PrepareAnvilEvent originalEvent;
    private boolean cancelled;
    public PrepareAnvilIRItemEvent(PrepareAnvilEvent event, IndustrialRevivalItem iritem) {
        super(event.getView(), event.getResult());
        this.originalEvent = event;
        this.iritem = iritem;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
        setResult(null);
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
