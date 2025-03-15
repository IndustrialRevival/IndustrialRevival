package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class IRItemKillEntityEvent extends EntityEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final IndustrialRevivalItem iritem;
    private final EntityDeathEvent originalEvent;
    private final Player killer;

    public IRItemKillEntityEvent(EntityDeathEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getEntity());
        this.iritem = iritem;
        this.originalEvent = originalEvent;
        this.killer = originalEvent.getEntity().getKiller();
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
