package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.inventory.MerchantRecipe;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class PrepareTradeSelectIRItemEvent extends TradeSelectEvent implements Cancellable, RelatedIRItem {
    private final IndustrialRevivalItem iritem;
    private final MerchantRecipe recipe;
    private final TradeSelectEvent originalEvent;
    @Setter
    private boolean cancelled;
    public PrepareTradeSelectIRItemEvent(TradeSelectEvent event, MerchantRecipe recipe, IndustrialRevivalItem iritem) {
        super(event.getView(), event.getIndex());
        this.originalEvent = event;
        this.recipe = recipe;
        this.iritem = iritem;
    }
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
        setResult(Result.DENY);
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
