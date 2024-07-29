package org.irmc.industrialrevival.core.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.data.object.BlockRecord;

public class BlockDataService {
    @Getter(AccessLevel.PACKAGE)
    private final Map<Location, IRBlockData> blockData;

    public BlockDataService() {
        this.blockData = new HashMap<>();

        loadData();
    }

    private void loadData() {
        List<BlockRecord> records =
                IndustrialRevival.getInstance().getDataManager().getAllBlockRecords();
        for (BlockRecord record : records) {
            Location loc = record.getLocation();
            YamlConfiguration config =
                    IndustrialRevival.getInstance().getDataManager().getBlockData(loc);
            blockData.put(loc, new IRBlockData(record.getMachineId(), record.getLocation(), config, null));
        }
    }

    public IRBlockData getBlockData(Location location) {
        return blockData.get(location);
    }

    public void saveAllData() {
        for (IRBlockData data : blockData.values()) {
            String dataString = data.getConfig().saveToString();
            Location location = data.getLocation();
            BlockRecord blockRecord = new BlockRecord(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ(), data.getId(), dataString);
            IndustrialRevival.getInstance().getDataManager().updateBlockData(data.getLocation(), blockRecord);
        }
    }
}
