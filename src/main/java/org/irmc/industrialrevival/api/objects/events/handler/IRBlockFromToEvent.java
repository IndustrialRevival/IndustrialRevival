package org.irmc.industrialrevival.api.objects.events.handler;

import lombok.Getter;
import org.bukkit.block.BlockFace;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class IRBlockFromToEvent extends BlockEvent {
    private final HandlerList handlers = new HandlerList();
    private final BlockFromToEvent originalEvent;
    private final IndustrialRevivalItem iritem;
    public IRBlockFromToEvent(BlockFromToEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getBlock());
        this.originalEvent = originalEvent;
        this.iritem = iritem;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
