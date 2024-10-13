package org.irmc.industrialrevival.api.objects.events.handler;

import lombok.Getter;
import org.bukkit.event.block.BlockExplodeEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@Getter
public class BlockExplodeIRBlockEvent extends BlockExplodeEvent {

    private final BlockExplodeEvent originalEvent;
    private final IndustrialRevivalItem iritem;

    public BlockExplodeIRBlockEvent(BlockExplodeEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getBlock(), originalEvent.blockList(), originalEvent.getYield());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
    }
}
