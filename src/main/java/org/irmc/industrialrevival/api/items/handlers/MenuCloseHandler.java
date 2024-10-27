package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.MenuCloseEvent;

public interface MenuCloseHandler extends ItemHandler {
    void onMenuClose(MenuCloseEvent event);
}
