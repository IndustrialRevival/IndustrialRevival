package org.irmc.industrialrevival.implementation.items.generators.electric;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.machines.ElectricSolarGenerator;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SolarGenerator extends ElectricSolarGenerator {
    @Override
    public long getEnergyProduction(@NotNull Block block, @Nullable MachineMenu menu) {
        return 0;
    }

    @Override
    protected boolean isDay(Block block, MachineMenu menu, byte lightLevel) {
        return false;
    }

    @Override
    protected long getDayEnergyProduction(Block block, MachineMenu menu, byte lightLevel) {
        return 0;
    }

    @Override
    protected long getNightEnergyProduction(Block block, MachineMenu menu, byte lightLevel) {
        return 0;
    }
}
