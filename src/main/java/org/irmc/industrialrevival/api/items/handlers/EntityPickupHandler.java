package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.EntityPickupIRItemEvent;

public interface EntityPickupHandler extends ItemHandler {
    void onEntityPickup(EntityPickupIRItemEvent event);
}
