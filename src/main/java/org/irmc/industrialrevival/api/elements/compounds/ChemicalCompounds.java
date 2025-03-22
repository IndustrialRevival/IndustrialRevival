package org.irmc.industrialrevival.api.elements.compounds;

import net.kyori.adventure.text.Component;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.compounds.types.AcidCompound;
import org.irmc.industrialrevival.api.elements.compounds.types.IonCompound;
import org.irmc.industrialrevival.api.elements.compounds.types.OxideCompound;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * This class contains all the built-in {@link ChemicalCompound}.
 * <p>
 * The compounds are defined as static fields of this class, and can be accessed using their names.
 * For example, to get the compound "CaSO4", you can use the following code:
 * <pre>
 *     ChemicalCompound compound = ChemicalCompounds.CaSO4;
 * </pre>
 * <p>
 * The compounds are defined using the {@link ChemicalCompound} class, which is a simple wrapper around a name and a map of
 * {@link Compound}s and their amounts. The {@link Compound} class is a simple wrapper around an element type and its amount.
 *
 * @author balugaq
 * @see ChemicalCompound
 * @see Chemical
 * @see Element
 */
public class ChemicalCompounds {
    //<editor-fold desc="Compounds ions">
    public static final @NotNull ChemicalCompound SO4 = new IonCompound(Component.text("SO4"), Map.of(
            new Element(ElementType.SULPHUR), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound NO3 = new IonCompound(Component.text("NO3"), Map.of(
            new Element(ElementType.NITROGEN), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound SO3 = new IonCompound(Component.text("SO3"), Map.of(
            new Element(ElementType.SULPHUR), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound OH = new IonCompound(Component.text("OH"), Map.of(
            new Element(ElementType.OXYGEN), 1D,
            new Element(ElementType.HYDROGEN), 1D
    ));
    public static final @NotNull ChemicalCompound CO3 = new IonCompound(Component.text("CO3"), Map.of(
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    //</editor-fold>

    //<editor-fold desc="Acids">
    public static final @NotNull ChemicalCompound H2SO4 = new AcidCompound(Component.text("H2SO4"), Map.of(
            new Element(ElementType.HYDROGEN), 2D,
            new Chemical(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound HNO3 = new AcidCompound(Component.text("HNO3"), Map.of(
            new Element(ElementType.HYDROGEN), 1D,
            new Chemical(NO3), 1D
    ));
    public static final @NotNull ChemicalCompound H2CO3 = new AcidCompound(Component.text("H2CO3"), Map.of(
            new Element(ElementType.HYDROGEN), 2D,
            new Chemical(CO3), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Oxides">
    public static final @NotNull ChemicalCompound PO4 = new OxideCompound(Component.text("PO4"), Map.of(
            new Element(ElementType.PHOSPHORUS), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound P2O5 = new OxideCompound(Component.text("P2O5"), Map.of(
            new Element(ElementType.PHOSPHORUS), 2D,
            new Element(ElementType.OXYGEN), 5D
    ));
    //</editor-fold>

    //<editor-fold desc="SO4s">
    public static final @NotNull ChemicalCompound MgSO4 = new ChemicalCompound(Component.text("MgSO4"), Map.of(
            new Element(ElementType.MAGNESIUM), 1D,
            new Chemical(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound FeSO4 = new ChemicalCompound(Component.text("FeSO4"), Map.of(
            new Element(ElementType.IRON), 1D,
            new Chemical(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound Na2SO4 = new ChemicalCompound(Component.text("Na2SO4"), Map.of(
            new Element(ElementType.SODIUM), 2D,
            new Chemical(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound CaSO4 = new ChemicalCompound(Component.text("CaSO4"), Map.of(
            new Element(ElementType.CALCIUM), 1D,
            new Chemical(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound K2SO4 = new ChemicalCompound(Component.text("K2SO4"), Map.of(
            new Element(ElementType.POTASSIUM), 2D,
            new Chemical(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound MnSO4 = new ChemicalCompound(Component.text("Cl2SO4"), Map.of(
            new Element(ElementType.MANGANESE), 1D,
            new Chemical(SO4), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="NO3s">
    public static final @NotNull ChemicalCompound KNO3 = new ChemicalCompound(Component.text("KNO3"), Map.of(
            new Element(ElementType.POTASSIUM), 1D,
            new Chemical(NO3), 1D
    ));
    public static final @NotNull ChemicalCompound MgNO3_2 = new ChemicalCompound(Component.text("Mg(NO3)_2"), Map.of(
            new Element(ElementType.MAGNESIUM), 1D,
            new Chemical(NO3), 2D
    ));
    public static final @NotNull ChemicalCompound FeNO3_2 = new ChemicalCompound(Component.text("Fe(NO3)_2"), Map.of(
            new Element(ElementType.IRON), 1D,
            new Chemical(NO3), 2D
    ));
    public static final @NotNull ChemicalCompound CaNO3_2 = new ChemicalCompound(Component.text("Ca(NO3)_2"), Map.of(
            new Element(ElementType.CALCIUM), 1D,
            new Chemical(NO3), 2D
    ));
    public static final @NotNull ChemicalCompound NaNO3 = new ChemicalCompound(Component.text("NaNO3"), Map.of(
            new Element(ElementType.SODIUM), 1D,
            new Chemical(NO3), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Cls">
    public static @NotNull ChemicalCompound KCL = new ChemicalCompound(Component.text("KCl"), Map.of(
            new Element(ElementType.POTASSIUM), 1D,
            new Element(ElementType.CHLORINE), 1D
    ));
    public static @NotNull ChemicalCompound CaCl2 = new ChemicalCompound(Component.text("CaCl2"), Map.of(
            new Element(ElementType.CALCIUM), 1D,
            new Element(ElementType.CHLORINE), 2D
    ));
    public static @NotNull ChemicalCompound MgCl2 = new ChemicalCompound(Component.text("MgCl2"), Map.of(
            new Element(ElementType.MAGNESIUM), 1D,
            new Element(ElementType.CHLORINE), 2D
    ));
    public static @NotNull ChemicalCompound FeCl2 = new ChemicalCompound(Component.text("FeCl2"), Map.of(
            new Element(ElementType.IRON), 1D,
            new Element(ElementType.CHLORINE), 2D
    ));
    public static @NotNull ChemicalCompound NaCl = new ChemicalCompound(Component.text("NaCl"), Map.of(
            new Element(ElementType.SODIUM), 1D,
            new Element(ElementType.CHLORINE), 1D
    ));
    public static @NotNull ChemicalCompound HCl = new ChemicalCompound(Component.text("HCl"), Map.of(
            new Element(ElementType.HYDROGEN), 1D,
            new Element(ElementType.CHLORINE), 1D
    ));
    //</editor-fold>


    //</editor-fold>

    //<editor-fold desc="OHs">
    public static @NotNull ChemicalCompound CaOH2 = new ChemicalCompound(Component.text("Ca(OH)2"), Map.of(
            new Element(ElementType.CALCIUM), 1D,
            new Chemical(OH), 2D
    ));
    public static @NotNull ChemicalCompound MgOH2 = new ChemicalCompound(Component.text("Mg(OH)2"), Map.of(
            new Element(ElementType.MAGNESIUM), 1D,
            new Chemical(OH), 2D
    ));
    public static @NotNull ChemicalCompound FeOH2 = new ChemicalCompound(Component.text("Fe(OH)2"), Map.of(
            new Element(ElementType.IRON), 1D,
            new Chemical(OH), 2D
    ));
    public static @NotNull ChemicalCompound NaOH = new ChemicalCompound(Component.text("NaOH"), Map.of(
            new Element(ElementType.SODIUM), 1D,
            new Chemical(OH), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Other compounds">
    public static @NotNull ChemicalCompound NH4 = new ChemicalCompound(Component.text("NH4"), Map.of(
            new Element(ElementType.NITROGEN), 1D,
            new Element(ElementType.HYDROGEN), 4D
    ));
    //</editor-fold>

    //<editor-fold desc="Basic substances">
    public static @NotNull ChemicalCompound H2O = new ChemicalCompound(Component.text("H2O"), Map.of(
            new Element(ElementType.HYDROGEN), 2D,
            new Element(ElementType.OXYGEN), 1D
    ));
    public static @NotNull ChemicalCompound CO2 = new ChemicalCompound(Component.text("CO2"), Map.of(
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.OXYGEN), 2D
    ));
    public static @NotNull ChemicalCompound N2O = new ChemicalCompound(Component.text("N2O"), Map.of(
            new Element(ElementType.NITROGEN), 2D,
            new Element(ElementType.OXYGEN), 1D
    ));
    public static @NotNull ChemicalCompound CH4 = new ChemicalCompound(Component.text("CH4"), Map.of(
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.HYDROGEN), 4D
    ));
    public static @NotNull ChemicalCompound CO = new ChemicalCompound(Component.text("CO"), Map.of(
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.OXYGEN), 1D
    ));
    public static @NotNull ChemicalCompound O2 = new ChemicalCompound(Component.text("O2"), Map.of(
            new Element(ElementType.OXYGEN), 2D
    ));
    public static @NotNull ChemicalCompound NH3 = new ChemicalCompound(Component.text("NH3"), Map.of(
            new Element(ElementType.NITROGEN), 1D,
            new Element(ElementType.HYDROGEN), 3D
    ));
    public static @NotNull ChemicalCompound H2 = new ChemicalCompound(Component.text("H2"), Map.of(
            new Element(ElementType.HYDROGEN), 2D
    ));
    //</editor-fold>
}
