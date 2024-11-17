package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItems;

public class Polisher extends ElectricMachine {
    public Polisher() {
        super();

        addRecipe(50, 5000,
                IndustrialRevivalItems.IRItemStacks.RAW_COPPER_FOIL,
                IndustrialRevivalItems.IRItemStacks.POLISHED_COPPER_FOIL);
        addRecipe(50, 5000,
                IndustrialRevivalItems.IRItemStacks.RAW_TIN_FOIL,
                IndustrialRevivalItems.IRItemStacks.POLISHED_TIN_FOIL);
        addRecipe(50, 5000,
                IndustrialRevivalItems.IRItemStacks.RAW_ZINC_FOIL,
                IndustrialRevivalItems.IRItemStacks.POLISHED_ZINC_FOIL);
        addRecipe(50, 5000,
                IndustrialRevivalItems.IRItemStacks.RAW_LEAD_FOIL,
                IndustrialRevivalItems.IRItemStacks.POLISHED_LEAD_FOIL);
        addRecipe(50, 5000,
                IndustrialRevivalItems.IRItemStacks.RAW_TUNGSTEN_FOIL,
                IndustrialRevivalItems.IRItemStacks.POLISHED_TUNGSTEN_FOIL);
        addRecipe(50, 5000,
                IndustrialRevivalItems.IRItemStacks.RAW_MAGNESIUM_FOIL,
                IndustrialRevivalItems.IRItemStacks.POLISHED_MAGNESIUM_FOIL);
        addRecipe(50, 5000,
                IndustrialRevivalItems.IRItemStacks.RAW_ALUMINIUM_FOIL,
                IndustrialRevivalItems.IRItemStacks.POLISHED_ALUMINIUM_FOIL);
        addRecipe(50, 5000,
                IndustrialRevivalItems.IRItemStacks.RAW_CHROMIUM_FOIL,
                IndustrialRevivalItems.IRItemStacks.POLISHED_CHROMIUM_FOIL);
        addRecipe(50, 5000,
                IndustrialRevivalItems.IRItemStacks.RAW_COBALT_FOIL,
                IndustrialRevivalItems.IRItemStacks.POLISHED_COBALT_FOIL);
        addRecipe(50, 5000,
                IndustrialRevivalItems.IRItemStacks.RAW_NICKEL_FOIL,
                IndustrialRevivalItems.IRItemStacks.POLISHED_NICKEL_FOIL);
        addRecipe(50, 5000,
                IndustrialRevivalItems.IRItemStacks.RAW_IRON_FOIL,
                IndustrialRevivalItems.IRItemStacks.POLISHED_IRON_FOIL);
    }
}
