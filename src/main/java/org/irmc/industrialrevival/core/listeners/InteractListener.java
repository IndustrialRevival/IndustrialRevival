package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.events.MenuOpenEvent;
import org.irmc.industrialrevival.api.objects.events.PlayerLeftClickEvent;
import org.irmc.industrialrevival.api.objects.events.PlayerRightClickEvent;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.DataUtil;

@SuppressWarnings("deprecation")
public class InteractListener implements Listener {
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

    @EventHandler
    public void onRightClick(PlayerRightClickEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            MachineMenu menu = DataUtil.getBlockData(e.getClickedBlock().getLocation()).getMachineMenu();
            if (menu != null) {
                MenuOpenEvent event = new MenuOpenEvent(e, menu);
                Bukkit.getServer().getPluginManager().callEvent(event);

                e.setCancelled(event.isCancelled());
            }
        }
    }
}
