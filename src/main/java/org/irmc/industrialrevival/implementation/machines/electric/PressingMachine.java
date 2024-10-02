package org.irmc.industrialrevival.implementation.machines.electric;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.items.IRItemStacks;
import org.jetbrains.annotations.NotNull;

public class PressingMachine extends ElectricMachine {
    public PressingMachine(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineRecipes machineRecipes, long capacity) {
        super(group, itemStack, recipeType, recipe, machineRecipes, capacity);

        addRecipe(20, 1000,
                new ItemStack(Material.COPPER_INGOT),
                IRItemStacks.RAW_COPPER_FOIL);
        addRecipe(20, 1000,
                IRItemStacks.TIN,
                IRItemStacks.RAW_TIN_FOIL);
        addRecipe(20, 1000,
                IRItemStacks.ZINC,
                IRItemStacks.RAW_ZINC_FOIL);
        addRecipe(20, 1000,
                IRItemStacks.LEAD,
                IRItemStacks.RAW_LEAD_FOIL);
        addRecipe(20, 1000,
                IRItemStacks.TUNGSTEN,
                IRItemStacks.RAW_TUNGSTEN_FOIL);
        addRecipe(20, 1000,
                IRItemStacks.MAGNESIUM,
                IRItemStacks.RAW_MAGNESIUM_FOIL);
        addRecipe(20, 1000,
                IRItemStacks.ALUMINIUM,
                IRItemStacks.RAW_ALUMINIUM_FOIL);
        addRecipe(20, 1000,
                IRItemStacks.CHROMIUM,
                IRItemStacks.RAW_CHROMIUM_FOIL);
        addRecipe(20, 1000,
                IRItemStacks.COBALT,
                IRItemStacks.RAW_COBALT_FOIL);
        addRecipe(20, 1000,
                IRItemStacks.NICKEL,
                IRItemStacks.RAW_NICKEL_FOIL);
        addRecipe(20, 1000,
                new ItemStack(Material.IRON_INGOT),
                IRItemStacks.RAW_IRON_FOIL);
    }
}
