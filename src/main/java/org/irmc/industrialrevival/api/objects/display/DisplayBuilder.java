package org.irmc.industrialrevival.api.objects.display;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Display;
import org.bukkit.util.Vector;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.pigeonlib.objects.Pair;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DisplayBuilder {
    private BlockFace northFace;
    private Location center;
    private Vector currentOffset;
    private final List<Pair<Display, Vector>> displays;
    private DisplayBuilder() {
        displays = new ArrayList<>();
    }

    public static DisplayBuilder create() {
        return new DisplayBuilder();
    }

    public DisplayBuilder northFace(BlockFace northFace) {
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
    
    public List<Pair<Display, Vector>> displays() {
        return displays;
    }

    public DisplayBuilder home() {
        this.currentOffset = new Vector(0, 0, 0);
        return this;
    }

    public DisplayBuilder center(Location center) {
        this.center = center;
        return this;
    }

    public DisplayBuilder center(Display display) {
        displays.add(new Pair<>(display, new Vector(0, 0, 0)));
        return this;
    }

    public Location center() {
        return center;
    }

    public DisplayBuilder leftBlock(Display display) {
        displays.add(new Pair<>(display, goLeft(1f)));
        return this;
    }

    public DisplayBuilder rightBlock(Display display) {
        displays.add(new Pair<>(display, goRight(1f)));
        return this;
    }

    public DisplayBuilder frontBlock(Display display) {
        displays.add(new Pair<>(display, goFront(1f)));
        return this;
    }

    public DisplayBuilder backBlock(Display display) {
        displays.add(new Pair<>(display, goBack(1f)));
        return this;
    }

    public DisplayBuilder upBlock(Display display) {
        displays.add(new Pair<>(display, goUp(1f)));
        return this;
    }

    public DisplayBuilder downBlock(Display display) {
        displays.add(new Pair<>(display, goDown(1f)));
        return this;
    }

    @Nonnull
    public static BlockFace getLeft(BlockFace blockFace) {
        return switch (blockFace) {
            case NORTH -> BlockFace.WEST;
            case SOUTH -> BlockFace.EAST;
            case EAST -> BlockFace.NORTH;
            case WEST -> BlockFace.SOUTH;
            default -> BlockFace.SELF;
        };
    }

    @Nonnull
    public static BlockFace getRight(BlockFace blockFace) {
        return switch (blockFace) {
            case NORTH -> BlockFace.EAST;
            case SOUTH -> BlockFace.WEST;
            case EAST -> BlockFace.SOUTH;
            case WEST -> BlockFace.NORTH;
            default -> BlockFace.SELF;
        };
    }

    @Nonnull
    public static BlockFace getOpposite(BlockFace blockFace) {
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
    public static BlockFace getFront(BlockFace blockFace) {
        return blockFace;
    }

    @Nonnull
    public static BlockFace getBack(BlockFace blockFace) {
        return getOpposite(blockFace);
    }

    @Nonnull
    public static Vector getOffset(BlockFace blockFace) {
        return switch (blockFace) {
            case NORTH -> new Vector(0, 0, -1);
            case SOUTH -> new Vector(0, 0, 1);
            case EAST -> new Vector(1, 0, 0);
            case WEST -> new Vector(-1, 0, 0);
            default -> new Vector(0, 0, 0);
        };
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
    public DisplayBuilder frontHalf(Display display) {
        displays.add(new Pair<>(display, goFront(0.5f)));
        return this;
    }

    public DisplayBuilder backHalf(Display display) {
        displays.add(new Pair<>(display, goBack(0.5f)));
        return this;
    }

    public DisplayBuilder upHalf(Display display) {
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
    public DisplayGroup build() {
        if (center == null) {
            throw new IllegalStateException("Center location is not set");
        }
        
        if (displays.isEmpty()) {
            return new DisplayGroup(IndustrialRevival.getInstance());
        }
        
        DisplayGroup displayGroup = new DisplayGroup(IndustrialRevival.getInstance());
        displayGroup.center(center);
        for (Pair<Display, Vector> display : displays) {
            displayGroup.add(display.getFirst(), display.getSecond());
        }

        return displayGroup;
    }
}
