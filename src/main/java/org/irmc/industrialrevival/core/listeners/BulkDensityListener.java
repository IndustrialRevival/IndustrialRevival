package org.irmc.industrialrevival.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.objects.ChunkPosition;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockBreakEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockPlaceEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Health higher -> more bulky
 */
public class BulkDensityListener implements Listener {
    private static final double BULK_DENSITY_FACTOR = 0.001;
    private final Map<ChunkPosition, Double> healthMap = new HashMap<>();

    @EventHandler(priority = EventPriority.MONITOR)
    public void onIRBlockPlace(IRBlockPlaceEvent event) {
        ChunkPosition chunkPosition = new ChunkPosition(event.getBlock().getChunk());

        double health;
        if (event.getIritem().getItemHandler(BlockTicker.class) != null) {
            health = 0.003;
        } else {
            health = 0.001;
        }

        healthMap.merge(chunkPosition, health, (original, incoming) -> BULK_DENSITY_FACTOR * (original + incoming) * (original + incoming));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onIRBlockBreak(IRBlockBreakEvent event) {
        ChunkPosition chunkPosition = new ChunkPosition(event.getBlock().getChunk());

        double health;
        if (event.getIritem().getItemHandler(BlockTicker.class) != null) {
            health = 0.003;
        } else {
            health = 0.001;
        }

        healthMap.merge(chunkPosition, health, (original, incoming) -> BULK_DENSITY_FACTOR * (original - incoming) * (original - incoming));
    }

    public Map<ChunkPosition, Double> getHealthMap() {
        return new HashMap<>(healthMap);
    }
}
