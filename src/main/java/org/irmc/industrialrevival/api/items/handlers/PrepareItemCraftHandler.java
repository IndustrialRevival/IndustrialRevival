package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareAnvilIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareItemCraftIRItemEvent;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface PrepareItemCraftHandler extends ItemHandler {
    void onPrepareItemCraft(@NotNull PrepareItemCraftIRItemEvent event);
    default Class<? extends ItemHandler> getIdentifier() {
        return PrepareItemCraftHandler.class;
    }
}
