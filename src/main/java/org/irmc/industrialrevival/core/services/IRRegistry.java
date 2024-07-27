package org.irmc.industrialrevival.core.services;

import java.util.*;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.MobDropItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.objects.Pair;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.api.researches.Research;

@Getter
public final class IRRegistry {
    private final Map<NamespacedKey, ItemGroup> itemGroups;
    private final Map<NamespacedKey, Research> researches;
    private final Map<String, IndustrialRevivalItem> items;
    private final Map<String, PlayerProfile> playerProfiles;
    private final Map<String, MachineMenuPreset> menuPresets;
    private final Map<EntityType, List<Pair<ItemStack, Double>>> mobDrops;

    public IRRegistry() {
        itemGroups = new HashMap<>();
        researches = new HashMap<>();
        items = new HashMap<>();
        playerProfiles = new HashMap<>();
        menuPresets = new HashMap<>();
        mobDrops = new HashMap<>();
    }

    public void resortItemGroups() {
        Map<NamespacedKey, ItemGroup> newItemGroups = new HashMap<>();
        Set<Map.Entry<NamespacedKey, ItemGroup>> entries = itemGroups.entrySet();
        entries.stream()
                .sorted(Comparator.comparingInt(i -> i.getValue().getTier()))
                .forEach(p -> newItemGroups.put(p.getKey(), p.getValue()));
        itemGroups.clear();
        itemGroups.putAll(newItemGroups);
    }

    public void registerMobDrop(IndustrialRevivalItem mobDropItem) {
        if (!(mobDropItem instanceof MobDropItem mdi)) {
            throw new IllegalArgumentException("Item must implement MobDropItem interface");
        }
        List<Pair<ItemStack, Double>> drops = mobDrops.getOrDefault(mdi.getMobType(), new ArrayList<>());
        ItemStack item = mobDropItem.getItem().clone();
        item.setAmount(mdi.dropAmount());
        drops.add(new Pair<>(item, mdi.getChance()));
        mobDrops.put(mdi.getMobType(), drops);
    }
}
