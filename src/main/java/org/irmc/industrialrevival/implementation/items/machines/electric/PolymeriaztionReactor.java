package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.items.IRItems;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;

public class PolymeriaztionReactor extends ElectricMachine {
    public PolymeriaztionReactor() {
        super();

        addRecipe(3000, 700000,
                ItemUtils.cloneItem(IRItems.IRItemStacks.MAGIC_CRYSTAL, 3),
                IRItems.IRItemStacks.LIQUID_CRYSTAL_POLYMERS);
    }
}
