package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareAnvilIRItemEvent;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface PrepareAnvilHandler extends ItemHandler {
    void onPrepareAnvil(@NotNull PrepareAnvilIRItemEvent event);

    default @NotNull Class<? extends ItemHandler> getIdentifier() {
        return PrepareAnvilHandler.class;
    }
}
