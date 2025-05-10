package org.irmc.industrialrevival.core.services;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.core.data.BlockRecord;
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
            ConfigurationSection config =
                    IndustrialRevival.getInstance().getDataManager().getBlockData(loc);
            blockDataMap.put(loc, new IRBlockData(record.getMachineId(), record.getLocation(), config, null));
        }
    }

    public IRBlockData getBlockData(Location location) {
        return blockDataMap.get(location);
    }

    public void handleBlockPlacing(Location loc, NamespacedKey machineId) {
        YamlConfiguration configuration = new YamlConfiguration();
        MachineMenuPreset preset = IndustrialRevival.getInstance().getRegistry().getMenuPresets().get(machineId);

        MachineMenu menu = null;
        if (preset != null) {
            menu = new MachineMenu(loc, preset);
            preset.newInstance(loc.getBlock(), menu);
        }

        IRBlockData blockData = new IRBlockData(machineId, loc, configuration, menu);
        blockDataMap.put(loc, blockData);
    }

    @CanIgnoreReturnValue
    public IRBlockData handleBlockBreaking(Location loc) {
        return blockDataMap.remove(loc);
    }

    public void saveAllData() {
        for (IRBlockData data : blockDataMap.values()) {
            String dataString = ((YamlConfiguration) data.getData()).saveToString();
            Location location = data.getLocation();

            BlockRecord blockRecord = new BlockRecord(
                    data.getId().toString(),
                    location.getWorld().getName(),
                    location.getBlockX(),
                    location.getBlockY(),
                    location.getBlockZ(),
                    dataString);

            IndustrialRevival.getInstance().getDataManager().saveBlockRecord(blockRecord);
        }
        blockDataMap.clear();
    }

    public Map<Location, IRBlockData> getBlockDataMap() {
        return new HashMap<>(blockDataMap);
    }

    private void restoreMenuData(MachineMenu menu, MachineMenuPreset preset) {
    }
}
