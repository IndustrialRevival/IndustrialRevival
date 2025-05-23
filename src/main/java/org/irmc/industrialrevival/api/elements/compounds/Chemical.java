package org.irmc.industrialrevival.api.elements.compounds;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.NamespacedKey;
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
    static {
        COMPOUND_READERS.add(new CompoundReader.ChemicalReader());
    }

    private final @NotNull ChemicalCompound compound;
    private final @NotNull NamespacedKey key;

    public Chemical(@NotNull ChemicalCompound compound) {
        this(compound, new NamespacedKey(Compound.CHEMICAL_NAMESPACE, compound.getName()));
    }

    @Override
    public double getMolarMass() {
        return compound.getMolarMass();
    }

    public Map<ElementType, Double> toAtomic() {
        return compound.toAtomic();
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return key;
    }
}
