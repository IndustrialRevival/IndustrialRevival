package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.LimitedItem;
import org.irmc.industrialrevival.api.items.attributes.Limited;
import org.irmc.industrialrevival.api.objects.events.PlayerRightClickEvent;

public class LimitedItemListener extends AbstractIRListener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLimitedUseItem(PlayerRightClickEvent e) {
        ItemStack is = e.getItem();
        if (is != null || !is.getType().isAir()) {
            IndustrialRevivalItem item = IndustrialRevivalItem.getByItem(is);
            if (item instanceof LimitedItem li) {
                li.setCountLeft(li.getCountLeft() - 1);
                if (li.getCountLeft() <= 0) {
                    is.setType(Material.AIR);
                    is.setAmount(0);
                }
            }
        }
    }
}
