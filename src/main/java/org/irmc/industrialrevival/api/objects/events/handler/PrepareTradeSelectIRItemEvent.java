package org.irmc.industrialrevival.api.objects.events.handler;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.inventory.MerchantRecipe;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@Getter
public class PrepareTradeSelectIRItemEvent extends TradeSelectEvent implements Cancellable {
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
}
