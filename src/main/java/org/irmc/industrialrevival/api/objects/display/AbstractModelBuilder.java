package org.irmc.industrialrevival.api.objects.display;

import org.bukkit.Location;
import org.bukkit.entity.Display;

public abstract class AbstractModelBuilder {
    public abstract Display buildAt(Location location);
}
