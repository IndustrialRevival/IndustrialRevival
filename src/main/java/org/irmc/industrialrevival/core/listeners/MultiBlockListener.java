package org.irmc.industrialrevival.core.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.multiblock.MultiBlock;
import org.irmc.industrialrevival.api.multiblock.Structure;
import org.irmc.industrialrevival.api.multiblock.piece.MaterialStructurePiece;
import org.irmc.industrialrevival.api.multiblock.piece.StructurePiece;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

public class MultiBlockListener implements Listener {
    @EventHandler
    public void onInteractMultiBlockCore(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }

        Set<MultiBlock> matched = new HashSet<>();
        Location location = block.getLocation();
        Material material = block.getType();
        IRDock.getPlugin().getRegistry().getMultiBlocks().forEach((id, multiBlock) -> {
            Structure structure = multiBlock.getStructure();
            StructurePiece piece = structure.getCenterPiece();
            if (piece instanceof MaterialStructurePiece msp) {
                if (msp.matches(material)) {
                    if (structure.isValid(location)) {
                        matched.add(multiBlock);
                    }
                }
            } else {
                if (piece.matches(block)) {
                    if (structure.isValid(location)) {
                        matched.add(multiBlock);
                    }
                }
            }
        });

        if (matched.isEmpty()) {
            return;
        }

        event.setCancelled(true);
        if (matched.size() > 1) {
            IRDock.getPlugin().getLogger().warning(MessageFormat.format("Matched {0} multi-blocks, it may cause unexpected behavior", matched.size()));
            IRDock.getPlugin().getLogger().warning("Conflicting multi-block: ");
            for (MultiBlock multiBlock : matched) {
                IRDock.getPlugin().getLogger().warning(MessageFormat.format("From Addon: {0} - MultiBlock ID: {1}", multiBlock.getAddon().getPlugin().getName(), multiBlock.getId()));
            }
        }

        for (MultiBlock multiBlock : matched) {
            multiBlock.onInteract(event);
        }
    }
}
