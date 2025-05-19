package org.irmc.industrialrevival.api.machines;

import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;

/**
 * ElectricCapacitor is an item that can be used to store energy.
 *
 * @author balugaq
 */
public abstract class ElectricCapacitor extends EnergyComponent {
    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.CAPACITOR;
    }
}
