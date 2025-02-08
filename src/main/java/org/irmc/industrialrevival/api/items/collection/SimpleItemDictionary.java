package org.irmc.industrialrevival.api.items.collection;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleItemDictionary extends ItemDictionary {
    private final List<IndustrialRevivalItem> items;

    public SimpleItemDictionary(NamespacedKey key) {
        super(key);
        this.items = new ArrayList<>();
    }

    @Override
    public ItemStack tagItem(@NotNull IndustrialRevivalItem item, boolean addToList) {
        if (addToList && item.isEnabled()) {
            throw new UnsupportedOperationException("Cannot tag enabled item.");
        }

        ItemStack stack = getTaggedItem(item);

        if (addToList) {
            this.items.add(item);
        }

        return stack;
    }

    @Override
    public List<IndustrialRevivalItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public boolean isInDictionary(IndustrialRevivalItem item) {
        return items.contains(item);
    }
}
