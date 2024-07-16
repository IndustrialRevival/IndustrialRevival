package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;

public interface EnergyNetProvider extends EnergyNetComponent {
    @Override
    default EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    long getEnergyProduction(SimpleMenu menu);
}
