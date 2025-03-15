package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.compounds.classes.Attribute;
import org.irmc.industrialrevival.api.recipes.RecipeType;

public interface RecipeTypeLike extends Attribute {
    RecipeType getRecipeType();
    ItemStack getRecipeTypeIcon();
}
