package org.irmc.industrialrevival.api.objects;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.utils.DataUtil;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Describer of a {@link IndustrialRevivalItem} blocks
 * <p>
 * A block data object is used to store additional data about a block that is not
 * stored in the block itself. This data is stored in a YAML configuration file
 * and can be accessed using the {@link #config} field.
 * <p>
 *
 * @author lijinhong11
 * @see IndustrialRevivalItem
 * @see MachineMenu
 * @see DataUtil
 */
@Getter
public class IRBlockData {
    private final NamespacedKey id;

    @ApiStatus.Experimental
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
