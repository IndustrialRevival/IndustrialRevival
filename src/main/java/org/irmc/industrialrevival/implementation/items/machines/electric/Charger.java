package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

public class Charger extends ElectricMachine {
    public Charger(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineRecipes machineRecipes, long capacity) {
        super(group, itemStack, recipeType, recipe, machineRecipes, capacity);
    }
    
    @Override
    public void tick(Block block, MachineMenu menu) {

    }
}
