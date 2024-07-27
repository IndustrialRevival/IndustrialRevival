package org.irmc.industrialrevival.core.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.data.object.BlockRecord;

public class BlockDataService {
    private final Map<Location, IRBlockData> blockData;

    public BlockDataService() {
        this.blockData = new HashMap<>();

        loadData();
    }

    private void loadData() {
        List<BlockRecord> records = IndustrialRevival.getInstance().getDataManager().getAllBlockRecords();
        for (BlockRecord record : records) {
            Location loc = record.getLocation();
            YamlConfiguration config = IndustrialRevival.getInstance().getDataManager().getBlockData(loc);
            blockData.put(loc, new IRBlockData(record.getId(), record.getLocation(), config, null));
        }
    }

    public IRBlockData getBlockData(Location location) {
        return blockData.get(location);
    }

    public void saveAllData() {
        for (IRBlockData data : blockData.values()) {
            Location loc = data.getLocation();
            BlockRecord record = new BlockRecord(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), data.getId(), data.getConfig().saveToString());
            IndustrialRevival.getInstance().getDataManager().updateBlockData(loc, record);
        }
    }

    List<IRBlockData> getAllBlockData() {
        return new ArrayList<>(blockData.values());
    }
}
