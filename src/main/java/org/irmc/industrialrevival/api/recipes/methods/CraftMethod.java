package org.irmc.industrialrevival.api.recipes.methods;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.recipes.RecipeType;

/**
 * @author baluagq
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
        recipeType.registerRecipe(this.ingredients, this.output);
    }

    public CraftMethod(RecipeType recipeType, ItemStack[] ingredients, IndustrialRevivalItem output) {
        this.recipeType = recipeType;
        this.ingredients = ingredients;
        this.output = output.getIcon();
        recipeType.registerRecipe(this.ingredients, this.output);
    }

    public static CraftMethod of(RecipeType recipeType, ItemStack[] ingredients, ItemStack output) {
        return new CraftMethod(recipeType, ingredients, output);
    }

    public static CraftMethod of(RecipeType recipeType, ItemStack[] ingredients, IndustrialRevivalItem output) {
        return new CraftMethod(recipeType, ingredients, output);
    }

    @Override
    public ItemStack[] getOutput() {
        return new ItemStack[]{output};
    }
}
