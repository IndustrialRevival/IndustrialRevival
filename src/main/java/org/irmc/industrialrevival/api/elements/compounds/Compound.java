package org.irmc.industrialrevival.api.elements.compounds;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A part to describe a chemical compound.
 *
 * @author balugaq
 * @see Chemical
 * @see Element
 */
public interface Compound extends Keyed {
    List<CompoundReader> COMPOUND_READERS = new ArrayList<>();
    String ELEMENT_NAMESPACE = "element";
    String CHEMICAL_NAMESPACE = "chemical";

    double getMolarMass();

    Map<ElementType, Double> toAtomic();

    @Nullable
    static Compound fromKey(NamespacedKey key) {
        for (CompoundReader reader : COMPOUND_READERS) {
            if (reader.adapter(key)) {
                return reader.read(key);
            }
        }

        return null;
    }
}
