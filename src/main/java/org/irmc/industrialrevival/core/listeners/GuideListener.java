package org.irmc.industrialrevival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.events.PlayerRightClickEvent;
import org.irmc.industrialrevival.core.guide.impl.CheatGuideImplementation;
import org.irmc.industrialrevival.core.guide.impl.SurvivalGuideImplementation;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;

public class GuideListener extends AbstractIRListener {
    @EventHandler
    public void onRightClick(PlayerRightClickEvent e) {
        ItemStack item = e.getItem();
        if (item != null) {
            int type = PersistentDataAPI.getInt(item.getItemMeta(), Constants.GUIDE_ITEM_KEY, 1);
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
