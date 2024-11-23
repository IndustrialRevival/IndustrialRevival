package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

@Getter
public class IRBlockData {
    private final String id;

    private final YamlConfiguration config;

    @Nullable
    private final MachineMenu machineMenu;

    private final Location location;
    private final Map<String, String> data = new HashMap<>();

    public IRBlockData(String id, Location location, @NotNull YamlConfiguration config, @Nullable MachineMenu menu) {
        this.id = id;
        this.location = location;
        this.config = config;
        this.machineMenu = menu;
    }

    private void setData(String key, String value) {
        config.set(key, value);
        if (value == null) {
            data.remove(key);
        } else {
            data.put(key, value);
        }
    }

    private String getData(String key) {
        return config.getString(key);
    }

    private String removeData(String key) {
        String value = config.getString(key);
        setData(key, null);
        return value;
    }
}
