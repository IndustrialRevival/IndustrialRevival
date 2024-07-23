package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.jetbrains.annotations.Nullable;

public class IRBlockData {
    // TODO: implement this class(implement sqlite/mysql database first)

    @Getter
    private final String id;
    private final YamlConfiguration config;
    @Getter
    @Nullable
    private final MachineMenu machineMenu;
    @Getter
    private final Location location;

    public IRBlockData(String id, Location location, YamlConfiguration config, @Nullable MachineMenu menu) {
        this.id = id;
        this.location = location;
        this.config = config;
        this.machineMenu = menu;
    }

    public <T> void setData(String key, DataType<T> type, T value) {
        type.set(config, key, value);
    }

    @Nullable
    public <T> T getData(String key, DataType<T> type) {
        return type.get(config, key);
    }
}
