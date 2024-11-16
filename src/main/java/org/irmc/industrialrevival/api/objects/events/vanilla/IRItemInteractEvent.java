package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@Getter
public class IRItemInteractEvent extends PlayerInteractEvent {
    private final IndustrialRevivalItem iritem;
    private final PlayerInteractEvent originalEvent;
    public IRItemInteractEvent(PlayerInteractEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getPlayer(), originalEvent.getAction(), originalEvent.getItem(), originalEvent.getClickedBlock(), originalEvent.getBlockFace(), originalEvent.getHand());
        this.iritem = iritem;
        this.originalEvent = originalEvent;
    }
}
