package org.irmc.industrialrevival.api.objects.events.ir;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.events.interfaces.RelatedIRItem;
import org.jetbrains.annotations.NotNull;

@Getter
public class IRBlockTickEvent extends Event implements Cancellable, RelatedIRItem {
    private static final HandlerList handlers = new HandlerList();
    private final Block block;
    private final MachineMenu menu;
    private final IndustrialRevivalItem iritem;
    private final IRBlockData blockData;
    @Setter
    private boolean cancelled;

    public IRBlockTickEvent(Block block, MachineMenu menu, IndustrialRevivalItem iritem, IRBlockData blockData) {
        super(true);
        this.block = block;
        this.menu = menu;
        this.iritem = iritem;
        this.blockData = blockData;
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
