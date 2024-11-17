package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItems;

public class ElectrolyticMachine extends ElectricMachine {
    public ElectrolyticMachine() {
        super();

        addRecipe(5, 1000,
                IndustrialRevivalItems.IRItemStacks.PURE_WATER_BOTTLE,
                IndustrialRevivalItems.IRItemStacks.DEIONIZED_WATER);
    }
}
