package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IRItems;

public class Colder extends ElectricMachine {
    public Colder() {
        super();

        addRecipe(240, 357000,
                IRItems.IRItemStacks.STEAM_BOTTLE,
                IRItems.IRItemStacks.PURE_WATER_BOTTLE);
    }
}
