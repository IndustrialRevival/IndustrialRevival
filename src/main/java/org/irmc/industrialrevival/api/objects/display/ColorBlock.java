package org.irmc.industrialrevival.api.objects.display;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

@Getter
public enum ColorBlock {
    NORTH_VISIBLE(
            new Quaternionf(0, 0, 1, 0),
            new Quaternionf(0, 0, 1, 1),
            scale -> 0.1375f * scale
    ),
    SOUTH_VISIBLE(
            new Quaternionf(0, 1, 0, 1),
            new Quaternionf(0, 1, 0, 1),
            scale -> -0.1125f * scale
    ),
    UP_VISIBLE(
            new Quaternionf(1, 1, 1, 1),
            new Quaternionf(1, 1, 1, 1),
            scale -> 0.1125f * scale
    ),
    DOWN_VISIBLE(
            new Quaternionf(1, 0, 1, 0),
            new Quaternionf(0, 1, 1, 0),
            scale -> -0.1125f * scale
    ),
    EAST_VISIBLE(
            new Quaternionf(0, 1, 0, 1),
            new Quaternionf(0, 1, 0, 0),
            scale -> 0.1125f * scale
    ),
    WEST_VISIBLE(
            new Quaternionf(0, 1, 0, 1),
            new Quaternionf(0, 0, 1, 1),
            scale -> -0.1375f * scale
    );

    private final Quaternionf leftRotation;
    private final Quaternionf rightRotation;
    private final Function<Float, Float> scaleHandler;

    ColorBlock(Quaternionf leftRotation, Quaternionf right_rotation, Function<Float, Float> scaleHandler) {
        this.leftRotation = leftRotation;
        this.rightRotation = right_rotation;
        this.scaleHandler = scaleHandler;
    }

    public void make(@NotNull Block block, @NotNull Color color, float @NotNull [] scale) {
        make(block, color, scale, null);
    }

    public void make(@NotNull Corners corners, @NotNull Color color, float @NotNull [] scale) {
        make(corners, color, scale, null);
    }

    public void make(@NotNull Block block, @NotNull Color color, float @NotNull [] scale, @Nullable TextureHandler textureHandler) {
        make(Corners.fromBlock(block), color, scale, textureHandler);
    }

    public void make(@NotNull Corners corners, @NotNull Color color, float @NotNull [] scale, @Nullable TextureHandler textureHandler) {
        Preconditions.checkArgument(corners != null, "Corners cannot be null");
        Preconditions.checkArgument(color != null, "Color cannot be null");
        Preconditions.checkArgument(scale != null, "Scale cannot be null");
        Preconditions.checkArgument(scale.length >= 3, "Scale must be at least length 3");
        Preconditions.checkArgument(corners.getWorld() != null, "World cannot be null");

        var builder = new TextModelBuilder()
                .setLeftRotation(leftRotation)
                .setRightRotation(rightRotation)
                .setSize(scale[0], scale[1], scale[2])
                .setTranslation(getTranslation(scale[0], scale[1], scale[2]))
                .backgroundColor(color)
                .textOpacity((byte) 5);

        if (textureHandler != null) {
            textureHandler.apply(corners, builder);
        }

        generate(corners, builder);
    }

    public @NotNull Vector3f getTranslation(float scaleX, float scaleY, float scaleZ) {
        return switch (this) {
            case SOUTH_VISIBLE -> new Vector3f(scaleHandler.apply(scaleX), 0f, 0f);
            case NORTH_VISIBLE, WEST_VISIBLE -> new Vector3f(0f, scaleHandler.apply(scaleY), 0f);
            case UP_VISIBLE, DOWN_VISIBLE, EAST_VISIBLE -> new Vector3f(0f, 0f, scaleHandler.apply(scaleZ));
        };
    }

    private void generate(@NotNull Corners corners, @NotNull TextModelBuilder builder) {
        Location location = null;
        switch (this) {
            case EAST_VISIBLE, UP_VISIBLE ->
                    location = new Location(corners.getWorld(), corners.getMinX(), corners.getMinY(), corners.getMinZ());
            case WEST_VISIBLE -> location = new Location(corners.getWorld(), corners.getMaxX(), corners.getMaxY(), corners.getMinZ());
            case DOWN_VISIBLE, NORTH_VISIBLE ->
                    location = new Location(corners.getWorld(), corners.getMinX(), corners.getMinY(), corners.getMaxZ());
            case SOUTH_VISIBLE -> location = new Location(corners.getWorld(), corners.getMaxX(), corners.getMinY(), corners.getMaxZ());
        }

        if (location == null) {
            return;
        }

        builder.buildAt(location);
    }

    public interface TextureHandler {
        void apply(@Nonnull Corners corners, @Nullable TextModelBuilder extraHandler);
    }

    public static void makeSurface(@NotNull Corners corners, @NotNull Color color, float @NotNull [] scale, @Nullable TextureHandler textureHandler) {
        for (ColorBlock value : values()) {
            value.make(corners, color, scale, textureHandler);
        }
    }
}
