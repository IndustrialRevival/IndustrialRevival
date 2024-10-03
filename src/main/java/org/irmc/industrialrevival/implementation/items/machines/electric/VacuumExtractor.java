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

public class VacuumExtractor extends ElectricMachine {
    public VacuumExtractor(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineRecipes machineRecipes, long capacity) {
        super(group, itemStack, recipeType, recipe, machineRecipes, capacity);

        addRecipe(60, 20000,
                new ItemStack(Material.GLASS_BOTTLE),
                IRItems.IRItemStacks.VACUUM_BOTTLE);
    }
}
