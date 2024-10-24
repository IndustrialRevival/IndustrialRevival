package org.irmc.industrialrevival.api.objects.events.ir;

import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.jetbrains.annotations.NotNull;

@Getter
public class IRBlockTickEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Block block;
    private final MachineMenu menu;
    private final IRBlockData blockData;

    public IRBlockTickEvent(Block block, MachineMenu menu, IRBlockData blockData) {
        this.block = block;
        this.menu = menu;
        this.blockData = blockData;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
