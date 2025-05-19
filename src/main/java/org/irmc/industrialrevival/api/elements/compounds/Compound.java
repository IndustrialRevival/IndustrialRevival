package org.irmc.industrialrevival.api.elements.compounds;

import org.irmc.industrialrevival.api.elements.ElementType;

import java.util.Map;

/**
 * A part to describe a chemical compound.
 *
 * @author balugaq
 * @see Chemical
 * @see Element
 */
public abstract class Compound {
    abstract double getMolarMass();

    abstract Map<ElementType, Double> toAtomic();
}
