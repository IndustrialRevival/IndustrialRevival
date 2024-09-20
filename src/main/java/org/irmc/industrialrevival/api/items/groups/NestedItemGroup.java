package org.irmc.industrialrevival.api.items.groups;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@Getter
public class NestedItemGroup extends ItemGroup {
    private final List<SubItemGroup> subItemGroups = new ArrayList<>();

    public NestedItemGroup(NamespacedKey key, ItemStack icon) {
        super(key, icon);
    }

    public NestedItemGroup(NamespacedKey key, ItemStack icon, int tier) {
        super(key, icon, tier);
    }

    public final void addItem(IndustrialRevivalItem item) {
        throw new UnsupportedOperationException("Nested item groups cannot have items added to them");
    }

    final void addSubItemGroup(SubItemGroup group) {
        if (locked) {
            throw new IllegalStateException("Cannot add sub item groups to a locked nested item group");
        }

        subItemGroups.add(group);

        tryResort();
    }

    private void tryResort() {
        List<SubItemGroup> sorted = subItemGroups.stream()
                .sorted(Comparator.comparingInt(ItemGroup::getTier))
                .toList();
        subItemGroups.clear();
        subItemGroups.addAll(sorted);
    }
}
