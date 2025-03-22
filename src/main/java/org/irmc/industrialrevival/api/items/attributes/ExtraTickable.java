package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.Keyed;
import org.bukkit.Location;

public interface ExtraTickable extends Keyed {
    Time getTime();
    void tick(Location location);
    enum Time {
        TICK_START,
        TICK_DONE
    }
}
