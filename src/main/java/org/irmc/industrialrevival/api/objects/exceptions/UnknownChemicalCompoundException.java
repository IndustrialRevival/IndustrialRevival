package org.irmc.industrialrevival.api.objects.exceptions;

import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;

public class UnknownChemicalCompoundException extends IllegalArgumentException {
    public String compound = null;
    public UnknownChemicalCompoundException(String compound) {
        super("Unknown chemical compound: " + compound);
        this.compound = compound;
    }

    public UnknownChemicalCompoundException(String formula, int id, UnknownChemicalCompoundException cause) {
        super("Unknown chemical compound: " + (cause.compound == null ? "" : cause.compound) + " in ChemicalFormula #" + id + " : " + formula);
    }
}
