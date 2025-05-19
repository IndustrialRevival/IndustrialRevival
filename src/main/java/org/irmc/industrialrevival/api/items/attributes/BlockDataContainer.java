package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

public interface BlockDataContainer<Result> extends ItemAttribute {
    /**
     * Get the data stored in the item
     *
     * @param location The location to get the data from
     * @return The data stored in the item
     */
    @Nullable
    default Result getBlockData(Location location) {
        return null;
    }

    /**
     * Set the data stored in the item
     *
     * @param location The item to set the data in
     * @param data     The data to store in the item
     */
    default void setBlockData(Location location, Result data) {

    }
}
