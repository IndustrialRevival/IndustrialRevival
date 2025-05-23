package org.irmc.industrialrevival.api.elements.compounds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

/**
 * A chemical part to describe a chemical compound.
 *
 * @author balugaq
 * @see Element
 */
@Data
@AllArgsConstructor
@ParametersAreNonnullByDefault
public class Chemical implements Compound {
    private final @NotNull ChemicalCompound compound;

    @Override
    public double getMolarMass() {
        return compound.getMolarMass();
    }

    public Map<ElementType, Double> toAtomic() {
        return compound.toAtomic();
    }
}
