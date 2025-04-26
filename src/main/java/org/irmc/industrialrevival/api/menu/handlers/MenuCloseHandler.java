package org.irmc.industrialrevival.api.menu.handlers;

import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.jetbrains.annotations.NotNull;

/**
 * @author lijinhong11
 */
@FunctionalInterface
public interface MenuCloseHandler {
    void onClose(@NotNull Player player, @NotNull SimpleMenu menu);
}
