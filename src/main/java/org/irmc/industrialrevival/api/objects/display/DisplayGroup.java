package org.irmc.industrialrevival.api.objects.display;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.objects.display.builder.AbstractModelBuilder;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class DisplayGroup {
    private static final String DISPLAY_GROUP_METADATA_KEY = KeyUtil.customKey("display_group").toString();
    private final @NotNull IndustrialRevivalAddon addon;
    private final List<Display> displays = new ArrayList<>();
    private Location center;

    public DisplayGroup(@NotNull IndustrialRevivalAddon addon) {
        if (!addon.getPlugin().isEnabled()) {
            throw new UnsupportedOperationException("Plugin is not enabled");
        }
        this.addon = addon;
    }

    public @NotNull DisplayGroup center(@NotNull Location location) {
        this.center = location.clone().add(0.5D, 0.5D, 0.5D);
        return this;
    }

    public @NotNull DisplayGroup add(@NotNull Display display) {
        return add(display, 0.0D, 0.0D, 0.0D);
    }

    public @NotNull DisplayGroup add(@NotNull Display display, double x, double y, double z) {
        return add(display, new Vector(x, y, z));
    }

    public @NotNull DisplayGroup add(@NotNull Display display, @NotNull Vector offset) {
        if (center == null) {
            throw new UnsupportedOperationException("Center location is not set");
        }

        display.teleport(center.clone().add(offset.getX(), offset.getY(), offset.getZ()));
        // When add display to a display group, set metadata to the display to identify its addons
        display.setMetadata(DISPLAY_GROUP_METADATA_KEY, new FixedMetadataValue(IRDock.getPlugin(), addon.getPlugin().getName()));
        return this;
    }

    public @NotNull DisplayGroup addDirectly(@NotNull Collection<TextDisplay> displays) {
        for (Display display : displays) {
            addDirectly(display);
        }
        return this;
    }

    public @NotNull DisplayGroup addDirectly(@NotNull Display display) {
        if (center == null) {
            throw new UnsupportedOperationException("Center location is not set");
        }

        // When add display to a display group, set metadata to the display to identify its addons
        display.setMetadata(DISPLAY_GROUP_METADATA_KEY, new FixedMetadataValue(IRDock.getPlugin(), addon.getPlugin().getName()));
        return this;
    }

    public @NotNull DisplayGroup add(@NotNull AbstractModelBuilder modelBuilder) {
        return add(modelBuilder, 0.0D, 0.0D, 0.0D);
    }

    public @NotNull DisplayGroup add(@NotNull AbstractModelBuilder modelBuilder, double x, double y, double z) {
        return add(modelBuilder, new Vector(x, y, z));
    }

    public @NotNull DisplayGroup add(@NotNull AbstractModelBuilder modelBuilder, @NotNull Vector offset) {
        if (center == null) {
            throw new UnsupportedOperationException("Center location is not set");
        }

        Display display = modelBuilder.buildAt(center.clone().add(offset.getX(), offset.getY(), offset.getZ()));
        if (display == null) {
            return this;
        }
        // When add display to a display group, set metadata to the display to identify its addons
        display.setMetadata(DISPLAY_GROUP_METADATA_KEY, new FixedMetadataValue(IRDock.getPlugin(), addon.getPlugin().getName()));
        return add(display);
    }

    public @NotNull DisplayGroup hide() {
        for (Display display : displays) {
            display.setInvisible(true);
        }
        return this;
    }

    public @NotNull DisplayGroup show() {
        for (Display display : displays) {
            display.setInvisible(false);
        }
        return this;
    }

    public @NotNull DisplayGroup remove() {
        for (Display display : displays) {
            display.remove();
        }
        displays.clear();
        return this;
    }
}
