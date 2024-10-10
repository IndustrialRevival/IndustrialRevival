package org.irmc.industrialrevival.api.items.collection;

import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class ItemDictionary implements Keyed {
    private final NamespacedKey key;

    public ItemDictionary(NamespacedKey key) {
        IndustrialRevival.getInstance().getRegistry().registerDictionary(this);

        this.key = key;
    }

    public ItemStack tagItem(@NotNull IndustrialRevivalItem item) {
        return tagItem(item, true);
    }

    public abstract ItemStack tagItem(@NotNull IndustrialRevivalItem item, boolean tag);

    public abstract List<IndustrialRevivalItem> getItems();

    public boolean isInDictionary(@NotNull ItemStack item) {
        return this.isInDictionary(IndustrialRevivalItem.getByItem(item));
    }

    public abstract boolean isInDictionary(IndustrialRevivalItem item);

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return key;
    }
    public boolean isTagged(@NotNull ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
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

    protected ItemStack getTaggedItem(@NotNull IndustrialRevivalItem item) {
        if (item == null) {
            return null;
        }

        return getTaggedItem(item.getItem().clone());
    }
    protected ItemStack getTaggedItem(@NotNull IndustrialRevivalItemStack item) {
        if (item == null) {
            return null;
        }

        return getTaggedItem(item.clone());
    }
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
