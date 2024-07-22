package org.irmc.industrialrevival.core.guide.impl;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

public class CheatGuideImplementation extends SurvivalGuideImplementation {
    @Override
    public void onItemClicked(Player player, IndustrialRevivalItem item) {
        ItemStack itemStack = item.getItem().clone();
        player.getInventory().addItem(itemStack);
    }
}
