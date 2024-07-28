package org.irmc.industrialrevival.core.registry;

import java.util.*;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.Research;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.player.PlayerProfile;

@Getter
public final class IRRegistry {
    private Map<NamespacedKey, ItemGroup> itemGroups;
    private Map<NamespacedKey, Research> researches;
    private Map<String, IndustrialRevivalItem> items;
    private Map<String, PlayerProfile> playerProfiles;
    private Map<String, MachineMenuPreset> menuPresets;

    public void resortItemGroups() {
        Map<NamespacedKey, ItemGroup> newItemGroups = new LinkedHashMap<>();
        Set<Map.Entry<NamespacedKey, ItemGroup>> entries = itemGroups.entrySet();
        entries.stream()
                .sorted(Comparator.comparingInt(i -> i.getValue().getTier()))
                .forEach(p -> newItemGroups.put(p.getKey(), p.getValue()));
        itemGroups = newItemGroups;
    }
}
