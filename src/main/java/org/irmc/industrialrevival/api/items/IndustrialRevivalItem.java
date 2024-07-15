package org.irmc.industrialrevival.api.items;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.attributes.Placeable;
import org.irmc.industrialrevival.api.objects.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.recipes.RecipeType;

public class IndustrialRevivalItem implements Placeable {
    private final ItemGroup group;
    private final IndustrialRevivalItemStack itemStack;
    private final RecipeType recipeType;
    private final ItemStack[] recipe;

    public IndustrialRevivalItem(ItemGroup group, IndustrialRevivalItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        this.group = group;
        this.itemStack = itemStack;
        this.recipeType = recipeType;
        this.recipe = recipe;
    }
}
