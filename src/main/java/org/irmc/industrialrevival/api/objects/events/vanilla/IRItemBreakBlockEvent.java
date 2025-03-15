package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class IRItemBreakBlockEvent extends BlockEvent implements RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final IndustrialRevivalItem iritem;
    private final BlockBreakEvent originalEvent;

    public IRItemBreakBlockEvent(BlockBreakEvent originalEvent, IndustrialRevivalItem iritem) {
        super(originalEvent.getBlock());
        this.iritem = iritem;
        this.originalEvent = originalEvent;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
