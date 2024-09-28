package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.items.RadiativeItem;
import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;
import org.irmc.industrialrevival.core.task.RadiationDetectTask;

/**
 * This interface defines a item which is radioactive. <br>
 * <br>
 * @see RadiativeItem
 * @see RadiationDetectTask
 */
public interface Radiation extends ItemAttribute {
    RadiationLevel getRadiationLevel();

    void setRadiationLevel(RadiationLevel level);
}
