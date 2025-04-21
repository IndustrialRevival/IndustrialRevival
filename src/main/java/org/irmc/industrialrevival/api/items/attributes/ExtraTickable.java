package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.Keyed;
import org.bukkit.Location;

public interface ExtraTickable extends Keyed {
    When getTime();
    void tick(Location location);
    enum When {
        TICK_START,
        TICK_DONE
    }
}
