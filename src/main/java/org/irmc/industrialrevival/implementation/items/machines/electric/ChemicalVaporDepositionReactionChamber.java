package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItems;

public class ChemicalVaporDepositionReactionChamber extends ElectricMachine {
    public ChemicalVaporDepositionReactionChamber() {
        super();

        addRecipe(480, 80000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.HEATED_COPPER_FOIL, IndustrialRevivalItems.IRItemStacks.HYDROGEN},
                IndustrialRevivalItems.IRItemStacks.RAW_GRAPHENE);
    }
}
