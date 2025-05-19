package org.irmc.industrialrevival.api.elements.compounds.types;

import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.Compound;

import java.util.Map;

public class AcidCompound extends ChemicalCompound {
    public AcidCompound(String name, Map<Compound, Double> properties) {
        super(name, properties);
    }
}
