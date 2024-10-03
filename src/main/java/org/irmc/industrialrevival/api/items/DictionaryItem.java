package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DictionaryItem extends IndustrialRevivalItem {
    @Getter
    private final List<ItemDictionary> dictionaries = new ArrayList<>();
    public DictionaryItem(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe) {
        super(group, itemStack);
    }

    public DictionaryItem addDictionary(ItemDictionary dictionary) {
        dictionaries.add(dictionary);
        return this;
    }
}
