package org.irmc.industrialrevival.core.services;

import com.google.common.base.Preconditions;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.irmc.industrialrevival.api.elements.melt.MeltedType;
import org.irmc.industrialrevival.api.elements.tinker.TinkerType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.BlockDropItem;
import org.irmc.industrialrevival.api.items.attributes.ChemReactable;
import org.irmc.industrialrevival.api.items.attributes.MobDropItem;
import org.irmc.industrialrevival.api.items.attributes.TinkerProduct;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.groups.NestedItemGroup;
import org.irmc.industrialrevival.api.items.groups.NormalItemGroup;
import org.irmc.industrialrevival.api.items.groups.SubItemGroup;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.multiblock.MultiBlock;
import org.irmc.industrialrevival.api.objects.display.DisplayGroup;
import org.irmc.industrialrevival.api.objects.exceptions.FormulaConflictException;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.api.recipes.methods.BlockDropMethod;
import org.irmc.industrialrevival.api.recipes.methods.MeltMethod;
import org.irmc.industrialrevival.api.recipes.methods.MobDropMethod;
import org.irmc.industrialrevival.api.recipes.methods.ProduceMethod;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.Debug;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public final class IRRegistry {
    private final Map<NamespacedKey, ItemGroup> itemGroups;
    private final Map<NamespacedKey, ItemDictionary> dictionaries;
    private final Map<NamespacedKey, DisplayGroup> displayGroups;
    private final Map<NamespacedKey, MachineMenuPreset> menuPresets;
    private final Map<NamespacedKey, IndustrialRevivalItem> items;
    private final Map<NamespacedKey, ChemReactable> chemReactables;
    @ApiStatus.Experimental
    private final Map<ChemReactable, ChemicalCompound> bindingCompounds;
    private final Map<String, PlayerProfile> playerProfiles;
    private final Map<NamespacedKey, MultiBlock> multiBlocks;
    private final Map<EntityType, List<MobDropMethod>> mobDrops;
    private final Map<Material, List<BlockDropMethod>> blockDrops;
    private final Map<RecipeType, Set<ItemStack>> produceable;
    private final Set<ProduceMethod> produceMethods;
    private final Map<MeltedType, Map<TinkerType, TinkerProduct>> tinkerMap;
    private final Map<Integer, ChemicalFormula> chemicalFormulas;
    public ChemicalFormula getById(int id) {
        return chemicalFormulas.get(id);
    }

    public IRRegistry() {
        itemGroups = new HashMap<>();
        dictionaries = new HashMap<>();
        displayGroups = new HashMap<>();
        items = new HashMap<>();
        chemReactables = new HashMap<>();
        bindingCompounds = new HashMap<>();
        playerProfiles = new HashMap<>();
        menuPresets = new HashMap<>();
        multiBlocks = new HashMap<>();
        mobDrops = new HashMap<>();
        blockDrops = new HashMap<>();
        produceable = new HashMap<>();
        produceMethods = new HashSet<>();
        tinkerMap = new HashMap<>();
        chemicalFormulas = new HashMap<>();
    }

    public void registerMultiBlock(MultiBlock multiBlock) {
        multiBlocks.put(multiBlock.getId(), multiBlock);
    }

    public void unregisterMultiBlock(MultiBlock multiBlock) {
        multiBlocks.remove(multiBlock.getId());
    }

    public void registerRecipeType(RecipeType recipeType) {
        produceable.put(recipeType, new HashSet<>());
    }

    public void unregisterRecipeType(RecipeType recipeType) {
        produceable.remove(recipeType);
    }

    public void registerCraftable(RecipeType recipeType, ItemStack itemStack) {
        Set<ItemStack> craftableSet = produceable.get(recipeType);
        if (craftableSet == null) {
            registerRecipeType(recipeType);
            craftableSet = produceable.get(recipeType);
        }
        craftableSet.add(itemStack);
    }

    public void unregisterProduceable(RecipeType recipeType, ItemStack itemStack) {
        Set<ItemStack> produceableSet = produceable.get(recipeType);
        if (produceableSet == null) {
            return;
        }
        produceableSet.remove(itemStack);
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

        for (ProduceMethod method : item.getProduceMethods()) {
            registerRecipeType(method.getRecipeType());
            for (ItemStack itemStack : method.getOutput()) {
                registerCraftable(method.getRecipeType(), itemStack);
            }
            registerProduceMethod(method);
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
            registerProduceMethod(method);
        }
    }

    public void registerBlockDrop(@NotNull BlockDropItem blockDropItem) {
        Preconditions.checkArgument(blockDropItem != null, "Item cannot be null");

        Set<BlockDropMethod> methods = blockDropItem.getDropMethods();
        for (BlockDropMethod method : methods) {
            List<BlockDropMethod> methodDrops = blockDrops.getOrDefault(method.getBlockType(), new ArrayList<>());
            methodDrops.add(method);
            blockDrops.put(method.getBlockType(), methodDrops);
            registerProduceMethod(method);
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

        for (ProduceMethod method : item.getProduceMethods()) {
            for (ItemStack itemStack : method.getOutput()) {
                unregisterProduceable(method.getRecipeType(), itemStack);
            }
            unregisterProduceMethod(method);
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
            unregisterProduceMethod(method);
        }

        mobDropItem.setDisabled(true, true);
    }

    public void unregisterBlockDrop(IndustrialRevivalItem blockDropItem) {
        if (!(blockDropItem instanceof BlockDropItem bdi)) {
            throw new IllegalArgumentException("Item must implement BlockDropItem interface");
        }
        Set<BlockDropMethod> methods = bdi.getDropMethods();
        for (BlockDropMethod method : methods) {
            List<BlockDropMethod> methodDrops = blockDrops.get(method.getBlockType());
            if (methodDrops == null) {
                continue;
            }

            methodDrops.remove(method);
            if (methodDrops.isEmpty()) {
                blockDrops.remove(method.getBlockType());
            }
            unregisterProduceMethod(method);
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

    public void registerProduceMethod(ProduceMethod produceMethod) {
        produceMethods.add(produceMethod);
    }

    public void unregisterProduceMethod(ProduceMethod produceMethod) {
        produceMethods.remove(produceMethod);
    }

    public Set<MeltMethod> getMeltMethods() {
        return produceMethods.stream()
                .filter(m -> m instanceof MeltMethod)
                .map(m -> (MeltMethod) m)
                .collect(Collectors.toSet());
    }

    public void registerTinkerItem(@NotNull MeltedType meltedType, @NotNull TinkerProduct product) {
        if (!tinkerMap.containsKey(meltedType)) {
            tinkerMap.put(meltedType, new HashMap<>());
        }

        tinkerMap.get(meltedType).put(product.getTinkerType(), product);
    }

    @Nullable
    public TinkerProduct getTinkerItem(@NotNull MeltedType meltedType, @NotNull TinkerType tinkerType) {
        if (tinkerMap.containsKey(meltedType) && tinkerMap.get(meltedType).containsKey(tinkerType)) {
            return tinkerMap.get(meltedType).get(tinkerType);
        }
        return null;
    }

    @Nullable
    public Set<TinkerType> getActiveTinkerTypes(@NotNull MeltedType meltedType) {
        if (tinkerMap.containsKey(meltedType)) {
            return tinkerMap.get(meltedType).keySet();
        }
        return null;
    }

    @NotNull
    public Set<MeltedType> getAllMeltedTypes() {
        return tinkerMap.keySet();
    }

    public void registerChemicalFormula(@NotNull ChemicalFormula formula) {
        if (chemicalFormulas.containsKey(formula.getId())) {
            Debug.error(new FormulaConflictException(chemicalFormulas.get(formula.getId()), formula));
            return;
        }

        for (var f2 : chemicalFormulas.values()) {
            if (f2.equals(formula)) {
                Debug.error(new FormulaConflictException(f2, formula));
            }
        }

        chemicalFormulas.put(formula.getId(), formula);
    }

    public void unregisterChemicalFormula(@NotNull ChemicalFormula formula) {
        chemicalFormulas.remove(formula.getId());
    }

    public void registerChemicalReactable(@NotNull ChemReactable reactable) {
        chemReactables.put(reactable.getKey(), reactable);
    }

    public void unregisterChemicalReactable(@NotNull ChemReactable reactable) {
        chemReactables.remove(reactable.getKey());
    }

    @ApiStatus.Experimental
    public void bindChemicalCompound(@NotNull ChemReactable reactable, @NotNull ChemicalCompound compound) {
        bindingCompounds.put(reactable, compound);
    }

    @ApiStatus.Experimental
    public void unbindChemicalCompound(@NotNull ChemReactable reactable) {
        bindingCompounds.remove(reactable);
    }
}
