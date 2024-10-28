package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.MenuCloseEvent;
import org.jetbrains.annotations.NotNull;

public interface MenuCloseHandler extends ItemHandler {
    void onMenuClose(@NotNull MenuCloseEvent event);
}
