package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class PistonRetractIRBlockEvent extends BlockEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final BlockPistonRetractEvent originalEvent;
    private final IndustrialRevivalItem iritem;

    public PistonRetractIRBlockEvent(BlockPistonRetractEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getBlock());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
