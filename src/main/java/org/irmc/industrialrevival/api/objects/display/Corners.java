package org.irmc.industrialrevival.api.objects.display;

import lombok.Builder;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;

@Data
@Builder
public class Corners {
    public WeakReference<World> world;
    private final float minX;
    private final float maxX;
    private final float minY;
    private final float maxY;
    private final float minZ;
    private final float maxZ;

    public Corners(WeakReference<World> world, float minX, float maxX, float minY, float maxY, float minZ, float maxZ) {
        this.world = world;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.minZ = minZ;
        this.maxZ = maxZ;
    }

    public Corners(World world, float minX, float maxX, float minY, float maxY, float minZ, float maxZ) {
        this.world = new WeakReference<>(world);
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.minZ = minZ;
        this.maxZ = maxZ;
    }

    public Corners merge(Corners corners) {
        return Corners.builder()
                .world(world)
                .minX(Math.min(minX, corners.minX))
                .maxX(Math.max(maxX, corners.maxX))
                .minY(Math.min(minY, corners.minY))
                .maxY(Math.max(maxY, corners.maxY))
                .minZ(Math.min(minZ, corners.minZ))
                .maxZ(Math.max(maxZ, corners.maxZ))
                .build();
    }

    public static Corners of(Block block) {
        return Corners.builder()
                .world(new WeakReference<>(block.getWorld()))
                .minX(block.getX())
                .maxX(block.getX()+1)
                .minY(block.getY())
                .maxY(block.getY()+1)
                .minZ(block.getZ())
                .maxZ(block.getZ()+1)
                .build();
    }

    public static Corners of(Location location) {
        return of(location.getBlock());
    }

    public static Corners of(Block block1, Block block2) {
        return of(block1).merge(of(block2));
    }

    public static Corners of(Location location1, Location location2) {
        return of(location1.getBlock(), location2.getBlock());
    }

    public @Nullable World getWorld() {
        return world.get();
    }
    public float getDistanceX() {
        return Math.abs(maxX - minX);
    }

    public float getDistanceY() {
        return Math.abs(maxY - minY);
    }

    public float getDistanceZ() {
        return Math.abs(maxZ - minZ);
    }
}
