package org.irmc.industrialrevival.api.items;

import org.irmc.industrialrevival.api.items.attributes.Radiation;
import org.irmc.industrialrevival.api.objects.RadiationLevel;

public class RadiationItem extends IndustrialRevivalItem implements Radiation {

    public RadiationItem() {

    }

    @Override
    public RadiationLevel getRadiationLevel() {
        return null;
    }
}
