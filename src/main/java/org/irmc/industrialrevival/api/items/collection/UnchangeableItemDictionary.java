package org.irmc.industrialrevival.api.items.collection;

import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

import java.util.ArrayList;
import java.util.List;

public class UnchangeableItemDictionary extends SimpleItemDictionary {
    private final List<IndustrialRevivalItem> items;

    UnchangeableItemDictionary(NamespacedKey key, final List<IndustrialRevivalItem> items) {
        super(key);
        this.items = new ArrayList<>(items);
        for (IndustrialRevivalItem item : items) {
            item.addItemDictionary(this);
        }
    }

    @Override
    public void addItem(IndustrialRevivalItem item) {
        throw new UnsupportedOperationException("Cannot add items to an unchangeable item dictionary");
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
