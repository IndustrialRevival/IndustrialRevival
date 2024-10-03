package org.irmc.industrialrevival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.LimitedItem;
import org.irmc.industrialrevival.api.objects.events.PlayerRightClickEvent;

public class LimitedItemListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLimitedUseItem(PlayerRightClickEvent e) {
        ItemStack is = e.getItem();
        if (is != null && !is.getType().isAir()) {
            IndustrialRevivalItem item = IndustrialRevivalItem.getByItem(is);
            if (item != null && item.isDisabledInWorld(e.getPlayer().getWorld())) {
                // TODO: remind player
                return;
            }
            if (item instanceof LimitedItem li) {
                li.doUse(e.getPlayer(), is);
            }
        }
    }
}
