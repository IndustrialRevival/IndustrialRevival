package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.items.ItemHandler;
import org.irmc.industrialrevival.api.objects.IRBlockData;

@FunctionalInterface
public interface BlockPlaceHandler extends ItemHandler {
    void onBlockPlace(Block block, IRBlockData blockData);
}
