package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

public class DisabledItemListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent event) {
        ItemStack itemStack = event.getItem();
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
            if (iritem == null) {
                return;
            }

            if (iritem.isDisabledInWorld(event.getPlayer().getWorld())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack itemStack = event.getItemInHand();
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
            if (iritem == null) {
                return;
            }

            if (iritem.isDisabledInWorld(event.getPlayer().getWorld())) {
                event.setCancelled(true);
            }
        }
    }
}
