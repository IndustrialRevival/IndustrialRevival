package org.irmc.industrialrevival.api.objects.display.builder;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pose;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.LazyMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
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
public class BlockModelBuilder extends AbstractModelBuilder implements Cloneable {
    private TransformationBuilder transformationBuilder;
    private BlockData blockData;
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

    public BlockModelBuilder() {
    }

    public void ifPresent(@Nullable Object object, @NotNull Runnable runnable) {
        if (object != null) {
            runnable.run();
        }
    }

    public @NotNull BlockModelBuilder clone() {
        BlockModelBuilder clone = new BlockModelBuilder();
        clone.blockData = this.blockData;
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
        clone.transformationBuilder = this.transformationBuilder;
        return clone;
    }

    public @NotNull BlockModelBuilder build() {
        return clone();
    }

    public @NotNull BlockDisplay buildAt(@NotNull Location location) {
        try {
            return Bukkit.getScheduler().callSyncMethod(IRDock.getPlugin(), () ->
                    location.getWorld().spawn(location, BlockDisplay.class, display -> {
                        ifPresent(this.blockData, () -> display.setBlock(this.blockData));
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
                    })).get();
        } catch (InterruptedException | ExecutionException e) {
            Debug.error(e);
            return null;
        }
    }

    // Block Display methods
    public @NotNull BlockModelBuilder block(BlockData blockData) {
        this.blockData = blockData;
        return this;
    }

    public @NotNull BlockModelBuilder block(@NotNull Block block) {
        return block(block.getBlockData());
    }

    public @NotNull BlockModelBuilder block(@NotNull Location location) {
        return block(location.getBlock().getBlockData());
    }

    public @NotNull BlockModelBuilder block(@NotNull Material material) throws IllegalArgumentException {
        if (!material.isBlock()) {
            throw new IllegalArgumentException("Material must be a block");
        }
        return block(material.createBlockData());
    }

    public @NotNull BlockModelBuilder block(@NotNull ItemStack itemStack) {
        return block(itemStack.getType());
    }

    // Display methods
    public @NotNull BlockModelBuilder interpolationDuration(int duration) {
        this.interpolationDuration = duration;
        return this;
    }

    public @NotNull BlockModelBuilder teleportDuration(int duration) {
        this.teleportDuration = duration;
        return this;
    }

    public @NotNull BlockModelBuilder viewRange(float viewRange) {
        this.viewRange = viewRange;
        return this;
    }

    public @NotNull BlockModelBuilder shadowRadius(float shadowRadius) {
        this.shadowRadius = shadowRadius;
        return this;
    }

    public @NotNull BlockModelBuilder shadowStrength(float shadowStrength) {
        this.shadowStrength = shadowStrength;
        return this;
    }

    public @NotNull BlockModelBuilder displayWidth(float displayWidth) {
        this.displayWidth = displayWidth;
        return this;
    }

    public @NotNull BlockModelBuilder displayHeight(float displayHeight) {
        this.displayHeight = displayHeight;
        return this;
    }

    public @NotNull BlockModelBuilder interpolationDelay(int delay) {
        this.interpolationDelay = delay;
        return this;
    }

    public @NotNull BlockModelBuilder billboard(Display.@NotNull Billboard billboard) {
        this.billboard = billboard;
        return this;
    }

    public @NotNull BlockModelBuilder glowColorOverride(Color color) {
        this.glowColorOverride = color;
        return this;
    }

    public @NotNull BlockModelBuilder glowColorOverride(int color) {
        return glowColorOverride(Color.fromRGB(color));
    }

    public @NotNull BlockModelBuilder glowColorOverride(int r, int g, int b) {
        return glowColorOverride(Color.fromRGB(r, g, b));
    }

    public @NotNull BlockModelBuilder brightness(Display.Brightness brightness) {
        this.brightness = brightness;
        return this;
    }

    // Entity methods
    public @NotNull BlockModelBuilder velocity(@NotNull Vector velocity) {
        this.velocity = velocity;
        return this;
    }

    public @NotNull BlockModelBuilder rotation(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
        return this;
    }

    public @NotNull BlockModelBuilder fireTicks(int ticks) {
        this.fireTicks = ticks;
        return this;
    }

    public @NotNull BlockModelBuilder visualFire(boolean visualFire) {
        this.visualFire = visualFire;
        return this;
    }

    public @NotNull BlockModelBuilder freezeTicks(int ticks) {
        this.freezeTicks = ticks;
        return this;
    }

    public @NotNull BlockModelBuilder invisible(boolean invisible) {
        this.invisible = invisible;
        return this;
    }

    public @NotNull BlockModelBuilder noPhysics(boolean noPhysics) {
        this.noPhysics = noPhysics;
        return this;
    }

    public @NotNull BlockModelBuilder lockFreezeTicks(boolean lockFreezeTicks) {
        this.lockFreezeTicks = lockFreezeTicks;
        return this;
    }

    public @NotNull BlockModelBuilder persistent(boolean persistent) {
        this.persistent = persistent;
        return this;
    }

    public @NotNull BlockModelBuilder passenger(@NotNull Entity passenger) {
        if (passengers == null) {
            passengers = new ArrayList<>();
        }
        this.passengers.add(passenger);
        return this;
    }

    public @NotNull BlockModelBuilder fallDistance(float fallDistance) {
        this.fallDistance = fallDistance;
        return this;
    }

    public @NotNull BlockModelBuilder ticksLived(int tickLived) {
        this.ticksLived = tickLived;
        return this;
    }

    public @NotNull BlockModelBuilder customNameVisible(boolean customNameVisible) {
        this.customNameVisible = customNameVisible;
        return this;
    }

    public @NotNull BlockModelBuilder visibleByDefault(boolean visibleByDefault) {
        this.visibleByDefault = visibleByDefault;
        return this;
    }

    public @NotNull BlockModelBuilder glowing(boolean glowing) {
        this.glowing = glowing;
        return this;
    }

    public @NotNull BlockModelBuilder invulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
        return this;
    }

    public @NotNull BlockModelBuilder silent(boolean silent) {
        this.silent = silent;
        return this;
    }

    public @NotNull BlockModelBuilder gravity(boolean gravity) {
        this.gravity = gravity;
        return this;
    }

    public @NotNull BlockModelBuilder portalCooldown(int cooldown) {
        this.portalCooldown = cooldown;
        return this;
    }

    public @NotNull BlockModelBuilder scoreboardTags(@NotNull String tag) {
        if (scoreboardTag == null) {
            scoreboardTag = new HashSet<>();
        }
        this.scoreboardTag.add(tag);
        return this;
    }

    public @NotNull BlockModelBuilder sneaking(boolean sneaking) {
        this.sneaking = sneaking;
        return this;
    }

    public @NotNull BlockModelBuilder pose(@NotNull Pose pose, boolean fixed) {
        this.pose = pose;
        this.fixedPose = fixed;
        return this;
    }

    // Metadatable methods
    public @NotNull BlockModelBuilder metaData(@NotNull String key, @NotNull MetadataValue value) {
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

    public @NotNull BlockModelBuilder fixedMetaData(@NotNull Plugin plugin, @NotNull String key, Object object) {
        FixedMetadataValue value = new FixedMetadataValue(plugin, object);
        return metaData(key, value);
    }

    public @NotNull BlockModelBuilder lazyMetaData(@NotNull Plugin plugin, @NotNull String key, Object object) {
        return metaData(key, new LazyMetadataValue(plugin, () -> object));
    }

    public @NotNull BlockModelBuilder lazyMetaData(@NotNull Plugin plugin, @NotNull String key, LazyMetadataValue.@NotNull CacheStrategy strategy, Object object) {
        return metaData(key, new LazyMetadataValue(plugin, strategy, () -> object));
    }

    // Nameable methods
    public @NotNull BlockModelBuilder customName(Component name) {
        this.customName = name;
        return this;
    }

    // Transformation
    private void initBuilder() {
        if (this.transformationBuilder == null) {
            this.transformationBuilder = new TransformationBuilder();
        }
    }

    public @NotNull BlockModelBuilder setTranslation(@NotNull Vector3f translation) {
        return setTranslation(translation.x, translation.y, translation.z);
    }

    public @NotNull BlockModelBuilder setTranslation(float x, float y, float z) {
        initBuilder();
        this.transformationBuilder.setTranslationX(x).setTranslationY(y).setTranslationZ(z);
        return this;
    }

    public @NotNull BlockModelBuilder setTranslation(float f) {
        return setTranslation(f, f, f);
    }

    public @NotNull BlockModelBuilder setLeftRotation(@NotNull Quaternionf rotation) {
        return setLeftRotation(rotation.x, rotation.y, rotation.z, rotation.w);
    }

    public @NotNull BlockModelBuilder setLeftRotation(float x, float y, float z, float w) {
        initBuilder();
        this.transformationBuilder.setLeftRotationX(x).setLeftRotationY(y).setLeftRotationZ(z).setLeftRotationW(w);
        return this;
    }

    public @NotNull BlockModelBuilder setLeftRotation(float f) {
        return setLeftRotation(f, f, f, f);
    }

    public @NotNull BlockModelBuilder setSize(@NotNull Vector3f size) {
        return setSize(size.x, size.y, size.z);
    }


    public @NotNull BlockModelBuilder setSize(float x, float y, float z) {
        this.transformationBuilder.setScaleX(x).setScaleY(y).setScaleZ(z);
        return this;
    }

    public @NotNull BlockModelBuilder setSize(float size) {
        return setSize(size, size, size);
    }

    public @NotNull BlockModelBuilder setRightRotation(@NotNull Quaternionf rotation) {
        return setRightRotation(rotation.x, rotation.y, rotation.z, rotation.w);
    }

    public @NotNull BlockModelBuilder setRightRotation(float x, float y, float z, float w) {
        initBuilder();
        this.transformationBuilder.setRightRotationX(x).setRightRotationY(y).setRightRotationZ(z).setRightRotationW(w);
        return this;
    }

    public @NotNull BlockModelBuilder setRightRotation(float f) {
        return setRightRotation(f, f, f, f);
    }
}
