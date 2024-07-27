package org.irmc.industrialrevival.core.data.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@Getter
@Setter
@AllArgsConstructor
public class BlockRecord {
    private String world;
    private int x;
    private int y;
    private int z;
    private String id;
    private String data;

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }
}
