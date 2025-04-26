package org.irmc.industrialrevival.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.menu.handlers.AdvancedClickHandler;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.menu.IRInventoryHolder;
import org.irmc.industrialrevival.api.menu.handlers.OutsideClickHandler;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.objects.events.vanilla.MenuCloseEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.MenuOpenEvent;
import org.irmc.industrialrevival.utils.Debug;

import java.util.HashSet;
import java.util.Set;

public class MachineMenuListener implements Listener {
    private static final Set<Player> opening = new HashSet<>();
    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Inventory inv = e.getView().getTopInventory();
        if (inv.getHolder() instanceof IRInventoryHolder irh) {
            if (e.getRawSlot() < e.getInventory().getSize()) {
                Debug.log("IRInventoryHolder clicked");
                int slot = e.getSlot();
                ClickType clickType = e.getClick();
                if (irh instanceof SimpleMenu menu) {
                    ItemStack item = e.getCurrentItem();
                    Player p = (Player) e.getWhoClicked();
                    ClickHandler handler = menu.getClickHandler(slot);
                    if (handler != null) {
                        boolean notShouldBeCancelled;
                        if (handler instanceof AdvancedClickHandler ach) {
                            notShouldBeCancelled = ach.onClick(p, item, slot, menu, clickType, e);
                        } else {
                            notShouldBeCancelled = handler.onClick(p, item, slot, menu, clickType);
                        }

                        Debug.log("ClickHandler returned " + notShouldBeCancelled);
                        e.setCancelled(!notShouldBeCancelled);
                    }
                }
            } else {
                Debug.log("IRInventoryHolder outside of menu");
                if (irh instanceof SimpleMenu menu) {
                    OutsideClickHandler handler = menu.getOutsideClickHandler();
                    if (handler != null) {
                        handler.onClick(e, menu);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMenuClose(MenuCloseEvent e) {
        Player p = e.getPlayer();
        opening.remove(p);
    }

    @EventHandler
    public void onMenuOpen(MenuOpenEvent event) {
        Player p = event.getPlayer();
        opening.add(p);
    }

    public static boolean isOpeningMenu(Player player) {
        return opening.contains(player);
    }
}
