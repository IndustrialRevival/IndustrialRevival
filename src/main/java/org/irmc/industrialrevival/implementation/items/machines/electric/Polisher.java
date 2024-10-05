package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.items.IRItems;
import org.jetbrains.annotations.NotNull;

public class Polisher extends ElectricMachine {
    public Polisher(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineRecipes machineRecipes, long capacity) {
        super(group, itemStack, recipeType, recipe, machineRecipes, capacity);

        addRecipe(50, 5000,
                IRItems.IRItemStacks.RAW_COPPER_FOIL,
                IRItems.IRItemStacks.POLISHED_COPPER_FOIL);
        addRecipe(50, 5000,
                IRItems.IRItemStacks.RAW_TIN_FOIL,
                IRItems.IRItemStacks.POLISHED_TIN_FOIL);
        addRecipe(50, 5000,
                IRItems.IRItemStacks.RAW_ZINC_FOIL,
                IRItems.IRItemStacks.POLISHED_ZINC_FOIL);
        addRecipe(50, 5000,
                IRItems.IRItemStacks.RAW_LEAD_FOIL,
                IRItems.IRItemStacks.POLISHED_LEAD_FOIL);
        addRecipe(50, 5000,
                IRItems.IRItemStacks.RAW_TUNGSTEN_FOIL,
                IRItems.IRItemStacks.POLISHED_TUNGSTEN_FOIL);
        addRecipe(50, 5000,
                IRItems.IRItemStacks.RAW_MAGNESIUM_FOIL,
                IRItems.IRItemStacks.POLISHED_MAGNESIUM_FOIL);
        addRecipe(50, 5000,
                IRItems.IRItemStacks.RAW_ALUMINIUM_FOIL,
                IRItems.IRItemStacks.POLISHED_ALUMINIUM_FOIL);
        addRecipe(50, 5000,
                IRItems.IRItemStacks.RAW_CHROMIUM_FOIL,
                IRItems.IRItemStacks.POLISHED_CHROMIUM_FOIL);
        addRecipe(50, 5000,
                IRItems.IRItemStacks.RAW_COBALT_FOIL,
                IRItems.IRItemStacks.POLISHED_COBALT_FOIL);
        addRecipe(50, 5000,
                IRItems.IRItemStacks.RAW_NICKEL_FOIL,
                IRItems.IRItemStacks.POLISHED_NICKEL_FOIL);
        addRecipe(50, 5000,
                IRItems.IRItemStacks.RAW_IRON_FOIL,
                IRItems.IRItemStacks.POLISHED_IRON_FOIL);
    }
}
