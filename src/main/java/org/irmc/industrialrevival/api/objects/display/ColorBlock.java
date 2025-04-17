package org.irmc.industrialrevival.api.objects.display;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Display;
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
            scale -> 0.1375f * scale * 2,
            scale -> 4f * scale
    ),
    SOUTH_VISIBLE(
            new Quaternionf(0, 1, 0, 1),
            new Quaternionf(0, 1, 0, 1),
            scale -> -0.1125f * scale * 4
    ),
    UP_VISIBLE(
            new Quaternionf(0.5, 0.5, 0.5, 0.5),
            new Quaternionf(0.5, 0.5, 0.5, 0.5),
            scale -> 0.1125f * scale,
            scale -> scale * 4
    ),
    DOWN_VISIBLE(
            new Quaternionf(1, 0, 1, 0),
            new Quaternionf(0, 1, 1, 0),
            scale -> -0.1125f * scale * 0
    ),
    EAST_VISIBLE(
            new Quaternionf(0, 1, 0, 1),
            new Quaternionf(0, 1, 0, 0),
            scale -> 0.1125f * scale * 2,
            scale -> scale * 4
    ),
    WEST_VISIBLE(
            new Quaternionf(0, 1, 0, 1),
            new Quaternionf(0, 0, 1, 1),
            scale -> -0.1375f * scale * 4
    );

    private final Quaternionf leftRotation;
    private final Quaternionf rightRotation;
    private Function<Float, Float> translationHandler;
    private Function<Float, Float> scaleHandler;

    ColorBlock(@NotNull Quaternionf leftRotation, @NotNull Quaternionf right_rotation, @NotNull Function<Float, Float> translationHandler, @NotNull Function<Float, Float> scaleHandler) {
        this.leftRotation = leftRotation;
        this.rightRotation = right_rotation;
        this.translationHandler = translationHandler;
        this.scaleHandler = scaleHandler;
    }

    ColorBlock(@NotNull Quaternionf leftRotation, @NotNull Quaternionf right_rotation, @NotNull Function<Float, Float> translationHandler) {
        this(leftRotation, right_rotation, translationHandler, scale -> scale);
    }

    // For test
    public ColorBlock setTranslationHandler(@NotNull Function<Float, Float> translationHandler) {
        this.translationHandler = translationHandler;
        return this;
    }

    // For test
    public ColorBlock setScaleHandler(@NotNull Function<Float, Float> scaleHandler) {
        this.scaleHandler = scaleHandler;
        return this;
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
                .setSize(scaleHandler.apply(scale[0]), scaleHandler.apply(scale[1]), scaleHandler.apply(scale[2]))
                .setTranslation(getTranslation(scale[0], scale[1], scale[2]))
                .backgroundColor(color)
                .text(Component.text("你"))
                .textOpacity((byte) 5)
                .brightness(new Display.Brightness(15, 15));

        if (textureHandler != null) {
            textureHandler.apply(corners, builder);
        }

        generate(corners, builder);
    }

    public @NotNull Vector3f getTranslation(float scaleX, float scaleY, float scaleZ) {
        return switch (this) {
            case SOUTH_VISIBLE -> new Vector3f(translationHandler.apply(scaleX), 0f, -scaleZ);
            case NORTH_VISIBLE, WEST_VISIBLE -> new Vector3f(0f, translationHandler.apply(scaleY), 0f);
            case DOWN_VISIBLE, EAST_VISIBLE -> new Vector3f(0f, 0f, translationHandler.apply(scaleZ));
            case UP_VISIBLE -> new Vector3f(0f, scaleY, translationHandler.apply(scaleZ));
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
