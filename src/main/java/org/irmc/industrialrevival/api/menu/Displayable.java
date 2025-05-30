package org.irmc.industrialrevival.api.menu;

import org.bukkit.inventory.ItemStack;

/**
 * @author balugaq
 * @param <T> The type of item to display.
 */
public interface Displayable<T> {
    ItemStack getDisplayItem(T item);
}
