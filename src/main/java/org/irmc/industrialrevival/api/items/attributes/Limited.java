package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.items.LimitedItem;

/**
 * This interface defines an item that has durability.<br>
 * <br>
 * <b>Note: </b> use {@link LimitedItem}.
 */
public interface Limited extends ItemAttribute {
    int getLimit();

    void setLimit(int limit);

    int getCountLeft();

    void setCountLeft(int countLeft);
}
