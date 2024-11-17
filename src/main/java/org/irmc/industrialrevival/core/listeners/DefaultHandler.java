package org.irmc.industrialrevival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.inventory.InventoryType;
import org.irmc.industrialrevival.api.items.attributes.NotHopperable;
import org.irmc.industrialrevival.api.objects.events.vanilla.EndermanMoveIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EntityChangeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EntityExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockBreakEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockFromToEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockPlaceEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.InventoryMoveIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.MenuOpenEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PistonExtendIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PistonRetractIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerBucketEmptyToIRBlockEvent;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

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
        if (event.isCancelled()) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityChangeIRBlock(EntityChangeIRBlockEvent event) {
        if (event.isCancelled()) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityExplodeIRBlock(EntityExplodeIRBlockEvent event) {
        if (event.isCancelled()) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryMoveIRItem(InventoryMoveIRItemEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (event.getDestination().getType().equals(InventoryType.HOPPER) && event.getIritem() instanceof NotHopperable) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onIRBlockBreak(IRBlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }

        IndustrialRevival.getInstance().getItemTextureService().blockBreaking(event);
        IndustrialRevival.getInstance().getDataManager().handleBlockBreaking(event.getBlock().getLocation());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onIRBlockFromTo(IRBlockFromToEvent event) {
        if (event.isCancelled()) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onIRBlockPlace(IRBlockPlaceEvent event) {
        if (event.isCancelled()) {
            return;
        }

        IndustrialRevival.getInstance().getItemTextureService().blockPlacing(event);
        IndustrialRevival.getInstance().getDataManager().handleBlockPlacing(event.getBlockPlaced().getLocation(), event.getIritem().getId());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMenuOpen(MenuOpenEvent event) {
        if (event.isCancelled()) {
            return;
        }

        event.getOpenedMenu().open(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPistonExtendIRBlock(PistonExtendIRBlockEvent event) {
        if (event.isCancelled()) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPistonRetractIRBlock(PistonRetractIRBlockEvent event) {
        if (event.isCancelled()) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBucketEmptyToIRBlock(PlayerBucketEmptyToIRBlockEvent event) {
        if (event.isCancelled()) {
            return;
        }

        event.setCancelled(true);
    }
}
