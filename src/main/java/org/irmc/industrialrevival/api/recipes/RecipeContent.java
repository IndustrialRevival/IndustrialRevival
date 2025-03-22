package org.irmc.industrialrevival.api.recipes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.utils.CleanedItemGetter;

public record RecipeContent(
        RecipeType recipeType, IndustrialRevivalItem maker, ItemStack[] recipe, IndustrialRevivalItem result) {
    public ItemStack getMakerItem() {
        if (maker == null) {
            return recipeType.getMaker() == null ? recipeType.getIcon() : recipeType.getMaker();
        } else {
            return CleanedItemGetter.getCleanedItem(maker.getIcon());
        }
    }
}
