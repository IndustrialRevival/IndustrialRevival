package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerBucketEmptyToIRBlockEvent;
import org.jetbrains.annotations.NotNull;

public interface PlayerBucketEmptyHandler extends ItemHandler {
    void onPlayerBucketEmpty(@NotNull PlayerBucketEmptyToIRBlockEvent event);
    default Class<? extends ItemHandler> getIdentifier() {
        return PlayerBucketEmptyHandler.class;
    }
}
