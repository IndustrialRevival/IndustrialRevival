package org.irmc.industrialrevival.implementation.items.generators.electric;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.machines.ElectricConsumableGenerator;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.implementation.items.IRItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HeatEngine extends ElectricConsumableGenerator {
    public HeatEngine() {
        super();
        addRecipe(20, 8000, IRItems.IRItemStacks.STEAM_BOTTLE, IRItems.IRItemStacks.EMPTY);
    }

    @Override
    public long getCapacity() {
        return 0;
    }

    @Override
    public long getEnergyProduction(@NotNull Block block, @Nullable MachineMenu menu) {
        return 0;
    }

    @Override
    public void onNewInstance(Block block, MachineMenu menu) {

    }
}
