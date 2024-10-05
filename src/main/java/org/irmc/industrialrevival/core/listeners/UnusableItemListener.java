package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.Unusable;

public class UnusableItemListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack itemStack = event.getItemInHand();
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            IndustrialRevivalItem item = IndustrialRevivalItem.getByItem(itemStack);
            if (item instanceof Unusable) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        ItemStack itemStack = event.getPlayer().getItemInHand();
        if (itemStack.getType() != Material.AIR) {
            IndustrialRevivalItem item = IndustrialRevivalItem.getByItem(itemStack);
            if (item instanceof Unusable) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ItemStack itemStack = event.getItem();
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            IndustrialRevivalItem item = IndustrialRevivalItem.getByItem(itemStack);
            if (item instanceof Unusable) {
                event.setCancelled(true);
            }
        }
    }
}
