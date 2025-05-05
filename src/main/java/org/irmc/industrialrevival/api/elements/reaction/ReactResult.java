package org.irmc.industrialrevival.api.elements.reaction;

import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;

import java.util.Map;

/**
 * The produce of a chemical reaction.
 *
 * @param formula The formula of the reaction.
 * @param consume The consumed compounds.
 * @param produce The product of the reaction.
 *
 * @see ReactHelper
 * @author lijinhong11
 * @author balugaq
 */
public record ReactResult(
        ChemicalFormula formula,
        Map<ChemicalCompound, Double> consume,
        Map<ChemicalCompound, Double> produce
) {
    public static final ReactResult FAILED = new ReactResult(null, null, null);
}
