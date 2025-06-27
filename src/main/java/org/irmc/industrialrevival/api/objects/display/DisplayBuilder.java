package org.irmc.industrialrevival.api.objects.display;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Display;
import org.bukkit.util.Vector;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.pigeonlib.objects.Pair;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DisplayBuilder {
    private final @NotNull List<Pair<Display, Vector>> displays;
    private BlockFace northFace;
    private Location center;
    private Vector currentOffset;

    private DisplayBuilder() {
        displays = new ArrayList<>();
    }

    public static @NotNull DisplayBuilder create() {
        return new DisplayBuilder();
    }

    @Nonnull
    public static BlockFace getLeft(@NotNull BlockFace blockFace) {
        return switch (blockFace) {
            case NORTH -> BlockFace.WEST;
            case SOUTH -> BlockFace.EAST;
            case EAST -> BlockFace.NORTH;
            case WEST -> BlockFace.SOUTH;
            default -> BlockFace.SELF;
        };
    }

    @Nonnull
    public static BlockFace getRight(@NotNull BlockFace blockFace) {
        return switch (blockFace) {
            case NORTH -> BlockFace.EAST;
            case SOUTH -> BlockFace.WEST;
            case EAST -> BlockFace.SOUTH;
            case WEST -> BlockFace.NORTH;
            default -> BlockFace.SELF;
        };
    }

    @Nonnull
    public static BlockFace getOpposite(@NotNull BlockFace blockFace) {
        return switch (blockFace) {
            case NORTH -> BlockFace.SOUTH;
            case SOUTH -> BlockFace.NORTH;
            case EAST -> BlockFace.WEST;
            case WEST -> BlockFace.EAST;
            case UP -> BlockFace.DOWN;
            case DOWN -> BlockFace.UP;
            default -> BlockFace.SELF;
        };
    }

    @Nonnull
    public static BlockFace getFront(@NotNull BlockFace blockFace) {
        return blockFace;
    }

    @Nonnull
    public static BlockFace getBack(@NotNull BlockFace blockFace) {
        return getOpposite(blockFace);
    }

    @Nonnull
    public static Vector getOffset(@NotNull BlockFace blockFace) {
        return switch (blockFace) {
            case NORTH -> new Vector(0, 0, -1);
            case SOUTH -> new Vector(0, 0, 1);
            case EAST -> new Vector(1, 0, 0);
            case WEST -> new Vector(-1, 0, 0);
            default -> new Vector(0, 0, 0);
        };
    }

    public @NotNull DisplayBuilder northFace(@NotNull BlockFace northFace) {
        if (northFace.ordinal() > 3) {
            throw new IllegalArgumentException("Invalid block face: " + northFace);
        }
        this.northFace = northFace;
        return this;
    }

    public BlockFace northFace() {
        return northFace;
    }

    public Vector currentOffset() {
        return currentOffset;
    }

    public @NotNull List<Pair<Display, Vector>> displays() {
        return displays;
    }

    public @NotNull DisplayBuilder home() {
        this.currentOffset = new Vector(0, 0, 0);
        return this;
    }

    public @NotNull DisplayBuilder center(Location center) {
        this.center = center;
        return this;
    }

    public @NotNull DisplayBuilder center(Display display) {
        displays.add(new Pair<>(display, new Vector(0, 0, 0)));
        return this;
    }

    public Location center() {
        return center;
    }

    public @NotNull DisplayBuilder leftBlock(Display display) {
        displays.add(new Pair<>(display, goLeft(1f)));
        return this;
    }

    public @NotNull DisplayBuilder rightBlock(Display display) {
        displays.add(new Pair<>(display, goRight(1f)));
        return this;
    }

    public @NotNull DisplayBuilder frontBlock(Display display) {
        displays.add(new Pair<>(display, goFront(1f)));
        return this;
    }

    public @NotNull DisplayBuilder backBlock(Display display) {
        displays.add(new Pair<>(display, goBack(1f)));
        return this;
    }

    public @NotNull DisplayBuilder upBlock(Display display) {
        displays.add(new Pair<>(display, goUp(1f)));
        return this;
    }

    public @NotNull DisplayBuilder downBlock(Display display) {
        displays.add(new Pair<>(display, goDown(1f)));
        return this;
    }

    @Nonnull
    public DisplayBuilder leftHalf(Display display) {
        displays.add(new Pair<>(display, goLeft(0.5f)));
        return this;
    }

    @Nonnull
    public DisplayBuilder rightHalf(Display display) {
        displays.add(new Pair<>(display, goRight(0.5f)));
        return this;
    }

    public @NotNull DisplayBuilder frontHalf(Display display) {
        displays.add(new Pair<>(display, goFront(0.5f)));
        return this;
    }

    public @NotNull DisplayBuilder backHalf(Display display) {
        displays.add(new Pair<>(display, goBack(0.5f)));
        return this;
    }

    public @NotNull DisplayBuilder upHalf(Display display) {
        displays.add(new Pair<>(display, goUp(0.5f)));
        return this;
    }

    @Nonnull
    public Vector goLeft(double distance) {
        return currentOffset.add(getOffset(getLeft(northFace)).multiply(distance));
    }

    @Nonnull
    public Vector goRight(double distance) {
        return currentOffset.add(getOffset(getRight(northFace)).multiply(distance));
    }

    @Nonnull
    public Vector goFront(double distance) {
        return currentOffset.add(getOffset(getFront(northFace)).multiply(distance));
    }

    @Nonnull
    public Vector goBack(double distance) {
        return currentOffset.add(getOffset(getBack(northFace)).multiply(distance));
    }

    @Nonnull
    public Vector goUp(double distance) {
        return currentOffset.add(getOffset(BlockFace.UP).multiply(distance));
    }

    @Nonnull
    public Vector goDown(double distance) {
        return currentOffset.add(getOffset(BlockFace.DOWN).multiply(distance));
    }

    public @NotNull DisplayGroup build() {
        if (center == null) {
            throw new IllegalStateException("Center location is not set");
        }

        if (displays.isEmpty()) {
            return new DisplayGroup(IRDock.getPlugin());
        }

        DisplayGroup displayGroup = new DisplayGroup(IRDock.getPlugin());
        displayGroup.center(center);
        for (Pair<Display, Vector> display : displays) {
            displayGroup.add(display.getFirst(), display.getSecond());
        }

        return displayGroup;
    }
}
