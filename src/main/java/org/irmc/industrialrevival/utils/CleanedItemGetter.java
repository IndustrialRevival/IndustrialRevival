package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.pigeonlib.items.ItemUtils;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;

/**
 * Utility class for retrieving sanitized itemstacks without extraneous metadata.
 * <p>
 * Maintains core Persistent Data Container (PDC) information while stripping temporary attributes.
 *
 * @author balugaq
 */
@UtilityClass
public class CleanedItemGetter {
    /** PDC key identifying cleaned items ("cleaned_item_id") */
    public static final NamespacedKey CLEANED_ITEM_ID_KEY = KeyUtil.customKey("cleaned_item_id");

    /**
     * Produces sanitized version of an itemstack.
     *
     * @param itemStack Original item to process
     * @return Cleaned itemstack preserving essential PDC data
     */
    public static ItemStack getCleanedItem(ItemStack itemStack) {
        return ItemUtils.getCleanedItem(itemStack, meta -> {
            IndustrialRevivalItem item = IndustrialRevivalItem.getByItem(itemStack);
            if (item != null) {
                PersistentDataAPI.setNamespacedKey(meta, CLEANED_ITEM_ID_KEY, item.getId());
            }
        });
    }
}
