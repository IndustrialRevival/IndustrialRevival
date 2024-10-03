package org.irmc.industrialrevival.core.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import org.irmc.industrialrevival.api.items.groups.NestedItemGroup;
import org.irmc.industrialrevival.api.items.groups.NormalItemGroup;
import org.irmc.industrialrevival.api.items.groups.SubItemGroup;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.objects.Pair;
import org.irmc.industrialrevival.api.objects.display.DisplayGroup;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.api.researches.Research;

@Getter
public final class IRRegistry {
    private final Map<NamespacedKey, ItemGroup> itemGroups;
    private final Map<NamespacedKey, Research> researches;
    private final Map<NamespacedKey, DisplayGroup> displayGroups;
    private final Map<String, IndustrialRevivalItem> items;
    private final Map<String, PlayerProfile> playerProfiles;
    private final Map<String, MachineMenuPreset> menuPresets;

    private final Map<EntityType, List<Pair<ItemStack, Double>>> mobDrops;
    private final Map<Material, List<Pair<ItemStack, Double>>> blockDrops;

    public IRRegistry() {
        itemGroups = new HashMap<>();
        researches = new HashMap<>();
        displayGroups = new HashMap<>();
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

    public void registerItemGroup(ItemGroup itemGroup) {
        itemGroups.put(itemGroup.getKey(), itemGroup);
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

    public void registerDisplayGroup(NamespacedKey key, DisplayGroup displayGroup) {
        displayGroups.put(key, displayGroup);
    }

    public void unregisterItemGroup(NamespacedKey key) {
        ItemGroup itemGroup = itemGroups.get(key);
        if (itemGroup instanceof NestedItemGroup nestedItemGroup) {
            nestedItemGroup.getSubItemGroups().forEach(subItemGroup -> {
                subItemGroup.getItems().forEach(this::unregisterItem);
            });

            nestedItemGroup.getItems().forEach(this::unregisterItem);
        }

        if (itemGroup instanceof SubItemGroup subItemGroup) {
            subItemGroup.getItems().forEach(this::unregisterItem);
        }

        if (itemGroup instanceof NormalItemGroup normalItemGroup) {
            normalItemGroup.getItems().forEach(this::unregisterItem);
        }
        itemGroups.remove(key);
    }

    public void unregisterItem(IndustrialRevivalItem item) {
        if (item instanceof MobDropItem) {
            unregisterMobDrop(item);
        }

        if (item instanceof BlockDropItem) {
            unregisterBlockDrop(item);
        }

        items.remove(item.getId());
        item.setDisabled(true);
    }

    public void unregisterMobDrop(IndustrialRevivalItem mobDropItem) {
        if (!(mobDropItem instanceof MobDropItem mdi)) {
            throw new IllegalArgumentException("Item must implement MobDropItem interface");
        }
        List<Pair<ItemStack, Double>> drops = mobDrops.get(mdi.getMobType());
        if (drops == null) {
            return;
        }
        drops.removeIf(p -> p.getFirst().isSimilar(mobDropItem.getItem()));
        if (drops.isEmpty()) {
            mobDrops.remove(mdi.getMobType());
        }

        mobDropItem.setDisabled(true);
    }

    public void unregisterBlockDrop(IndustrialRevivalItem blockDropItem) {
        if (!(blockDropItem instanceof BlockDropItem bdi)) {
            throw new IllegalArgumentException("Item must implement BlockDropItem interface");
        }
        List<Pair<ItemStack, Double>> drops = blockDrops.get(bdi.dropBlock());
        if (drops == null) {
            return;
        }
        drops.removeIf(p -> p.getFirst().isSimilar(blockDropItem.getItem()));
        if (drops.isEmpty()) {
            blockDrops.remove(bdi.dropBlock());
        }

        blockDropItem.setDisabled(true);
    }

    public void unregisterDisplayGroup(NamespacedKey key) {
        displayGroups.remove(key);
    }

    public List<IndustrialRevivalItem> searchItems(String term) {
        return items.values().stream()
                .filter(i -> {
                    String coloredName = MiniMessage.miniMessage().serialize(i.getItemName());
                    String noColoredName = MiniMessage.miniMessage().stripTags(coloredName);
                    return noColoredName.toLowerCase().contains(term.toLowerCase());
                })
                .toList();
    }
}
