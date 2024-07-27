package org.irmc.industrialrevival.api.items.groups;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class SubItemGroup extends ItemGroup {
    public SubItemGroup(NestedItemGroup nig, NamespacedKey key, ItemStack icon) {
        super(key, icon);

        nig.addSubItemGroup(this);
    }

    public SubItemGroup(NestedItemGroup nig, NamespacedKey key, ItemStack icon, int tier) {
        super(key, icon, tier);

        nig.addSubItemGroup(this);
    }
}
