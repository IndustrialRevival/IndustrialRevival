package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;

@Getter
public class PlayerInteractIRBlockEvent extends PlayerInteractEvent implements RelatedIRItem {
    private final PlayerInteractEvent originalEvent;
    private final IndustrialRevivalItem iritem;
    public PlayerInteractIRBlockEvent(PlayerInteractEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getPlayer(), originalEvent.getAction(), originalEvent.getItem(), originalEvent.getClickedBlock(), originalEvent.getBlockFace(), originalEvent.getHand());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
    }
}
