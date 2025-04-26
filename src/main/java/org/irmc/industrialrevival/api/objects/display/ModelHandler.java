package org.irmc.industrialrevival.api.objects.display;

import lombok.Getter;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author baluagq
 */
@Getter
public class ModelHandler {
    @Getter
    private final Map<Location, DisplayGroup> models;
    @Getter
    private boolean removeOldWhenRenderNew = true;

    public ModelHandler() {
        this.models = new HashMap<>();
    }

    public ModelHandler removeOldWhenRenderNew(boolean removeOldWhenRenderNew) {
        this.removeOldWhenRenderNew = removeOldWhenRenderNew;
        return this;
    }

    public ModelHandler addDisplayGroup(Location location, DisplayGroup displayGroup) {
        if (removeOldWhenRenderNew) {
            Optional.ofNullable(models.get(location)).ifPresent(DisplayGroup::remove);
        }
        models.put(location, displayGroup);
        return this;
    }
}
