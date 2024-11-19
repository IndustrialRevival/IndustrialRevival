package org.irmc.industrialrevival.api.pipe;

import org.bukkit.Location;
import org.irmc.industrialrevival.api.objects.enums.ItemFlow;

public interface PipeServer {
    Pipe getPipe();
    PipeFlowType getType();

    default Location getLocation() {
        return getPipe().getLocation();
    }
}
