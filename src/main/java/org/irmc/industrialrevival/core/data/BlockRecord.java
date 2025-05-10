package org.irmc.industrialrevival.core.data;

import io.github.linsminecraftstudio.mxlib.database.serialization.annotations.Column;
import io.github.linsminecraftstudio.mxlib.database.serialization.annotations.PrimaryKey;
import io.github.linsminecraftstudio.mxlib.database.serialization.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.ApiStatus;

import java.io.StringReader;

@Table(name = "blocks")
@NoArgsConstructor
@AllArgsConstructor
@ApiStatus.Internal
public class BlockRecord {
    @Column
    private String id;

    @Column
    @PrimaryKey
    private String world;

    @Column
    @PrimaryKey
    private int x;

    @Column
    @PrimaryKey
    private int y;

    @Column
    @PrimaryKey
    private int z;

    @Column
    private String data;

    public Location getLocation() {
        World bk = Bukkit.getWorld(world);
        return bk == null ? new Location(null, x, y, z) : new Location(bk, x, y, z);
    }

    public NamespacedKey getMachineId() {
        return NamespacedKey.fromString(id);
    }

    public ConfigurationSection getData() {
        StringReader reader = new StringReader(data);
        return YamlConfiguration.loadConfiguration(reader);
    }

    public void setLocation(Location location) {
        this.world = location.getWorld().getName();
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
    }
}
