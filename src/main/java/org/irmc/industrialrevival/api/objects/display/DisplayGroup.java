package org.irmc.industrialrevival.api.objects.display;

import lombok.Getter;
import org.bukkit.entity.Display;
import org.bukkit.entity.Interaction;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DisplayGroup {
    private final @NotNull IndustrialRevivalAddon addon;
    private final List<Display> displays = new ArrayList<>();
    private Interaction interaction;

    public DisplayGroup(@NotNull IndustrialRevivalAddon addon) {
        if (!addon.getPlugin().isEnabled()) {
            throw new UnsupportedOperationException("Plugin is not enabled");
        }
        this.addon = addon;
    }

    public @NotNull DisplayGroup add(Display display) {
        displays.add(display);
        return this;
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
        if (interaction != null) {
            interaction.remove();
        }
        return this;
    }

    public @NotNull DisplayGroup setInteraction(Interaction interaction) {
        this.interaction = interaction;
        return this;
    }
}
