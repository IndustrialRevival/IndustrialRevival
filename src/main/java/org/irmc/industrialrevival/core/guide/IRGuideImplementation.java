package org.irmc.industrialrevival.core.guide;

import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

public interface IRGuideImplementation {
    void open(Player p);

    void onItemClicked(Player player, IndustrialRevivalItem item);

    void onGroupClicked(Player player, ItemGroup group);

    void goBack(Player player);
}
