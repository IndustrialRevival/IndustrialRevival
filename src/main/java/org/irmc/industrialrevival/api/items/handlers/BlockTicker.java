package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.items.ItemHandler;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.objects.IRBlockData;

@FunctionalInterface
public interface BlockTicker extends ItemHandler {
    void onTick(Block block, MachineMenuPreset menu, IRBlockData data);
}
