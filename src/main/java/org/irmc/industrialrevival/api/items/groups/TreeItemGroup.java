package org.irmc.industrialrevival.api.items.groups;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * An item group class that can nest other tree item groups.<br>
 * You can use it to nest three times or more levels of other tree item groups.
 */
public final class TreeItemGroup extends ItemGroup {
    private final List<TreeItemGroup> itemGroups = new ArrayList<>();

    public TreeItemGroup(NamespacedKey key, ItemStack icon) {
        super(key, icon);
    }

    public TreeItemGroup(NamespacedKey key, ItemStack icon, int tier) {
        super(key, icon, tier);
    }

    @Override
    public void addItem(IndustrialRevivalItem item) {
        throw new UnsupportedOperationException("Cannot add items to a TreeItemGroup");
    }

    @Override
    public void register() {
        for (TreeItemGroup i : itemGroups) {
            if (!i.isRegistered()) {
                i.register();
            }
        }
    }

    public void addItemGroup(TreeItemGroup i) {
        itemGroups.add(i);

        tryResort();
    }

    private void tryResort() {
        List<TreeItemGroup> sorted = itemGroups.stream()
                .sorted(Comparator.comparingInt(ItemGroup::getTier))
                .toList();
        itemGroups.clear();
        itemGroups.addAll(sorted);
    }
}
