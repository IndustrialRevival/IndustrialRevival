package org.irmc.industrialrevival.api.objects.display;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pose;
import org.bukkit.entity.TextDisplay;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.LazyMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

@Getter
public class BlockModelBuilder {
    private final @NotNull BlockDisplay display;

    public BlockModelBuilder(@NotNull Location location) {
        display = location.getWorld().spawn(location, BlockDisplay.class);
    }

    public BlockDisplay build() {
        return display;
    }


    // Block Display methods
    public @NotNull BlockModelBuilder block(@NotNull BlockData blockData) {
        display.setBlock(blockData);
        return this;
    }

    public BlockModelBuilder block(@NotNull Block block) {
        return block(block.getBlockData());
    }

    public BlockModelBuilder block(@NotNull Location location) {
        return block(location.getBlock());
    }

    public BlockModelBuilder block(@NotNull Material material) {
        if (!material.isBlock()) {
            throw new IllegalArgumentException("Material must be a block");
        }

        return block(material.createBlockData());
    }

    // Display methods
    public @NotNull BlockModelBuilder transformation(@NotNull Transformation transformation) {
        display.setTransformation(transformation);
        return this;
    }

    public @NotNull BlockModelBuilder interpolationDuration(int duration) {
        display.setInterpolationDuration(duration);
        return this;
    }

    public @NotNull BlockModelBuilder teleportDuration(int duration) {
        display.setTeleportDuration(duration);
        return this;
    }

    public @NotNull BlockModelBuilder viewRange(float viewRange) {
        display.setViewRange(viewRange);
        return this;
    }

    public @NotNull BlockModelBuilder shadowRadius(float shadowRadius) {
        display.setShadowRadius(shadowRadius);
        return this;
    }

    public @NotNull BlockModelBuilder shadowStrength(float shadowStrength) {
        display.setShadowStrength(shadowStrength);
        return this;
    }

    public @NotNull BlockModelBuilder displayWidth(float displayWidth) {
        display.setDisplayWidth(displayWidth);
        return this;
    }

    public @NotNull BlockModelBuilder displayHeight(float displayHeight) {
        display.setDisplayHeight(displayHeight);
        return this;
    }

    public @NotNull BlockModelBuilder interpolationDelay(int delay) {
        display.setInterpolationDelay(delay);
        return this;
    }

    public @NotNull BlockModelBuilder billboard(Display.@NotNull Billboard billboard) {
        display.setBillboard(billboard);
        return this;
    }

    public @NotNull BlockModelBuilder glowColorOverride(int r, int g, int b) {
        display.setGlowColorOverride(Color.fromRGB(r, g, b));
        return this;
    }

    public @NotNull BlockModelBuilder glowColorOverride(int color) {
        display.setGlowColorOverride(Color.fromRGB(color));
        return this;
    }

    public @NotNull BlockModelBuilder glowColorOverride(Color color) {
        display.setGlowColorOverride(color);
        return this;
    }

    public @NotNull BlockModelBuilder brightness(Display.Brightness brightness) {
        display.setBrightness(brightness);
        return this;
    }

    // Entity methods
    public @NotNull BlockModelBuilder velocity(@NotNull Vector velocity) {
        display.setVelocity(velocity);
        return this;
    }

    public @NotNull BlockModelBuilder rotation(float yaw, float pitch) {
        display.setRotation(yaw, pitch);
        return this;
    }

    public @NotNull BlockModelBuilder teleport(@NotNull Location location) {
        display.teleport(location);
        return this;
    }

    public @NotNull BlockModelBuilder fireTicks(int ticks) {
        display.setFireTicks(ticks);
        return this;
    }

    public @NotNull BlockModelBuilder visualFire(boolean visualFire) {
        display.setVisualFire(visualFire);
        return this;
    }

    public @NotNull BlockModelBuilder freezeTicks(int ticks) {
        display.setFreezeTicks(ticks);
        return this;
    }

    public @NotNull BlockModelBuilder invisible(boolean invisible) {
        display.setInvisible(invisible);
        return this;
    }

    public @NotNull BlockModelBuilder noPhysics(boolean noPhysics) {
        display.setNoPhysics(noPhysics);
        return this;
    }

    public @NotNull BlockModelBuilder lockFreezeTicks(boolean lockFreezeTicks) {
        display.lockFreezeTicks(lockFreezeTicks);
        return this;
    }

    public @NotNull BlockModelBuilder persistent(boolean persistent) {
        display.setPersistent(persistent);
        return this;
    }

    public @NotNull BlockModelBuilder passenger(@NotNull Entity passenger) {
        display.addPassenger(passenger);
        return this;
    }

    public @NotNull BlockModelBuilder fallDistance(float fallDistance) {
        display.setFallDistance(fallDistance);
        return this;
    }

    public @NotNull BlockModelBuilder ticksLived(int tickLived) {
        display.setTicksLived(tickLived);
        return this;
    }

    public @NotNull BlockModelBuilder customNameVisible(boolean customNameVisible) {
        display.setCustomNameVisible(customNameVisible);
        return this;
    }

    public @NotNull BlockModelBuilder visibleByDefault(boolean visibleByDefault) {
        display.setVisibleByDefault(visibleByDefault);
        return this;
    }

    public @NotNull BlockModelBuilder glowing(boolean glowing) {
        display.setGlowing(glowing);
        return this;
    }

    public @NotNull BlockModelBuilder invulnerable(boolean invulnerable) {
        display.setInvulnerable(invulnerable);
        return this;
    }

    public @NotNull BlockModelBuilder silent(boolean silent) {
        display.setSilent(silent);
        return this;
    }

    public @NotNull BlockModelBuilder gravity(boolean gravity) {
        display.setGravity(gravity);
        return this;
    }

    public @NotNull BlockModelBuilder portalCooldown(int cooldown) {
        display.setPortalCooldown(cooldown);
        return this;
    }

    public @NotNull BlockModelBuilder scoreboardTags(@NotNull String tag) {
        display.addScoreboardTag(tag);
        return this;
    }

    public @NotNull BlockModelBuilder sneaking(boolean sneaking) {
        display.setSneaking(sneaking);
        return this;
    }

    public @NotNull BlockModelBuilder pose(@NotNull Pose pose, boolean fixed) {
        display.setPose(pose, fixed);
        return this;
    }

    // Metadatable methods
    public @NotNull BlockModelBuilder metaData(@NotNull String key, @NotNull MetadataValue value) {
        display.setMetadata(key, value);
        return this;
    }

    public @NotNull BlockModelBuilder fixedMetaData(@NotNull Plugin plugin, @NotNull String key, Object object) {
        FixedMetadataValue value = new FixedMetadataValue(plugin, object);
        display.setMetadata(key, value);
        return this;
    }

    public @NotNull BlockModelBuilder lazyMetaData(@NotNull Plugin plugin, @NotNull String key, Object object) {
        display.setMetadata(key, new LazyMetadataValue(plugin, () -> object));
        return this;
    }

    public @NotNull BlockModelBuilder lazyMetaData(@NotNull Plugin plugin, @NotNull String key, LazyMetadataValue.@NotNull CacheStrategy strategy, Object object) {
        display.setMetadata(key, new LazyMetadataValue(plugin, strategy, () -> object));
        return this;
    }

    // Nameable methods
    public @NotNull BlockModelBuilder customName(Component name) {
        display.customName(name);
        return this;
    }
}
