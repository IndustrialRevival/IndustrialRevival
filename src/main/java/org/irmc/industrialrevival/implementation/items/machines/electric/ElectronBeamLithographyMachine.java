package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IRItems;

public class ElectronBeamLithographyMachine extends ElectricMachine {
    public ElectronBeamLithographyMachine() {
        super();

        addRecipe(30, 80000,
                IRItems.IRItemStacks.GRAPHENE,
                IRItems.IRItemStacks.NANOANTENNA);
    }
}
