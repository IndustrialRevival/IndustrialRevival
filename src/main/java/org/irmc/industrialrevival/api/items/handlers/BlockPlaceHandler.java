package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface BlockPlaceHandler extends ItemHandler {
    void onBlockPlace(@Nullable Player player, Block block, boolean placeByPlacers);
}
