package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.LimitedItem;
import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerRightClickEvent;

public class LimitedItemListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLimitedUseItem(PlayerRightClickEvent e) {
        ItemStack is = e.getOriginalEvent().getItem();
        if (is != null && is.getType() != Material.AIR) {
            IndustrialRevivalItem item = IndustrialRevivalItem.getByItem(is);
            if (item != null && item.isDisabledInWorld(e.getOriginalEvent().getPlayer().getWorld())) {
                return;
            }
            if (item instanceof LimitedItem li) {
                li.doUse(e.getOriginalEvent().getPlayer(), is);
            }
        }
    }
}
