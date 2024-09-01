package org.irmc.industrialrevival.api.items.attributes;

import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * An interface for items that can be dropped by a block when it is destroyed.<br>
 * This is not {@link BlockDropItem}, this is for specific items that can be dropped by specific block when it is destroyed.
 */
public interface ItemDroppable extends ItemAttribute {
    /**
     * Returns a list of ItemStacks that can be dropped by the block when it is destroyed.
     *
     * @param p the {@link Player} who is mined the block
     * @return a list of ItemStacks that can be dropped by the block when it is destroyed
     */
    List<ItemStack> drops(Player p);

    /**
     * Whether to drop {@link BlockDropItem}s or not.
     *
     * @return true if the block should drop {@link BlockDropItem}s, false otherwise.
     */
    default boolean dropBlockDropItems() {
        return true;
    }
}
