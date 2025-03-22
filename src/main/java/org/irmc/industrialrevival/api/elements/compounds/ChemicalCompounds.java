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
 * @author lijinhong11
 * @see ChemicalCompound
 * @see Chemical
 * @see Element
 */
@SuppressWarnings("unused")
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
    public static final @NotNull ChemicalCompound HCO3 = new IonCompound(Component.text("HCO3"), Map.of(
            new Element(ElementType.HYDROGEN), 1D,
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound PO4 = new IonCompound(Component.text("PO4"), Map.of(
            new Element(ElementType.PHOSPHORUS), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound Cl = new IonCompound(Component.text("Cl"), Map.of(
            new Element(ElementType.CHLORINE), 1D
    ));
    public static final @NotNull ChemicalCompound MnO4 = new IonCompound(Component.text("MnO4"), Map.of(
            new Element(ElementType.MANGANESE), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound CrO4 = new IonCompound(Component.text("CrO4"), Map.of(
            new Element(ElementType.CHROMIUM), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound S2O3 = new IonCompound(Component.text("S2O3"), Map.of(
            new Element(ElementType.SULPHUR), 2D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound CH3COO = new IonCompound(Component.text("CH3COO"), Map.of(
            new Element(ElementType.CARBON), 2D,
            new Element(ElementType.HYDROGEN), 3D,
            new Element(ElementType.OXYGEN), 2D
    ));
    public static final @NotNull ChemicalCompound C2O4 = new IonCompound(Component.text("C2O4"), Map.of(
            new Element(ElementType.CARBON), 2D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound HCOO = new IonCompound(Component.text("HCOO"), Map.of(
            new Element(ElementType.HYDROGEN), 1D,
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.OXYGEN), 2D
    ));
    public static final @NotNull ChemicalCompound ClO = new IonCompound(Component.text("ClO"), Map.of(
            new Element(ElementType.CHLORINE), 1D,
            new Element(ElementType.OXYGEN), 1D
    ));
    public static final @NotNull ChemicalCompound ClO3 = new IonCompound(Component.text("ClO3"), Map.of(
            new Element(ElementType.CHLORINE), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound BOH_4 = new IonCompound(Component.text("B(OH)_4"), Map.of(
            new Element(ElementType.BORON), 1D,
            new Element(ElementType.HYDROGEN), 4D
    ));
    public static final @NotNull ChemicalCompound WO4 = new IonCompound(Component.text("WO4"), Map.of(
            new Element(ElementType.TUNGSTEN), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound SeO4 = new IonCompound(Component.text("SeO4"), Map.of(
            new Element(ElementType.SELENIUM), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound FeO4 = new IonCompound(Component.text("FeO4"), Map.of(
            new Element(ElementType.IRON), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound AlO2 = new IonCompound(Component.text("AlO2"), Map.of(
            new Element(ElementType.ALUMINIUM), 1D,
            new Element(ElementType.OXYGEN), 2D
    ));
    public static final @NotNull ChemicalCompound TiO3 = new IonCompound(Component.text("TiO3"), Map.of(
            new Element(ElementType.TITANIUM), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound CN = new IonCompound(Component.text("CN"), Map.of(
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.NITROGEN), 1D
    ));
    public static final @NotNull ChemicalCompound SCN = new IonCompound(Component.text("SCN"), Map.of(
            new Element(ElementType.SULPHUR), 1D,
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.NITROGEN), 1D
    ));
    public static final @NotNull ChemicalCompound XeO6 = new IonCompound(Component.text("XeO6"), Map.of(
            new Element(ElementType.XENON), 1D,
            new Element(ElementType.OXYGEN), 6D
    ));
    public static final @NotNull ChemicalCompound VO4 = new IonCompound(Component.text("VO4"), Map.of(
            new Element(ElementType.VANADIUM), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound TcO4 = new IonCompound(Component.text("TcO4"), Map.of(
            new Element(ElementType.TECHNETIUM), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound ReO4 = new IonCompound(Component.text("ReO4"), Map.of(
            new Element(ElementType.RHENIUM), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound HPO4 = new IonCompound(Component.text("HPO4"), Map.of(
            new Element(ElementType.HYDROGEN), 1D,
            new Element(ElementType.PHOSPHORUS), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound HSO3 = new IonCompound(Component.text("HSO3"), Map.of(
            new Element(ElementType.HYDROGEN), 1D,
            new Element(ElementType.SULPHUR), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound BrO3 = new IonCompound(Component.text("BrO3"), Map.of(
            new Element(ElementType.BROMINE), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound IO3 = new IonCompound(Component.text("IO3"), Map.of(
            new Element(ElementType.IODINE), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound SiO3 = new IonCompound(Component.text("SiO3"), Map.of(
            new Element(ElementType.SILICON), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound Cr2O7 = new IonCompound(Component.text("Cr2O7"), Map.of(
            new Element(ElementType.CHROMIUM), 2D,
            new Element(ElementType.OXYGEN), 7D
    ));
    public static @NotNull ChemicalCompound NH4 = new ChemicalCompound(Component.text("NH4"), Map.of(
            new Element(ElementType.NITROGEN), 1D,
            new Element(ElementType.HYDROGEN), 4D
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
    public static @NotNull ChemicalCompound HCl = new AcidCompound(Component.text("HCl"), Map.of(
            new Element(ElementType.HYDROGEN), 1D,
            new Element(ElementType.CHLORINE), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Oxides">
    public static final @NotNull ChemicalCompound P2O5 = new OxideCompound(Component.text("P2O5"), Map.of(
            new Element(ElementType.PHOSPHORUS), 2D,
            new Element(ElementType.OXYGEN), 5D
    ));
    public static @NotNull ChemicalCompound H2O = new OxideCompound(Component.text("H2O"), Map.of(
            new Element(ElementType.HYDROGEN), 2D,
            new Element(ElementType.OXYGEN), 1D
    ));
    public static @NotNull ChemicalCompound CO2 = new OxideCompound(Component.text("CO2"), Map.of(
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.OXYGEN), 2D
    ));
    public static @NotNull ChemicalCompound CO = new OxideCompound(Component.text("CO"), Map.of(
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.OXYGEN), 1D
    ));
    public static @NotNull ChemicalCompound N2O = new OxideCompound(Component.text("N2O"), Map.of(
            new Element(ElementType.NITROGEN), 2D,
            new Element(ElementType.OXYGEN), 1D
    ));
    public static @NotNull ChemicalCompound H2O2 = new ChemicalCompound(Component.text("H2O2"), Map.of(
            new Element(ElementType.HYDROGEN), 2D,
            new Element(ElementType.OXYGEN), 2D
    ));
    public static @NotNull ChemicalCompound MnO2 = new OxideCompound(Component.text("MnO2"), Map.of(
            new Element(ElementType.MANGANESE), 1D,
            new Element(ElementType.OXYGEN), 2D
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
    public static final @NotNull ChemicalCompound Fe2SO4_3 = new ChemicalCompound(Component.text("Fe2(SO4)_3"), Map.of(
            new Element(ElementType.IRON), 2D,
            new Chemical(SO4), 3D
    ));
    public static final @NotNull ChemicalCompound KNO3_2 = new ChemicalCompound(Component.text("K(NO3)_2"), Map.of(
            new Element(ElementType.POTASSIUM), 1D,
            new Chemical(NO3), 2D
    ));
    public static final @NotNull ChemicalCompound MnNO3 = new ChemicalCompound(Component.text("MnNO3"), Map.of(
            new Element(ElementType.MANGANESE), 1D,
            new Chemical(NO3), 1D
    ));
    public static final @NotNull ChemicalCompound Na2CO3 = new ChemicalCompound(Component.text("Na2CO3"), Map.of(
            new Element(ElementType.SODIUM), 2D,
            new Chemical(CO3), 1D
    ));

    public static final @NotNull ChemicalCompound NaHCO3 = new ChemicalCompound(Component.text("NaHCO3"), Map.of(
            new Element(ElementType.SODIUM), 1D,
            new Chemical(HCO3), 1D
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
    //</editor-fold>

    //<editor-fold desc="OHs">
    public static @NotNull ChemicalCompound CaOH2 = new ChemicalCompound(Component.text("Ca(OH)_2"), Map.of(
            new Element(ElementType.CALCIUM), 1D,
            new Chemical(OH), 2D
    ));
    public static @NotNull ChemicalCompound MgOH2 = new ChemicalCompound(Component.text("Mg(OH)_2"), Map.of(
            new Element(ElementType.MAGNESIUM), 1D,
            new Chemical(OH), 2D
    ));
    public static @NotNull ChemicalCompound FeOH2 = new ChemicalCompound(Component.text("Fe(OH)_2"), Map.of(
            new Element(ElementType.IRON), 1D,
            new Chemical(OH), 2D
    ));
    public static @NotNull ChemicalCompound NaOH = new ChemicalCompound(Component.text("NaOH"), Map.of(
            new Element(ElementType.SODIUM), 1D,
            new Chemical(OH), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Other compounds">
    public static @NotNull ChemicalCompound O2 = new ChemicalCompound(Component.text("O2"), Map.of(
            new Element(ElementType.OXYGEN), 2D
    ));
    public static @NotNull ChemicalCompound H2 = new ChemicalCompound(Component.text("H2"), Map.of(
            new Element(ElementType.HYDROGEN), 2D
    ));
    public static @NotNull ChemicalCompound N2 = new ChemicalCompound(Component.text("N2"), Map.of(
            new Element(ElementType.NITROGEN), 2D
    ));
    public static @NotNull ChemicalCompound NH3 = new ChemicalCompound(Component.text("NH3"), Map.of(
            new Element(ElementType.NITROGEN), 1D,
            new Element(ElementType.HYDROGEN), 3D
    ));
    public static @NotNull ChemicalCompound CH4 = new ChemicalCompound(Component.text("CH4"), Map.of(
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.HYDROGEN), 4D
    ));
    public static @NotNull ChemicalCompound C2H5OH = new ChemicalCompound(Component.text("C2H5OH"), Map.of(
            new Element(ElementType.CARBON), 2D,
            new Element(ElementType.HYDROGEN), 6D,
            new Element(ElementType.OXYGEN), 1D
    ));
    //</editor-fold>
}
