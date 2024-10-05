package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class IRBlockData {
    private final String id;

    private final YamlConfiguration config;

    @Nullable
    private final MachineMenu machineMenu;

    private final Location location;

    public IRBlockData(String id, Location location, @NotNull YamlConfiguration config, @Nullable MachineMenu menu) {
        this.id = id;
        this.location = location;
        this.config = config;
        this.machineMenu = menu;
    }
}
