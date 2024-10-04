package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.recipes.MobDropMethod;
import org.irmc.industrialrevival.core.services.IRRegistry;

/**
 * This interface defines an item that can be dropped by a entity.<br>
 * <br>
 *
 * @see IRRegistry
 */
public interface MobDropItem {
    MobDropMethod[] getDropMethods();
}
