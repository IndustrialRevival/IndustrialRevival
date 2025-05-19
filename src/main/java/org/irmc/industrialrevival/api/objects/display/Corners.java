package org.irmc.industrialrevival.api.objects.display;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Corners {
    private final float minX;
    private final float maxX;
    private final float minY;
    private final float maxY;
    private final float minZ;
    private final float maxZ;
    public WeakReference<World> world;

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

    public static Corners of(Block block) {
        return of(block.getLocation()).merge(of(block.getLocation().clone().add(1, 1, 1)));
    }

    public static Corners of(Location location) {
        return Corners.builder()
                .world(new WeakReference<>(location.getWorld()))
                .minX((float) location.getX())
                .maxX((float) location.getX())
                .minY((float) location.getY())
                .maxY((float) location.getY())
                .minZ((float) location.getZ())
                .maxZ((float) location.getZ())
                .build();
    }

    public static Corners of(Block block1, Block block2) {
        return of(block1).merge(of(block2));
    }

    public static Corners of(Location location1, Location location2) {
        return of(location1).merge(of(location2));
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
