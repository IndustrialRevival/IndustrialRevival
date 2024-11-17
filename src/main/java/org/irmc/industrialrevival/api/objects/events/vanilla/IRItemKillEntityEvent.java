package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDeathEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class IRItemKillEntityEvent extends EntityDeathEvent implements RelatedIRItem {
    private final IndustrialRevivalItem iritem;
    private final EntityDeathEvent originalEvent;
    private final Player killer;
    public IRItemKillEntityEvent(EntityDeathEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getEntity(), originalEvent.getDamageSource(), originalEvent.getDrops(), originalEvent.getDroppedExp());
        this.iritem = iritem;
        this.originalEvent = originalEvent;
        this.killer = originalEvent.getEntity().getKiller();
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
