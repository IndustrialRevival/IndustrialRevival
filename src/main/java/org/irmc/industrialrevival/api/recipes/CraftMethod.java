package org.irmc.industrialrevival.api.recipes;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

/**
 * Not supported custom recipe output yet.
 */
@Getter
public class CraftMethod implements ProduceMethod {
    private final RecipeType recipeType;
    private final ItemStack[] ingredients;
    private final ItemStack output;

    public CraftMethod(RecipeType recipeType, ItemStack[] ingredients, ItemStack output) {
        this.recipeType = recipeType;
        this.ingredients = ingredients;
        this.output = output;
    }

    public CraftMethod(RecipeType recipeType, ItemStack[] ingredients, IndustrialRevivalItem output) {
        this.recipeType = recipeType;
        this.ingredients = ingredients;
        this.output = output.getItem();
    }
}
