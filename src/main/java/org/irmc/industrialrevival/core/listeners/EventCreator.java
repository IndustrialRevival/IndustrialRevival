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
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.events.vanilla.BlockExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EndermanMoveIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EntityExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EntityPickupIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockBreakEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockPlaceEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRItemDamageEntityEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.InventoryMoveIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.MenuOpenEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerBucketEmptyToIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerLeftClickEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerRightClickEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareAnvilIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareGrindstoneIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareIRItemEnchantEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareSmithingIRItemEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PrepareTradeSelectIRItemEvent;
import org.irmc.industrialrevival.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates the events and calls the corresponding event handlers.
 *
 * @see HandlerCaller
 * @see DefaultHandler
 * @author balugaq
 */
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

            e.setCancelled(event.isCancelled());
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
            e.setCancelled(event.isCancelled());
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
            e.setCancelled(event.isCancelled());
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
        e.setCancelled(event.isCancelled());
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

        e.setCancelled(event.isCancelled());
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

        e.setCancelled(event.isCancelled());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent e) {
        Location location = e.getBlockClicked().getLocation();
        IRBlockData data = DataUtil.getBlockData(location);
        if (data == null) {
            return;
        }

        IndustrialRevivalItem item = IndustrialRevivalItem.getById(data.getId());
        if (item == null) {
            return;
        }

        if (item.isDisabledInWorld(location.getWorld())) {
            return;
        }

        PlayerBucketEmptyToIRBlockEvent event = new PlayerBucketEmptyToIRBlockEvent(e, item);
        Bukkit.getServer().getPluginManager().callEvent(event);

        e.setCancelled(event.isCancelled());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPrepareItemEnchant(PrepareItemEnchantEvent e) {
        ItemStack itemStack = e.getItem();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
        if (iritem == null) {
            return;
        }

        if (iritem.isDisabledInWorld(e.getEnchantBlock().getWorld())) {
            e.setCancelled(true);
            return;
        }

        PrepareIRItemEnchantEvent event = new PrepareIRItemEnchantEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);

        e.setCancelled(event.isCancelled());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPrepareSmithingIRItem(PrepareSmithingEvent e) {
        ItemStack itemStack = e.getResult();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
        if (iritem == null) {
            return;
        }

        if (iritem.isDisabledInWorld(e.getInventory().getLocation().getWorld())) {
            e.setResult(null);
            return;
        }

        PrepareSmithingIRItemEvent event = new PrepareSmithingIRItemEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPrepareGrindstone(PrepareGrindstoneEvent e) {
        ItemStack itemStack = e.getResult();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
        if (iritem == null) {
            return;
        }

        if (iritem.isDisabledInWorld(e.getInventory().getLocation().getWorld())) {
            e.setResult(null);
            return;
        }

        PrepareGrindstoneIRItemEvent event = new PrepareGrindstoneIRItemEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPrepareTradeSelect(TradeSelectEvent e) {
        MerchantRecipe recipe = e.getMerchant().getRecipe(e.getIndex());
        List<ItemStack> testItems = new ArrayList<>();
        testItems.addAll(recipe.getIngredients());
        testItems.add(recipe.getResult());
        for (ItemStack testItem : testItems) {
            IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(testItem);
            if (iritem == null) {
                continue;
            }
            if (iritem.isDisabledInWorld(e.getInventory().getLocation().getWorld())) {
                e.setCancelled(true);
                return;
            }

            PrepareTradeSelectIRItemEvent event = new PrepareTradeSelectIRItemEvent(e, recipe, iritem);
            Bukkit.getServer().getPluginManager().callEvent(event);

            e.setCancelled(event.isCancelled());
            if (event.isCancelled()) {
                break;
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPrepareAnvil(PrepareAnvilEvent e) {
        ItemStack itemStack = e.getResult();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
        if (iritem == null) {
            return;
        }

        if (iritem.isDisabledInWorld(e.getInventory().getLocation().getWorld())) {
            e.setResult(null);
            return;
        }

        PrepareAnvilIRItemEvent event = new PrepareAnvilIRItemEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityPickupIRItem(EntityPickupItemEvent e) {
        ItemStack itemStack = e.getItem().getItemStack();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
        if (iritem == null) {
            return;
        }

        if (iritem.isDisabledInWorld(e.getEntity().getWorld())) {
            return;
        }

        EntityPickupIRItemEvent event = new EntityPickupIRItemEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);

        e.setCancelled(event.isCancelled());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryMoveItem(InventoryMoveItemEvent e) {
        ItemStack itemStack = e.getItem();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
        if (iritem == null) {
            return;
        }
        if (iritem.isDisabledInWorld(e.getDestination().getLocation().getWorld())) {
            e.setCancelled(true);
            return;
        }

        InventoryMoveIRItemEvent event = new InventoryMoveIRItemEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);

        e.setCancelled(event.isCancelled());
    }
}