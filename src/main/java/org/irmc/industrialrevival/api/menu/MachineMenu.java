package org.irmc.industrialrevival.api.menu;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;

@Getter
public class MachineMenu extends SimpleMenu {
    // private final IRBlockData blockData;
    private final Location location;
    private final MachineMenuPreset preset;

    public MachineMenu(Location location, MachineMenuPreset preset) {
        super(preset.getTitle());

        /*
        IRBlockData blockData = IndustrialRevival.getInstance().getBlockDataService().getBlockData(location);

        if (blockData == null) {
            throw new IllegalArgumentException("Block data not found at location " + location.toString());
        }

        this.blockData = blockData;
         */

        this.location = location;
        this.preset = preset;
    }

    public void setSize(int size) {
        throw new UnsupportedOperationException("Cannot set size of a machine menu");
    }

    public void setTitle(Component title) {
        throw new UnsupportedOperationException("Cannot set title of a machine menu");
    }
}
