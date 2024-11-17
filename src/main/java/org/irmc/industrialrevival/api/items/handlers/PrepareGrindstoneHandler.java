package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareAnvilIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareGrindstoneIRItemEvent;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface PrepareGrindstoneHandler extends ItemHandler {
    void onPrepareGrindstone(@NotNull PrepareGrindstoneIRItemEvent event);
    default Class<? extends ItemHandler> getIdentifier() {
        return PrepareGrindstoneHandler.class;
    }
}
