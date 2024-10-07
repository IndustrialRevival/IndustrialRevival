package org.irmc.industrialrevival.core.task;

import com.tcoded.folialib.wrapper.task.WrappedTask;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TickerTask implements Consumer<WrappedTask> {
    private final Supplier<Map<Location, IRBlockData>> blockDataSupplier =
            IndustrialRevival.getInstance().getBlockDataService()::getBlockDataMap;
    public static final Map<Location, Integer> bugsCount = new ConcurrentHashMap<>();
    // TODO: When place or break a block, bugsCount should be reset to 0.

    private final int checkInterval;

    public TickerTask(int checkInterval) {
        this.checkInterval = checkInterval;
    }
    @Override
    public void accept(WrappedTask wrappedTask) {
        Map<Location, IRBlockData> blockDataMap = blockDataSupplier.get();
        if (blockDataMap == null) {
            return;
        }

        for (Map.Entry<Location, IRBlockData> entry : blockDataMap.entrySet()) {
            IRBlockData blockData = entry.getValue();

            IndustrialRevivalItem item = IndustrialRevivalItem.getById(blockData.getId());
            if (item == null) {
                removeTickingBlock(entry.getKey());
                continue;
            }

            BlockTicker ticker = item.getItemHandler(BlockTicker.class);
            if (ticker != null) {
                try {
                    ticker.onTick(entry.getKey().getBlock(), blockData.getMachineMenu(), blockData);
                } catch (Exception e) {
                    e.printStackTrace();
                    bugsCount.put(entry.getKey(), bugsCount.getOrDefault(entry.getKey(), 0) + 1);
                    if (bugsCount.get(entry.getKey()) >= 4) {
                        removeTickingBlock(entry.getKey());
                        reportBug(entry.getKey(), entry.getValue(), e);
                    }
                }
            }
        }
    }

    private void removeTickingBlock(Location location) {
        Map<Location, IRBlockData> blockDataMap = blockDataSupplier.get();
        if (blockDataMap == null) {
            return;
        }

        blockDataMap.remove(location);
    }

    /**
     * Called when an existing chunk is loaded.
     * @param chunk The chunk that was loaded.
     */
    public void loadChunk(Chunk chunk) {
        // TODO: load chunk
        // get available ticking blocks from database
    }

    /**
     * Called when a existing block is loaded.
     * @param location The location of the block that was loaded.
     */
    public void loadBlock(Location location) {
        // TODO: load block
    }

    private void reportBug(Location location, IRBlockData blockData, Exception e) {
        String message = "An error caught while ticking a block at " + location + ":\n" + e.getMessage();
    }
}
