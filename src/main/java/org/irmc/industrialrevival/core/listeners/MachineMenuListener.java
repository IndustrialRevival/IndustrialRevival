package org.irmc.industrialrevival.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.menu.IRInventoryHolder;
import org.irmc.industrialrevival.api.menu.SimpleMenu;

public class MachineMenuListener implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Inventory inv = e.getInventory();
        if (inv.getHolder() instanceof IRInventoryHolder irh) {
            int slot = e.getRawSlot();
            ClickType clickType = e.getClick();
            SimpleMenu menu = (SimpleMenu) irh;
            ItemStack item = e.getCurrentItem();
            Player p = (Player) e.getWhoClicked();
            SimpleMenu.ClickHandler handler = menu.getClickHandler(slot);
            boolean notShouldBeCancelled;
            if (handler instanceof SimpleMenu.AdvancedClickHandler ach) {
                notShouldBeCancelled = ach.onClick(slot, p, item, menu, clickType, e);
            } else {
                notShouldBeCancelled = handler.onClick(slot, p, item, menu, clickType);
            }

            e.setCancelled(!notShouldBeCancelled);
        }
    }

    @EventHandler
    public void onMenuClose(InventoryClickEvent e) {
        Inventory inv = e.getInventory();
        if (inv.getHolder() instanceof IRInventoryHolder irh) {
            SimpleMenu menu = (SimpleMenu) irh;
            Player p = (Player) e.getWhoClicked();
            menu.getCloseHandler().onClose(p);
        }
    }
}
