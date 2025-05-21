package org.irmc.industrialrevival.api.items.attributes;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.CompoundContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author balugaq
 */
public interface CompoundContainerHolder extends ItemAttribute {
    Map<Location, CompoundContainer> COMPOUND_CONTAINER = new HashMap<>();

    @CanIgnoreReturnValue
    default CompoundContainerHolder clearCompounds(Location location) {
        COMPOUND_CONTAINER.remove(location);
        return this;
    }

    @CanIgnoreReturnValue
    default CompoundContainerHolder clearCompounds(Location location, Set<ChemicalCompound> take) {
        var all = getOrNewCompoundContainer(location).getMixed();
        for (var compound : take) {
            all.remove(compound);
        }
        return this;
    }

    @CanIgnoreReturnValue
    default CompoundContainerHolder mixCompounds(Location location, CompoundContainer other) {
        var container = getOrNewCompoundContainer(location);
        container.mix(other);
        return this;
    }

    @CanIgnoreReturnValue
    default CompoundContainerHolder mixCompounds(Location location, Map<ChemicalCompound, Double> other) {
        var container = getOrNewCompoundContainer(location);
        container.mix(other);
        return this;
    }

    @CanIgnoreReturnValue
    default CompoundContainerHolder mixCompounds(Location location, ChemicalCompound other, double mass) {
        return mixCompounds(location, Map.of(other, mass));
    }

    @CanIgnoreReturnValue
    default CompoundContainerHolder consumeCompounds(Location location, CompoundContainer other) {
        var container = getOrNewCompoundContainer(location);
        container.consume(other);
        return this;
    }

    @CanIgnoreReturnValue
    default CompoundContainerHolder consumeCompounds(Location location, Map<ChemicalCompound, Double> other) {
        var container = getOrNewCompoundContainer(location);
        container.consume(other);
        return this;
    }

    default CompoundContainer getCompoundContainer(Location location) {
        return COMPOUND_CONTAINER.get(location);
    }

    default CompoundContainer newCompoundContainer() {
        return new CompoundContainer();
    }

    default CompoundContainer getOrNewCompoundContainer(Location location) {
        var v = getCompoundContainer(location);
        if (v == null) {
            v = newCompoundContainer();
            COMPOUND_CONTAINER.put(location, v);
        }
        return v;
    }
}
