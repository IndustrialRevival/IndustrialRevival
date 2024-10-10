package org.irmc.industrialrevival.core.listeners;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.ItemDroppable;
import org.irmc.industrialrevival.api.items.handlers.BlockBreakHandler;
import org.irmc.industrialrevival.api.items.handlers.BlockPistonRetractHandler;
import org.irmc.industrialrevival.api.items.handlers.BlockPlaceHandler;
import org.irmc.industrialrevival.api.items.handlers.BlockPistonExtendHandler;
import org.irmc.industrialrevival.api.items.handlers.EntityChangeBlockHandler;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

public class BlockListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        IndustrialRevivalItem iritem = IndustrialRevivalItem.getByItem(item);
        if (iritem != null) {
            String id = iritem.getId();

            Block block = event.getBlockPlaced();

            BlockPlaceHandler handler = iritem.getItemHandler(BlockPlaceHandler.class);

            if (handler != null) {
                handler.onBlockPlace(event);
            }

            IndustrialRevival.getInstance().getItemTextureService().blockPlacing(event);
            IndustrialRevival.getInstance().getDataManager().handleBlockPlacing(block.getLocation(), id);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        IRBlockData blockData = IndustrialRevival.getInstance()
                .getBlockDataService()
                .getBlockData(e.getBlock().getLocation());
        if (blockData != null) {
            String id = blockData.getId();
            IndustrialRevivalItem iritem = IndustrialRevivalItem.getById(id);
            if (iritem == null) {
                return;
            }
            Block block = e.getBlock();

            BlockBreakHandler handler = iritem.getItemHandler(BlockBreakHandler.class);
            if (handler != null) {
                handler.onBlockBreak(e);
            }

            e.setDropItems(false);
            if (!(iritem instanceof ItemDroppable)) {
                World world = block.getWorld();
                world.dropItemNaturally(block.getLocation(), iritem.getItem().clone());
            }

            IndustrialRevival.getInstance().getItemTextureService().blockBreaking(e);
            IndustrialRevival.getInstance().getDataManager().handleBlockBreaking(block.getLocation());
        }
    }

    public void onPistonExtend(BlockPistonExtendEvent event) {
        IRBlockData blockData = IndustrialRevival.getInstance()
                .getBlockDataService()
                .getBlockData(event.getBlock().getLocation());
        if (blockData == null) {
            return;
        }

        IndustrialRevivalItem iritem = IndustrialRevivalItem.getById(blockData.getId());
        if (iritem == null) {
            return;
        }

        BlockPistonExtendHandler handler = iritem.getItemHandler(BlockPistonExtendHandler.class);
        if (handler != null) {
            boolean pass = handler.onPistonExtend(event, iritem, blockData);
            if (!pass) {
                event.setCancelled(true);
            }
        }
    }

    public void onPistonRetract(BlockPistonRetractEvent event) {
        IRBlockData blockData = IndustrialRevival.getInstance()
                .getBlockDataService()
                .getBlockData(event.getBlock().getLocation());
        if (blockData == null) {
            return;
        }

        IndustrialRevivalItem iritem = IndustrialRevivalItem.getById(blockData.getId());
        if (iritem == null) {
            return;
        }

        BlockPistonRetractHandler handler = iritem.getItemHandler(BlockPistonRetractHandler.class);
        if (handler != null) {
            boolean pass = handler.onPistonRetract(event, iritem, blockData);
            if (!pass) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent e) {
        IRBlockData blockData = IndustrialRevival.getInstance().getBlockDataService().getBlockData(e.getBlock().getLocation());
        if (blockData == null) {
            return;
        }

        IndustrialRevivalItem iritem = IndustrialRevivalItem.getById(blockData.getId());
        if (iritem == null) {
            return;
        }

        EntityChangeBlockHandler handler = iritem.getItemHandler(EntityChangeBlockHandler.class);
        if (handler != null) {
            handler.onEntityChangeBlock(e, iritem, blockData);
        }
    }
}
