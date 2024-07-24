package org.irmc.industrialrevival.core.services;

import java.util.*;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.MobDropItem;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.api.researches.Research;

@Getter
public final class IRRegistry {
    private Map<NamespacedKey, ItemGroup> itemGroups;
    private final Map<NamespacedKey, Research> researches;
    private final Map<String, IndustrialRevivalItem> items;
    private final Map<String, PlayerProfile> playerProfiles;
    private final Map<String, MachineMenuPreset> menuPresets;
    private final Map<EntityType, List<MobDropItem>> mobDrops;

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
        itemGroups = newItemGroups;
    }

    public void registerMobDrop(MobDropItem mobDropItem) {
        mobDrops.computeIfAbsent(mobDropItem.getMobType(), k -> new ArrayList<>())
                .add(mobDropItem);
    }
}
