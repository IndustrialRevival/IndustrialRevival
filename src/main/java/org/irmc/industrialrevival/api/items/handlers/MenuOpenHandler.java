package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.objects.events.vanilla.MenuOpenEvent;

public interface MenuOpenHandler extends ItemHandler {
    void onMenuOpen(MenuOpenEvent event);
}
