package org.irmc.industrialrevival.api.objects.events;

import lombok.Getter;
import org.bukkit.event.block.BlockBreakEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@Getter
public class IRBlockBreakEvent extends BlockBreakEvent {
    private final BlockBreakEvent originalEvent;
    private final IndustrialRevivalItem iritem;
    public IRBlockBreakEvent(BlockBreakEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getBlock(), originalEvent.getPlayer());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
    }
}
