package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.industrialrevival.implementation.guide.CheatGuideImplementation;
import org.irmc.industrialrevival.implementation.guide.SurvivalGuideImplementation;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;

public class GuideListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            if (item.getType() != Material.AIR) {
                int type = PersistentDataAPI.getInt(item.getItemMeta(), Constants.GUIDE_ITEM_KEY, -1);
                if (type == 1) {
                    SurvivalGuideImplementation.INSTANCE.open(e.getPlayer());
                    e.setCancelled(true);
                } else if (type == 2) {
                    CheatGuideImplementation.INSTANCE.open(e.getPlayer());
                    e.setCancelled(true);
                }
            }
        }
    }
}
