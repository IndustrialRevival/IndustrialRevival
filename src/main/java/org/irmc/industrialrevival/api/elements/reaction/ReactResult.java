package org.irmc.industrialrevival.api.elements.reaction;

import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.irmc.industrialrevival.api.items.attributes.ChemReactable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * The result of a chemical reaction.
 *
 * @param formula The formula of the reaction.
 * @param consume The consumed compounds.
 * @param result The result of the reaction.
 *
 * @see ReactHelper
 * @author lijinhong11
 * @author balugaq
 */
public record ReactResult(
        ChemicalFormula formula,
        Map<ChemicalCompound, Double> consume,
        Map<ChemicalCompound, Double> result
) {
    public static final ReactResult FAILED = new ReactResult(null, null, null);
}
