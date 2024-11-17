package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItems;

public class ElectronBeamLithographyMachine extends ElectricMachine {
    public ElectronBeamLithographyMachine() {
        super();

        addRecipe(30, 80000,
                IndustrialRevivalItems.IRItemStacks.GRAPHENE,
                IndustrialRevivalItems.IRItemStacks.NANOANTENNA);
    }
}
