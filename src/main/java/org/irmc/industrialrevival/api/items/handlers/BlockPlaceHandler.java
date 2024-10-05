package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.event.block.BlockPlaceEvent;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface BlockPlaceHandler extends ItemHandler {
    void onBlockPlace(@Nonnull BlockPlaceEvent event);
}
