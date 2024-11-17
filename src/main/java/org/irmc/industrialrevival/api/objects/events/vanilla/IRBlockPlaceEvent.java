package org.irmc.industrialrevival.api.objects.events.vanilla;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockPlaceEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class IRBlockPlaceEvent extends BlockPlaceEvent implements RelatedIRItem {
    private final BlockPlaceEvent originalEvent;
    private final IndustrialRevivalItem iritem;
    @Setter
    private boolean cancelled;
    public IRBlockPlaceEvent(BlockPlaceEvent event, IndustrialRevivalItem iritem) {
        super(
                event.getBlockPlaced(),
                event.getBlockReplacedState(),
                event.getBlockAgainst(),
                event.getItemInHand(),
                event.getPlayer(),
                event.canBuild(),
                event.getHand()
        );
        this.originalEvent = event;
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
