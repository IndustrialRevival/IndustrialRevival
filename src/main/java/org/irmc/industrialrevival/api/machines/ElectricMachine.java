package org.irmc.industrialrevival.api.machines;

import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;
import org.irmc.industrialrevival.api.objects.events.ir.IRBlockTickEvent;
import org.jetbrains.annotations.NotNull;

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
        addItemHandlers((BlockTicker) this::tick);
        super.preRegister();
    }

    protected void tick(@NotNull IRBlockTickEvent event) {
        // TODO: implement tick logic
        // machineRecipes.findNextRecipe()
    }
}
