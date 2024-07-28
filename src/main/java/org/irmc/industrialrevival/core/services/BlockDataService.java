package org.irmc.industrialrevival.core.services;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.objects.IRBlockData;

public class BlockDataService {
    private final Map<Location, IRBlockData> blockData;

    public BlockDataService() {
        this.blockData = new HashMap<>();

        loadData();
    }

    private void loadData() {}

    public IRBlockData getBlockData(Location location) {
        return blockData.get(location);
    }

    public void saveAllData() {}
}
