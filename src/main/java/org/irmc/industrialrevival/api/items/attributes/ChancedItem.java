package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.items.ItemAttribute;
import org.jetbrains.annotations.Range;

public interface ChancedItem extends ItemAttribute {
    /**
     * Gets the chance of the item being dropped/generated.
     *
     * @return the chance of the item being dropped/generated.
     */
    @Range(from = 0, to = 100)
    double getChance();
}
