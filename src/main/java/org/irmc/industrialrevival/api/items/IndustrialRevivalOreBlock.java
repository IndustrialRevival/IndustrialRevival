package org.irmc.industrialrevival.api.items;

import lombok.Builder;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.irmc.industrialrevival.api.items.attributes.VanillaSmeltingItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.objects.IRRecipeChoice;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

/**
 * An ore block that drops itself when mined.
 */
@Builder
public class IndustrialRevivalOreBlock extends IndustrialRevivalItem implements VanillaSmeltingItem {
    @Getter
    private float exp;
    @Getter
    private int cookingTime;
    private ItemStack output;

    public IndustrialRevivalOreBlock setExp(float exp) {
        checkRegistered();
        this.exp = exp;
        return this;
    }

    public IndustrialRevivalOreBlock setCookingTime(int cookingTime) {
        checkRegistered();
        this.cookingTime = cookingTime;
        return this;
    }

    public IndustrialRevivalOreBlock setOutput(ItemStack output) {
        checkRegistered();
        this.output = output;
        return this;
    }


    @Override
    @Nonnull
    public RecipeChoice getRecipeInput() {
        return new IRRecipeChoice(getItem());
    }
    @Override
    @Nonnull
    public ItemStack getRecipeOutput() {
        return output;
    }
}
