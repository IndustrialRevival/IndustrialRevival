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
    /**
     * The data value of the block.
     * WARNING: This value is encoded using base64 and not a YamlConfiguration object, DON'T TRY TO USE IT!
     */
    private String data;

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }
}
