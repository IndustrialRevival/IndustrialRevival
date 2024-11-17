package org.irmc.industrialrevival.api.machines;

import org.irmc.industrialrevival.api.objects.enums.GeneratorType;
import org.irmc.industrialrevival.api.objects.events.ir.IRBlockTickEvent;

public abstract class ElectricAutonomousGenerator extends AbstractElectricGenerator {
    @Override
    protected void tick(IRBlockTickEvent event) {
        addEnergyProduction(event.getMenu().getLocation(), getEnergyProduction(event.getBlock(), event.getMenu()));
    }

    @Override
    public GeneratorType getGeneratorType() {
        return GeneratorType.AUTONOMOUS;
    }
}
