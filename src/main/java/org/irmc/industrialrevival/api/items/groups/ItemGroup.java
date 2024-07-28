package org.irmc.industrialrevival.api.items.groups;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.core.IndustrialRevival;

public abstract class ItemGroup {
    @Getter
    private final NamespacedKey key;

    @Getter
    private final ItemStack icon;

    @Getter
    private int tier;

    boolean locked = false;

    private final List<IndustrialRevivalItem> items = new LinkedList<>();

    protected ItemGroup(NamespacedKey key, ItemStack icon) {
        this.key = key;
        this.icon = icon;
        this.tier = 3;
    }

    protected ItemGroup(NamespacedKey key, ItemStack icon, int tier) {
        this.key = key;
        this.icon = icon;
        this.tier = tier;
    }

    /*
    public void onClick(Player player, IRGuideImplementation currentGuide, int page) {
        int startIndex = 9;

        List<List<IndustrialRevivalItem>> parts = Lists.partition(items, 36);
        List<IndustrialRevivalItem> thePart = parts.get(page);

        SimpleMenu menu = new SimpleMenu(IndustrialRevival.getInstance()
                .getLanguageManager()
                .getMsgComponent(player, Constants.GUIDE_TITLE_KEY));

        for (IndustrialRevivalItem item : thePart) {
            menu.setItem(startIndex, item.getItem(), ((slot, player1, item1, menu1, clickType) -> {
                currentGuide.onItemClicked(player1, item);
                return false;
            }));
        }

        menu.open(player);
    }

     */

    public List<IndustrialRevivalItem> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(IndustrialRevivalItem item) {
        if (locked) {
            throw new IllegalStateException("the item group is locked");
        }

        this.items.add(item);
    }

    public void register() {
        this.locked = true;

        IndustrialRevival.getInstance().getRegistry().getItemGroups().put(key, this);
    }

    public final boolean isRegistered() {
        return IndustrialRevival.getInstance().getRegistry().getItemGroups().containsKey(key);
    }

    public void setTier(int tier) {
        this.tier = tier;

        resort();
    }

    private void resort() {
        IndustrialRevival.getInstance().getRegistry().resortItemGroups();
    }
}
