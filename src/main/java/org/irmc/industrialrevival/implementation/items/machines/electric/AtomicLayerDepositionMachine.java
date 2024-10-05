package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IRItems;

public class AtomicLayerDepositionMachine extends ElectricMachine {
    public AtomicLayerDepositionMachine() {
        super();

        addRecipe(600, 6000,
                new ItemStack[]{IRItems.IRItemStacks.GAS, IRItems.IRItemStacks.NANOANTENNA},
                IRItems.IRItemStacks.RECONFIGURABLE_NANOANTENNA);
    }
}
