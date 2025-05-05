package org.irmc.industrialrevival.api.machines;

import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;

/**
 * ElectricLimitedConnector is a limited connector.
 * @author balugaq
 */
public abstract class ElectricLimitedConnector extends EnergyComponent {
    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.LIMITED_CONNECTOR;
    }
}
