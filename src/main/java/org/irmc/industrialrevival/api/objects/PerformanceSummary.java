package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.ProfiledBlock;

import java.util.HashMap;
import java.util.Map;

@Getter
public class PerformanceSummary {
    private final Map<ProfiledBlock, Long> data;
    private final Map<Location, Long> dataByLocation;
    private final Map<String, Long> dataByID;
    private final Map<ChunkPosition, Long> dataByChunk;
    private final Map<String, Long> dataByPlugin;
    private final long totalTime;

    public PerformanceSummary(Map<ProfiledBlock, Long> data, Map<String, Long> dataByID, Map<ChunkPosition, Long> dataByChunk, Map<String, Long> dataByPlugin, long totalTime) {
        this.data = data;
        this.dataByID = dataByID;
        this.dataByChunk = dataByChunk;
        this.dataByPlugin = dataByPlugin;
        this.dataByLocation = new HashMap<>();
        for (ProfiledBlock profiledBlock : data.keySet()) {
            dataByLocation.put(profiledBlock.getLocation(), data.get(profiledBlock));
        }
        this.totalTime = totalTime;
    }
}
