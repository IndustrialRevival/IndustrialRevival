package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.recipes.BlockDropMethod;

/**
 * This interface defines a block drop items.<br>
 * <br>
 * <b>Note: </b> this is not {@link ItemDroppable}, this is to define a block to drop specific items
 */
public interface BlockDropItem {
    BlockDropMethod[] getDropMethods();
}
