package org.irmc.industrialrevival.implementation.items.generators.electric;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.ElectricConsumableGenerator;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.implementation.items.IRItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DieselGenerator extends ElectricConsumableGenerator {
    public DieselGenerator() {
        super();
        addRecipe(10, 1000, IRItems.IRItemStacks.GAS, (ItemStack) null);
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
