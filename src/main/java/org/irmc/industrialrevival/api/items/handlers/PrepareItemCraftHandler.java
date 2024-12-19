package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareItemCraftIRItemEvent;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface PrepareItemCraftHandler extends ItemHandler {
    void onPrepareItemCraft(@NotNull PrepareItemCraftIRItemEvent event);

    default @NotNull Class<? extends ItemHandler> getIdentifier() {
        return PrepareItemCraftHandler.class;
    }
}
