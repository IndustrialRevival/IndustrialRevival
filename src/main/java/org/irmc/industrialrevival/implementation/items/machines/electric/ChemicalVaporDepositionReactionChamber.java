package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IRItems;

public class ChemicalVaporDepositionReactionChamber extends ElectricMachine {
    public ChemicalVaporDepositionReactionChamber() {
        super();

        addRecipe(480, 80000,
                new ItemStack[]{IRItems.IRItemStacks.HEATED_COPPER_FOIL, IRItems.IRItemStacks.HYDROGEN},
                IRItems.IRItemStacks.RAW_GRAPHENE);
    }
}
