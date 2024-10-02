package org.irmc.industrialrevival.implementation.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.items.IRItemStacks;
import org.jetbrains.annotations.NotNull;

public class HotPress extends ElectricMachine {
    public HotPress(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineRecipes machineRecipes, long capacity) {
        super(group, itemStack, recipeType, recipe, machineRecipes, capacity);

        addRecipe(50, 8000,
                new ItemStack[] {IRItemStacks.GRAPHENE_FILM_COATED_WITH_POLYMETHYL_METHACRYLATE, IRItemStacks.POLYETHYLENE_TEREPHTHALATE},
                IRItemStacks.DIRTY_GRAPHENE);
        addRecipe(50, 4000,
                IRItemStacks.DIRTY_GRAPHENE,
                IRItemStacks.GRAPHENE);
    }
}
