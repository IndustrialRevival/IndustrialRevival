package org.irmc.industrialrevival.api.elements.compounds;

import net.kyori.adventure.text.Component;
import org.irmc.industrialrevival.api.elements.compounds.classes.Oxide;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class OxideCompound extends ChemicalCompound implements Oxide {
    public OxideCompound(@NotNull Component name, @NotNull Map<Compound, Double> compounds) {
        super(name, compounds);
    }
}
