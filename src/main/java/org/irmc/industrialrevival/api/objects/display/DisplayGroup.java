package org.irmc.industrialrevival.api.objects.display;

import lombok.Getter;
import org.bukkit.entity.Display;
import org.bukkit.entity.Interaction;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
public class DisplayGroup {
    // TODO
    private final String plugin;
    private final List<Display> displays;
    private final Interaction interaction;

    public DisplayGroup(@NotNull IndustrialRevivalAddon addon, List<Display> displays, Interaction interaction) {
        if (!addon.getPlugin().isEnabled()) {
            throw new UnsupportedOperationException("Plugin is not enabled");
        }
        plugin = addon.getPlugin().getName();
        this.displays = displays;
        this.interaction = interaction;
    }

    public boolean display() {
        // TODO
        return false;
    }

    public boolean hide() {
        // TODO
        return false;
    }

    public boolean remove() {
        // TODO
        return false;
    }
}
