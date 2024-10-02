package org.irmc.industrialrevival.implementation.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.items.IRItemStacks;
import org.jetbrains.annotations.NotNull;

public class Polisher extends ElectricMachine {
    public Polisher(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineRecipes machineRecipes, long capacity) {
        super(group, itemStack, recipeType, recipe, machineRecipes, capacity);

        addRecipe(50, 5000,
                IRItemStacks.RAW_COPPER_FOIL,
                IRItemStacks.POLISHED_COPPER_FOIL);
        addRecipe(50, 5000,
                IRItemStacks.RAW_TIN_FOIL,
                IRItemStacks.POLISHED_TIN_FOIL);
        addRecipe(50, 5000,
                IRItemStacks.RAW_ZINC_FOIL,
                IRItemStacks.POLISHED_ZINC_FOIL);
        addRecipe(50, 5000,
                IRItemStacks.RAW_LEAD_FOIL,
                IRItemStacks.POLISHED_LEAD_FOIL);
        addRecipe(50, 5000,
                IRItemStacks.RAW_TUNGSTEN_FOIL,
                IRItemStacks.POLISHED_TUNGSTEN_FOIL);
        addRecipe(50, 5000,
                IRItemStacks.RAW_MAGNESIUM_FOIL,
                IRItemStacks.POLISHED_MAGNESIUM_FOIL);
        addRecipe(50, 5000,
                IRItemStacks.RAW_ALUMINIUM_FOIL,
                IRItemStacks.POLISHED_ALUMINIUM_FOIL);
        addRecipe(50, 5000,
                IRItemStacks.RAW_CHROMIUM_FOIL,
                IRItemStacks.POLISHED_CHROMIUM_FOIL);
        addRecipe(50, 5000,
                IRItemStacks.RAW_COBALT_FOIL,
                IRItemStacks.POLISHED_COBALT_FOIL);
        addRecipe(50, 5000,
                IRItemStacks.RAW_NICKEL_FOIL,
                IRItemStacks.POLISHED_NICKEL_FOIL);
        addRecipe(50, 5000,
                IRItemStacks.RAW_IRON_FOIL,
                IRItemStacks.POLISHED_IRON_FOIL);
    }
}
