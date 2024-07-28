package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface BlockBreakHandler extends ItemHandler {
    void onBlockBreak(@Nullable Player player, Block block, boolean breakByBreakers);
}
