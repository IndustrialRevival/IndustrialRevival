package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.EntityPickupIRItemEvent;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface EntityPickupHandler extends ItemHandler {
    void onEntityPickup(EntityPickupIRItemEvent event);

    default @NotNull Class<? extends ItemHandler> getIdentifier() {
        return EntityPickupHandler.class;
    }
}
