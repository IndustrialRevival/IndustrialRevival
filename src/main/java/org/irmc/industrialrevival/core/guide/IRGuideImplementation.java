package org.irmc.industrialrevival.core.guide;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.SimpleMenu;

public interface IRGuideImplementation {
    void open(Player p);

    void onItemClicked(Player player, IndustrialRevivalItem item);

    void onGroupClicked(Player player, ItemGroup group);

    void goBack(Player player1);

    void menuSetup(SimpleMenu menu);
}
