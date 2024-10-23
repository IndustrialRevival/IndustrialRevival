package org.irmc.industrialrevival.api.objects.events.handler;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@Getter
public class PrepareIRItemEnchantEvent extends PrepareItemEnchantEvent implements Cancellable {
    private final IndustrialRevivalItem iritem;
    private final PrepareItemEnchantEvent originalEvent;
    @Setter
    private boolean cancelled;
    public PrepareIRItemEnchantEvent(PrepareItemEnchantEvent event, IndustrialRevivalItem iritem) {
        super(event.getEnchanter(), event.getView(), event.getEnchantBlock(), event.getItem(), event.getOffers(), event.getEnchantmentBonus());
        this.originalEvent = event;
        this.iritem = iritem;
    }
}
