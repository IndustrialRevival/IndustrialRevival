package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockFromToEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class IRBlockFromToEvent extends BlockFromToEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final BlockFromToEvent originalEvent;
    private final IndustrialRevivalItem iritem;

    public IRBlockFromToEvent(BlockFromToEvent originalEvent, Block block, BlockFace face, IndustrialRevivalItem iritem) {
        super(block, face);
        this.originalEvent = originalEvent;
        this.iritem = iritem;
    }

    public IRBlockFromToEvent(BlockFromToEvent originalEvent, Block block, Block toBlock, IndustrialRevivalItem iritem) {
        super(block, toBlock);
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
