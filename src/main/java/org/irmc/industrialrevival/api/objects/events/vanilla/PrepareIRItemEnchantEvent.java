package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class PrepareIRItemEnchantEvent extends PrepareItemEnchantEvent implements Cancellable, RelatedIRItem {
    private final IndustrialRevivalItem iritem;
    private final PrepareItemEnchantEvent originalEvent;
    @Setter
    private boolean cancelled;
    public PrepareIRItemEnchantEvent(PrepareItemEnchantEvent event, IndustrialRevivalItem iritem) {
        super(event.getEnchanter(), event.getView(), event.getEnchantBlock(), event.getItem(), event.getOffers(), event.getEnchantmentBonus());
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
