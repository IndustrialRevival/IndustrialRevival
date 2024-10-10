package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.InventoryBlock;
import org.irmc.industrialrevival.api.items.handlers.BlockInteractHandler;
import org.irmc.industrialrevival.api.items.handlers.EntityChangeBlockHandler;
import org.irmc.industrialrevival.api.items.handlers.ItemKillEntityHandler;
import org.irmc.industrialrevival.api.items.handlers.ItemBreakBlockHandler;
import org.irmc.industrialrevival.api.items.handlers.ItemInteractHandler;
import org.irmc.industrialrevival.api.items.handlers.ItemDamageEntityHandler;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.util.ArrayList;

public class ItemHandlerListener implements Listener {
    @EventHandler
    public void onItemInteract(PlayerInteractEvent e) {
        ItemStack itemInHand = e.getItem();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemInHand);
        if (iritem == null) {
            return;
        }

        ItemInteractHandler handler = iritem.getItemHandler(ItemInteractHandler.class);
        if (handler != null) {
            handler.onInteract(e);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockInteract(PlayerInteractEvent e) {
        if (!e.getAction().isRightClick()) {
            return;
        }

        Block block = e.getClickedBlock();
        if (block == null) {
            return;
        }

        Location location = block.getLocation();
        IRBlockData blockData = IndustrialRevival.getInstance().getBlockDataService().getBlockData(location);
        if (blockData == null) {
            return;
        }

        String id = blockData.getId();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getById(id);
        if (iritem == null) {
            return;
        }

        BlockInteractHandler handler = iritem.getItemHandler(BlockInteractHandler.class);
        if (handler != null) {
            handler.onBlockUse(e);
        }

        if (iritem instanceof InventoryBlock) {
            MachineMenu menu = blockData.getMachineMenu();
            if (menu != null) {
                menu.open(e.getPlayer());
            }
        }
    }

    @EventHandler
    public void onItemBreakBlock(BlockBreakEvent e) {
        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(item);
        if (iritem == null) {
            return;
        }

        ItemBreakBlockHandler handler = iritem.getItemHandler(ItemBreakBlockHandler.class);
        if (handler != null) {
            handler.onToolUse(e, item, new ArrayList<>(e.getBlock().getDrops()));
        }
    }

    @EventHandler
    public void onItemDamageEntity(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player player)) {
            return;
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemInHand);
        if (iritem == null) {
            return;
        }

        Material material = itemInHand.getType();
        boolean isSword = Tag.ITEMS_SWORDS.isTagged(material);
        if (!isSword) {
            return;
        }

        ItemDamageEntityHandler handler = iritem.getItemHandler(ItemDamageEntityHandler.class);
        if (handler != null) {
            handler.onHit(e, player, itemInHand);
        }
    }

    @EventHandler
    public void onItemKillEntity(EntityDeathEvent e) {
        Entity killer = e.getEntity().getKiller();
        if (!(killer instanceof Player player)) {
            return;
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemInHand);
        if (iritem == null) {
            return;
        }

        Material material = itemInHand.getType();
        boolean isSword = Tag.ITEMS_SWORDS.isTagged(material);
        if (!isSword) {
            return;
        }

        ItemKillEntityHandler handler = iritem.getItemHandler(ItemKillEntityHandler.class);
        if (handler != null) {
            handler.onKill(e, player, iritem);
        }
    }
}
