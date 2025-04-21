package org.irmc.industrialrevival.api.menu.handlers;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
@FunctionalInterface
public interface OutsideClickHandler {
    void onClick(@NotNull InventoryClickEvent event, @NotNull SimpleMenu menu);
}
