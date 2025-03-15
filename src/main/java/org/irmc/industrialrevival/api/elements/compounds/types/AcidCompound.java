package org.irmc.industrialrevival.api.elements.compounds.types;

import net.kyori.adventure.text.Component;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.Compound;

import java.util.Map;

public class AcidCompound extends ChemicalCompound {
    public AcidCompound(Component name, Map<Compound, Double> properties) {
        super(name, properties);
    }
}
