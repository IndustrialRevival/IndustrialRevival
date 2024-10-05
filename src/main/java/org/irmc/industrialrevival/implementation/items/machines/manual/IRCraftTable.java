package org.irmc.industrialrevival.implementation.items.machines.manual;

import org.irmc.industrialrevival.api.items.attributes.InventoryBlock;
import org.irmc.industrialrevival.api.machines.AbstractMachine;

public class IRCraftTable extends AbstractMachine implements InventoryBlock {
    public IRCraftTable() {
        super();
    }

    @Override
    public int[] getInputSlots() {
        return new int[0];
    }

    @Override
    public int[] getOutputSlots() {
        return new int[0];
    }
}
