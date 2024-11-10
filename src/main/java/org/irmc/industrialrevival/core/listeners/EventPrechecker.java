package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.events.vanilla.BlockExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EndermanMoveIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EntityChangeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EntityExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EntityPickupIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockBreakEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockFromToEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockPlaceEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRItemBreakBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRItemDamageEntityEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRItemInteractEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRItemKillEntityEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.InventoryMoveIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.MenuOpenEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PistonExtendIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PistonRetractIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerBucketEmptyToIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerInteractIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareGrindstoneIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareIRItemEnchantEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareItemCraftIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareSmithingIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareTradeSelectIRItemEvent;
import org.irmc.industrialrevival.utils.DataUtil;

public class EventPrechecker implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void onBlockExplodeIRBlockEvent(BlockExplodeIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onEndermanMoveIRBlock(EndermanMoveIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onEntityChangeIRBlock(EntityChangeIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onEntityExplodeIRBlock(EntityExplodeIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getIritemLocation().getWorld())) {
            event.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onEntityPickupIRItem(EntityPickupIRItemEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getItem().getWorld())) {
            event.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onInventoryMoveIRItem(InventoryMoveIRItemEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getDestination().getLocation().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onIRBlockBreak(IRBlockBreakEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onIRBlockFromTo(IRBlockFromToEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getToBlock().getWorld())) {
            event.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onIRBlockPlace(IRBlockPlaceEvent event) {
        Location location = event.getBlockPlaced().getLocation();
        IRBlockData blockData = DataUtil.getBlockData(location);
        if (blockData != null) {
            event.setCancelled(true);
        }

        if (event.getIritem().isDisabledInWorld(event.getBlockPlaced().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onIRItemBreakBlock(IRItemBreakBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onIRItemDamageEntity(IRItemDamageEntityEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getEntity().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onIRItemInteract(IRItemInteractEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getPlayer().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onIRItemKillEntity(IRItemKillEntityEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getEntity().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onMenuOpen(MenuOpenEvent event) {
        if (!event.getPlayer().isOp()) {
            IndustrialRevivalItem iritem = IndustrialRevivalItem.getById(event.getOpenedMenu().getPreset().getId());
            if (iritem != null && iritem.isDisabledInWorld(event.getPlayer().getWorld())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPistonExtendIRBlock(PistonExtendIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPistonRetractIRBlock(PistonRetractIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerBucketEmptyToIRBlock(PlayerBucketEmptyToIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerInteractIRBlock(PlayerInteractIRBlockEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getClickedBlock().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPrepareGrindstoneIRItem(PrepareGrindstoneIRItemEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getInventory().getLocation().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPrepareIRItemEnchant(PrepareIRItemEnchantEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getInventory().getLocation().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPrepareItemCraftIRItem(PrepareItemCraftIRItemEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getInventory().getLocation().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPrepareSmithingIRItem(PrepareSmithingIRItemEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getInventory().getLocation().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPrepareTradeSelectIRItem(PrepareTradeSelectIRItemEvent event) {
        if (event.getIritem().isDisabledInWorld(event.getInventory().getLocation().getWorld())) {
            event.setCancelled(true);
        }
    }
}
