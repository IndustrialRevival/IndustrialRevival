package org.irmc.industrialrevival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.inventory.InventoryType;
import org.irmc.industrialrevival.api.items.attributes.NotHopperable;
import org.irmc.industrialrevival.api.objects.events.handler.EndermanMoveIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.handler.EntityChangeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.handler.EntityExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.handler.IRBlockBreakEvent;
import org.irmc.industrialrevival.api.objects.events.handler.IRBlockFromToEvent;
import org.irmc.industrialrevival.api.objects.events.handler.IRBlockPlaceEvent;
import org.irmc.industrialrevival.api.objects.events.handler.InventoryMoveIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.handler.MenuOpenEvent;
import org.irmc.industrialrevival.api.objects.events.handler.PistonExtendIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.handler.PistonRetractIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.handler.PlayerBucketEmptyToIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.handler.PrepareAnvilIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.handler.PrepareGrindstoneIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.handler.PrepareIRItemEnchantEvent;
import org.irmc.industrialrevival.api.objects.events.handler.PrepareItemCraftIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.handler.PrepareSmithingIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.handler.PrepareTradeSelectIRItemEvent;
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
    public void onInventoryMoveIRItem(InventoryMoveIRItemEvent event) {
        if (event.getDestination().getType().equals(InventoryType.HOPPER) && event.getIritem() instanceof NotHopperable) {
            event.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onIRBlockBreak(IRBlockBreakEvent event) {
        IndustrialRevival.getInstance().getItemTextureService().blockBreaking(event);
        IndustrialRevival.getInstance().getDataManager().handleBlockBreaking(event.getBlock().getLocation());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onIRBlockFromTo(IRBlockFromToEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onIRBlockPlace(IRBlockPlaceEvent event) {
        IndustrialRevival.getInstance().getItemTextureService().blockPlacing(event);
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrepareAnvilIRItem(PrepareAnvilIRItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrepareGrindstoneIRItem(PrepareGrindstoneIRItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrepareIRItemEnchant(PrepareIRItemEnchantEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrepareItemCraftIrItem(PrepareItemCraftIRItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrepareSmithingIRItem(PrepareSmithingIRItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrepareTradeSelectIRItem(PrepareTradeSelectIRItemEvent event) {
        event.setCancelled(true);
    }
}
