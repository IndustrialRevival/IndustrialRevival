package org.irmc.industrialrevival.api.machines;

import lombok.Getter;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetComponent;

@Getter
public abstract class EnergyComponent extends BasicMachine implements EnergyNetComponent {
    private long capacity = 0;

    public EnergyComponent setCapacity(long capacity) {
        checkRegistered();
        this.capacity = capacity;
        return this;
    }
}
