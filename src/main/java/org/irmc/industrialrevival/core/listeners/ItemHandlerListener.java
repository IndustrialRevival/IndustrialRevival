package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.handlers.BlockBreakHandler;
import org.irmc.industrialrevival.api.items.handlers.BlockPlaceHandler;
import org.irmc.industrialrevival.api.items.handlers.BlockUseHandler;
import org.irmc.industrialrevival.api.items.handlers.UseItemInteractHandler;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.core.IndustrialRevival;

public class ItemHandlerListener extends AbstractIRListener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (item instanceof IndustrialRevivalItemStack iris) {
            String id = iris.getId();
            IndustrialRevivalItem iritem = IndustrialRevivalItem.getById(id);
            UseItemInteractHandler handler = iritem.getItemHandler(UseItemInteractHandler.class);
            if (handler != null) {
                handler.onInteract(e);
            }
        }
    }

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItemInHand();
        if (item instanceof IndustrialRevivalItemStack iris) {
            String id = iris.getId();
            IndustrialRevivalItem iritem = IndustrialRevivalItem.getById(id);
            BlockPlaceHandler handler = iritem.getItemHandler(BlockPlaceHandler.class);
            if (handler != null) {
                handler.onBlockPlace(player, e.getBlockPlaced(), false);
            }

            IndustrialRevival.getInstance().getItemTextureService().blockPlacing(e);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        IRBlockData blockData = IndustrialRevival.getInstance()
                .getBlockDataService()
                .getBlockData(e.getBlock().getLocation());
        if (blockData != null) {
            String id = blockData.getId();
            IndustrialRevivalItem iritem = IndustrialRevivalItem.getById(id);
            BlockBreakHandler handler = iritem.getItemHandler(BlockBreakHandler.class);
            if (handler != null) {
                handler.onBlockBreak(player, e.getBlock(), false);
            }

            IndustrialRevival.getInstance().getItemTextureService().blockBreaking(e);
        }
    }

    @EventHandler
    public void onBlockUse(PlayerInteractEvent e) {
        if (e.getAction().isRightClick()) {
            Block block = e.getClickedBlock();
            if (block != null) {
                Location location = block.getLocation();
                IRBlockData blockData = IndustrialRevival.getInstance()
                        .getBlockDataService()
                        .getBlockData(location);
                if (blockData != null) {
                    String id = blockData.getId();
                    IndustrialRevivalItem iritem = IndustrialRevivalItem.getById(id);
                    BlockUseHandler handler = iritem.getItemHandler(BlockUseHandler.class);
                    if (handler != null) {
                        handler.onRightClick(e);
                    }
                }
            }
        }
    }
}
