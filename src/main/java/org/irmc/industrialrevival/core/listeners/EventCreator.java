package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
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
import org.irmc.industrialrevival.api.objects.events.vanilla.MenuCloseEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.MenuOpenEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PistonExtendIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PistonRetractIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerBucketEmptyToIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerInteractIRBlockEvent;
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
 * @author balugaq
 * @see HandlerCaller
 * @see DefaultHandler
 */
public class EventCreator implements Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
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

            BlockExplodeIRBlockEvent event = new BlockExplodeIRBlockEvent(e, block.getLocation(), item);
            events.add(event);
        }

        for (BlockExplodeIRBlockEvent event : events) {
            Bukkit.getServer().getPluginManager().callEvent(event);
            
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
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

            EndermanMoveIRBlockEvent event = new EndermanMoveIRBlockEvent(e, item);
            Bukkit.getServer().getPluginManager().callEvent(event);

            
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityChangeIRBlock(EntityChangeBlockEvent e) {
        Location location = e.getBlock().getLocation();
        IRBlockData data = DataUtil.getBlockData(location);
        if (data == null) {
            return;
        }
        IndustrialRevivalItem item = IndustrialRevivalItem.getById(data.getId());
        if (item == null) {
            return;
        }

        EntityChangeIRBlockEvent event = new EntityChangeIRBlockEvent(e, item);
        Bukkit.getServer().getPluginManager().callEvent(event);

        
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
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

            EntityExplodeIRBlockEvent event = new EntityExplodeIRBlockEvent(e, block.getLocation(), item);
            events.add(event);
        }

        for (EntityExplodeIRBlockEvent event : events) {
            Bukkit.getServer().getPluginManager().callEvent(event);
            if (event.getOriginalEvent().isCancelled()) {
                e.blockList().remove(event.getOriginalEvent().getLocation().getBlock());
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityPickupIRItem(EntityPickupItemEvent e) {
        ItemStack itemStack = e.getItem().getItemStack();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
        if (iritem == null) {
            return;
        }

        EntityPickupIRItemEvent event = new EntityPickupIRItemEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);

        
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onInventoryMoveItem(InventoryMoveItemEvent e) {
        ItemStack itemStack = e.getItem();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
        if (iritem == null) {
            return;
        }

        InventoryMoveIRItemEvent event = new InventoryMoveIRItemEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);

        
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onIRBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (block == null) {
            return;
        }
        IRBlockData data = DataUtil.getBlockData(block.getLocation());
        if (data == null) {
            return;
        }

        IndustrialRevivalItem item = IndustrialRevivalItem.getById(data.getId());
        if (item == null) {
            return;
        }

        IRBlockBreakEvent event = new IRBlockBreakEvent(e, item);
        Bukkit.getServer().getPluginManager().callEvent(event);
        
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onIRBlockFromTo(BlockFromToEvent e) {
        Location location = e.getBlock().getLocation();
        IRBlockData data = DataUtil.getBlockData(location);
        if (data == null) {
            return;
        }

        IndustrialRevivalItem item = IndustrialRevivalItem.getById(data.getId());
        if (item == null) {
            return;
        }

        IRBlockFromToEvent event;
        if (e.getFace() != BlockFace.SELF) {
            event = new IRBlockFromToEvent(e, e.getBlock(), e.getFace(), item);
        } else {
            event = new IRBlockFromToEvent(e, e.getBlock(), e.getToBlock(), item);
        }
        Bukkit.getServer().getPluginManager().callEvent(event);

        
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onIRBlockPlace(BlockPlaceEvent e) {
        ItemStack itemStack = e.getItemInHand();
        IndustrialRevivalItem item = IndustrialRevivalItem.getByItem(itemStack);
        if (item != null) {
            IRBlockPlaceEvent event = new IRBlockPlaceEvent(e, item);
            Bukkit.getServer().getPluginManager().callEvent(event);

            
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onIRItemBreakBlock(BlockBreakEvent e) {
        ItemStack itemInHand = e.getPlayer().getItemInHand();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemInHand);
        if (iritem == null) {
            return;
        }

        IRItemBreakBlockEvent event = new IRItemBreakBlockEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);

        
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onIRItemDamageEntity(EntityDamageByEntityEvent e) {
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

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onIRItemInteract(PlayerInteractEvent e) {
        ItemStack itemInHand = e.getPlayer().getItemInHand();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemInHand);
        if (iritem == null) {
            return;
        }

        IRItemInteractEvent event = new IRItemInteractEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onIRItemKillEntity(EntityDeathEvent e) {
        Entity entity = e.getDamageSource().getCausingEntity();
        if (entity instanceof Player player) {
            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemInHand);
            if (iritem == null) {
                return;
            }

            IRItemKillEntityEvent event = new IRItemKillEntityEvent(e, iritem);
            Bukkit.getServer().getPluginManager().callEvent(event);

            
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onMenuClose(InventoryCloseEvent e) {
        Location location = e.getInventory().getLocation();
        IRBlockData data = DataUtil.getBlockData(location);
        if (data == null) {
            return;
        }
        MachineMenu menu = data.getMachineMenu();
        if (menu == null) {
            return;
        }

        MenuCloseEvent event = new MenuCloseEvent(e, menu);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onMenuOpen(PlayerRightClickEvent e) {
        if (e.getOriginalEvent().getAction() == Action.RIGHT_CLICK_BLOCK) {
            IRBlockData data = DataUtil.getBlockData(e.getOriginalEvent().getClickedBlock().getLocation());
            if (data == null) {
                return;
            }
            MachineMenu menu = data.getMachineMenu();
            if (menu != null) {
                MenuOpenEvent event = new MenuOpenEvent(e, menu);
                Bukkit.getServer().getPluginManager().callEvent(event);

                
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPistonExtendIRBlock(BlockPistonExtendEvent e) {
        List<PistonExtendIRBlockEvent> events = new ArrayList<>();
        for (Block block : e.getBlocks()) {
            IRBlockData data = DataUtil.getBlockData(block.getLocation());
            if (data == null) {
                continue;
            }

            IndustrialRevivalItem item = IndustrialRevivalItem.getById(data.getId());
            if (item == null) {
                continue;
            }

            PistonExtendIRBlockEvent event = new PistonExtendIRBlockEvent(e, item);
            events.add(event);
        }

        for (PistonExtendIRBlockEvent event : events) {
            Bukkit.getServer().getPluginManager().callEvent(event);
            
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPistonRetractIRBlock(BlockPistonRetractEvent e) {
        List<PistonRetractIRBlockEvent> events = new ArrayList<>();
        for (Block block : e.getBlocks()) {
            IRBlockData data = DataUtil.getBlockData(block.getLocation());
            if (data == null) {
                continue;
            }

            IndustrialRevivalItem item = IndustrialRevivalItem.getById(data.getId());
            if (item == null) {
                continue;
            }

            PistonRetractIRBlockEvent event = new PistonRetractIRBlockEvent(e, item);
            events.add(event);
        }

        for (PistonRetractIRBlockEvent event : events) {
            Bukkit.getServer().getPluginManager().callEvent(event);
            
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
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

        PlayerBucketEmptyToIRBlockEvent event = new PlayerBucketEmptyToIRBlockEvent(e, item);
        Bukkit.getServer().getPluginManager().callEvent(event);

        
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerInteractIRBlock(PlayerInteractEvent e) {
        Block block = e.getClickedBlock();
        if (block == null) {
            return;
        }

        IRBlockData data = DataUtil.getBlockData(block.getLocation());
        if (data == null) {
            return;
        }

        IndustrialRevivalItem item = IndustrialRevivalItem.getById(data.getId());
        if (item == null) {
            return;
        }

        PlayerInteractIRBlockEvent event = new PlayerInteractIRBlockEvent(e, item);
        Bukkit.getServer().getPluginManager().callEvent(event);

        
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerLeftClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) {
            PlayerLeftClickEvent event = new PlayerLeftClickEvent(e);
            Bukkit.getServer().getPluginManager().callEvent(event);

            
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            PlayerRightClickEvent event = new PlayerRightClickEvent(e);
            Bukkit.getServer().getPluginManager().callEvent(event);

            
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPrepareAnvilIRItem(PrepareAnvilEvent e) {
        ItemStack itemStack = e.getResult();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
        if (iritem == null) {
            return;
        }

        PrepareAnvilIRItemEvent event = new PrepareAnvilIRItemEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPrepareGrindstoneIRItem(PrepareGrindstoneEvent e) {
        ItemStack itemStack = e.getResult();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
        if (iritem == null) {
            return;
        }

        PrepareGrindstoneIRItemEvent event = new PrepareGrindstoneIRItemEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPrepareItemEnchantIRItem(PrepareItemEnchantEvent e) {
        ItemStack itemStack = e.getItem();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
        if (iritem == null) {
            return;
        }

        PrepareIRItemEnchantEvent event = new PrepareIRItemEnchantEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);

        
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPrepareSmithingIRItem(PrepareSmithingEvent e) {
        ItemStack itemStack = e.getResult();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(itemStack);
        if (iritem == null) {
            return;
        }

        PrepareSmithingIRItemEvent event = new PrepareSmithingIRItemEvent(e, iritem);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPrepareTradeSelectIRItem(TradeSelectEvent e) {
        MerchantRecipe recipe = e.getMerchant().getRecipe(e.getIndex());
        List<ItemStack> testItems = new ArrayList<>();
        testItems.addAll(recipe.getIngredients());
        testItems.add(recipe.getResult());
        for (ItemStack testItem : testItems) {
            IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(testItem);
            if (iritem == null) {
                continue;
            }

            PrepareTradeSelectIRItemEvent event = new PrepareTradeSelectIRItemEvent(e, recipe, iritem);
            Bukkit.getServer().getPluginManager().callEvent(event);

            
            if (event.isCancelled()) {
                break;
            }
        }
    }
}