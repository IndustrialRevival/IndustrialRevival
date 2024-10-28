package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;

@Getter
public class PistonRetractIRBlockEvent extends BlockPistonRetractEvent implements RelatedIRItem {
    private final BlockPistonRetractEvent originalEvent;
    private final IndustrialRevivalItem iritem;
    public PistonRetractIRBlockEvent(BlockPistonRetractEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getBlock(), originalEvent.getBlocks(), originalEvent.getDirection());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
    }
}
