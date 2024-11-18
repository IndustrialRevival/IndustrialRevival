package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.enums.GuideMode;
import org.irmc.industrialrevival.implementation.guide.CheatGuideImplementation;
import org.irmc.industrialrevival.implementation.guide.SurvivalGuideImplementation;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;

public class GuideListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            if (item != null && item.getType() != Material.AIR) {
                String smode = PersistentDataAPI.getString(item.getItemMeta(), Constants.ItemStackKeys.GUIDE_ITEM_KEY, GuideMode.UNKNOWN.name());
                GuideMode mode = GuideMode.valueOf(smode);
                switch (mode) {
                    case SURVIVAL -> {
                        SurvivalGuideImplementation.INSTANCE.open(e.getPlayer());
                        e.setCancelled(true);
                    }
                    case CHEAT -> {
                        CheatGuideImplementation.INSTANCE.open(e.getPlayer());
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
