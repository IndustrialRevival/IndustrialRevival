package org.irmc.industrialrevival.core.guide.impl;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;

public class CheatGuideImplementation implements IRGuideImplementation {
    @Override
    public void open(Player p) {

    }

    @Override
    public void onItemClicked(Player player, IndustrialRevivalItem item) {
        ItemStack itemStack = item.getItem().clone();
        player.getInventory().addItem(itemStack);
    }

    @Override
    public void onGroupClicked(Player player, ItemGroup group) {

    }

    @Override
    public void goBack(Player player) {

    }
}
