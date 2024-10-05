package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.items.RadiativeItem;
import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;

/**
 * This interface defines an item which is radioactive. <br>
 * <br>
 *
 * @see RadiativeItem
 */
public interface Radiation extends ItemAttribute {
    RadiationLevel getRadiationLevel();
}
