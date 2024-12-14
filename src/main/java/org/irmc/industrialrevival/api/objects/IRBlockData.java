package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

@Getter
public class IRBlockData {
    private final NamespacedKey id;

    private final YamlConfiguration config;

    @Nullable
    private final MachineMenu machineMenu;

    private final Location location;
    private final Map<String, String> data = new HashMap<>();

    public IRBlockData(NamespacedKey id, Location location, @NotNull YamlConfiguration config, @Nullable MachineMenu menu) {
        this.id = id;
        this.location = location;
        this.config = config;
        this.machineMenu = menu;
    }
}
