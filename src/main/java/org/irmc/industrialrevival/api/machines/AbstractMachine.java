package org.irmc.industrialrevival.api.machines;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.recipes.RecipeType;

import javax.annotation.Nonnull;

/**
 * Abstract class for all IndustrialRevival machines.
 */
public abstract class AbstractMachine extends IndustrialRevivalItem {
    final MachineRecipes machineRecipes;

    public AbstractMachine(
            @Nonnull ItemGroup group,
            @Nonnull IndustrialRevivalItemStack itemStack,
            @Nonnull RecipeType recipeType,
            @Nonnull ItemStack[] recipe,
            @Nonnull MachineRecipes machineRecipes) {
        super(group, itemStack, recipeType, recipe);
        this.machineRecipes = machineRecipes;
    }
}
