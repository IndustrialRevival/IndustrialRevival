package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerBucketEmptyToIRBlockEvent;

public interface PlayerBucketEmptyHandler extends ItemHandler {
    void onPlayerBucketEmpty(PlayerBucketEmptyToIRBlockEvent event);
}
