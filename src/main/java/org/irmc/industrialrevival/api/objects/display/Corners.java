package org.irmc.industrialrevival.api.objects.display;

import lombok.Builder;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;

@Builder
@Getter
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

    public static @NotNull Corners fromBox(World world, @NotNull BoundingBox box) {
        return new Corners(world, (float) box.getMinX(), (float) box.getMaxX(), (float) box.getMinY(), (float) box.getMaxY(), (float) box.getMinZ(), (float) box.getMaxZ());
    }

    public static @NotNull Corners fromBlock(@NotNull Block block) {
        return fromBox(block.getWorld(), block.getBoundingBox());
    }

    public static @NotNull Corners fromBlock(@NotNull Block block1, @NotNull Block block2) {
        return fromLocation(block1.getLocation(), block2.getLocation());
    }

    public static @NotNull Corners fromLocation(@NotNull Location location) {
        return fromBlock(location.getBlock());
    }
    public static @NotNull Corners fromLocation(@NotNull Location location1, @NotNull Location location2) {
        return fromBox(location1.getWorld(), BoundingBox.of(location1, location2));
    }

    public @Nullable World getWorld() {
        return world.get();
    }
}
