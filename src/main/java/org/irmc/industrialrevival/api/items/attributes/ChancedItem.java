package org.irmc.industrialrevival.api.items.attributes;

import org.jetbrains.annotations.Range;

/**
 * This interface defines an item that has a certain chance to obtain. <br>
 * @see MobDropItem
 */
public interface ChancedItem extends ItemAttribute {
    /**
     * Gets the chance of the item being dropped/generated.
     *
     * @return the chance of the item being dropped/generated.
     */
    @Range(from = 0, to = 100)
    double getChance();
}
