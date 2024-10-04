package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

@FunctionalInterface
public interface EntityKillHandler extends ItemHandler {
    void onKill(EntityDeathEvent event, Player killer, IndustrialRevivalItem item);
}
