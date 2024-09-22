package org.irmc.industrialrevival.api.multiblocks;

import java.util.Map;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.objects.display.DisplayGroup;

public interface DisplayableMultiBlock {
    Map<Location, DisplayGroup> getDisplayGroups();

    default void addDisplay(Location location, DisplayGroup displayGroup) {
        getDisplayGroups().put(location, displayGroup);
    }

    default void removeDisplay(Location location, DisplayGroup displayGroup) {
        getDisplayGroups().remove(location);
    }

    default boolean hasDisplay(Location location) {
        return getDisplayGroups().containsKey(location);
    }

    default boolean onDisplay(Location location) {
        return getDisplayGroups().get(location).display();
    }
}
