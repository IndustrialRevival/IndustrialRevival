package org.irmc.industrialrevival.api.elements.compounds;

import net.kyori.adventure.text.Component;
import org.irmc.industrialrevival.api.elements.compounds.classes.Ion;
import org.irmc.industrialrevival.api.elements.compounds.classes.Oxide;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * An oxide is a chemical compound that is not a metal or a non-metallic element.
 * It is a type of compound that is used in the manufacture of oxides and other
 * oxidation products.
 * <p>
 * Examples of oxides include:
 * <ul>
 *     <li>Sulfur dioxide</li>
 *     <li>Phosphorus monoxide</li>
 *     <li>Potassium oxide</li>
 *     <li>Calcium oxide</li>
 * </ul>
 * <p>
 * In IndustrialRevival, oxides are represented by the {@link OxideCompound} class.
 * <p>
 * The {@link Oxide} interface is used to mark all oxides as oxides.
 * <p>
 * The {@link ChemicalCompound} class is used to represent all chemical compounds.
 *
 * @author balugaq
 * @see ChemicalCompounds
 * @see ChemicalCompound
 * @see Oxide
 * @see IonCompound
 */
public class OxideCompound extends ChemicalCompound implements Oxide {
    public OxideCompound(@NotNull Component name, @NotNull Map<Compound, Double> compounds) {
        super(name, compounds);
    }
}
