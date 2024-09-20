package org.irmc.industrialrevival.api.items.collection;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class ItemDictionary implements Keyed {
    private final NamespacedKey key;

    public ItemDictionary(NamespacedKey key) {
        Dictionaries.registry.put(key, this);

        this.key = key;
    }

    public abstract void addItem(IndustrialRevivalItem item);

    public abstract List<IndustrialRevivalItem> getItems();

    public boolean isInDictionary(ItemStack item) {
        return this.isInDictionary(IndustrialRevivalItem.getByItem(item));
    }

    public abstract boolean isInDictionary(IndustrialRevivalItem item);

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return key;
    }
}
