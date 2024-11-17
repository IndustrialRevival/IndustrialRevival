package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItems;

public class Colder extends ElectricMachine {
    public Colder() {
        super();

        addRecipe(240, 357000,
                IndustrialRevivalItems.IRItemStacks.STEAM_BOTTLE,
                IndustrialRevivalItems.IRItemStacks.PURE_WATER_BOTTLE);
    }
}
