package org.irmc.industrialrevival.api.machines;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.enums.GeneratorType;
import org.irmc.industrialrevival.api.objects.events.ir.IRBlockTickEvent;

public abstract class ElectricManualGenerator extends AbstractElectricGenerator {
    @Override
    public GeneratorType getGeneratorType() {
        return GeneratorType.MANUAL;
    }

    @Override
    protected void tick(IRBlockTickEvent event) {

    }
}
