package org.irmc.industrialrevival.core.implemention.recipes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.recipes.RecipeType;

public record RecipeContent(RecipeType recipeType, IndustrialRevivalItem maker, ItemStack[] recipe, IndustrialRevivalItem result) {
}
