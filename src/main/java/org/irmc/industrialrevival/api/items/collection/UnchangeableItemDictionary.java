package org.irmc.industrialrevival.api.items.collection;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UnchangeableItemDictionary extends SimpleItemDictionary {
    private final List<IndustrialRevivalItem> items;

    public UnchangeableItemDictionary(NamespacedKey key, final List<IndustrialRevivalItem> items) {
        super(key);
        this.items = new ArrayList<>(items);
        for (IndustrialRevivalItem item : items) {
            item.addItemDictionary(this);
        }
    }

    @Override
    public ItemStack tagItem(@NotNull IndustrialRevivalItem item, boolean addToList) {
        if (items.contains(item)) {
            return getTaggedItem(item);
        }

        throw new IllegalArgumentException("Cannot tag item that is not in the dictionary.");
    }

    @Override
    public List<IndustrialRevivalItem> getItems() {
        return items;
    }

    @Override
    public boolean isInDictionary(IndustrialRevivalItem item) {
        return items.contains(item);
    }
}
