package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class PistonExtendIRBlockEvent extends BlockPistonExtendEvent implements RelatedIRItem {
    private final BlockPistonExtendEvent originalEvent;
    private final IndustrialRevivalItem iritem;
    public PistonExtendIRBlockEvent(BlockPistonExtendEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getBlock(), originalEvent.getBlocks(), originalEvent.getDirection());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
    }

    private static final HandlerList handlers = new HandlerList();
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
