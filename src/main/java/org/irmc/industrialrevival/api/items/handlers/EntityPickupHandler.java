package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.EntityPickupIRItemEvent;

@FunctionalInterface
public interface EntityPickupHandler extends ItemHandler {
    void onEntityPickup(EntityPickupIRItemEvent event);
    default Class<? extends ItemHandler> getIdentifier() {
        return EntityPickupHandler.class;
    }
}
