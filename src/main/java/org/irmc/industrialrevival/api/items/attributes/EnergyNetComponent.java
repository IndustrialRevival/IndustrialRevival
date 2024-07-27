package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;

public interface EnergyNetComponent extends ItemAttribute {
    long getCapacity();

    EnergyNetComponentType getComponentType();
}
