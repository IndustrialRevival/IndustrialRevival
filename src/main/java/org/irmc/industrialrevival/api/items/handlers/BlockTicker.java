package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.IRBlockData;

@FunctionalInterface
public interface BlockTicker extends ItemHandler {
    void onTick(Block block, MachineMenu menu, IRBlockData data);
}
