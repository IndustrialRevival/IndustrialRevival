package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.items.ItemHandler;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface BlockPlaceHandler extends ItemHandler {
    void onBlockPlace(@Nullable Player player, Block block, IRBlockData blockData, boolean placeByPlacers);
}
