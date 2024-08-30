package org.irmc.industrialrevival.core.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.pigeonlib.items.ItemUtils;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;

public class CleanedItemGetter {
    public static final NamespacedKey CLEANED_ITEM_ID_KEY = new NamespacedKey("industrialrevival", "cleaned_item_id");

    public static ItemStack getCleanedItem(ItemStack item) {
        return ItemUtils.getCleanedItem(item, (meta) -> {
            if (item instanceof IndustrialRevivalItemStack iris) {
                PersistentDataAPI.setString(meta, CLEANED_ITEM_ID_KEY, iris.getId());
            }
        });
    }
}
