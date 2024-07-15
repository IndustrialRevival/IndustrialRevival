package org.irmc.industrialrevival.api.groups;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class ItemGroup {
    private final NamespacedKey key;
    private final ItemStack icon;
    private int tier;

    public ItemGroup(NamespacedKey key, ItemStack icon) {
        this.key = key;
        this.icon = icon;
        this.tier = 3;
    }

    public ItemGroup(NamespacedKey key, ItemStack icon, int tier) {
        this.key = key;
        this.icon = icon;
        this.tier = tier;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public NamespacedKey getKey() {
        return key;
    }

    public ItemStack getIcon() {
        return icon;
    }
}
