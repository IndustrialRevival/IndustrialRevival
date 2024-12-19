package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.InventoryMoveIRItemEvent;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface InventoryMoveHandler extends ItemHandler {
    void onInventoryMove(@NotNull InventoryMoveIRItemEvent event);

    default @NotNull Class<? extends ItemHandler> getIdentifier() {
        return InventoryMoveHandler.class;
    }
}
