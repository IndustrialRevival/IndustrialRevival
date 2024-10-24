package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@Getter
public class PistonExtendIRBlockEvent extends BlockPistonExtendEvent {
    private final BlockPistonExtendEvent originalEvent;
    private final IndustrialRevivalItem iritem;
    public PistonExtendIRBlockEvent(BlockPistonExtendEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getBlock(), originalEvent.getBlocks(), originalEvent.getDirection());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
    }
}
