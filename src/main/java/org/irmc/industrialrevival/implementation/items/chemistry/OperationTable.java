package org.irmc.industrialrevival.implementation.items.chemistry;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.machines.BasicMachine;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

public class OperationTable extends BasicMachine {
    public OperationTable() {
        setAddon(IndustrialRevival.getInstance());
    }
}
