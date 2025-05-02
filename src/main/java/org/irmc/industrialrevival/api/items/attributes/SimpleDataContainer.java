package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface SimpleDataContainer<Result> extends ItemAttribute {
    /**
     * Get the data stored in the item
     * @param itemStack  The item to get the data from
     * @return The data stored in the item
     */
    @Nullable
    default Result getData(ItemStack itemStack) {
        return null;
    }

    /**
     * Set the data stored in the item
     * @param itemStack The item to set the data in
     * @param data      The data to store in the item
     */
    default void setData(ItemStack itemStack, Result data) {

    }
}
