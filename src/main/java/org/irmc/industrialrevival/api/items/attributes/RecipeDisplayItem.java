package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

/**
 * This interface defines a machine which needs to show a custom recipe instead of `item.getRecipe()`. <br>
 * <br>
 * <br>@see RecipeType
 * <br>@see DefaultRecipeDisplay
 */
public interface RecipeDisplayItem extends ItemAttribute {
    /*
     * @param maker The recipe type's industrial revival item.
     * Returns the recipe for the machine.
     */
    ItemStack[] getRecipe(IndustrialRevivalItem maker);
}
