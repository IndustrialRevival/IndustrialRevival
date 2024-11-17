package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockExplodeEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class BlockExplodeIRBlockEvent extends BlockExplodeEvent implements RelatedIRItem {

    private final BlockExplodeEvent originalEvent;
    private final Location iritemLocation;
    private final IndustrialRevivalItem iritem;

    public BlockExplodeIRBlockEvent(BlockExplodeEvent originalEvent, Location iritemLocation, IndustrialRevivalItem iritem) {
        super(originalEvent.getBlock(), originalEvent.getExplodedBlockState(), originalEvent.blockList(), originalEvent.getYield(), originalEvent.getExplosionResult());
        this.originalEvent = originalEvent;
        this.iritemLocation = iritemLocation;
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
