package org.irmc.industrialrevival.api.objects.events;

import lombok.Getter;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@Getter
public class EndermanMoveIRBlockEvent extends EntityChangeBlockEvent {
    private final EntityChangeBlockEvent originalEvent;
    private final IndustrialRevivalItem iritem;

    public EndermanMoveIRBlockEvent(EntityChangeBlockEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getEntity(), originalEvent.getBlock(), originalEvent.getBlockData());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
    }
}
