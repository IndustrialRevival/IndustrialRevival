package org.irmc.industrialrevival.api.machines;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.enums.GeneratorType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ElectricManualGenerator extends AbstractElectricGenerator {
    @Override
    public GeneratorType getGeneratorType() {
        return GeneratorType.MANUAL;
    }

    @Override
    protected void tick(Block block, MachineMenu menu) {

    }
}
