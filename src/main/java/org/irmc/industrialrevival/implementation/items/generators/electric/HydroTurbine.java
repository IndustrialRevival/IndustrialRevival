package org.irmc.industrialrevival.implementation.items.generators.electric;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.AbstractElectricGenerator;
import org.irmc.industrialrevival.api.machines.ElectricAutonomousGenerator;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.enums.GeneratorType;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HydroTurbine extends ElectricAutonomousGenerator {
    @Override
    public long getEnergyProduction(@NotNull Block block, @Nullable MachineMenu menu) {
        return 0;
    }
}
