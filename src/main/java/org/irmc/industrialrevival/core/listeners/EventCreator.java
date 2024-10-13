package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.events.handler.BlockExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.handler.EndermanMoveIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.handler.EntityExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.handler.IRBlockBreakEvent;
import org.irmc.industrialrevival.api.objects.events.handler.IRBlockPlaceEvent;
import org.irmc.industrialrevival.api.objects.events.handler.IRItemDamageEntityEvent;
import org.irmc.industrialrevival.api.objects.events.handler.MenuOpenEvent;
import org.irmc.industrialrevival.api.objects.events.handler.PlayerLeftClickEvent;
import org.irmc.industrialrevival.api.objects.events.handler.PlayerRightClickEvent;
import org.irmc.industrialrevival.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class EventCreator implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerClick(PlayerInteractEvent e) {
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

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMenuOpen(PlayerRightClickEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            MachineMenu menu = DataUtil.getBlockData(e.getClickedBlock().getLocation()).getMachineMenu();
            if (menu != null) {
                MenuOpenEvent event = new MenuOpenEvent(e, menu);
                Bukkit.getServer().getPluginManager().callEvent(event);

                e.setCancelled(event.isCancelled());
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEndermanMoveIRBlockEvent(EntityChangeBlockEvent e) {
        if (e.getEntity().getType() == EntityType.ENDERMAN) {
            Location location = e.getBlock().getLocation();
            IRBlockData data = DataUtil.getBlockData(location);
            if (data == null) {
                return;
            }

            IndustrialRevivalItem item = IndustrialRevivalItem.getById(data.getId());
            if (item == null) {
                return;
            }

            if (item.isDisabledInWorld(location.getWorld())) {
                e.setCancelled(true);
                return;
            }

            EndermanMoveIRBlockEvent event = new EndermanMoveIRBlockEvent(e, item);
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityExplodeIRBlock(EntityExplodeEvent e) {
        List<EntityExplodeIRBlockEvent> events = new ArrayList<>();
        for (Block block : e.blockList()) {
            IRBlockData data = DataUtil.getBlockData(block.getLocation());
            if (data == null) {
                continue;
            }

            IndustrialRevivalItem item = IndustrialRevivalItem.getById(data.getId());
            if (item == null) {
                continue;
            }

            if (item.isDisabledInWorld(block.getWorld())) {
                continue;
            }

            EntityExplodeIRBlockEvent event = new EntityExplodeIRBlockEvent(e, item);
            events.add(event);
        }

        for (EntityExplodeIRBlockEvent event : events) {
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockExplodeIRBlock(BlockExplodeEvent e) {
        List<BlockExplodeIRBlockEvent> events = new ArrayList<>();
        for (Block block : e.blockList()) {
            IRBlockData data = DataUtil.getBlockData(block.getLocation());
            if (data == null) {
                continue;
            }
            IndustrialRevivalItem item = IndustrialRevivalItem.getById(data.getId());
            if (item == null) {
                continue;
            }

            if (item.isDisabledInWorld(block.getWorld())) {
                continue;
            }

            BlockExplodeIRBlockEvent event = new BlockExplodeIRBlockEvent(e, item);
            events.add(event);
        }

        for (BlockExplodeIRBlockEvent event : events) {
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onIRBlockBreak(BlockBreakEvent e) {
        IRBlockData data = DataUtil.getBlockData(e.getBlock().getLocation());
        if (data == null) {
            return;
        }

        IndustrialRevivalItem item = IndustrialRevivalItem.getById(data.getId());
        if (item == null) {
            return;
        }

        if (item.isDisabledInWorld(e.getBlock().getWorld())) {
            e.setCancelled(true);
            return;
        }

        IRBlockBreakEvent event = new IRBlockBreakEvent(e, item);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onIRBlockPlace(BlockPlaceEvent e) {
        Block block = e.getBlockPlaced();
        IRBlockData data = DataUtil.getBlockData(block.getLocation());
        if (data == null) {
            return;
        }

        IndustrialRevivalItem item = IndustrialRevivalItem.getById(data.getId());
        if (item == null) {
            return;
        }

        if (item.isDisabledInWorld(block.getWorld())) {
            e.setCancelled(true);
            return;
        }

        IRBlockPlaceEvent event = new IRBlockPlaceEvent(e, item);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
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

        IRItemDamageEntityEvent event = new IRItemDamageEntityEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }
}
