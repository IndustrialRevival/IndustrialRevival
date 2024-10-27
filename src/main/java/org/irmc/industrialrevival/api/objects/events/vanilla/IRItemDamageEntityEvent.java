package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@Getter
public class IRItemDamageEntityEvent extends EntityDamageByEntityEvent {
    private final EntityDamageByEntityEvent originalEvent;
    private final Player player;
    private final Entity hit;
    private final IndustrialRevivalItem iritem;
    public IRItemDamageEntityEvent(EntityDamageByEntityEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getDamager(), originalEvent.getEntity(), originalEvent.getCause(), originalEvent.getDamageSource(), originalEvent.getDamage());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
        if (originalEvent.getDamager() instanceof Player) {
            this.player = (Player) originalEvent.getDamager();
        } else {
            throw new IllegalArgumentException("Damager is not a player");
        }
        this.hit = originalEvent.getEntity();
    }
}
