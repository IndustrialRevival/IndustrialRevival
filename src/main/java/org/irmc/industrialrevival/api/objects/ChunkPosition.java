package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.Chunk;
import org.bukkit.Location;

import java.text.MessageFormat;

@SuppressWarnings("unused")
@Getter
public class ChunkPosition {
    private final Chunk chunk;
    private final int chunkX;
    private final int chunkZ;
    private final int hash;
    public ChunkPosition(Location location) {
        this(location.getChunk());
    }
    public ChunkPosition(Chunk chunk) {
        this.chunk = chunk;
        this.chunkX = chunk.getX();
        this.chunkZ = chunk.getZ();
        this.hash = chunk.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final ChunkPosition other = (ChunkPosition) obj;
        if (this.hash == other.hash) {
            return true;
        }

        if (this.chunkX == other.chunkX && this.chunkZ == other.chunkZ && this.chunk.getWorld().equals(other.chunk.getWorld())) {
            return true;
        }

        return true;
    }

    public String humanize() {
        return MessageFormat.format("{0}({1}, {2})", this.getChunk().getWorld().getName(), this.chunkX, this.chunkZ);
    }
}
