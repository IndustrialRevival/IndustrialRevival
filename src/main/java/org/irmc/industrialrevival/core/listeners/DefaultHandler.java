package org.irmc.industrialrevival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.irmc.industrialrevival.api.objects.events.handler.EndermanMoveIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.handler.EntityChangeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.handler.EntityExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.handler.IRBlockBreakEvent;
import org.irmc.industrialrevival.api.objects.events.handler.IRBlockFromToEvent;
import org.irmc.industrialrevival.api.objects.events.handler.IRBlockPlaceEvent;
import org.irmc.industrialrevival.api.objects.events.handler.MenuOpenEvent;
import org.irmc.industrialrevival.api.objects.events.handler.PistonExtendIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.handler.PistonRetractIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.handler.PlayerBucketEmptyToIRBlockEvent;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.DataUtil;

public class DefaultHandler implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockExplodeIRBlock(BlockExplodeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEndermanMoveIrBlock(EndermanMoveIRBlockEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityChangeIRBlock(EntityChangeIRBlockEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityExplodeIRBlock(EntityExplodeIRBlockEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onIRBlockBreak(IRBlockBreakEvent event) {
        DataUtil.removeBlockData(event.getBlock().getLocation());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onIRBlockFromTo(IRBlockFromToEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onIRBlockPlace(IRBlockPlaceEvent event) {
        IndustrialRevival.getInstance().getDataManager().handleBlockPlacing(event.getBlockPlaced().getLocation(), event.getIritem().getId());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMenuOpen(MenuOpenEvent event) {
        event.getOpenedMenu().open(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPistonExtendIRBlock(PistonExtendIRBlockEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPistonRetractIRBlock(PistonRetractIRBlockEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBucketEmptyToIRBlock(PlayerBucketEmptyToIRBlockEvent event) {
        event.setCancelled(true);
    }
}
