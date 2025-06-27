package org.irmc.industrialrevival.api.data;

import org.irmc.industrialrevival.api.IndustrialRevivalAddon;

/**
 * This interface is used to mark a class as a data store point.
 * The data store point is a serializable object that can be stored in the data storage.
 * <p>
 * NOTE:<br>
 * BEFORE USE IT, PLEASE ANNOTATE IT WITH {@link io.github.lijinhong11.mdatabase.serialization.annotations.Table}
 * IN YOUR CLASS.
 * </p>
 */
public interface DataStorePoint {
    IndustrialRevivalAddon getAddon();
}
