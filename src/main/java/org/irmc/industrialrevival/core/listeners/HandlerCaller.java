package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.handlers.BlockBreakHandler;
import org.irmc.industrialrevival.api.items.handlers.BlockExplodeHandler;
import org.irmc.industrialrevival.api.items.handlers.BlockFromToHandler;
import org.irmc.industrialrevival.api.items.handlers.BlockPistonExtendHandler;
import org.irmc.industrialrevival.api.items.handlers.BlockPistonRetractHandler;
import org.irmc.industrialrevival.api.items.handlers.BlockPlaceHandler;
import org.irmc.industrialrevival.api.items.handlers.EndermanMoveBlockHandler;
import org.irmc.industrialrevival.api.items.handlers.EntityChangeBlockHandler;
import org.irmc.industrialrevival.api.items.handlers.EntityExplodeHandler;
import org.irmc.industrialrevival.api.items.handlers.EntityPickupHandler;
import org.irmc.industrialrevival.api.items.handlers.InventoryMoveHandler;
import org.irmc.industrialrevival.api.items.handlers.ItemDamageEntityHandler;
import org.irmc.industrialrevival.api.items.handlers.ItemHandler;
import org.irmc.industrialrevival.api.items.handlers.ItemInteractHandler;
import org.irmc.industrialrevival.api.items.handlers.ItemKillEntityHandler;
import org.irmc.industrialrevival.api.items.handlers.PlayerBucketEmptyHandler;
import org.irmc.industrialrevival.api.items.handlers.PrepareAnvilHandler;
import org.irmc.industrialrevival.api.items.handlers.PrepareGrindstoneHandler;
import org.irmc.industrialrevival.api.items.handlers.PrepareItemCraftHandler;
import org.irmc.industrialrevival.api.items.handlers.PrepareItemEnchantHandler;
import org.irmc.industrialrevival.api.items.handlers.PrepareSmithingHandler;
import org.irmc.industrialrevival.api.items.handlers.PrepareTradeSelectHandler;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.objects.events.vanilla.BlockExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EndermanMoveIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EntityChangeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EntityExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EntityPickupIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockBreakEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockFromToEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockPlaceEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRItemDamageEntityEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRItemInteractEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRItemKillEntityEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.InventoryMoveIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.MenuCloseEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.MenuOpenEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PistonExtendIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PistonRetractIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerBucketEmptyToIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareAnvilIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareGrindstoneIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareIRItemEnchantEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareItemCraftIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareSmithingIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareTradeSelectIRItemEvent;
import org.jetbrains.annotations.Nullable;

/**
 * This class is used to call {@link ItemHandler}
 *
 * @author balugaq
 * @see EventCreator
 * @see DefaultHandler
 */
public class HandlerCaller implements Listener {
    // todo: add more event handlers
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockExplodeIRBlock(BlockExplodeIRBlockEvent e) {
        IndustrialRevivalItem iritem = e.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        BlockExplodeHandler handler = iritem.getItemHandler(BlockExplodeHandler.class);
        if (handler != null) {
            handler.onBlockExplode(e);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEndermanMoveIRBlock(EndermanMoveIRBlockEvent e) {
        IndustrialRevivalItem iritem = e.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        EndermanMoveBlockHandler handler = iritem.getItemHandler(EndermanMoveBlockHandler.class);
        if (handler != null) {
            handler.onEndermanMoveBlock(e);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityChangeBlock(EntityChangeIRBlockEvent e) {
        IndustrialRevivalItem iritem = e.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        EntityChangeBlockHandler handler = iritem.getItemHandler(EntityChangeBlockHandler.class);
        if (handler != null) {
            handler.onEntityChangeBlock(e);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityExplodeIRBlock(EntityExplodeIRBlockEvent e) {
        IndustrialRevivalItem iritem = e.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        EntityExplodeHandler handler = iritem.getItemHandler(EntityExplodeHandler.class);
        if (handler != null) {
            handler.onEntityExplode(e);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityPickupIRItem(EntityPickupIRItemEvent e) {
        IndustrialRevivalItem iritem = e.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        EntityPickupHandler handler = iritem.getItemHandler(EntityPickupHandler.class);
        if (handler != null) {
            handler.onEntityPickup(e);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onInventoryMoveIRItem(InventoryMoveIRItemEvent e) {
        IndustrialRevivalItem iritem = e.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        InventoryMoveHandler handler = iritem.getItemHandler(InventoryMoveHandler.class);
        if (handler != null) {
            handler.onInventoryMove(e);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onIRBlockBreak(IRBlockBreakEvent e) {
        IndustrialRevivalItem iritem = e.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        BlockBreakHandler handler = iritem.getItemHandler(BlockBreakHandler.class);
        if (handler != null) {
            handler.onBlockBreak(e);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onIRBlockFromTo(IRBlockFromToEvent e) {
        IndustrialRevivalItem iritem = e.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        BlockFromToHandler handler = iritem.getItemHandler(BlockFromToHandler.class);
        if (handler != null) {
            handler.onBlockFromTo(e);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onIRBlockPlace(IRBlockPlaceEvent event) {
        IndustrialRevivalItem iritem = event.getIritem();
        if (!checkValid(iritem, event.getBlock().getWorld())) {
            return;
        }

        BlockPlaceHandler handler = iritem.getItemHandler(BlockPlaceHandler.class);

        if (handler != null) {
            handler.onBlockPlace(event);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onIRItemDamageEntityEvent(IRItemDamageEntityEvent e) {
        IndustrialRevivalItem iritem = e.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        ItemDamageEntityHandler handler = iritem.getItemHandler(ItemDamageEntityHandler.class);
        if (handler != null) {
            handler.onHit(e);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onIRItemInteract(IRItemInteractEvent e) {
        IndustrialRevivalItem iritem = e.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        ItemInteractHandler handler = iritem.getItemHandler(ItemInteractHandler.class);
        if (handler != null) {
            handler.onInteract(e);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onIRItemKillEntity(IRItemKillEntityEvent e) {
        IndustrialRevivalItem iritem = e.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        ItemKillEntityHandler handler = iritem.getItemHandler(ItemKillEntityHandler.class);
        if (handler != null) {
            handler.onKill(e);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onMenuClose(MenuCloseEvent e) {
        SimpleMenu.MenuCloseHandler handler = e.getMenu().getCloseHandler();
        if (handler != null) {
            handler.onClose((Player) e.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onMenuOpen(MenuOpenEvent e) {
        MachineMenu menu = e.getOpenedMenu();
        SimpleMenu.MenuOpenHandler handler = menu.getOpenHandler();
        if (handler != null) {
            handler.onOpen(e.getPlayer(), menu);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPistonExtendIRBlock(PistonExtendIRBlockEvent event) {
        BlockPistonExtendHandler handler = event.getIritem().getItemHandler(BlockPistonExtendHandler.class);
        if (handler != null) {
            boolean pass = handler.onPistonExtend(event);
            if (!pass) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPistonRetractIRBlock(PistonRetractIRBlockEvent event) {
        BlockPistonRetractHandler handler = event.getIritem().getItemHandler(BlockPistonRetractHandler.class);
        if (handler != null) {
            boolean pass = handler.onPistonRetract(event);
            if (!pass) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerBucketEmptyToIRBlock(PlayerBucketEmptyToIRBlockEvent event) {
        IndustrialRevivalItem iritem = event.getIritem();
        if (!checkValid(iritem, event.getBlock().getWorld())) {
            return;
        }

        PlayerBucketEmptyHandler handler = iritem.getItemHandler(PlayerBucketEmptyHandler.class);
        if (handler != null) {
            handler.onPlayerBucketEmpty(event);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPrepareAnvilIRItem(PrepareAnvilIRItemEvent event) {
        IndustrialRevivalItem iritem = event.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        PrepareAnvilHandler handler = iritem.getItemHandler(PrepareAnvilHandler.class);
        if (handler != null) {
            handler.onPrepareAnvil(event);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPrepareGrindstoneIRItem(PrepareGrindstoneIRItemEvent event) {
        IndustrialRevivalItem iritem = event.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        PrepareGrindstoneHandler handler = iritem.getItemHandler(PrepareGrindstoneHandler.class);
        if (handler != null) {
            handler.onPrepareGrindstone(event);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPrepareIRItemCraft(PrepareItemCraftIRItemEvent event) {
        IndustrialRevivalItem iritem = event.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        PrepareItemCraftHandler handler = iritem.getItemHandler(PrepareItemCraftHandler.class);
        if (handler != null) {
            handler.onPrepareItemCraft(event);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPrepareIRItemEnchant(PrepareIRItemEnchantEvent event) {
        IndustrialRevivalItem iritem = event.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        PrepareItemEnchantHandler handler = iritem.getItemHandler(PrepareItemEnchantHandler.class);
        if (handler != null) {
            handler.onPrepareEnchant(event);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPrepareSmithingIRItem(PrepareSmithingIRItemEvent event) {
        IndustrialRevivalItem iritem = event.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        PrepareSmithingHandler handler = iritem.getItemHandler(PrepareSmithingHandler.class);
        if (handler != null) {
            handler.onPrepareSmithing(event);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPrepareTradeSelect(PrepareTradeSelectIRItemEvent event) {
        IndustrialRevivalItem iritem = event.getIritem();
        if (!checkValid(iritem)) {
            return;
        }

        PrepareTradeSelectHandler handler = iritem.getItemHandler(PrepareTradeSelectHandler.class);
        if (handler != null) {
            handler.onPrepareTradeSelect(event);
        }
    }

    private boolean checkValid(@Nullable IndustrialRevivalItem iritem) {
        return iritem != null && !iritem.isDisabled();
    }

    private boolean checkValid(@Nullable IndustrialRevivalItem iritem, World world) {
        return iritem != null && !iritem.isDisabledInWorld(world);
    }

    private boolean checkValid(@Nullable IndustrialRevivalItem iritem, Block block) {
        return iritem != null && !iritem.isDisabledInWorld(block.getWorld());
    }

    private boolean checkValid(@Nullable IndustrialRevivalItem iritem, Location location) {
        return iritem != null && !iritem.isDisabledInWorld(location.getWorld());
    }
}
