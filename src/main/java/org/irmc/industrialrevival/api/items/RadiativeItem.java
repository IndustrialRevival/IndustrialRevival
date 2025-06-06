package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.irmc.industrialrevival.api.items.attributes.Radiation;
import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;

@Getter
public class RadiativeItem extends IndustrialRevivalItem implements Radiation {
    private RadiationLevel radiationLevel = RadiationLevel.LOW;

    public RadiativeItem setRadiationLevel(RadiationLevel radiationLevel) {
        checkRegistered();
        this.radiationLevel = radiationLevel;
        return this;
    }
}
