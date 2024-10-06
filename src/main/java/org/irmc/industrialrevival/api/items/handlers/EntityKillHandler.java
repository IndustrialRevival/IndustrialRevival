package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface EntityKillHandler extends ItemHandler {
    void onKill(@NotNull EntityDeathEvent event, @NotNull Player killer, @NotNull IndustrialRevivalItem item);
}
