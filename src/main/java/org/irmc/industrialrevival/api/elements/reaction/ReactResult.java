package org.irmc.industrialrevival.api.elements.reaction;

import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * The getProduce of a chemical reaction.
 *
 * @param formula The formula of the reaction.
 * @param getConsume The consumed compounds.
 * @param getProduce The product of the reaction.
 * @author lijinhong11
 * @author balugaq
 * @see ReactHelper
 */
public record ReactResult(
        @Nullable ChemicalFormula formula,
        @Nullable Map<ChemicalCompound, Double> getConsume,
        @Nullable Map<ChemicalCompound, Double> getProduce
) {
    public static final ReactResult FAILED = new ReactResult(null, null, null);
}
