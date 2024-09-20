package org.irmc.industrialrevival.api.items.collection;

import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

import java.util.ArrayList;
import java.util.List;

public class SimpleItemDictionary extends ItemDictionary {
    private final List<IndustrialRevivalItem> items;

    public SimpleItemDictionary(NamespacedKey key) {
        super(key);
        this.items = new ArrayList<>();
    }

    @Override
    public void addItem(IndustrialRevivalItem item) {
        this.items.add(item);
        item.setItemDictionary(this);
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
