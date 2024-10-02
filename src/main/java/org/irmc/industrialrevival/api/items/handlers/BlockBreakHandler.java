package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface BlockBreakHandler extends ItemHandler {
    void onBlockBreak(@Nonnull BlockBreakEvent event);
}
