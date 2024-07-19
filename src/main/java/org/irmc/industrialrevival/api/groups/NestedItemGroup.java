package org.irmc.industrialrevival.api.groups;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class NestedItemGroup extends ItemGroup {
    public NestedItemGroup(NamespacedKey key, ItemStack icon) {
        super(key, icon);
    }

    public NestedItemGroup(NamespacedKey key, ItemStack icon, int tier) {
        super(key, icon, tier);
    }
}
