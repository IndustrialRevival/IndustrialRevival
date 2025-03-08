package org.irmc.industrialrevival.api.elements.compounds;

import com.google.common.base.Preconditions;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class ChemicalCompound {
    public static final Set<ChemicalCompound> ALL_CHEMICALS = new HashSet<>();
    @NotNull public Component name;
    @NotNull public Map<Compound, Double> compounds;
    public ChemicalCompound(@NotNull Component name, @NotNull Map<Compound, Double> compounds) {
        Preconditions.checkNotNull(name, "name cannot be null");
        Preconditions.checkNotNull(compounds, "compounds cannot be null");
        Preconditions.checkArgument(!compounds.isEmpty(), "compounds cannot be empty");

        this.name = name;
        this.compounds = compounds;
        ALL_CHEMICALS.add(this);
    }

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
