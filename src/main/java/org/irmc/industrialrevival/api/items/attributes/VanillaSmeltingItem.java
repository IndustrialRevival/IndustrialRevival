package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

public interface VanillaSmeltingItem extends ItemAttribute {
    float getExp();

    int getCookingTime();

    /**
     * Returns the output of the recipe
     * @return the output of the recipe
     */
    @NotNull ItemStack getRecipeOutput();

    @NotNull RecipeChoice getRecipeInput();
}
