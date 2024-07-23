package org.irmc.industrialrevival.core.guide.impl;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.utils.Constants;

public class CheatGuideImplementation extends SurvivalGuideImplementation {
    public static final CheatGuideImplementation INSTANCE = new CheatGuideImplementation();

    CheatGuideImplementation() {}

    @Override
    public void open(Player p) {
        SimpleMenu sm = new SimpleMenu(
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Constants.GUIDE_CHEAT_KEY));
        setupGuideMenu(p, sm);
        sm.open(p);
    }

    @Override
    public void onItemClicked(Player player, IndustrialRevivalItem item) {
        ItemStack itemStack = item.getItem().clone();
        player.getInventory().addItem(itemStack);
    }
}
