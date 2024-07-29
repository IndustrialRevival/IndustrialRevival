package org.irmc.industrialrevival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.guide.impl.CheatGuideImplementation;
import org.irmc.industrialrevival.core.guide.impl.SurvivalGuideImplementation;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.industrialrevival.core.utils.PersistentDataAPI;

public class GuideListener extends AbstractIRListener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction().isRightClick()) {
            ItemStack item = e.getItem();
            if (item != null) {
                int type = PersistentDataAPI.getInt(item.getItemMeta(), Constants.GUIDE_ITEM_KEY, -1);
                if (type == 1) {
                    e.setCancelled(true);

                    SurvivalGuideImplementation.INSTANCE.open(e.getPlayer());
                } else if (type == 2) {
                    e.setCancelled(true);

                    CheatGuideImplementation.INSTANCE.open(e.getPlayer());
                }
            }
        }
    }
}
