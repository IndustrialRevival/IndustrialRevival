package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IRItems;

public class ElectrolyticMachine extends ElectricMachine {
    public ElectrolyticMachine() {
        super();

        addRecipe(5, 1000,
                IRItems.IRItemStacks.PURE_WATER_BOTTLE,
                IRItems.IRItemStacks.DEIONIZED_WATER);
    }
}
