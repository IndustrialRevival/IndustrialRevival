package org.irmc.industrialrevival.core.guide;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.objects.enums.GuideMode;

public interface IRGuideImplementation {
    void open(Player player);

    void onItemClicked(Player player, IndustrialRevivalItem item, ClickType clickType);

    void onGroupClicked(Player player, ItemGroup group);

    void onGroupClicked(Player player, ItemGroup group, int page);

    void goBack(Player player);

    GuideMode getGuideMode();
}
