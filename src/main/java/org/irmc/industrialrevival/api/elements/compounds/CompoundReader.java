package org.irmc.industrialrevival.api.elements.compounds;

import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.api.elements.registry.ChemicalCompounds;
import org.irmc.industrialrevival.utils.ElementUtil;
import org.jetbrains.annotations.NotNull;

/**
 * @author balugaq
 */
public abstract class CompoundReader {
    public abstract boolean adapter(@NotNull NamespacedKey key);
    @NotNull
    public abstract Compound read(@NotNull NamespacedKey key);

    /**
     * @author balugaq
     */
    public static class ElementReader extends CompoundReader {
        @Override
        public boolean adapter(@NotNull NamespacedKey key) {
            return key.getNamespace().equals(Compound.ELEMENT_NAMESPACE);
        }

        @Override
        @NotNull
        public Compound read(@NotNull NamespacedKey key) {
            return ChemicalCompounds.asCompound(ElementUtil.forName(key.getKey()));
        }
    }

    /**
     * @author balugaq
     */
    public static class ChemicalReader extends CompoundReader {
        @Override
        public boolean adapter(@NotNull NamespacedKey key) {
            return key.getNamespace().equals(Compound.CHEMICAL_NAMESPACE);
        }

        @Override
        @NotNull
        public Compound read(@NotNull NamespacedKey key) {
            return ChemicalCompounds.asCompound(ChemicalCompound.forName(key.getKey()));
        }
    }
}
