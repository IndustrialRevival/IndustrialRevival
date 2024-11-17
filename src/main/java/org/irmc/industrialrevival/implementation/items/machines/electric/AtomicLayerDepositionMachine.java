package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItems;

public class AtomicLayerDepositionMachine extends ElectricMachine {
    public AtomicLayerDepositionMachine() {
        super();

        addRecipe(600, 6000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.GAS, IndustrialRevivalItems.IRItemStacks.NANOANTENNA},
                IndustrialRevivalItems.IRItemStacks.RECONFIGURABLE_NANOANTENNA);
    }
}
