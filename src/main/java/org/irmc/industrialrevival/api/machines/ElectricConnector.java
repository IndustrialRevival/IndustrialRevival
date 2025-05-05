package org.irmc.industrialrevival.api.machines;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetConnector;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;

/**
 * ElectricConnector is an item that can be used to connect machines.
 * @author balugaq
 */
public abstract class ElectricConnector extends IndustrialRevivalItem implements EnergyNetConnector {
    @Override
    public long getCapacity() {
        return 0;
    }

    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.CONNECTOR;
    }
}
