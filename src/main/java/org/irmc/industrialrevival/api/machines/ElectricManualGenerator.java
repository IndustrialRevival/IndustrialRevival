package org.irmc.industrialrevival.api.machines;

import org.irmc.industrialrevival.api.objects.enums.GeneratorType;
import org.irmc.industrialrevival.api.objects.events.ir.BlockTickEvent;

/**
 * @author balugaq
 */
public abstract class ElectricManualGenerator extends AbstractElectricGenerator {
    @Override
    public GeneratorType getGeneratorType() {
        return GeneratorType.MANUAL;
    }

    @Override
    protected void tick(BlockTickEvent event) {

    }
}
