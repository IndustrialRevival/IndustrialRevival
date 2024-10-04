package org.irmc.industrialrevival.api.machines;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.enums.GeneratorType;

public abstract class ElectricAutonomousGenerator extends AbstractElectricGenerator {
    @Override
    protected void tick(Block block, MachineMenu menu) {
        addEnergyProduction(menu.getLocation(), getEnergyProduction(block, menu));
    }

    @Override
    public GeneratorType getGeneratorType() {
        return GeneratorType.AUTONOMOUS;
    }
}
