package org.irmc.industrialrevival.api.elements.compounds;

import com.google.common.base.Preconditions;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.irmc.industrialrevival.api.elements.compounds.types.IonCompound;
import org.irmc.industrialrevival.api.elements.compounds.types.OxideCompound;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A chemical compound, consisting of a name and a map of its constituent compounds and their respective amounts.
 * All built-in {@link ChemicalCompound}s are in {@link ChemicalCompounds}
 *
 * @author balugaq
 * @see ChemicalCompounds
 * @see IonCompound
 * @see OxideCompound
 */
@Getter
public class ChemicalCompound {
    public static final Set<ChemicalCompound> ALL_CHEMICALS = new HashSet<>();
    @NotNull
    public Component name;
    @NotNull
    public Map<Compound, Double> compounds;

    /**
     * Creates a new chemical compound with the given name and compounds.
     *
     * @param name      the name of the chemical compound
     * @param compounds the map of constituent compounds and their respective amounts
     */
    public ChemicalCompound(@NotNull Component name, @NotNull Map<Compound, Double> compounds) {
        this(name, compounds, true);
    }

    /**
     * Creates a new chemical compound with the given name and compounds.
     *
     * @param name      the name of the chemical compound
     * @param compounds the map of constituent compounds and their respective amounts
     * @param register  whether to register the chemical compound in the global set of all chemical compounds
     */
    public ChemicalCompound(@NotNull Component name, @NotNull Map<Compound, Double> compounds, boolean register) {
        Preconditions.checkNotNull(name, "name cannot be null");
        Preconditions.checkNotNull(compounds, "compounds cannot be null");
        Preconditions.checkArgument(!compounds.isEmpty(), "compounds cannot be empty");

        this.name = name;
        this.compounds = compounds;
        if (register) {
            ALL_CHEMICALS.add(this);
        }
    }

    /**
     * Used to find a chemical compound by its name like {@code forName("SO4")} or {@code forName("Ca(OH)2")} are both valid.
     *
     * @param name the name of the chemical compound to find
     * @return the chemical compound with the given name, or null if no such compound exists
     */
    @Nullable
    @Contract("null -> null")
    public static ChemicalCompound forName(@Nullable String name) {
        if (name == null) {
            return null;
        }
        for (ChemicalCompound chemical : ALL_CHEMICALS) {
            if (chemical.name.equals(Component.text(name))) {
                return chemical;
            }
        }
        return null;
    }
}
