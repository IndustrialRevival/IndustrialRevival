package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class IRItemDamageEntityEvent extends EntityEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final EntityDamageByEntityEvent originalEvent;
    private final Player player;
    private final Entity hit;
    private final IndustrialRevivalItem iritem;

    public IRItemDamageEntityEvent(EntityDamageByEntityEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getEntity());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
        if (originalEvent.getDamager() instanceof Player) {
            this.player = (Player) originalEvent.getDamager();
        } else {
            throw new IllegalArgumentException("Damager is not a player");
        }
        this.hit = originalEvent.getEntity();
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
