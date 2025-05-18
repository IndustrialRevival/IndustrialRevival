package org.irmc.industrialrevival.api.objects.display.builder;

import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.jetbrains.annotations.NotNull;

/**
 * AbstractModelBuilder is the base class for all model builders.
 * Used to build a display entity at a given location.
 */
public abstract class AbstractModelBuilder {
    /**
     * Builds a display entity at the given location.
     *
     * @param location the location to build the display entity at
     * @return the built display entity
     */
    public abstract Display buildAt(@NotNull Location location);
}
