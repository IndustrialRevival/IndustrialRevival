package org.irmc.industrialrevival.api.elements.compounds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

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
}
