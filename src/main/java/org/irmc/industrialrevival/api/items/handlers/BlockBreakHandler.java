package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.event.block.BlockBreakEvent;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface BlockBreakHandler extends ItemHandler {
    void onBlockBreak(@Nonnull BlockBreakEvent event);
}
