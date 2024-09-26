package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Limited extends ItemAttribute {
    int getLimit();

    int getCountLeft(ItemStack item);

    void setCountLeft(ItemStack item, int countLeft);

    void doUse(Player player, ItemStack item);
}
