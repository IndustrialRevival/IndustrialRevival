package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

public interface RecipeDisplayItem extends ItemAttribute {
    ItemStack[] getRecipe(IndustrialRevivalItem maker);
}
