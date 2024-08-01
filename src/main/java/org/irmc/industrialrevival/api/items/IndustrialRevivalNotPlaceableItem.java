package org.irmc.industrialrevival.api.items;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.recipes.RecipeType;

public class IndustrialRevivalNotPlaceableItem extends IndustrialRevivalItem implements NotPlaceable {
    public IndustrialRevivalNotPlaceableItem(ItemGroup group, IndustrialRevivalItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        super(group, itemStack, recipeType, recipe);
    }
}
