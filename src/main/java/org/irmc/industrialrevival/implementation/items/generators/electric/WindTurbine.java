package org.irmc.industrialrevival.implementation.items.generators.electric;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.machines.ElectricAutonomousGenerator;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// 产生随机数以决定是否发电，发电多少
public class WindTurbine extends ElectricAutonomousGenerator {
    @Override
    public long getEnergyProduction(@NotNull Block block, @Nullable MachineMenu menu) {
        return 0;
    }
}
