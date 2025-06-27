package org.irmc.industrialrevival.api.items.collection;

import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A good way to make machines regard different items
 * as the same is to use an ItemDictionary.
 *
 * @author lijinhong11
 * @author balugaq
 */
public abstract class ItemDictionary implements Keyed {
    private final NamespacedKey key;

    /**
     * Create a new ItemDictionary with the given key.
     *
     * @param key the key of the dictionary
     */
    public ItemDictionary(@NotNull NamespacedKey key) {
        IRDock.getPlugin().getRegistry().registerDictionary(this);

        this.key = key;
    }

    public ItemStack tagItem(@NotNull IndustrialRevivalItem item) {
        return tagItem(item, true);
    }

    /**
     * Tag an item with the dictionary key and add it to the dictionary if it's not already there.
     *
     * @param item      the item to tag
     * @param addToList whether to add the item to the dictionary or not
     * @return the tagged item
     */
    public abstract ItemStack tagItem(@NotNull IndustrialRevivalItem item, boolean addToList);

    /**
     * Get all the items in the dictionary.
     *
     * @return a list of all the items in the dictionary
     */
    public abstract List<IndustrialRevivalItem> getItems();

    /**
     * Check if an item is in the dictionary.
     *
     * @param item the item to check
     * @return true if the item is in the dictionary, false otherwise
     */
    public boolean isInDictionary(@NotNull ItemStack item) {
        return this.isInDictionary(IndustrialRevivalItem.getByItem(item));
    }

    /**
     * Check if an item is in the dictionary.
     *
     * @param item the item to check
     * @return true if the item is in the dictionary, false otherwise
     */
    public abstract boolean isInDictionary(IndustrialRevivalItem item);

    /**
     * Get the key of the dictionary.
     *
     * @return the key of the dictionary
     */
    @Override
    @NotNull
    public final NamespacedKey getKey() {
        return key;
    }

    /**
     * Check if an item is tagged with the dictionary key.
     *
     * @param itemStack the item to check
     * @return true if the item is tagged with the dictionary key, false otherwise
     */
    public boolean isTagged(@NotNull ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR) {
            return false;
        }

        if (!itemStack.hasItemMeta()) {
            return false;
        }

        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) {
            return false;
        }

        return Boolean.TRUE.equals(meta.getPersistentDataContainer().get(getKey(), PersistentDataType.BOOLEAN));
    }

    /**
     * Get the tagged item with the dictionary key.
     *
     * @param item the item to get the tagged item for
     * @return the tagged item with the dictionary key, or null if the item is not tagged with the key
     */
    protected ItemStack getTaggedItem(@NotNull IndustrialRevivalItem item) {
        return getTaggedItem(item.getIcon().clone());
    }

    /**
     * Get the tagged item with the dictionary key.
     *
     * @param itemStack the item to get the tagged item for
     * @return the tagged item with the dictionary key, or null if the item is not tagged with the key
     */
    protected ItemStack getTaggedItem(@NotNull ItemStack itemStack) {
        ItemStack stack = itemStack.clone();
        if (stack == null || stack.getType() == Material.AIR) {
            throw new UnsupportedOperationException("Cannot tag null item.");
        }

        ItemMeta meta = stack.getItemMeta();
        if (meta == null) {
            throw new UnsupportedOperationException("Cannot tag item without meta.");
        }

        meta.getPersistentDataContainer().set(getKey(), PersistentDataType.BOOLEAN, true);
        stack.setItemMeta(meta);
        return stack;
    }
}
