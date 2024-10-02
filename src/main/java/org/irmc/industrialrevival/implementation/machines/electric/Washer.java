package org.irmc.industrialrevival.implementation.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.items.IRItemStacks;
import org.jetbrains.annotations.NotNull;

public class Washer extends ElectricMachine {
    public Washer(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineRecipes machineRecipes, long capacity) {
        super(group, itemStack, recipeType, recipe, machineRecipes, capacity);

        addRecipe(80, 4000,
                new ItemStack[] {IRItemStacks.PURE_WATER_BOTTLE, IRItemStacks.POLISHED_COPPER_FOIL},
                IRItemStacks.FLAWLESS_COPPER_FOIL);
        addRecipe(80, 4000,
                new ItemStack[] {IRItemStacks.PURE_WATER_BOTTLE, IRItemStacks.POLISHED_TIN_FOIL},
                IRItemStacks.FLAWLESS_TIN_FOIL);
        addRecipe(80, 4000,
                new ItemStack[] {IRItemStacks.PURE_WATER_BOTTLE, IRItemStacks.POLISHED_ZINC_FOIL},
                IRItemStacks.FLAWLESS_ZINC_FOIL);
        addRecipe(80, 4000,
                new ItemStack[] {IRItemStacks.PURE_WATER_BOTTLE, IRItemStacks.POLISHED_LEAD_FOIL},
                IRItemStacks.FLAWLESS_LEAD_FOIL);
        addRecipe(80, 4000,
                new ItemStack[] {IRItemStacks.PURE_WATER_BOTTLE, IRItemStacks.POLISHED_TUNGSTEN_FOIL},
                IRItemStacks.FLAWLESS_TUNGSTEN_FOIL);
        addRecipe(80, 4000,
                new ItemStack[] {IRItemStacks.PURE_WATER_BOTTLE, IRItemStacks.POLISHED_MAGNESIUM_FOIL},
                IRItemStacks.FLAWLESS_MAGNESIUM_FOIL);
        addRecipe(80, 4000,
                new ItemStack[] {IRItemStacks.PURE_WATER_BOTTLE, IRItemStacks.POLISHED_ALUMINIUM_FOIL},
                IRItemStacks.FLAWLESS_ALUMINIUM_FOIL);
        addRecipe(80, 4000,
                new ItemStack[] {IRItemStacks.PURE_WATER_BOTTLE, IRItemStacks.POLISHED_CHROMIUM_FOIL},
                IRItemStacks.FLAWLESS_CHROMIUM_FOIL);
        addRecipe(80, 4000,
                new ItemStack[] {IRItemStacks.PURE_WATER_BOTTLE, IRItemStacks.POLISHED_COBALT_FOIL},
                IRItemStacks.FLAWLESS_COBALT_FOIL);
        addRecipe(80, 4000,
                new ItemStack[] {IRItemStacks.PURE_WATER_BOTTLE, IRItemStacks.POLISHED_NICKEL_FOIL},
                IRItemStacks.FLAWLESS_NICKEL_FOIL);
        addRecipe(80, 4000,
                new ItemStack[] {IRItemStacks.PURE_WATER_BOTTLE, IRItemStacks.POLISHED_IRON_FOIL},
                IRItemStacks.FLAWLESS_IRON_FOIL);
        addRecipe(80, 4000,
                new ItemStack[] {IRItemStacks.DEIONIZED_WATER, IRItemStacks.GRAPHENE_THIN_FILM},
                IRItemStacks.CLEANED_GRAPHENE_THIN_FILM);
    }
}
