package org.irmc.industrialrevival.api.elements.compounds;

import com.google.common.base.Preconditions;
import lombok.Data;
import net.kyori.adventure.text.Component;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.compounds.types.IonCompound;
import org.irmc.industrialrevival.api.elements.compounds.types.OxideCompound;
import org.irmc.industrialrevival.api.elements.registry.ChemicalCompounds;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
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
@Data
public class ChemicalCompound {
    public static final Map<String, ChemicalCompound> ALL_CHEMICALS = new HashMap<>();
    @NotNull
    public final String name;
    @NotNull
    public final Map<Compound, Double> compounds;

    /**
     * Creates a new chemical compound with the given name and compounds.
     *
     * @param name      the name of the chemical compound
     * @param compounds the map of constituent compounds and their respective amounts
     */
    public ChemicalCompound(@NotNull String name, @NotNull Map<Compound, Double> compounds) {
        this(name, compounds, true);
    }

    /**
     * Creates a new chemical compound with the given name and compounds.
     *
     * @param name      the name of the chemical compound
     * @param compounds the map of constituent compounds and their respective amounts
     * @param register  whether to register the chemical compound in the global set of all chemical compounds
     */
    public ChemicalCompound(@NotNull String name, @NotNull Map<Compound, Double> compounds, boolean register) {
        Preconditions.checkNotNull(name, "name cannot be null");
        Preconditions.checkNotNull(compounds, "compounds cannot be null");
        Preconditions.checkArgument(!compounds.isEmpty(), "compounds cannot be empty");

        this.name = name;
        this.compounds = compounds;
        if (register) {
            ALL_CHEMICALS.put(name, this);
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

        return ALL_CHEMICALS.get(name);
    }

    public String asKey() {
        return name.replaceAll("\\(", "-").replaceAll("\\)", ".").toLowerCase();
    }

    public double getMolarMass() {
        double mass = 0;
        for (var entry : compounds.entrySet()) {
            mass += entry.getKey().getMolarMass() * entry.getValue();
        }

        return mass;
    }

    public Map<ElementType, Double> toAtomic() {
        Map<ElementType, Double> atomic = new HashMap<>();
        for (var entry : compounds.entrySet()) {
            for (var atomicEntry : entry.getKey().toAtomic().entrySet()) {
                atomic.merge(atomicEntry.getKey(), atomicEntry.getValue() * entry.getValue(), Double::sum);
            }
        }

        return atomic;
    }

    public String getHumanizedName() {
        return name.replaceAll("_", "");
    }

    public Component humanizedName() {
        return Component.text(getHumanizedName());
    }
}
