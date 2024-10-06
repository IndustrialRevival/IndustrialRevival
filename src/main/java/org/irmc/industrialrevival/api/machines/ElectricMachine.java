package org.irmc.industrialrevival.api.machines;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * ElectricMachine is a BasicMachine that consumes energy.
 */
public abstract class ElectricMachine extends EnergyComponent {
    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    protected void preRegister() throws Exception {
        addItemHandlers((BlockTicker) (block, menu, data) -> tick(block, menu));
        super.preRegister();
    }

    protected void tick(@NotNull Block block, @Nullable MachineMenu menu) {
        // TODO: implement tick logic
        // machineRecipes.findNextRecipe()
    }
}
