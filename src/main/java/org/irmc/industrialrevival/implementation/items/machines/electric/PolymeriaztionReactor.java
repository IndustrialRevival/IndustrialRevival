package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItems;
import org.irmc.pigeonlib.items.ItemUtils;

public class PolymeriaztionReactor extends ElectricMachine {
    public PolymeriaztionReactor() {
        super();

        addRecipe(3000, 700000,
                ItemUtils.cloneItem(IndustrialRevivalItems.IRItemStacks.MAGIC_CRYSTAL, 3),
                IndustrialRevivalItems.IRItemStacks.LIQUID_CRYSTAL_POLYMERS);
    }
}
