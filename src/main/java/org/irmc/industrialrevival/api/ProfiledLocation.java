package org.irmc.industrialrevival.api;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.ChunkPosition;
import org.irmc.industrialrevival.utils.DataUtil;

@Getter
public class ProfiledLocation {
    private Location location;
    private ChunkPosition chunkPosition;
    private IndustrialRevivalItem item;
    private String plugin;

    public ProfiledLocation(Location location) {
        this.location = location;
        this.chunkPosition = new ChunkPosition(location.getChunk());
        this.item = IndustrialRevivalItem.getById(DataUtil.getBlockData(location).getId());
        this.plugin = this.item.getAddon().getPlugin().getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProfiledLocation other)) {
            return false;
        }
        return this.location.equals(other.location) && this.chunkPosition.equals(other.chunkPosition) && this.item.equals(other.item) && this.plugin.equals(other.plugin);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.location.hashCode();
        result = 31 * result + this.chunkPosition.hashCode();
        result = 31 * result + this.item.hashCode();
        result = 31 * result + this.plugin.hashCode();
        return result;
    }
}
