package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.items.IRItems;
import org.jetbrains.annotations.NotNull;

public class Washer extends ElectricMachine {
    public Washer() {
        super();

        addRecipe(80, 4000,
                new ItemStack[]{IRItems.IRItemStacks.PURE_WATER_BOTTLE, IRItems.IRItemStacks.POLISHED_COPPER_FOIL},
                IRItems.IRItemStacks.FLAWLESS_COPPER_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IRItems.IRItemStacks.PURE_WATER_BOTTLE, IRItems.IRItemStacks.POLISHED_TIN_FOIL},
                IRItems.IRItemStacks.FLAWLESS_TIN_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IRItems.IRItemStacks.PURE_WATER_BOTTLE, IRItems.IRItemStacks.POLISHED_ZINC_FOIL},
                IRItems.IRItemStacks.FLAWLESS_ZINC_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IRItems.IRItemStacks.PURE_WATER_BOTTLE, IRItems.IRItemStacks.POLISHED_LEAD_FOIL},
                IRItems.IRItemStacks.FLAWLESS_LEAD_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IRItems.IRItemStacks.PURE_WATER_BOTTLE, IRItems.IRItemStacks.POLISHED_TUNGSTEN_FOIL},
                IRItems.IRItemStacks.FLAWLESS_TUNGSTEN_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IRItems.IRItemStacks.PURE_WATER_BOTTLE, IRItems.IRItemStacks.POLISHED_MAGNESIUM_FOIL},
                IRItems.IRItemStacks.FLAWLESS_MAGNESIUM_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IRItems.IRItemStacks.PURE_WATER_BOTTLE, IRItems.IRItemStacks.POLISHED_ALUMINIUM_FOIL},
                IRItems.IRItemStacks.FLAWLESS_ALUMINIUM_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IRItems.IRItemStacks.PURE_WATER_BOTTLE, IRItems.IRItemStacks.POLISHED_CHROMIUM_FOIL},
                IRItems.IRItemStacks.FLAWLESS_CHROMIUM_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IRItems.IRItemStacks.PURE_WATER_BOTTLE, IRItems.IRItemStacks.POLISHED_COBALT_FOIL},
                IRItems.IRItemStacks.FLAWLESS_COBALT_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IRItems.IRItemStacks.PURE_WATER_BOTTLE, IRItems.IRItemStacks.POLISHED_NICKEL_FOIL},
                IRItems.IRItemStacks.FLAWLESS_NICKEL_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IRItems.IRItemStacks.PURE_WATER_BOTTLE, IRItems.IRItemStacks.POLISHED_IRON_FOIL},
                IRItems.IRItemStacks.FLAWLESS_IRON_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IRItems.IRItemStacks.DEIONIZED_WATER, IRItems.IRItemStacks.GRAPHENE_THIN_FILM},
                IRItems.IRItemStacks.CLEANED_GRAPHENE_THIN_FILM);
    }
}
