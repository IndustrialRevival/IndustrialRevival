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
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Pose;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.LazyMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

@Getter
public class ItemModelBuilder {
    private final @NotNull ItemDisplay display;

    public ItemModelBuilder(@NotNull Location location) {
        display = location.getWorld().spawn(location, ItemDisplay.class);
    }

    public ItemDisplay build() {
        return display;
    }

    // Item Display methods
    public @NotNull ItemModelBuilder itemStack(ItemStack itemStack) {
        display.setItemStack(itemStack);
        return this;
    }

    public @NotNull ItemModelBuilder itemDisplayTransform(ItemDisplay.@NotNull ItemDisplayTransform transform) {
        display.setItemDisplayTransform(transform);
        return this;
    }

    public ItemModelBuilder transform(ItemDisplay.@NotNull ItemDisplayTransform transform) {
        return itemDisplayTransform(transform);
    }

    // Display methods
    public @NotNull ItemModelBuilder transformation(@NotNull Transformation transformation) {
        display.setTransformation(transformation);
        return this;
    }

    public @NotNull ItemModelBuilder interpolationDuration(int duration) {
        display.setInterpolationDuration(duration);
        return this;
    }

    public @NotNull ItemModelBuilder teleportDuration(int duration) {
        display.setTeleportDuration(duration);
        return this;
    }

    public @NotNull ItemModelBuilder viewRange(float viewRange) {
        display.setViewRange(viewRange);
        return this;
    }

    public @NotNull ItemModelBuilder shadowRadius(float shadowRadius) {
        display.setShadowRadius(shadowRadius);
        return this;
    }

    public @NotNull ItemModelBuilder shadowStrength(float shadowStrength) {
        display.setShadowStrength(shadowStrength);
        return this;
    }

    public @NotNull ItemModelBuilder displayWidth(float displayWidth) {
        display.setDisplayWidth(displayWidth);
        return this;
    }

    public @NotNull ItemModelBuilder displayHeight(float displayHeight) {
        display.setDisplayHeight(displayHeight);
        return this;
    }

    public @NotNull ItemModelBuilder interpolationDelay(int delay) {
        display.setInterpolationDelay(delay);
        return this;
    }

    public @NotNull ItemModelBuilder billboard(Display.@NotNull Billboard billboard) {
        display.setBillboard(billboard);
        return this;
    }

    public @NotNull ItemModelBuilder glowColorOverride(int r, int g, int b) {
        display.setGlowColorOverride(Color.fromRGB(r, g, b));
        return this;
    }

    public @NotNull ItemModelBuilder glowColorOverride(int color) {
        display.setGlowColorOverride(Color.fromRGB(color));
        return this;
    }

    public @NotNull ItemModelBuilder glowColorOverride(Color color) {
        display.setGlowColorOverride(color);
        return this;
    }

    public @NotNull ItemModelBuilder brightness(Display.Brightness brightness) {
        display.setBrightness(brightness);
        return this;
    }

    // Entity methods
    public @NotNull ItemModelBuilder velocity(@NotNull Vector velocity) {
        display.setVelocity(velocity);
        return this;
    }

    public @NotNull ItemModelBuilder rotation(float yaw, float pitch) {
        display.setRotation(yaw, pitch);
        return this;
    }

    public @NotNull ItemModelBuilder teleport(@NotNull Location location) {
        display.teleport(location);
        return this;
    }

    public @NotNull ItemModelBuilder fireTicks(int ticks) {
        display.setFireTicks(ticks);
        return this;
    }

    public @NotNull ItemModelBuilder visualFire(boolean visualFire) {
        display.setVisualFire(visualFire);
        return this;
    }

    public @NotNull ItemModelBuilder freezeTicks(int ticks) {
        display.setFreezeTicks(ticks);
        return this;
    }

    public @NotNull ItemModelBuilder invisible(boolean invisible) {
        display.setInvisible(invisible);
        return this;
    }

    public @NotNull ItemModelBuilder noPhysics(boolean noPhysics) {
        display.setNoPhysics(noPhysics);
        return this;
    }

    public @NotNull ItemModelBuilder lockFreezeTicks(boolean lockFreezeTicks) {
        display.lockFreezeTicks(lockFreezeTicks);
        return this;
    }

    public @NotNull ItemModelBuilder persistent(boolean persistent) {
        display.setPersistent(persistent);
        return this;
    }

    public @NotNull ItemModelBuilder passenger(@NotNull Entity passenger) {
        display.addPassenger(passenger);
        return this;
    }

    public @NotNull ItemModelBuilder fallDistance(float fallDistance) {
        display.setFallDistance(fallDistance);
        return this;
    }

    public @NotNull ItemModelBuilder ticksLived(int tickLived) {
        display.setTicksLived(tickLived);
        return this;
    }

    public @NotNull ItemModelBuilder customNameVisible(boolean customNameVisible) {
        display.setCustomNameVisible(customNameVisible);
        return this;
    }

    public @NotNull ItemModelBuilder visibleByDefault(boolean visibleByDefault) {
        display.setVisibleByDefault(visibleByDefault);
        return this;
    }

    public @NotNull ItemModelBuilder glowing(boolean glowing) {
        display.setGlowing(glowing);
        return this;
    }

    public @NotNull ItemModelBuilder invulnerable(boolean invulnerable) {
        display.setInvulnerable(invulnerable);
        return this;
    }

    public @NotNull ItemModelBuilder silent(boolean silent) {
        display.setSilent(silent);
        return this;
    }

    public @NotNull ItemModelBuilder gravity(boolean gravity) {
        display.setGravity(gravity);
        return this;
    }

    public @NotNull ItemModelBuilder portalCooldown(int cooldown) {
        display.setPortalCooldown(cooldown);
        return this;
    }

    public @NotNull ItemModelBuilder scoreboardTags(@NotNull String tag) {
        display.addScoreboardTag(tag);
        return this;
    }

    public @NotNull ItemModelBuilder sneaking(boolean sneaking) {
        display.setSneaking(sneaking);
        return this;
    }

    public @NotNull ItemModelBuilder pose(@NotNull Pose pose, boolean fixed) {
        display.setPose(pose, fixed);
        return this;
    }

    // Metadatable methods
    public @NotNull ItemModelBuilder metaData(@NotNull String key, @NotNull MetadataValue value) {
        display.setMetadata(key, value);
        return this;
    }

    public @NotNull ItemModelBuilder fixedMetaData(@NotNull Plugin plugin, @NotNull String key, Object object) {
        FixedMetadataValue value = new FixedMetadataValue(plugin, object);
        display.setMetadata(key, value);
        return this;
    }

    public @NotNull ItemModelBuilder lazyMetaData(@NotNull Plugin plugin, @NotNull String key, Object object) {
        display.setMetadata(key, new LazyMetadataValue(plugin, () -> object));
        return this;
    }

    public @NotNull ItemModelBuilder lazyMetaData(@NotNull Plugin plugin, @NotNull String key, LazyMetadataValue.@NotNull CacheStrategy strategy, Object object) {
        display.setMetadata(key, new LazyMetadataValue(plugin, strategy, () -> object));
        return this;
    }

    // Nameable methods
    public @NotNull ItemModelBuilder customName(Component name) {
        display.customName(name);
        return this;
    }
}
