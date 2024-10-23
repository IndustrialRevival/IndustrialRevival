package org.irmc.industrialrevival.api.objects.events.handler;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@Getter
public class PrepareSmithingIRItemEvent extends PrepareSmithingEvent implements Cancellable {
    private final IndustrialRevivalItem iritem;
    private final PrepareSmithingEvent originalEvent;
    private boolean cancelled;
    public PrepareSmithingIRItemEvent(PrepareSmithingEvent event, IndustrialRevivalItem iritem) {
        super(event.getView(), event.getResult());
        this.originalEvent = event;
        this.iritem = iritem;
    }
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
        setResult(null);
    }
}
