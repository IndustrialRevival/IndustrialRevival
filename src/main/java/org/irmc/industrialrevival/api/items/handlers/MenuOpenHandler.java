package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.MenuOpenEvent;
import org.jetbrains.annotations.NotNull;

public interface MenuOpenHandler extends ItemHandler {
    void onMenuOpen(@NotNull MenuOpenEvent event);
}
