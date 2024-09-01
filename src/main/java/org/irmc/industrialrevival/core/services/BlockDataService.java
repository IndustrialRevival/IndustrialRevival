package org.irmc.industrialrevival.core.services;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.core.data.object.BlockRecord;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockDataService {
    private final Map<Location, IRBlockData> blockDataMap;

    public BlockDataService() {
        this.blockDataMap = new HashMap<>();

        loadData();
    }

    private void loadData() {
        List<BlockRecord> records =
                IndustrialRevival.getInstance().getDataManager().getAllBlockRecords();
        for (BlockRecord record : records) {
            Location loc = record.getLocation();
            YamlConfiguration config =
                    IndustrialRevival.getInstance().getDataManager().getBlockData(loc);
            blockDataMap.put(loc, new IRBlockData(record.getMachineId(), record.getLocation(), config, null));
        }
    }

    public IRBlockData getBlockData(Location location) {
        return blockDataMap.get(location);
    }

    public void saveAllData() {
        for (IRBlockData data : blockDataMap.values()) {
            String dataString = data.getConfig().saveToString();
            Location location = data.getLocation();
            BlockRecord blockRecord = new BlockRecord(
                    location.getWorld().getName(),
                    location.getBlockX(),
                    location.getBlockY(),
                    location.getBlockZ(),
                    data.getId(),
                    dataString);
            IndustrialRevival.getInstance().getDataManager().updateBlockData(data.getLocation(), blockRecord);
        }
        blockDataMap.clear();
    }

    public Map<Location, IRBlockData> getBlockDataMap() {
        return new HashMap<>(blockDataMap);
    }

    private void restoreMenuData(MachineMenu menu, MachineMenuPreset preset) {
    }
}
