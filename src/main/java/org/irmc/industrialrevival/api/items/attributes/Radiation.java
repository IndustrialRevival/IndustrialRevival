package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.items.ItemAttribute;
import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;

public interface Radiation extends ItemAttribute {
    RadiationLevel getRadiationLevel();
}
