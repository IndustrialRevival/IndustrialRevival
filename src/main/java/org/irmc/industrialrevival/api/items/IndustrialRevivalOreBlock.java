package org.irmc.industrialrevival.api.items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.irmc.industrialrevival.api.items.attributes.VanillaSmeltingItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.objects.IRRecipeChoice;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

/**
 * An ore block that drops itself when mined.
 */
public class IndustrialRevivalOreBlock extends IndustrialRevivalItem implements VanillaSmeltingItem {
    private final float exp;
    private final int cookingTime;
    private final ItemStack output;

    public IndustrialRevivalOreBlock(
            ItemGroup group,
            IndustrialRevivalItemStack itemStack,
            RecipeType recipeType,
            ItemStack[] recipe,
            float exp,
            int cookingTime,
            ItemStack output) {
        super(group, itemStack, recipeType, recipe);

        this.exp = exp;
        this.cookingTime = cookingTime;
        this.output = output;
    }

    @Override
    public float getExp() {
        return exp;
    }

    @Override
    public int getCookingTime() {
        return cookingTime;
    }

    @Override
    public @NotNull ItemStack getRecipeOutput() {
        return output;
    }

    @Override
    public @NotNull RecipeChoice getRecipeInput() {
        return new IRRecipeChoice(getItem());
    }
}
