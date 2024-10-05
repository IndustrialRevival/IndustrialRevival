package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.items.IRItems;
import org.jetbrains.annotations.NotNull;

public class ElectrolyticMachine extends ElectricMachine {
    public ElectrolyticMachine() {
        super();

        addRecipe(5, 1000,
                IRItems.IRItemStacks.PURE_WATER_BOTTLE,
                IRItems.IRItemStacks.DEIONIZED_WATER);
    }
}
