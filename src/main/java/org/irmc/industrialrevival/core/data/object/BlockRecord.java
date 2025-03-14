package org.irmc.industrialrevival.core.data.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;

@Data
public class BlockRecord {
    public final String world;
    public final int x;
    public final int y;
    public final int z;
    public final String machineId;
    public final String data;

    public BlockRecord(String world, int x, int y, int z, String machineId, String data) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.machineId = machineId;
        this.data = data;
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

    public String world() {
        return world;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int z() {
        return z;
    }

    public NamespacedKey machineId() {
        return NamespacedKey.fromString(machineId);
    }

    public String data() {
        return data;
    }
}
