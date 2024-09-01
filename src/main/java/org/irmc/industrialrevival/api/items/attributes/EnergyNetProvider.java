package org.irmc.industrialrevival.api.items.attributes;

import javax.annotation.Nullable;
import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;

public interface EnergyNetProvider extends EnergyNetComponent {
    @Override
    default EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    long getEnergyProduction(Block block, @Nullable MachineMenu menu);
}
