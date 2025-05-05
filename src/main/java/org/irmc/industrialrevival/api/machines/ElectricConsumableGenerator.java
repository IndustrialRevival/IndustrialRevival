package org.irmc.industrialrevival.api.machines;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetProvider;
import org.irmc.industrialrevival.api.machines.process.MachineOperation;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.enums.GeneratorType;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public abstract class ElectricConsumableGenerator extends BasicMachine implements EnergyNetProvider {
    @Override
    public GeneratorType getGeneratorType() {
        return GeneratorType.CONSUMABLE;
    }

    @Override
    public void onDone(@NotNull Block block, MachineMenu menu, @NotNull MachineOperation operation) {
        super.onDone(block, menu, operation);
        addEnergyProduction(menu.getLocation(), getEnergyProduction(block, menu));
    }
}
