package org.irmc.industrialrevival.api.objects.events.handler;

import lombok.Getter;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@Getter
public class EndermanMoveIRBlockEvent extends EntityChangeBlockEvent {
    private final EntityChangeBlockEvent originalEvent;
    private final Entity enderman;
    private final IndustrialRevivalItem iritem;

    public EndermanMoveIRBlockEvent(EntityChangeBlockEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getEntity(), originalEvent.getBlock(), originalEvent.getBlockData());
        if (originalEvent.getEntity().getType() == EntityType.ENDERMAN) {
            this.originalEvent = originalEvent;
            this.enderman = originalEvent.getEntity();
            this.iritem = iritem;
        } else {
            throw new IllegalArgumentException("Entity is not an enderman");
        }
    }
}
