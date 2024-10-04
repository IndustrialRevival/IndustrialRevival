package org.irmc.industrialrevival.api.objects.display;

import lombok.Getter;
import org.bukkit.entity.Display;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;

import javax.annotation.Nonnull;
import java.util.List;

@Getter
public class DisplayGroup {
    // TODO
    private final String plugin;
    private final List<Display> displays;

    public DisplayGroup(@Nonnull IndustrialRevivalAddon addon, List<Display> displays) {
        if (!addon.getPlugin().isEnabled()) {
            throw new UnsupportedOperationException("Plugin is not enabled");
        }
        plugin = addon.getPlugin().getName();
        this.displays = displays;
    }

    public boolean display() {
        // TODO
        return false;
    }
}
