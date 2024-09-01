package org.irmc.industrialrevival.core.services;

import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.BlockDropItem;
import org.irmc.industrialrevival.api.items.attributes.MobDropItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.objects.Pair;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.api.researches.Research;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
public final class IRRegistry {
    private final Map<NamespacedKey, ItemGroup> itemGroups;
    private final Map<NamespacedKey, Research> researches;
    private final Map<String, IndustrialRevivalItem> items;
    private final Map<String, PlayerProfile> playerProfiles;
    private final Map<String, MachineMenuPreset> menuPresets;

    private final Map<EntityType, List<Pair<ItemStack, Double>>> mobDrops;
    private final Map<Material, List<Pair<ItemStack, Double>>> blockDrops;

    public IRRegistry() {
        itemGroups = new HashMap<>();
        researches = new HashMap<>();
        items = new HashMap<>();
        playerProfiles = new HashMap<>();
        menuPresets = new HashMap<>();
        mobDrops = new HashMap<>();
        blockDrops = new HashMap<>();
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

    public void registerBlockDrop(IndustrialRevivalItem blockDropItem) {
        if (!(blockDropItem instanceof BlockDropItem bdi)) {
            throw new IllegalArgumentException("Item must implement BlockDropItem interface");
        }

        List<Pair<ItemStack, Double>> drops = blockDrops.getOrDefault(bdi.dropBlock(), new ArrayList<>());
        ItemStack item = blockDropItem.getItem().clone();
        item.setAmount(bdi.dropAmount());
        drops.add(new Pair<>(item, bdi.getChance()));
        blockDrops.put(bdi.dropBlock(), drops);
    }

    public List<IndustrialRevivalItem> searchItems(String term) {
        return items.values().stream()
                .filter(i -> {
                    String coloredName = MiniMessage.miniMessage().serialize(i.getItemName());
                    String noColoredName = MiniMessage.miniMessage().stripTags(coloredName);
                    return noColoredName.toLowerCase().contains(term.toLowerCase());
                }).toList();
    }
}
