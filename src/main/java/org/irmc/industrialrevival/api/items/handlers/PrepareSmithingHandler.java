package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareAnvilIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareSmithingIRItemEvent;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface PrepareSmithingHandler extends ItemHandler {
    void onPrepareSmithing(@NotNull PrepareSmithingIRItemEvent event);
    default Class<? extends ItemHandler> getIdentifier() {
        return PrepareSmithingHandler.class;
    }
}
