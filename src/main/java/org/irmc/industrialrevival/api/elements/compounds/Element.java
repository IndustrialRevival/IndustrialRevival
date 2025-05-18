package org.irmc.industrialrevival.api.elements.compounds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

/**
 * An element part to describe a chemical compound.
 *
 * @author balugaq
 * @see Chemical
 */
@Getter
@AllArgsConstructor
@ParametersAreNonnullByDefault
public class Element extends Compound {
    private final @NotNull ElementType element;

    public double getMolarMass() {
        return element.getRelativeAtomicMass();
    }

    public Map<ElementType, Double> toAtomic() {
        return Map.of(element, 1D);
    }
}
