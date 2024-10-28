package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.block.BlockBreakEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;

@Getter
public class IRItemBreakBlockEvent extends BlockBreakEvent implements RelatedIRItem {
    private final IndustrialRevivalItem iritem;
    private final BlockBreakEvent originalEvent;
    public IRItemBreakBlockEvent(BlockBreakEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getBlock(), originalEvent.getPlayer());
        this.iritem = iritem;
        this.originalEvent = originalEvent;
    }
}
