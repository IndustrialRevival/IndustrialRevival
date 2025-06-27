package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.InventoryBlock;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.events.vanilla.EndermanMoveIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EntityChangeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EntityExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockBreakEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockFromToEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockPlaceEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.MenuOpenEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PistonExtendIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PistonRetractIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerBucketEmptyToIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerRightClickEvent;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.DataUtil;
import org.irmc.industrialrevival.utils.Debug;

/**
 * Do operation after {@link HandlerCaller} and all other operations are done
 *
 * @author balugaq
 * @see EventCreator
 * @see HandlerCaller
 */
public class DefaultHandler implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockExplodeIRBlock(BlockExplodeEvent event) {
        if (event.isCancelled()) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEndermanMoveIrBlock(EndermanMoveIRBlockEvent event) {
        if (event.getOriginalEvent().isCancelled()) {
            return;
        }

        event.getOriginalEvent().setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityChangeIRBlock(EntityChangeIRBlockEvent event) {
        if (event.getOriginalEvent().isCancelled()) {
            return;
        }

        event.getOriginalEvent().setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityExplodeIRBlock(EntityExplodeIRBlockEvent event) {
        if (event.getOriginalEvent().isCancelled()) {
            return;
        }

        event.getOriginalEvent().setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onIRBlockBreak(IRBlockBreakEvent event) {
        if (event.getOriginalEvent().isCancelled()) {
            return;
        }

        //IRDock.getPlugin().getItemTextureService().blockBreaking(event);
        IRDock.getPlugin().getBlockDataService().handleBlockBreaking(event.getOriginalEvent().getBlock().getLocation());
        IRDock.getPlugin().getDataManager().handleBlockBreaking(event.getOriginalEvent().getBlock().getLocation());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onIRBlockFromTo(IRBlockFromToEvent event) {
        if (event.getOriginalEvent().isCancelled()) {
            return;
        }

        event.getOriginalEvent().setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onIRBlockPlace(IRBlockPlaceEvent event) {
        Debug.log("onIRBlockPlace - 1");
        if (event.isCancelled()) {
            return;
        }

        Location location = event.getOriginalEvent().getBlockPlaced().getLocation();
        NamespacedKey id = event.getIritem().getId();

        Debug.log("onIRBlockPlace - 2");
        //IRDock.getPlugin().getItemTextureService().blockPlacing(event);
        IRDock.getPlugin().getBlockDataService().handleBlockPlacing(location, id);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMenuOpen(MenuOpenEvent event) {
        if (event.getInventoryOpenEvent().isCancelled()) {
            return;
        }

        event.getOpenedMenu().open((Player) event.getInventoryOpenEvent().getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPistonExtendIRBlock(PistonExtendIRBlockEvent event) {
        if (event.getOriginalEvent().isCancelled()) {
            return;
        }

        event.getOriginalEvent().setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPistonRetractIRBlock(PistonRetractIRBlockEvent event) {
        if (event.getOriginalEvent().isCancelled()) {
            return;
        }

        event.getOriginalEvent().setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBucketEmptyToIRBlock(PlayerBucketEmptyToIRBlockEvent event) {
        if (event.getOriginalEvent().isCancelled()) {
            return;
        }

        event.getOriginalEvent().setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRightClick(PlayerRightClickEvent event) {
        if (event.getOriginalEvent().isCancelled()) {
            return;
        }

        if (!event.getOriginalEvent().getAction().isRightClick()) {
            return;
        }

        Location location = event.getOriginalEvent().getClickedBlock().getLocation();
        IndustrialRevivalItem iritem = DataUtil.getItem(location);

        if (iritem == null) {
            return;
        }

        if (iritem instanceof InventoryBlock) {
            IRBlockData blockData = DataUtil.getBlockData(location);
            MachineMenu menu = blockData.getMachineMenu();
            if (menu != null) {
                menu.open(event.getOriginalEvent().getPlayer());
            }
        }
        event.getOriginalEvent().setCancelled(true);
    }
}
