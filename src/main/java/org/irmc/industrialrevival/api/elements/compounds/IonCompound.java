package org.irmc.industrialrevival.api.elements.compounds;

import net.kyori.adventure.text.Component;
import org.irmc.industrialrevival.api.elements.compounds.classes.Ion;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * An ion is a chemical compound that is not a metal or a non-metallic element.
 * It is a type of compound that is used in the manufacture of ions and other
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
 */
public class IonCompound extends ChemicalCompound implements Ion {
    public IonCompound(@NotNull Component name, @NotNull Map<Compound, Double> compounds) {
        super(name, compounds);
    }
}
