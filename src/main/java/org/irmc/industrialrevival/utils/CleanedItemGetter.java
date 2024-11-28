package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.pigeonlib.items.ItemUtils;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;

/**
 * This class serves guide itemstack instead of {@link IndustrialRevivalItemStack}
 */
@UtilityClass
public class CleanedItemGetter {
    public static final NamespacedKey CLEANED_ITEM_ID_KEY = KeyUtil.customKey("cleaned_item_id");

    public static ItemStack getCleanedItem(ItemStack itemStack) {
        return ItemUtils.getCleanedItem(itemStack, meta -> {
            IndustrialRevivalItem item = IndustrialRevivalItem.getByItem(itemStack);
            if (item != null) {
                PersistentDataAPI.set(meta, CLEANED_ITEM_ID_KEY, PersistentDataType.STRING, item.getId());
            }
        });
    }
}
