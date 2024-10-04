package org.irmc.industrialrevival.api.items.collection;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

class UnchangeableItemDictionary extends SimpleItemDictionary {
    private final List<IndustrialRevivalItem> items;

    UnchangeableItemDictionary(NamespacedKey key, final List<IndustrialRevivalItem> items) {
        super(key);
        this.items = new ArrayList<>(items);
    }

    @Override
    public void addItem(DictionaryItem item) {
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
