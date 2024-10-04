package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;

import javax.annotation.Nullable;

/**
 * This interface defines a generator can provide energy to an energy network.<br>
 * <br>
 * <b>Note: </b> use {@link EnergyNetComponent} if the machine not provides energy to the network.
 */
public interface EnergyNetProvider extends EnergyNetComponent {
    @Override
    default EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    long getEnergyProduction(Block block, @Nullable MachineMenu menu);
}
