package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.ItemHandler;

@FunctionalInterface
public interface UseItemInteractHandler extends ItemHandler {
  void onInteract(PlayerInteractEvent e);
}
