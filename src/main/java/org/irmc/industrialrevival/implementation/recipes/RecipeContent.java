package org.irmc.industrialrevival.implementation.recipes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.core.utils.ItemUtils;

public record RecipeContent(
        RecipeType recipeType, IndustrialRevivalItem maker, ItemStack[] recipe, IndustrialRevivalItem result) {
    public ItemStack getMakerItem() {
        if (maker == null) {
            return recipeType.getMaker() == null ? recipeType.getIcon() : recipeType.getMaker();
        } else {
            return ItemUtils.getCleanedItem(maker.getItem());
        }
    }
}
