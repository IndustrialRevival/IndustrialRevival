package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.ProfiledBlock;

import java.util.HashMap;
import java.util.Map;

@Getter
public class PerformanceSummary {
    private final Map<ProfiledBlock, Long> data;
    private final Map<Location, Long> dataByLocation;
    private final Map<NamespacedKey, Long> dataByID;
    private final Map<ChunkPosition, Long> dataByChunk;
    private final Map<String, Long> dataByPlugin;
    private final long totalTime;

    public PerformanceSummary(Map<ProfiledBlock, Long> data, Map<NamespacedKey, Long> dataByID, Map<ChunkPosition, Long> dataByChunk, Map<String, Long> dataByPlugin, long totalTime) {
        this.data = data;
        this.dataByID = dataByID;
        this.dataByChunk = dataByChunk;
        this.dataByPlugin = dataByPlugin;
        this.dataByLocation = new HashMap<>();
        for (Map.Entry<ProfiledBlock, Long> entry : data.entrySet()) {
            this.dataByLocation.put(entry.getKey().getLocation(), entry.getValue());
        }
        this.totalTime = totalTime;
    }
}
