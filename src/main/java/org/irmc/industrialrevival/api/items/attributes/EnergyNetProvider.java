package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;

import javax.annotation.Nullable;

public interface EnergyNetProvider extends EnergyNetComponent {
    @Override
    default EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    long getEnergyProduction(Block block, @Nullable MachineMenu menu);
}
