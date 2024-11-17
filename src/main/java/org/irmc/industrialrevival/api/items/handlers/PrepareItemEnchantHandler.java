package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareAnvilIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareIRItemEnchantEvent;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface PrepareItemEnchantHandler extends ItemHandler {
    void onPrepareEnchant(@NotNull PrepareIRItemEnchantEvent event);
    default Class<? extends ItemHandler> getIdentifier() {
        return PrepareItemEnchantHandler.class;
    }
}
