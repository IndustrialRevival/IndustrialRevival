package org.irmc.industrialrevival.api.machines;

import org.irmc.industrialrevival.api.objects.enums.GeneratorType;
import org.irmc.industrialrevival.api.objects.events.ir.BlockTickEvent;

public abstract class ElectricAutonomousGenerator extends AbstractElectricGenerator {
    @Override
    protected void tick(BlockTickEvent event) {
        addEnergyProduction(event.getMenu().getLocation(), getEnergyProduction(event.getBlock(), event.getMenu()));
    }

    @Override
    public GeneratorType getGeneratorType() {
        return GeneratorType.AUTONOMOUS;
    }
}
