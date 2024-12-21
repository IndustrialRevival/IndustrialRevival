package org.irmc.industrialrevival.core.data.object;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;

public record BlockRecord(String world, int x, int y, int z, NamespacedKey machineId, String data) {
    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }
}
