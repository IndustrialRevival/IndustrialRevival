package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.items.IRItems;
import org.jetbrains.annotations.NotNull;

public class ElectronBeamLithographyMachine extends ElectricMachine {
    public ElectronBeamLithographyMachine() {
        super();

        addRecipe(30, 80000,
                IRItems.IRItemStacks.GRAPHENE,
                IRItems.IRItemStacks.NANOANTENNA);
    }
}
