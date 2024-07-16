package org.irmc.industrialrevival.api.groups;

import com.google.common.collect.Lists;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;

import java.util.LinkedList;
import java.util.List;

public class ItemGroup {
    private final NamespacedKey key;
    private final ItemStack icon;
    private int tier;

    private boolean locked = false;

    private final List<IndustrialRevivalItem> items = new LinkedList<>();

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

    public void onClick(Player player, int page, SimpleMenu menu, IRGuideImplementation currentGuide) {
        int startIndex = 9;

        List<List<IndustrialRevivalItem>> parts = Lists.partition(items, 36);
        List<IndustrialRevivalItem> thePart = parts.get(page);

        for (IndustrialRevivalItem item : thePart) {
            menu.setItem(startIndex, item.getItem(), ((slot, player1, item1, menu1, clickType) -> {
                currentGuide.onItemClicked(player1, item);
                return false;
            }));
        }

        menu.open(player);
    }

    public void addItem(IndustrialRevivalItem item) {
        if (locked) {
            throw new IllegalStateException("ItemGroup is locked");
        }

        this.items.add(item);
    }

    public List<IndustrialRevivalItem> getItems() {
        if (locked) {
            throw new IllegalStateException("ItemGroup is locked");
        }

        return items;
    }

    public void register() {
        this.locked = true;
    }

    public final boolean isRegistered() {
        return IndustrialRevival.getInstance().getRegistry().isGroupRegistered(key);
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
