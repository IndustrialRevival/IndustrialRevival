package org.irmc.industrialrevival.api.elements.compounds.types;

import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompounds;
import org.irmc.industrialrevival.api.elements.compounds.Compound;
import org.irmc.industrialrevival.api.elements.compounds.classes.Ion;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * An ion is a chemical compound that is not a metal or a non-metallic element.
 * It is a type of compound that is used in the manufacture of ions and another
 * electrolytic and catalysts.
 * <p>
 * Examples of ions include:
 * <ul>
 *     <li>Sodium chloride</li>
 *     <li>Potassium iodide</li>
 *     <li>Calcium sulfate</li>
 *     <li>Magnesium sulfate</li>
 * </ul>
 * <p>
 * In IndustrialRevival, ions are represented by the {@link IonCompound} class.
 * <p>
 * The {@link Ion} interface is used to mark all ions as ions.
 * <p>
 * The {@link ChemicalCompound} class is used to represent all chemical compounds.
 *
 * @author balugaq
 * @see ChemicalCompounds
 * @see ChemicalCompound
 * @see Ion
 * @see OxideCompound
 * @see AcidCompound
 */
public class IonCompound extends ChemicalCompound implements Ion {
    public IonCompound(@NotNull String name, @NotNull Map<Compound, Double> compounds) {
        super(name, compounds);
    }
}
