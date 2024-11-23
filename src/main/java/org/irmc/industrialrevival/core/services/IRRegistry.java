package org.irmc.industrialrevival.core.services;

import com.google.common.base.Preconditions;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.BlockDropItem;
import org.irmc.industrialrevival.api.items.attributes.MobDropItem;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.groups.NestedItemGroup;
import org.irmc.industrialrevival.api.items.groups.NormalItemGroup;
import org.irmc.industrialrevival.api.items.groups.SubItemGroup;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.multiblock.MultiBlock;
import org.irmc.industrialrevival.api.objects.display.DisplayGroup;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.api.recipes.BlockDropMethod;
import org.irmc.industrialrevival.api.recipes.CraftMethod;
import org.irmc.industrialrevival.api.recipes.MobDropMethod;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.api.researches.Research;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
public final class IRRegistry {
    private final Map<NamespacedKey, ItemGroup> itemGroups;
    private final Map<NamespacedKey, Research> researches;
    private final Map<NamespacedKey, ItemDictionary> dictionaries;
    private final Map<NamespacedKey, DisplayGroup> displayGroups;
    private final Map<String, IndustrialRevivalItem> items;
    private final Map<String, PlayerProfile> playerProfiles;
    private final Map<String, MachineMenuPreset> menuPresets;
    private final Map<String, MultiBlock> multiBlocks;

    private final Map<EntityType, List<MobDropMethod>> mobDrops;
    private final Map<Material, List<BlockDropMethod>> blockDrops;

    private final Map<RecipeType, Set<ItemStack>> craftables;


    public IRRegistry() {
        itemGroups = new HashMap<>();
        researches = new HashMap<>();
        dictionaries = new HashMap<>();
        displayGroups = new HashMap<>();
        items = new HashMap<>();
        playerProfiles = new HashMap<>();
        menuPresets = new HashMap<>();
        multiBlocks = new HashMap<>();
        mobDrops = new HashMap<>();
        blockDrops = new HashMap<>();
        craftables = new HashMap<>();
    }

    public void registerMultiBlock(MultiBlock multiBlock) {
        multiBlocks.put(multiBlock.getId(), multiBlock);
    }

    public void unregisterMultiBlock(MultiBlock multiBlock) {
        multiBlocks.remove(multiBlock.getId());
    }

    public void registerRecipeType(RecipeType recipeType) {
        craftables.put(recipeType, new HashSet<>());
    }

    public void unregisterRecipeType(RecipeType recipeType) {
        craftables.remove(recipeType);
    }

    public void registerCraftable(RecipeType recipeType, ItemStack itemStack) {
        Set<ItemStack> craftableSet = craftables.get(recipeType);
        if (craftableSet == null) {
            registerRecipeType(recipeType);
            craftableSet = craftables.get(recipeType);
        }
        craftableSet.add(itemStack);
    }

    public void unregisterCraftable(RecipeType recipeType, ItemStack itemStack) {
        Set<ItemStack> craftableSet = craftables.get(recipeType);
        if (craftableSet == null) {
            return;
        }
        craftableSet.remove(itemStack);
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

    public void registerDictionary(@NotNull ItemDictionary dictionary) {
        dictionaries.put(dictionary.getKey(), dictionary);
    }

    public void registerItem(@NotNull IndustrialRevivalItem item) {
        Preconditions.checkArgument(item != null, "Item cannot be null");
        items.put(item.getId(), item);

        for (CraftMethod method : item.getCraftMethods()) {
            registerRecipeType(method.getRecipeType());
            registerCraftable(method.getRecipeType(), method.getOutput());
        }
    }

    public void registerItemGroup(@NotNull ItemGroup itemGroup) {
        Preconditions.checkArgument(itemGroup.getKey() != null, "Item group key cannot be null");
        itemGroups.put(itemGroup.getKey(), itemGroup);
    }

    public void registerMobDrop(@NotNull MobDropItem mobDropItem) {
        Preconditions.checkArgument(mobDropItem != null, "Item cannot be null");

        MobDropMethod[] methods = mobDropItem.getDropMethods();
        for (MobDropMethod method : methods) {
            List<MobDropMethod> methodDrops = mobDrops.getOrDefault(method.getMobType(), new ArrayList<>());
            methodDrops.add(method);
            mobDrops.put(method.getMobType(), methodDrops);
        }
    }

    public void registerBlockDrop(@NotNull BlockDropItem blockDropItem) {
        Preconditions.checkArgument(blockDropItem != null, "Item cannot be null");

        BlockDropMethod[] methods = blockDropItem.getDropMethods();
        for (BlockDropMethod method : methods) {
            List<BlockDropMethod> methodDrops = blockDrops.getOrDefault(method.getBlockType(), new ArrayList<>());
            methodDrops.add(method);
            blockDrops.put(method.getBlockType(), methodDrops);
        }
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

        for (CraftMethod method : item.getCraftMethods()) {
            unregisterCraftable(method.getRecipeType(), method.getOutput());
        }
        items.remove(item.getId());
        item.setDisabled(true, true);
    }

    public void unregisterMobDrop(IndustrialRevivalItem mobDropItem) {
        if (!(mobDropItem instanceof MobDropItem mdi)) {
            throw new IllegalArgumentException("Item must implement MobDropItem interface");
        }

        MobDropMethod[] methods = mdi.getDropMethods();
        for (MobDropMethod method : methods) {
            List<MobDropMethod> methodDrops = mobDrops.get(method.getMobType());
            if (methodDrops == null) {
                continue;
            }
            methodDrops.remove(method);
            if (methodDrops.isEmpty()) {
                mobDrops.remove(method.getMobType());
            }
        }

        mobDropItem.setDisabled(true, true);
    }

    public void unregisterBlockDrop(IndustrialRevivalItem blockDropItem) {
        if (!(blockDropItem instanceof BlockDropItem bdi)) {
            throw new IllegalArgumentException("Item must implement BlockDropItem interface");
        }
        BlockDropMethod[] methods = bdi.getDropMethods();
        for (BlockDropMethod method : methods) {
            List<BlockDropMethod> methodDrops = blockDrops.get(method.getBlockType());
            if (methodDrops == null) {
                continue;
            }

            methodDrops.remove(method);
            if (methodDrops.isEmpty()) {
                blockDrops.remove(method.getBlockType());
            }
        }

        blockDropItem.setDisabled(true, true);
    }

    public void unregisterDisplayGroup(NamespacedKey key) {
        displayGroups.remove(key);
    }

    // todo: pinyin search
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
