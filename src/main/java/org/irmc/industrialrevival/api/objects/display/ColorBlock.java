package org.irmc.industrialrevival.api.objects.display;

import com.google.common.base.Preconditions;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;
import org.irmc.industrialrevival.api.objects.display.builder.TextModelBuilder;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author baluagq
 */
@Getter
public enum ColorBlock {
    NORTH_VISIBLE(
            Component.text("北"), //! DO NOT TRANSLATE
            new Quaternionf().identity(),
            new Quaternionf().identity(),
            scale -> -scale * 0.55f,
            scale -> scale * 4f
    ),
    SOUTH_VISIBLE(
            Component.text("南"), //! DO NOT TRANSLATE
            new Quaternionf().rotationX((float) Math.toRadians(180)),
            new Quaternionf().identity(),
            scale -> -scale * 0.55f,
            scale -> scale * 4f
    ),
    UP_VISIBLE(
            Component.text("上"), //! DO NOT TRANSLATE
            new Quaternionf().rotationX((float) Math.toRadians(-90)),
            new Quaternionf().identity(),
            scale -> scale * 0.45f,
            scale -> scale * 4f
    ),
    DOWN_VISIBLE(
            Component.text("下"), //! DO NOT TRANSLATE
            new Quaternionf().rotationX((float) Math.toRadians(90)),
            new Quaternionf().identity(),
            scale -> scale * 0.45f,
            scale -> scale * 4f
    ),
    EAST_VISIBLE(
            Component.text("东"), //! DO NOT TRANSLATE
            new Quaternionf().rotationY((float) Math.toRadians(-90)),
            new Quaternionf().identity(),
            scale -> scale * 0.45f,
            scale -> scale * 4f
    ),
    WEST_VISIBLE(
            Component.text("西"), //! DO NOT TRANSLATE
            new Quaternionf().rotationY((float) Math.toRadians(90)),
            new Quaternionf().identity(),
            scale -> scale * 0.55f,
            scale -> scale * 4f
    );

    public static final ColorBlock[] DEFAULT_SURFACE = new ColorBlock[]{
            ColorBlock.NORTH_VISIBLE,
            ColorBlock.SOUTH_VISIBLE,
            ColorBlock.WEST_VISIBLE,
            ColorBlock.EAST_VISIBLE,
            ColorBlock.UP_VISIBLE,
            ColorBlock.DOWN_VISIBLE
    };
    private final Component baseString;
    private final Quaternionf leftRotation;
    private final Quaternionf rightRotation;
    private final Function<Float, Float> translationHandler;
    private final Function<Float, Float> scaleHandler;

    ColorBlock(@NotNull Component baseString, @NotNull Quaternionf leftRotation, @NotNull Quaternionf right_rotation, @NotNull Function<Float, Float> translationHandler, @NotNull Function<Float, Float> scaleHandler) {
        this.baseString = baseString;
        this.leftRotation = leftRotation;
        this.rightRotation = right_rotation;
        this.translationHandler = translationHandler;
        this.scaleHandler = scaleHandler;
    }

    ColorBlock(@NotNull Component baseString, @NotNull Quaternionf leftRotation, @NotNull Quaternionf right_rotation, @NotNull Function<Float, Float> translationHandler) {
        this(baseString, leftRotation, right_rotation, translationHandler, scale -> scale);
    }

    public static List<TextDisplay> makeSurface(@NotNull Corners corners, @NotNull Color color) {
        return makeSurface(corners, color, null);
    }

    public static List<TextDisplay> makeSurface(@NotNull Corners corners, @NotNull Color color, @Nullable TextureHandler textureHandler) {
        List<TextDisplay> displays = new ArrayList<>();
        for (ColorBlock value : DEFAULT_SURFACE) {
            displays.add(value.make(corners, color, textureHandler));
        }

        return displays;
    }

    public static List<TextDisplay> makeLiquid(@NotNull Corners corners, @NotNull Color color, @Nullable TextureHandler textureHandler) {
        Location tmp = new Location(corners.getWorld(), 0, 0, 0);
        for (double x = corners.getMinX(); x < corners.getMaxX(); x += 1) {
            for (double y = corners.getMinY(); y < corners.getMaxY(); y += 1) {
                for (double z = corners.getMinZ(); z < corners.getMaxZ(); z += 1) {
                    tmp.set(x, y, z).getBlock().setType(Material.LAVA);
                }
            }
        }
        return makeSurface(corners, color, textureHandler);
    }

    public void make(@NotNull Block block, @NotNull Color color) {
        make(block, color, null);
    }

    public void make(@NotNull Corners corners, @NotNull Color color) {
        make(corners, color, null);
    }

    public void make(@NotNull Block block, @NotNull Color color, @Nullable TextureHandler textureHandler) {
        make(Corners.of(block), color, textureHandler);
    }

    public TextDisplay make(@NotNull Corners corners, @NotNull Color color, @Nullable TextureHandler textureHandler) {
        Preconditions.checkArgument(corners != null, "Corners cannot be null");
        Preconditions.checkArgument(color != null, "Color cannot be null");
        Preconditions.checkArgument(corners.getWorld() != null, "World cannot be null");

        float scaleX = corners.getDistanceX();
        float scaleY = corners.getDistanceY();
        float scaleZ = corners.getDistanceZ();
        var builder = new TextModelBuilder()
                .setLeftRotation(leftRotation)
                .setRightRotation(rightRotation)
                .setSize(getSize(scaleX, scaleY, scaleZ))
                .setTranslation(getTranslation(scaleX, scaleY, scaleZ))
                .backgroundColor(color)
                .text(this.getBaseString())
                .textOpacity((byte) 5)
                .brightness(new Display.Brightness(15, 15));

        if (textureHandler != null) {
            textureHandler.accept(corners, builder);
        }

        return generate(corners, builder);
    }

    public @NotNull Vector3f getTranslation(float scaleX, float scaleY, float scaleZ) {
        return switch (this) {
            case NORTH_VISIBLE -> new Vector3f(translationHandler.apply(scaleX), 0f, 0f);
            case SOUTH_VISIBLE -> new Vector3f(translationHandler.apply(scaleX), 0f, 0f);
            case WEST_VISIBLE -> new Vector3f(0f, 0f, translationHandler.apply(scaleZ));
            case EAST_VISIBLE -> new Vector3f(0f, 0f, translationHandler.apply(scaleZ));
            case UP_VISIBLE -> new Vector3f(translationHandler.apply(scaleX), 0f, 0f);
            case DOWN_VISIBLE -> new Vector3f(translationHandler.apply(scaleX), 0f, 0f);
            default -> new Vector3f(0f, 0f, 0f);
        };
    }

    public @NotNull Vector3f getSize(float scaleX, float scaleY, float scaleZ) {
        float fixedScaleX = scaleHandler.apply(scaleX);
        float fixedScaleY = scaleHandler.apply(scaleY);
        float fixedScaleZ = scaleHandler.apply(scaleZ);
        return switch (this) {
            case UP_VISIBLE -> new Vector3f(fixedScaleX, fixedScaleZ, 0f);
            case DOWN_VISIBLE -> new Vector3f(fixedScaleX, fixedScaleZ, 0f);
            case NORTH_VISIBLE -> new Vector3f(fixedScaleX, fixedScaleY, 0f);
            case SOUTH_VISIBLE -> new Vector3f(fixedScaleX, fixedScaleY, 0f);
            case WEST_VISIBLE -> new Vector3f(fixedScaleZ, fixedScaleY, 0f);
            case EAST_VISIBLE -> new Vector3f(fixedScaleZ, fixedScaleY, 0f);
            default -> new Vector3f();
        };
    }

    private TextDisplay generate(@NotNull Corners corners, @NotNull TextModelBuilder builder) {
        Location location = null;
        switch (this) {
            case UP_VISIBLE ->
                    location = new Location(corners.getWorld(), corners.getMinX(), corners.getMaxY(), corners.getMaxZ());
            case SOUTH_VISIBLE ->
                    location = new Location(corners.getWorld(), corners.getMaxX(), corners.getMaxY(), corners.getMinZ());
            case WEST_VISIBLE ->
                    location = new Location(corners.getWorld(), corners.getMaxX(), corners.getMinY(), corners.getMinZ());
            case NORTH_VISIBLE ->
                    location = new Location(corners.getWorld(), corners.getMaxX(), corners.getMinY(), corners.getMaxZ());
            default ->
                    location = new Location(corners.getWorld(), corners.getMinX(), corners.getMinY(), corners.getMinZ());
        }

        if (location == null) {
            return null;
        }

        return builder.buildAt(location);
    }
}