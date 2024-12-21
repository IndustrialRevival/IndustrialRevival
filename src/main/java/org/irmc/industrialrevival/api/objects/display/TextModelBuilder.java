package org.irmc.industrialrevival.api.objects.display;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Location;
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
public class TextModelBuilder {
    private final @NotNull TextDisplay display;

    public TextModelBuilder(@NotNull Location location) {
        display = location.getWorld().spawn(location, TextDisplay.class);
    }

    public TextDisplay build() {
        return display;
    }

    // Text Display methods
    public @NotNull TextModelBuilder text(Component text) {
        display.text(text);
        return this;
    }

    public @NotNull TextModelBuilder lineWidth(int width) {
        display.setLineWidth(width);
        return this;
    }
    
    public @NotNull TextModelBuilder backgroundColor(Color color) {
        display.setBackgroundColor(color);
        return this;
    }

    public @NotNull TextModelBuilder backgroundColor(int color) {
        display.setBackgroundColor(Color.fromRGB(color));
        return this;
    }

    public @NotNull TextModelBuilder backgroundColor(int r, int g, int b) {
        display.setBackgroundColor(Color.fromRGB(r, g, b));
        return this;
    }

    public @NotNull TextModelBuilder textOpacity(byte opacity) {
        display.setTextOpacity(opacity);
        return this;
    }

    public @NotNull TextModelBuilder shadowed(boolean shadowed) {
        display.setShadowed(shadowed);
        return this;
    }

    public @NotNull TextModelBuilder seeThrough(boolean seeThrough) {
        display.setSeeThrough(seeThrough);
        return this;
    }

    public @NotNull TextModelBuilder defaultBackground(boolean defaultBackground) {
        display.setDefaultBackground(defaultBackground);
        return this;
    }

    public @NotNull TextModelBuilder alignment(TextDisplay.@NotNull TextAlignment alignment) {
        display.setAlignment(alignment);
        return this;
    }


    // Display methods
    public @NotNull TextModelBuilder transformation(@NotNull Transformation transformation) {
        display.setTransformation(transformation);
        return this;
    }

    public @NotNull TextModelBuilder interpolationDuration(int duration) {
        display.setInterpolationDuration(duration);
        return this;
    }

    public @NotNull TextModelBuilder teleportDuration(int duration) {
        display.setTeleportDuration(duration);
        return this;
    }

    public @NotNull TextModelBuilder viewRange(float viewRange) {
        display.setViewRange(viewRange);
        return this;
    }

    public @NotNull TextModelBuilder shadowRadius(float shadowRadius) {
        display.setShadowRadius(shadowRadius);
        return this;
    }

    public @NotNull TextModelBuilder shadowStrength(float shadowStrength) {
        display.setShadowStrength(shadowStrength);
        return this;
    }

    public @NotNull TextModelBuilder displayWidth(float displayWidth) {
        display.setDisplayWidth(displayWidth);
        return this;
    }

    public @NotNull TextModelBuilder displayHeight(float displayHeight) {
        display.setDisplayHeight(displayHeight);
        return this;
    }

    public @NotNull TextModelBuilder interpolationDelay(int delay) {
        display.setInterpolationDelay(delay);
        return this;
    }

    public @NotNull TextModelBuilder billboard(Display.@NotNull Billboard billboard) {
        display.setBillboard(billboard);
        return this;
    }

    public @NotNull TextModelBuilder glowColorOverride(int r, int g, int b) {
        display.setGlowColorOverride(Color.fromRGB(r, g, b));
        return this;
    }

    public @NotNull TextModelBuilder glowColorOverride(int color) {
        display.setGlowColorOverride(Color.fromRGB(color));
        return this;
    }

    public @NotNull TextModelBuilder glowColorOverride(Color color) {
        display.setGlowColorOverride(color);
        return this;
    }

    public @NotNull TextModelBuilder brightness(Display.Brightness brightness) {
        display.setBrightness(brightness);
        return this;
    }

    // Entity methods
    public @NotNull TextModelBuilder velocity(@NotNull Vector velocity) {
        display.setVelocity(velocity);
        return this;
    }

    public @NotNull TextModelBuilder rotation(float yaw, float pitch) {
        display.setRotation(yaw, pitch);
        return this;
    }

    public @NotNull TextModelBuilder teleport(@NotNull Location location) {
        display.teleport(location);
        return this;
    }

    public @NotNull TextModelBuilder fireTicks(int ticks) {
        display.setFireTicks(ticks);
        return this;
    }

    public @NotNull TextModelBuilder visualFire(boolean visualFire) {
        display.setVisualFire(visualFire);
        return this;
    }

    public @NotNull TextModelBuilder freezeTicks(int ticks) {
        display.setFreezeTicks(ticks);
        return this;
    }

    public @NotNull TextModelBuilder invisible(boolean invisible) {
        display.setInvisible(invisible);
        return this;
    }

    public @NotNull TextModelBuilder noPhysics(boolean noPhysics) {
        display.setNoPhysics(noPhysics);
        return this;
    }

    public @NotNull TextModelBuilder lockFreezeTicks(boolean lockFreezeTicks) {
        display.lockFreezeTicks(lockFreezeTicks);
        return this;
    }

    public @NotNull TextModelBuilder persistent(boolean persistent) {
        display.setPersistent(persistent);
        return this;
    }

    public @NotNull TextModelBuilder passenger(@NotNull Entity passenger) {
        display.addPassenger(passenger);
        return this;
    }

    public @NotNull TextModelBuilder fallDistance(float fallDistance) {
        display.setFallDistance(fallDistance);
        return this;
    }

    public @NotNull TextModelBuilder ticksLived(int tickLived) {
        display.setTicksLived(tickLived);
        return this;
    }

    public @NotNull TextModelBuilder customNameVisible(boolean customNameVisible) {
        display.setCustomNameVisible(customNameVisible);
        return this;
    }

    public @NotNull TextModelBuilder visibleByDefault(boolean visibleByDefault) {
        display.setVisibleByDefault(visibleByDefault);
        return this;
    }

    public @NotNull TextModelBuilder glowing(boolean glowing) {
        display.setGlowing(glowing);
        return this;
    }

    public @NotNull TextModelBuilder invulnerable(boolean invulnerable) {
        display.setInvulnerable(invulnerable);
        return this;
    }

    public @NotNull TextModelBuilder silent(boolean silent) {
        display.setSilent(silent);
        return this;
    }

    public @NotNull TextModelBuilder gravity(boolean gravity) {
        display.setGravity(gravity);
        return this;
    }

    public @NotNull TextModelBuilder portalCooldown(int cooldown) {
        display.setPortalCooldown(cooldown);
        return this;
    }

    public @NotNull TextModelBuilder scoreboardTags(@NotNull String tag) {
        display.addScoreboardTag(tag);
        return this;
    }

    public @NotNull TextModelBuilder sneaking(boolean sneaking) {
        display.setSneaking(sneaking);
        return this;
    }

    public @NotNull TextModelBuilder pose(@NotNull Pose pose, boolean fixed) {
        display.setPose(pose, fixed);
        return this;
    }

    // Metadatable methods
    public @NotNull TextModelBuilder metaData(@NotNull String key, @NotNull MetadataValue value) {
        display.setMetadata(key, value);
        return this;
    }

    public @NotNull TextModelBuilder fixedMetaData(@NotNull Plugin plugin, @NotNull String key, Object object) {
        FixedMetadataValue value = new FixedMetadataValue(plugin, object);
        display.setMetadata(key, value);
        return this;
    }

    public @NotNull TextModelBuilder lazyMetaData(@NotNull Plugin plugin, @NotNull String key, Object object) {
        display.setMetadata(key, new LazyMetadataValue(plugin, () -> object));
        return this;
    }

    public @NotNull TextModelBuilder lazyMetaData(@NotNull Plugin plugin, @NotNull String key, LazyMetadataValue.@NotNull CacheStrategy strategy, Object object) {
        display.setMetadata(key, new LazyMetadataValue(plugin, strategy, () -> object));
        return this;
    }

    // Nameable methods
    public @NotNull TextModelBuilder customName(Component name) {
        display.customName(name);
        return this;
    }
}
