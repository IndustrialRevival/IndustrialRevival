package org.irmc.industrialrevival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.events.PlayerRightClickEvent;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.guide.impl.CheatGuideImplementation;
import org.irmc.industrialrevival.core.guide.impl.SurvivalGuideImplementation;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.industrialrevival.core.utils.PersistentDataAPI;

public class GuideListener extends AbstractIRListener {
    @EventHandler
    public void onRightClick(PlayerRightClickEvent e) {
        ItemStack item = e.getItem();
        IndustrialRevival.getInstance().getLogger().info("item nullable: " + (item == null));
        if (item != null) {
            IndustrialRevival.getInstance()
                    .getLogger()
                    .info("item: " + item.getItemMeta().getDisplayName());
            int type = PersistentDataAPI.getInt(item.getItemMeta(), Constants.GUIDE_ITEM_KEY, 1);
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
