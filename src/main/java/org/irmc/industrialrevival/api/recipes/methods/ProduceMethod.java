package org.irmc.industrialrevival.api.recipes.methods;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.recipes.RecipeType;

/**
 * ProduceMethod is an interface for all getProduce methods.
 * Used to describe the method of production of a product.\
 *
 * @author balugaq
 * @since 1.0
 */
public interface ProduceMethod {
    RecipeType getRecipeType();

    ItemStack[] getIngredients();

    ItemStack[] getOutput();
}
