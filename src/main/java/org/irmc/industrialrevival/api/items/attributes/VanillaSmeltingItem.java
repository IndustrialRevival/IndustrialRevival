package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

/**
 * This interface defines a machine that can obtain by smelting in vanilla furnace. <br>
 * <br>
 *
 * @see IndustrialRevivalItem
 */
public interface VanillaSmeltingItem extends ItemAttribute {
    float getExp();

    int getCookingTime();

    /**
     * Returns the output of the recipe
     *
     * @return the output of the recipe
     */
    @NotNull ItemStack getSmeltOutput();

    @NotNull RecipeChoice getRecipeInput();
}
