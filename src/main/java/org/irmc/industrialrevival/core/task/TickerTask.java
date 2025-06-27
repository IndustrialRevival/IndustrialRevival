package org.irmc.industrialrevival.core.task;

import com.tcoded.folialib.wrapper.task.WrappedTask;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.objects.ChunkPosition;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.events.ir.BlockTickEvent;
import org.irmc.industrialrevival.api.objects.events.ir.TickDoneEvent;
import org.irmc.industrialrevival.api.objects.events.ir.TickStartEvent;
import org.irmc.industrialrevival.core.data.BlockRecord;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TickerTask implements Consumer<WrappedTask> {
    public static final Map<Location, Integer> bugsCount = new ConcurrentHashMap<>();
    private final Supplier<Map<Location, IRBlockData>> blockDataSupplier =
            IRDock.getPlugin().getBlockDataService()::getBlockDataMap;
    // TODO: When place or break a block, bugsCount should be reset to 0.
    @Getter
    private final int checkInterval;
    @Getter
    private long ticked = 0;

    public TickerTask(int checkInterval) {
        this.checkInterval = checkInterval;
    }

    @Override
    public void accept(WrappedTask wrappedTask) {
        Map<Location, IRBlockData> blockDataMap = blockDataSupplier.get();
        TickStartEvent startEvent = new TickStartEvent(blockDataMap, checkInterval, ticked);
        TickDoneEvent doneEvent = new TickDoneEvent();
        IndustrialRevival.runAsync(() -> Bukkit.getPluginManager().callEvent(startEvent));
        IRDock.getPlugin().getProfilerService().clearProfilingData();

        if (blockDataMap == null) {
            IndustrialRevival.runAsync(() -> Bukkit.getPluginManager().callEvent(doneEvent));
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
                    BlockTickEvent event = new BlockTickEvent(entry.getKey().getBlock(), blockData.getMachineMenu(), item, blockData);
                    Bukkit.getPluginManager().callEvent(event);

                    IRDock.getPlugin().getProfilerService().startProfiling(entry.getKey());
                    if (!event.isCancelled()) {
                        ticker.onTick(event);
                    }
                    IRDock.getPlugin().getProfilerService().stopProfiling(entry.getKey());
                } catch (Throwable ex) {
                    ex.printStackTrace();
                    bugsCount.put(entry.getKey(), bugsCount.getOrDefault(entry.getKey(), 0) + 1);
                    if (bugsCount.get(entry.getKey()) >= 4) {
                        removeTickingBlock(entry.getKey());
                        reportBug(entry.getKey(), entry.getValue(), ex);
                    }
                }
            }
        }

        IndustrialRevival.runAsync(() -> Bukkit.getPluginManager().callEvent(doneEvent));

        ticked++;
    }

    private void addTickingBlock(Location location, IRBlockData blockData) {
        Map<Location, IRBlockData> blockDataMap = blockDataSupplier.get();
        if (blockDataMap == null) {
            return;
        }

        blockDataMap.put(location, blockData);
        bugsCount.put(location, 0);
    }

    private void removeTickingBlock(Location location) {
        Map<Location, IRBlockData> blockDataMap = blockDataSupplier.get();
        if (blockDataMap == null) {
            return;
        }

        blockDataMap.remove(location);
        bugsCount.remove(location);
    }

    public Map<Location, IRBlockData> getTickingBlocks() {
        return new HashMap<>(blockDataSupplier.get());
    }

    /**
     * Called when an existing chunk is loaded.
     *
     * @param chunk The chunk that was loaded.
     */
    public void loadChunk(Chunk chunk) {
        chunk.load();
        // get available ticking blocks from database
        ChunkPosition chunkPosition = new ChunkPosition(chunk);
        for (BlockRecord record : IRDock.getPlugin().getDataManager().getAllBlockRecords()) {
            ChunkPosition recordChunkPosition = new ChunkPosition(record.getLocation().getChunk());
            if (recordChunkPosition.equals(chunkPosition)) {
                loadBlock(record.getLocation());
            }
        }
    }

    /**
     * Called when an existing block is loaded.
     *
     * @param location The location of the block that was loaded.
     */
    public void loadBlock(Location location) {
        IRBlockData blockData = IRDock.getPlugin().getBlockDataService().getBlockData(location);
        if (blockData == null) {
            return;
        }

        addTickingBlock(location, blockData);
    }

    private void reportBug(Location location, IRBlockData blockData, Throwable e) {
        // todo
        String message = "An error caught while ticking a block at " + location + ":\n" + e.getMessage();
    }
}
