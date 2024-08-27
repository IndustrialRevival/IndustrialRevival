package org.irmc.industrialrevival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.Limited;
import org.irmc.industrialrevival.api.objects.events.PlayerRightClickEvent;

public class InteractListener extends AbstractIRListener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            PlayerRightClickEvent event = new PlayerRightClickEvent(e);
            event.callEvent();
        }
    }

    @EventHandler
    public void onLimitedUseItem(PlayerRightClickEvent e) {
        ItemStack item = e.getItem();
        if (item != null) {
            if (IndustrialRevivalItem.getByItem(item) instanceof Limited l) {
                int left = l.getCountLeft();
                l.setCountLeft(left - 1);
            }
        }
    }
}
