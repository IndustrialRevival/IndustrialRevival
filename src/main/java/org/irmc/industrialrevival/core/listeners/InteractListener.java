package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.objects.events.PlayerLeftClickEvent;
import org.irmc.industrialrevival.api.objects.events.PlayerRightClickEvent;

@SuppressWarnings("deprecation")
public class InteractListener extends AbstractIRListener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            PlayerRightClickEvent event = new PlayerRightClickEvent(e);
            Bukkit.getServer().getPluginManager().callEvent(event);

            e.setCancelled(event.isCancelled());
        }

        if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) {
            PlayerLeftClickEvent event = new PlayerLeftClickEvent(e);
            Bukkit.getServer().getPluginManager().callEvent(event);

            e.setCancelled(event.isCancelled());
        }
    }
}
