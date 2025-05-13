package org.irmc.industrialrevival.api.elements.compounds;

import lombok.Data;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author balugaq
 */
@Data
public class CompoundContainerHolder {
    private final Map<Location, CompoundContainer> containers = new HashMap<>();

    public CompoundContainerHolder clear(Location location) {
        containers.remove(location);
        return this;
    }

    public CompoundContainerHolder clear(Location location, Set<ChemicalCompound> take) {
        var all = getOrNew(location).getMixed();
        for (var compound : take) {
            all.remove(compound);
        }
        return this;
    }

    public CompoundContainerHolder mix(Location location, CompoundContainer other) {
        var container = getOrNew(location);
        container.mix(other);
        return this;
    }

    public CompoundContainerHolder mix(Location location, Map<ChemicalCompound, Double> other) {
        var container = getOrNew(location);
        container.mix(other);
        return this;
    }

    public CompoundContainerHolder consume(Location location, CompoundContainer other) {
        var container = getOrNew(location);
        container.consume(other);
        return this;
    }

    public CompoundContainerHolder consume(Location location, Map<ChemicalCompound, Double> other) {
        var container = getOrNew(location);
        container.consume(other);
        return this;
    }

    public CompoundContainer getOrNew(Location location) {
        return containers.computeIfAbsent(location, k -> new CompoundContainer());
    }
}
