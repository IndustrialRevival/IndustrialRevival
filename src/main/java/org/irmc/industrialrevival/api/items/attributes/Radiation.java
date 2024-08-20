package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;

public interface Radiation extends ItemAttribute {
    void setRadiationLevel(RadiationLevel level);
    RadiationLevel getRadiationLevel();
}
