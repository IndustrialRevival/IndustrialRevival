package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.items.IRItems;
import org.jetbrains.annotations.NotNull;

public class PressingMachine extends ElectricMachine {
    public PressingMachine() {
        super();

        addRecipe(20, 1000,
                new ItemStack(Material.COPPER_INGOT),
                IRItems.IRItemStacks.RAW_COPPER_FOIL);
        addRecipe(20, 1000,
                IRItems.IRItemStacks.TIN,
                IRItems.IRItemStacks.RAW_TIN_FOIL);
        addRecipe(20, 1000,
                IRItems.IRItemStacks.ZINC,
                IRItems.IRItemStacks.RAW_ZINC_FOIL);
        addRecipe(20, 1000,
                IRItems.IRItemStacks.LEAD,
                IRItems.IRItemStacks.RAW_LEAD_FOIL);
        addRecipe(20, 1000,
                IRItems.IRItemStacks.TUNGSTEN,
                IRItems.IRItemStacks.RAW_TUNGSTEN_FOIL);
        addRecipe(20, 1000,
                IRItems.IRItemStacks.MAGNESIUM,
                IRItems.IRItemStacks.RAW_MAGNESIUM_FOIL);
        addRecipe(20, 1000,
                IRItems.IRItemStacks.ALUMINIUM,
                IRItems.IRItemStacks.RAW_ALUMINIUM_FOIL);
        addRecipe(20, 1000,
                IRItems.IRItemStacks.CHROMIUM,
                IRItems.IRItemStacks.RAW_CHROMIUM_FOIL);
        addRecipe(20, 1000,
                IRItems.IRItemStacks.COBALT,
                IRItems.IRItemStacks.RAW_COBALT_FOIL);
        addRecipe(20, 1000,
                IRItems.IRItemStacks.NICKEL,
                IRItems.IRItemStacks.RAW_NICKEL_FOIL);
        addRecipe(20, 1000,
                new ItemStack(Material.IRON_INGOT),
                IRItems.IRItemStacks.RAW_IRON_FOIL);
    }
}
