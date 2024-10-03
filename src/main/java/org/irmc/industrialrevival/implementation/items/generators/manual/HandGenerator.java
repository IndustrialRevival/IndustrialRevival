package org.irmc.industrialrevival.implementation.items.generators.manual;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetProvider;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.BasicMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// 玩家尝试打开机器菜单时，发电1j （或许是一个很好的新手发电机）
public class HandGenerator extends BasicMachine implements EnergyNetProvider {
    public HandGenerator(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineRecipes machineRecipes) {
        super(group, itemStack, recipeType, recipe, machineRecipes);
    }

    @Override
    public long getCapacity() {
        return 128;
    }

    @Override
    public long getEnergyProduction(Block block, @Nullable MachineMenu menu) {
        return 0;
    }
}
