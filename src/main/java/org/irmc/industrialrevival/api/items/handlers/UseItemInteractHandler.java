package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.event.player.PlayerInteractEvent;

@FunctionalInterface
public interface UseItemInteractHandler extends ItemHandler {
    void onInteract(PlayerInteractEvent e);
}
