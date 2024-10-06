package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface UseItemInteractHandler extends ItemHandler {
    void onInteract(@NotNull PlayerInteractEvent e);
}
