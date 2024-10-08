package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;

public class NotPlaceableListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack itemStack = event.getItemInHand();
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
            if (iritem == null) {
                return;
            }

            if (iritem instanceof NotPlaceable) {
                event.setCancelled(true);
            }
        }
    }
}
