package org.irmc.industrialrevival.api.elements.compounds;

import net.kyori.adventure.text.Component;
import org.irmc.industrialrevival.api.elements.compounds.classes.Ion;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class IonCompound extends ChemicalCompound implements Ion {
    public IonCompound(@NotNull Component name, @NotNull Map<Compound, Double> compounds) {
        super(name, compounds);
    }
}
