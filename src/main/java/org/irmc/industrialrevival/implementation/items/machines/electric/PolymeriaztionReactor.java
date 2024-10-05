package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IRItems;
import org.irmc.pigeonlib.items.ItemUtils;

public class PolymeriaztionReactor extends ElectricMachine {
    public PolymeriaztionReactor() {
        super();

        addRecipe(3000, 700000,
                ItemUtils.cloneItem(IRItems.IRItemStacks.MAGIC_CRYSTAL, 3),
                IRItems.IRItemStacks.LIQUID_CRYSTAL_POLYMERS);
    }
}
