package org.irmc.industrialrevival.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.irmc.industrialrevival.core.translation.ItemTranslator;

/**
 * @author baluagq
 * @see ItemTranslator
 */
public class TranslateUpdater implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryOpen(InventoryOpenEvent event) {
        // Send SET_SLOT and WINDOW_ITEMS packets
        if (event.getPlayer() instanceof Player player) {
            player.updateInventory();
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onClickItem(InventoryClickEvent event) {
        // Send SET_SLOT packet
        if (event.getWhoClicked() instanceof Player player) {
            player.updateInventory();
        }
    }
}
