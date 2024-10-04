package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.items.IRItems;
import org.jetbrains.annotations.NotNull;

public class SpinCoater extends ElectricMachine {
    public SpinCoater(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineRecipes machineRecipes, long capacity) {
        super(group, itemStack, recipeType, recipe, machineRecipes, capacity);

        addRecipe(80, 1000,
                new ItemStack[]{IRItems.IRItemStacks.CLEANED_GRAPHENE_THIN_FILM, IRItems.IRItemStacks.POLYMETHYL_METHACRYLATE},
                IRItems.IRItemStacks.GRAPHENE_FILM_COATED_WITH_POLYMETHYL_METHACRYLATE);
    }
}
