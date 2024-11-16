package org.irmc.industrialrevival.api.items.collection;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UnchangeableItemDictionary extends SimpleItemDictionary {
    private final List<IndustrialRevivalItem> items;
    private boolean locked = false;
    public UnchangeableItemDictionary(NamespacedKey key, final List<IndustrialRevivalItem> items) {
        super(key);
        this.items = new ArrayList<>(items);
        for (IndustrialRevivalItem item : items) {
            item.addItemDictionary(this);
        }
        locked = true;
    }

    @Override
    public ItemStack tagItem(@NotNull IndustrialRevivalItem item, boolean addToList) {
        if (addToList) {
            throw new UnsupportedOperationException("Cannot add items to an unchangeable item dictionary");
        }

        return getTaggedItem(item);
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
