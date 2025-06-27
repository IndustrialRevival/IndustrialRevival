package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.recipes.methods.BlockDropMethod;

import java.util.Set;

/**
 * This interface defines a block drop items.<br>
 * <br>
 * <b>Note: </b>this is not {@link ItemDroppable}, this is to define a block to drop specific items
 */
public interface BlockDropItem {
    Set<BlockDropMethod> getDropMethods();
}
