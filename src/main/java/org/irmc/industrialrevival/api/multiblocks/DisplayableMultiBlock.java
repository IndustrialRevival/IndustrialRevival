package org.irmc.industrialrevival.api.multiblocks;

import org.bukkit.Location;
import org.irmc.industrialrevival.api.objects.display.DisplayGroup;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@SuppressWarnings("unused")
public interface DisplayableMultiBlock {
    Map<Location, DisplayGroup> getDisplayGroups();

    default void addDisplay(@NotNull Location location, @NotNull DisplayGroup displayGroup) {
        getDisplayGroups().put(location, displayGroup);
    }

    default void removeDisplay(@NotNull Location location) {
        onRemove(location);
        getDisplayGroups().remove(location);
    }

    default boolean hasDisplay(@NotNull Location location) {
        return getDisplayGroups().containsKey(location);
    }

    default boolean onDisplay(@NotNull Location location) {
        return getDisplayGroups().get(location).display();
    }
    default boolean onHide(@NotNull Location location) {
        return getDisplayGroups().get(location).hide();
    }
    default boolean onRemove(@NotNull Location location) {
        return getDisplayGroups().get(location).remove();
    }
}
