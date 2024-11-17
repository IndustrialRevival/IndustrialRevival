package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.ProfiledLocation;

import java.util.HashMap;
import java.util.Map;

@Getter
public class PerformanceSummary {
    private final Map<ProfiledLocation, Long> data;
    private final Map<Location, Long> dataByLocation;
    private final Map<String, Long> dataByID;
    private final Map<ChunkPosition, Long> dataByChunk;
    private final Map<String, Long> dataByPlugin;
    private final long totalTime;

    public PerformanceSummary(Map<ProfiledLocation, Long> data, Map<String, Long> dataByID, Map<ChunkPosition, Long> dataByChunk, Map<String, Long> dataByPlugin, long totalTime) {
        this.data = data;
        this.dataByID = dataByID;
        this.dataByChunk = dataByChunk;
        this.dataByPlugin = dataByPlugin;
        this.dataByLocation = new HashMap<>();
        for (ProfiledLocation profiledLocation : data.keySet()) {
            dataByLocation.put(profiledLocation.getLocation(), data.get(profiledLocation));
        }
        this.totalTime = totalTime;
    }
}
