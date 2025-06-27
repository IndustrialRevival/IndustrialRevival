package org.irmc.industrialrevival.api.objects.display.builder;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
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
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Getter
public class TextModelBuilder extends AbstractModelBuilder implements Cloneable {
    private TransformationBuilder transformationBuilder;
    private Component text;
    private Integer lineWidth;
    private Color backgroundColor;
    private Byte textOpacity;
    private Boolean shadowed;
    private Boolean seeThrough;
    private Boolean defaultBackground;
    private TextDisplay.TextAlignment alignment;
    private Transformation transformation;
    private Integer interpolationDuration;
    private Integer teleportDuration;
    private Float viewRange;
    private Float shadowRadius;
    private Float shadowStrength;
    private Float displayWidth;
    private Float displayHeight;
    private Integer interpolationDelay;
    private Display.Billboard billboard;
    private Color glowColorOverride;
    private Display.Brightness brightness;
    private Vector velocity;
    private Float yaw;
    private Float pitch;
    private Integer fireTicks;
    private Boolean visualFire;
    private Integer freezeTicks;
    private Boolean invisible;
    private Boolean noPhysics;
    private Boolean lockFreezeTicks;
    private Boolean persistent;
    private List<Entity> passengers;
    private Float fallDistance;
    private Integer ticksLived;
    private Boolean customNameVisible;
    private Boolean visibleByDefault;
    private Boolean glowing;
    private Boolean invulnerable;
    private Boolean silent;
    private Boolean gravity;
    private Integer portalCooldown;
    private Set<String> scoreboardTag;
    private Boolean sneaking;
    private Pose pose;
    private Boolean fixedPose;
    private Map<String, List<MetadataValue>> metadata;
    private Component customName;

    public TextModelBuilder() {
    }

    public void ifPresent(@Nullable Object object, @NotNull Runnable runnable) {
        if (object != null) {
            runnable.run();
        }
    }

    public @NotNull TextModelBuilder clone() {
        TextModelBuilder clone = new TextModelBuilder();
        clone.transformationBuilder = this.transformationBuilder;
        clone.text = Component.text(((TextComponent) this.text).content());
        clone.lineWidth = this.lineWidth;
        clone.backgroundColor = this.backgroundColor;
        clone.textOpacity = this.textOpacity;
        clone.shadowed = this.shadowed;
        clone.seeThrough = this.seeThrough;
        clone.defaultBackground = this.defaultBackground;
        clone.alignment = this.alignment;
        clone.transformation = this.transformation;
        clone.interpolationDuration = this.interpolationDuration;
        clone.teleportDuration = this.teleportDuration;
        clone.viewRange = this.viewRange;
        clone.shadowRadius = this.shadowRadius;
        clone.shadowStrength = this.shadowStrength;
        clone.displayWidth = this.displayWidth;
        clone.displayHeight = this.displayHeight;
        clone.interpolationDelay = this.interpolationDelay;
        clone.billboard = this.billboard;
        clone.glowColorOverride = this.glowColorOverride;
        clone.brightness = this.brightness;
        clone.velocity = this.velocity;
        clone.yaw = this.yaw;
        clone.pitch = this.pitch;
        clone.fireTicks = this.fireTicks;
        clone.visualFire = this.visualFire;
        clone.freezeTicks = this.freezeTicks;
        clone.invisible = this.invisible;
        clone.noPhysics = this.noPhysics;
        clone.lockFreezeTicks = this.lockFreezeTicks;
        clone.persistent = this.persistent;
        clone.passengers = new ArrayList<>(this.passengers);
        clone.fallDistance = this.fallDistance;
        clone.ticksLived = this.ticksLived;
        clone.customNameVisible = this.customNameVisible;
        clone.visibleByDefault = this.visibleByDefault;
        clone.glowing = this.glowing;
        clone.invulnerable = this.invulnerable;
        clone.silent = this.silent;
        clone.gravity = this.gravity;
        clone.portalCooldown = this.portalCooldown;
        clone.scoreboardTag = new HashSet<>(this.scoreboardTag);
        clone.sneaking = this.sneaking;
        clone.pose = this.pose;
        clone.fixedPose = this.fixedPose;
        clone.metadata = new HashMap<>(this.metadata);
        clone.customName = this.customName;
        return clone;
    }

    public @NotNull TextModelBuilder build() {
        return clone();
    }

    public @NotNull TextDisplay buildAt(@NotNull Location location) {
        try {
            return Bukkit.getScheduler().callSyncMethod(IRDock.getPlugin(), () ->
                    location.getWorld().spawn(location, TextDisplay.class, display -> {
                        //<editor-fold> desc="args"
                        ifPresent(this.text, () -> display.text(this.text));
                        ifPresent(this.lineWidth, () -> display.setLineWidth(this.lineWidth));
                        ifPresent(this.backgroundColor, () -> display.setBackgroundColor(this.backgroundColor));
                        ifPresent(this.textOpacity, () -> display.setTextOpacity(this.textOpacity));
                        ifPresent(this.shadowed, () -> display.setShadowed(this.shadowed));
                        ifPresent(this.seeThrough, () -> display.setSeeThrough(this.seeThrough));
                        ifPresent(this.defaultBackground, () -> display.setDefaultBackground(this.defaultBackground));
                        ifPresent(this.alignment, () -> display.setAlignment(this.alignment));
                        ifPresent(this.transformation, () -> display.setTransformation(this.transformation));
                        ifPresent(this.interpolationDuration, () -> display.setInterpolationDuration(this.interpolationDuration));
                        ifPresent(this.teleportDuration, () -> display.setTeleportDuration(this.teleportDuration));
                        ifPresent(this.viewRange, () -> display.setViewRange(this.viewRange));
                        ifPresent(this.shadowRadius, () -> display.setShadowRadius(this.shadowRadius));
                        ifPresent(this.shadowStrength, () -> display.setShadowStrength(this.shadowStrength));
                        ifPresent(this.displayWidth, () -> display.setDisplayWidth(this.displayWidth));
                        ifPresent(this.displayHeight, () -> display.setDisplayHeight(this.displayHeight));
                        ifPresent(this.interpolationDelay, () -> display.setInterpolationDelay(this.interpolationDelay));
                        ifPresent(this.billboard, () -> display.setBillboard(this.billboard));
                        ifPresent(this.glowColorOverride, () -> display.setGlowColorOverride(this.glowColorOverride));
                        ifPresent(this.brightness, () -> display.setBrightness(this.brightness));
                        ifPresent(this.velocity, () -> display.setVelocity(this.velocity));
                        ifPresent(this.yaw, () -> display.setRotation(this.yaw, this.pitch));
                        ifPresent(this.pitch, () -> display.setRotation(this.yaw, this.pitch));
                        ifPresent(this.fireTicks, () -> display.setFireTicks(this.fireTicks));
                        ifPresent(this.visualFire, () -> display.setVisualFire(this.visualFire));
                        ifPresent(this.freezeTicks, () -> display.setFreezeTicks(this.freezeTicks));
                        ifPresent(this.invisible, () -> display.setInvisible(this.invisible));
                        ifPresent(this.noPhysics, () -> display.setNoPhysics(this.noPhysics));
                        ifPresent(this.lockFreezeTicks, () -> display.lockFreezeTicks(this.lockFreezeTicks));
                        ifPresent(this.persistent, () -> display.setPersistent(this.persistent));
                        ifPresent(this.passengers, () -> this.passengers.forEach(display::addPassenger));
                        ifPresent(this.fallDistance, () -> display.setFallDistance(this.fallDistance));
                        ifPresent(this.ticksLived, () -> display.setTicksLived(this.ticksLived));
                        ifPresent(this.customNameVisible, () -> display.setCustomNameVisible(this.customNameVisible));
                        ifPresent(this.visibleByDefault, () -> display.setVisibleByDefault(this.visibleByDefault));
                        ifPresent(this.glowing, () -> display.setGlowing(this.glowing));
                        ifPresent(this.invulnerable, () -> display.setInvulnerable(this.invulnerable));
                        ifPresent(this.silent, () -> display.setSilent(this.silent));
                        ifPresent(this.gravity, () -> display.setGravity(this.gravity));
                        ifPresent(this.portalCooldown, () -> display.setPortalCooldown(this.portalCooldown));
                        ifPresent(this.scoreboardTag, () -> this.scoreboardTag.forEach(display::addScoreboardTag));
                        ifPresent(this.sneaking, () -> display.setSneaking(this.sneaking));
                        ifPresent(this.pose, () -> display.setPose(this.pose, this.fixedPose));
                        ifPresent(this.metadata, () -> this.metadata.forEach((key, values) -> values.forEach(value -> display.setMetadata(key, value))));
                        ifPresent(this.customName, () -> display.customName(this.customName));
                        ifPresent(this.transformationBuilder, () -> display.setTransformation(this.transformationBuilder.build()));
                        //</editor-fold>
                    })).get();
        } catch (InterruptedException | ExecutionException e) {
            Debug.error(e);
            return null;
        }
    }

    // Text Display methods
    public @NotNull TextModelBuilder text(Component text) {
        this.text = text;
        return this;
    }

    public @NotNull TextModelBuilder lineWidth(int width) {
        this.lineWidth = width;
        return this;
    }

    public @NotNull TextModelBuilder backgroundColor(Color color) {
        this.backgroundColor = color;
        return this;
    }

    public @NotNull TextModelBuilder backgroundColor(int color) {
        return backgroundColor(Color.fromRGB(color));
    }

    public @NotNull TextModelBuilder backgroundColor(int r, int g, int b) {
        return backgroundColor(Color.fromRGB(r, g, b));
    }

    public @NotNull TextModelBuilder textOpacity(byte opacity) {
        this.textOpacity = opacity;
        return this;
    }

    public @NotNull TextModelBuilder shadowed(boolean shadowed) {
        this.shadowed = shadowed;
        return this;
    }

    public @NotNull TextModelBuilder seeThrough(boolean seeThrough) {
        this.seeThrough = seeThrough;
        return this;
    }

    public @NotNull TextModelBuilder defaultBackground(boolean defaultBackground) {
        this.defaultBackground = defaultBackground;
        return this;
    }

    public @NotNull TextModelBuilder alignment(TextDisplay.@NotNull TextAlignment alignment) {
        this.alignment = alignment;
        return this;
    }


    // Display methods
    public @NotNull TextModelBuilder interpolationDuration(int duration) {
        this.interpolationDuration = duration;
        return this;
    }

    public @NotNull TextModelBuilder teleportDuration(int duration) {
        this.teleportDuration = duration;
        return this;
    }

    public @NotNull TextModelBuilder viewRange(float viewRange) {
        this.viewRange = viewRange;
        return this;
    }

    public @NotNull TextModelBuilder shadowRadius(float shadowRadius) {
        this.shadowRadius = shadowRadius;
        return this;
    }

    public @NotNull TextModelBuilder shadowStrength(float shadowStrength) {
        this.shadowStrength = shadowStrength;
        return this;
    }

    public @NotNull TextModelBuilder displayWidth(float displayWidth) {
        this.displayWidth = displayWidth;
        return this;
    }

    public @NotNull TextModelBuilder displayHeight(float displayHeight) {
        this.displayHeight = displayHeight;
        return this;
    }

    public @NotNull TextModelBuilder interpolationDelay(int delay) {
        this.interpolationDelay = delay;
        return this;
    }

    public @NotNull TextModelBuilder billboard(Display.@NotNull Billboard billboard) {
        this.billboard = billboard;
        return this;
    }

    public @NotNull TextModelBuilder glowColorOverride(Color color) {
        this.glowColorOverride = color;
        return this;
    }

    public @NotNull TextModelBuilder glowColorOverride(int color) {
        return glowColorOverride(Color.fromRGB(color));
    }

    public @NotNull TextModelBuilder glowColorOverride(int r, int g, int b) {
        return glowColorOverride(Color.fromRGB(r, g, b));
    }

    public @NotNull TextModelBuilder brightness(Display.Brightness brightness) {
        this.brightness = brightness;
        return this;
    }

    // Entity methods
    public @NotNull TextModelBuilder velocity(@NotNull Vector velocity) {
        this.velocity = velocity;
        return this;
    }

    public @NotNull TextModelBuilder rotation(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
        return this;
    }

    public @NotNull TextModelBuilder fireTicks(int ticks) {
        this.fireTicks = ticks;
        return this;
    }

    public @NotNull TextModelBuilder visualFire(boolean visualFire) {
        this.visualFire = visualFire;
        return this;
    }

    public @NotNull TextModelBuilder freezeTicks(int ticks) {
        this.freezeTicks = ticks;
        return this;
    }

    public @NotNull TextModelBuilder invisible(boolean invisible) {
        this.invisible = invisible;
        return this;
    }

    public @NotNull TextModelBuilder noPhysics(boolean noPhysics) {
        this.noPhysics = noPhysics;
        return this;
    }

    public @NotNull TextModelBuilder lockFreezeTicks(boolean lockFreezeTicks) {
        this.lockFreezeTicks = lockFreezeTicks;
        return this;
    }

    public @NotNull TextModelBuilder persistent(boolean persistent) {
        this.persistent = persistent;
        return this;
    }

    public @NotNull TextModelBuilder passenger(@NotNull Entity passenger) {
        if (passengers == null) {
            passengers = new ArrayList<>();
        }
        this.passengers.add(passenger);
        return this;
    }

    public @NotNull TextModelBuilder fallDistance(float fallDistance) {
        this.fallDistance = fallDistance;
        return this;
    }

    public @NotNull TextModelBuilder ticksLived(int tickLived) {
        this.ticksLived = tickLived;
        return this;
    }

    public @NotNull TextModelBuilder customNameVisible(boolean customNameVisible) {
        this.customNameVisible = customNameVisible;
        return this;
    }

    public @NotNull TextModelBuilder visibleByDefault(boolean visibleByDefault) {
        this.visibleByDefault = visibleByDefault;
        return this;
    }

    public @NotNull TextModelBuilder glowing(boolean glowing) {
        this.glowing = glowing;
        return this;
    }

    public @NotNull TextModelBuilder invulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
        return this;
    }

    public @NotNull TextModelBuilder silent(boolean silent) {
        this.silent = silent;
        return this;
    }

    public @NotNull TextModelBuilder gravity(boolean gravity) {
        this.gravity = gravity;
        return this;
    }

    public @NotNull TextModelBuilder portalCooldown(int cooldown) {
        this.portalCooldown = cooldown;
        return this;
    }

    public @NotNull TextModelBuilder scoreboardTags(@NotNull String tag) {
        if (scoreboardTag == null) {
            scoreboardTag = new HashSet<>();
        }
        this.scoreboardTag.add(tag);
        return this;
    }

    public @NotNull TextModelBuilder sneaking(boolean sneaking) {
        this.sneaking = sneaking;
        return this;
    }

    public @NotNull TextModelBuilder pose(@NotNull Pose pose, boolean fixed) {
        this.pose = pose;
        this.fixedPose = fixed;
        return this;
    }

    // Metadatable methods
    public @NotNull TextModelBuilder metaData(@NotNull String key, @NotNull MetadataValue value) {
        if (metadata == null) {
            metadata = new HashMap<>();
        }

        if (metadata.containsKey(key)) {
            metadata.get(key).add(value);
        } else {
            List<MetadataValue> values = new ArrayList<>();
            values.add(value);
            metadata.put(key, values);
        }
        return this;
    }

    public @NotNull TextModelBuilder fixedMetaData(@NotNull Plugin plugin, @NotNull String key, Object object) {
        FixedMetadataValue value = new FixedMetadataValue(plugin, object);
        return metaData(key, value);
    }

    public @NotNull TextModelBuilder lazyMetaData(@NotNull Plugin plugin, @NotNull String key, Object object) {
        return metaData(key, new LazyMetadataValue(plugin, () -> object));
    }

    public @NotNull TextModelBuilder lazyMetaData(@NotNull Plugin plugin, @NotNull String key, LazyMetadataValue.@NotNull CacheStrategy strategy, Object object) {
        return metaData(key, new LazyMetadataValue(plugin, strategy, () -> object));
    }

    // Nameable methods
    public @NotNull TextModelBuilder customName(Component name) {
        this.customName = name;
        return this;
    }

    // Transformation
    private void initBuilder() {
        if (this.transformationBuilder == null) {
            this.transformationBuilder = new TransformationBuilder();
        }
    }

    public @NotNull TextModelBuilder setTranslation(@NotNull Vector3f translation) {
        return setTranslation(translation.x, translation.y, translation.z);
    }

    public @NotNull TextModelBuilder setTranslation(float x, float y, float z) {
        initBuilder();
        this.transformationBuilder.setTranslationX(x).setTranslationY(y).setTranslationZ(z);
        return this;
    }

    public @NotNull TextModelBuilder setTranslation(float f) {
        return setTranslation(f, f, f);
    }

    public @NotNull TextModelBuilder setLeftRotation(@NotNull Quaternionf rotation) {
        return setLeftRotation(rotation.x, rotation.y, rotation.z, rotation.w);
    }

    public @NotNull TextModelBuilder setLeftRotation(float x, float y, float z, float w) {
        initBuilder();
        this.transformationBuilder.setLeftRotationX(x).setLeftRotationY(y).setLeftRotationZ(z).setLeftRotationW(w);
        return this;
    }

    public @NotNull TextModelBuilder setLeftRotation(float f) {
        return setLeftRotation(f, f, f, f);
    }

    public @NotNull TextModelBuilder setSize(@NotNull Vector3f size) {
        return setSize(size.x, size.y, size.z);
    }


    public @NotNull TextModelBuilder setSize(float x, float y, float z) {
        this.transformationBuilder.setScaleX(x).setScaleY(y).setScaleZ(z);
        return this;
    }

    public @NotNull TextModelBuilder setSize(float size) {
        return setSize(size, size, size);
    }

    public @NotNull TextModelBuilder setRightRotation(@NotNull Quaternionf rotation) {
        return setRightRotation(rotation.x, rotation.y, rotation.z, rotation.w);
    }

    public @NotNull TextModelBuilder setRightRotation(float x, float y, float z, float w) {
        initBuilder();
        this.transformationBuilder.setRightRotationX(x).setRightRotationY(y).setRightRotationZ(z).setRightRotationW(w);
        return this;
    }

    public @NotNull TextModelBuilder setRightRotation(float f) {
        return setRightRotation(f, f, f, f);
    }
}
