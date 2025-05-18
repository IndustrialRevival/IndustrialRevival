package org.irmc.industrialrevival.api.elements.compounds;

import net.kyori.adventure.text.Component;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.compounds.types.AcidCompound;
import org.irmc.industrialrevival.api.elements.compounds.types.IonCompound;
import org.irmc.industrialrevival.api.elements.compounds.types.OxideCompound;
import org.irmc.industrialrevival.utils.Debug;
import org.irmc.industrialrevival.utils.TextUtil;
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
    public static boolean loaded = false;
    //<editor-fold desc="Compounds elements">
    static {
        for (ElementType elementType : ElementType.values()) {
            new ChemicalCompound(
                    TextUtil.onlyUpperFirstLetter(elementType.getSymbol()),
                    Map.of(new Element(elementType), 1D));
        }
    }
    //</editor-fold>

    //<editor-fold desc="Compounds ions">
    public static final @NotNull ChemicalCompound SO4 = new IonCompound("SO4", Map.of(
            new Element(ElementType.SULPHUR), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound NO3 = new IonCompound("NO3", Map.of(
            new Element(ElementType.NITROGEN), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound SO3 = new IonCompound("SO3", Map.of(
            new Element(ElementType.SULPHUR), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound OH = new IonCompound("OH", Map.of(
            new Element(ElementType.OXYGEN), 1D,
            new Element(ElementType.HYDROGEN), 1D
    ));
    public static final @NotNull ChemicalCompound CO3 = new IonCompound("CO3", Map.of(
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound HCO3 = new IonCompound("HCO3", Map.of(
            new Element(ElementType.HYDROGEN), 1D,
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound PO4 = new IonCompound("PO4", Map.of(
            new Element(ElementType.PHOSPHORUS), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound MnO4 = new IonCompound("MnO4", Map.of(
            new Element(ElementType.MANGANESE), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound CrO4 = new IonCompound("CrO4", Map.of(
            new Element(ElementType.CHROMIUM), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound S2O3 = new IonCompound("S2O3", Map.of(
            new Element(ElementType.SULPHUR), 2D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound CH3COO = new IonCompound("CH3COO", Map.of(
            new Element(ElementType.CARBON), 2D,
            new Element(ElementType.HYDROGEN), 3D,
            new Element(ElementType.OXYGEN), 2D
    ));
    public static final @NotNull ChemicalCompound C2O4 = new IonCompound("C2O4", Map.of(
            new Element(ElementType.CARBON), 2D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound HCOO = new IonCompound("HCOO", Map.of(
            new Element(ElementType.HYDROGEN), 1D,
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.OXYGEN), 2D
    ));
    public static final @NotNull ChemicalCompound ClO = new IonCompound("ClO", Map.of(
            new Element(ElementType.CHLORINE), 1D,
            new Element(ElementType.OXYGEN), 1D
    ));
    public static final @NotNull ChemicalCompound ClO3 = new IonCompound("ClO3", Map.of(
            new Element(ElementType.CHLORINE), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound BOH_4 = new IonCompound("B(OH)_4", Map.of(
            new Element(ElementType.BORON), 1D,
            new Element(ElementType.HYDROGEN), 4D
    ));
    public static final @NotNull ChemicalCompound WO4 = new IonCompound("WO4", Map.of(
            new Element(ElementType.TUNGSTEN), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound SeO4 = new IonCompound("SeO4", Map.of(
            new Element(ElementType.SELENIUM), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound FeO4 = new IonCompound("FeO4", Map.of(
            new Element(ElementType.IRON), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound AlO2 = new IonCompound("AlO2", Map.of(
            new Element(ElementType.ALUMINIUM), 1D,
            new Element(ElementType.OXYGEN), 2D
    ));
    public static final @NotNull ChemicalCompound TiO3 = new IonCompound("TiO3", Map.of(
            new Element(ElementType.TITANIUM), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound CN = new IonCompound("CN", Map.of(
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.NITROGEN), 1D
    ));
    public static final @NotNull ChemicalCompound SCN = new IonCompound("SCN", Map.of(
            new Element(ElementType.SULPHUR), 1D,
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.NITROGEN), 1D
    ));
    public static final @NotNull ChemicalCompound XeO6 = new IonCompound("XeO6", Map.of(
            new Element(ElementType.XENON), 1D,
            new Element(ElementType.OXYGEN), 6D
    ));
    public static final @NotNull ChemicalCompound VO4 = new IonCompound("VO4", Map.of(
            new Element(ElementType.VANADIUM), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound TcO4 = new IonCompound("TcO4", Map.of(
            new Element(ElementType.TECHNETIUM), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound ReO4 = new IonCompound("ReO4", Map.of(
            new Element(ElementType.RHENIUM), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound HPO4 = new IonCompound("HPO4", Map.of(
            new Element(ElementType.HYDROGEN), 1D,
            new Element(ElementType.PHOSPHORUS), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static final @NotNull ChemicalCompound HSO3 = new IonCompound("HSO3", Map.of(
            new Element(ElementType.HYDROGEN), 1D,
            new Element(ElementType.SULPHUR), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound BrO3 = new IonCompound("BrO3", Map.of(
            new Element(ElementType.BROMINE), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound IO3 = new IonCompound("IO3", Map.of(
            new Element(ElementType.IODINE), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound SiO3 = new IonCompound("SiO3", Map.of(
            new Element(ElementType.SILICON), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static final @NotNull ChemicalCompound Cr2O7 = new IonCompound("Cr2O7", Map.of(
            new Element(ElementType.CHROMIUM), 2D,
            new Element(ElementType.OXYGEN), 7D
    ));
    public static @NotNull ChemicalCompound NH4 = new ChemicalCompound("NH4", Map.of(
            new Element(ElementType.NITROGEN), 1D,
            new Element(ElementType.HYDROGEN), 4D
    ));
    //</editor-fold>

    //<editor-fold desc="Acids">
    public static final @NotNull ChemicalCompound H2SO4 = new AcidCompound("H2SO4", Map.of(
            new Element(ElementType.HYDROGEN), 2D,
            new Chemical(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound HNO3 = new AcidCompound("HNO3", Map.of(
            new Element(ElementType.HYDROGEN), 1D,
            new Chemical(NO3), 1D
    ));
    public static final @NotNull ChemicalCompound H2CO3 = new AcidCompound("H2CO3", Map.of(
            new Element(ElementType.HYDROGEN), 2D,
            new Chemical(CO3), 1D
    ));
    public static @NotNull ChemicalCompound HCl = new AcidCompound("HCl", Map.of(
            new Element(ElementType.HYDROGEN), 1D,
            new Element(ElementType.CHLORINE), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Oxides">
    public static final @NotNull ChemicalCompound P2O5 = new OxideCompound("P2O5", Map.of(
            new Element(ElementType.PHOSPHORUS), 2D,
            new Element(ElementType.OXYGEN), 5D
    ));
    public static @NotNull ChemicalCompound H2O = new OxideCompound("H2O", Map.of(
            new Element(ElementType.HYDROGEN), 2D,
            new Element(ElementType.OXYGEN), 1D
    ));
    public static @NotNull ChemicalCompound CO2 = new OxideCompound("CO2", Map.of(
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.OXYGEN), 2D
    ));
    public static @NotNull ChemicalCompound CO = new OxideCompound("CO", Map.of(
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.OXYGEN), 1D
    ));
    public static @NotNull ChemicalCompound N2O = new OxideCompound("N2O", Map.of(
            new Element(ElementType.NITROGEN), 2D,
            new Element(ElementType.OXYGEN), 1D
    ));
    public static @NotNull ChemicalCompound H2O2 = new ChemicalCompound("H2O2", Map.of(
            new Element(ElementType.HYDROGEN), 2D,
            new Element(ElementType.OXYGEN), 2D
    ));
    public static @NotNull ChemicalCompound MnO2 = new OxideCompound("MnO2", Map.of(
            new Element(ElementType.MANGANESE), 1D,
            new Element(ElementType.OXYGEN), 2D
    ));
    public static @NotNull ChemicalCompound CaO = new OxideCompound("CaO", Map.of(
            new Element(ElementType.CALCIUM), 1D,
            new Element(ElementType.OXYGEN), 1D
    ));
    public static @NotNull ChemicalCompound FeO = new OxideCompound("FeO", Map.of(
            new Element(ElementType.IRON), 1D,
            new Element(ElementType.OXYGEN), 1D
    ));
    public static @NotNull ChemicalCompound Fe2O3 = new OxideCompound("Fe2O3", Map.of(
            new Element(ElementType.IRON), 2D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static @NotNull ChemicalCompound Fe3O4 = new OxideCompound("Fe3O4", Map.of(
            new Element(ElementType.IRON), 3D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static @NotNull ChemicalCompound Al2O3 = new OxideCompound("Al2O3", Map.of(
            new Element(ElementType.ALUMINIUM), 2D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static @NotNull ChemicalCompound MnO = new OxideCompound("MnO", Map.of(
            new Element(ElementType.MANGANESE), 1D,
            new Element(ElementType.OXYGEN), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="SO4s">
    public static final @NotNull ChemicalCompound MgSO4 = new ChemicalCompound("MgSO4", Map.of(
            new Element(ElementType.MAGNESIUM), 1D,
            new Chemical(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound FeSO4 = new ChemicalCompound("FeSO4", Map.of(
            new Element(ElementType.IRON), 1D,
            new Chemical(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound Na2SO4 = new ChemicalCompound("Na2SO4", Map.of(
            new Element(ElementType.SODIUM), 2D,
            new Chemical(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound CaSO4 = new ChemicalCompound("CaSO4", Map.of(
            new Element(ElementType.CALCIUM), 1D,
            new Chemical(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound K2SO4 = new ChemicalCompound("K2SO4", Map.of(
            new Element(ElementType.POTASSIUM), 2D,
            new Chemical(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound MnSO4 = new ChemicalCompound("Cl2SO4", Map.of(
            new Element(ElementType.MANGANESE), 1D,
            new Chemical(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound ZnSO4 = new ChemicalCompound("ZnSO4", Map.of(
            new Element(ElementType.ZINC), 1D,
            new Chemical(SO4), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="NO3s">
    public static final @NotNull ChemicalCompound KNO3 = new ChemicalCompound("KNO3", Map.of(
            new Element(ElementType.POTASSIUM), 1D,
            new Chemical(NO3), 1D
    ));
    public static final @NotNull ChemicalCompound MgNO3_2 = new ChemicalCompound("Mg(NO3)_2", Map.of(
            new Element(ElementType.MAGNESIUM), 1D,
            new Chemical(NO3), 2D
    ));
    public static final @NotNull ChemicalCompound FeNO3_2 = new ChemicalCompound("Fe(NO3)_2", Map.of(
            new Element(ElementType.IRON), 1D,
            new Chemical(NO3), 2D
    ));
    public static final @NotNull ChemicalCompound CaNO3_2 = new ChemicalCompound("Ca(NO3)_2", Map.of(
            new Element(ElementType.CALCIUM), 1D,
            new Chemical(NO3), 2D
    ));
    public static final @NotNull ChemicalCompound NaNO3 = new ChemicalCompound("NaNO3", Map.of(
            new Element(ElementType.SODIUM), 1D,
            new Chemical(NO3), 1D
    ));
    public static final @NotNull ChemicalCompound Fe2SO4_3 = new ChemicalCompound("Fe2(SO4)_3", Map.of(
            new Element(ElementType.IRON), 2D,
            new Chemical(SO4), 3D
    ));
    public static final @NotNull ChemicalCompound KNO3_2 = new ChemicalCompound("K(NO3)_2", Map.of(
            new Element(ElementType.POTASSIUM), 1D,
            new Chemical(NO3), 2D
    ));
    public static final @NotNull ChemicalCompound MnNO3 = new ChemicalCompound("MnNO3", Map.of(
            new Element(ElementType.MANGANESE), 1D,
            new Chemical(NO3), 1D
    ));
    public static final @NotNull ChemicalCompound Na2CO3 = new ChemicalCompound("Na2CO3", Map.of(
            new Element(ElementType.SODIUM), 2D,
            new Chemical(CO3), 1D
    ));
    public static final @NotNull ChemicalCompound AgNO3 = new ChemicalCompound("AgNO3", Map.of(
            new Element(ElementType.SILVER), 1D,
            new Chemical(NO3), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="HCO3s">
    public static final @NotNull ChemicalCompound NaHCO3 = new ChemicalCompound("NaHCO3", Map.of(
            new Element(ElementType.SODIUM), 1D,
            new Chemical(HCO3), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Cls">
    public static @NotNull ChemicalCompound KCL = new ChemicalCompound("KCl", Map.of(
            new Element(ElementType.POTASSIUM), 1D,
            new Element(ElementType.CHLORINE), 1D
    ));
    public static @NotNull ChemicalCompound CaCl2 = new ChemicalCompound("CaCl2", Map.of(
            new Element(ElementType.CALCIUM), 1D,
            new Element(ElementType.CHLORINE), 2D
    ));
    public static @NotNull ChemicalCompound MgCl2 = new ChemicalCompound("MgCl2", Map.of(
            new Element(ElementType.MAGNESIUM), 1D,
            new Element(ElementType.CHLORINE), 2D
    ));
    public static @NotNull ChemicalCompound FeCl2 = new ChemicalCompound("FeCl2", Map.of(
            new Element(ElementType.IRON), 1D,
            new Element(ElementType.CHLORINE), 2D
    ));
    public static @NotNull ChemicalCompound NaCl = new ChemicalCompound("NaCl", Map.of(
            new Element(ElementType.SODIUM), 1D,
            new Element(ElementType.CHLORINE), 1D
    ));
    public static @NotNull ChemicalCompound AgCl = new ChemicalCompound("AgCl", Map.of(
            new Element(ElementType.SILVER), 1D,
            new Element(ElementType.CHLORINE), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="OHs">
    public static @NotNull ChemicalCompound CaOH_2 = new ChemicalCompound("Ca(OH)_2", Map.of(
            new Element(ElementType.CALCIUM), 1D,
            new Chemical(OH), 2D
    ));
    public static @NotNull ChemicalCompound MgOH_2 = new ChemicalCompound("Mg(OH)_2", Map.of(
            new Element(ElementType.MAGNESIUM), 1D,
            new Chemical(OH), 2D
    ));
    public static @NotNull ChemicalCompound FeOH_2 = new ChemicalCompound("Fe(OH)_2", Map.of(
            new Element(ElementType.IRON), 1D,
            new Chemical(OH), 2D
    ));
    public static @NotNull ChemicalCompound NaOH = new ChemicalCompound("NaOH", Map.of(
            new Element(ElementType.SODIUM), 1D,
            new Chemical(OH), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="CO3s">
    public static @NotNull ChemicalCompound CaCO3 = new ChemicalCompound("CaCO3", Map.of(
            new Element(ElementType.CALCIUM), 1D,
            new Chemical(CO3), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Other compounds">
    public static @NotNull ChemicalCompound O2 = new ChemicalCompound("O2", Map.of(
            new Element(ElementType.OXYGEN), 2D
    ));
    public static @NotNull ChemicalCompound H2 = new ChemicalCompound("H2", Map.of(
            new Element(ElementType.HYDROGEN), 2D
    ));
    public static @NotNull ChemicalCompound N2 = new ChemicalCompound("N2", Map.of(
            new Element(ElementType.NITROGEN), 2D
    ));
    public static @NotNull ChemicalCompound Cl2 = new ChemicalCompound("Cl2", Map.of(
            new Element(ElementType.CHLORINE), 2D
    ));
    public static @NotNull ChemicalCompound NO2 = new ChemicalCompound("NO2", Map.of(
            new Element(ElementType.NITROGEN), 1D,
            new Element(ElementType.OXYGEN), 2D
    ));
    public static @NotNull ChemicalCompound NH3 = new ChemicalCompound("NH3", Map.of(
            new Element(ElementType.NITROGEN), 1D,
            new Element(ElementType.HYDROGEN), 3D
    ));
    public static @NotNull ChemicalCompound CH4 = new ChemicalCompound("CH4", Map.of(
            new Element(ElementType.CARBON), 1D,
            new Element(ElementType.HYDROGEN), 4D
    ));
    public static @NotNull ChemicalCompound C2H5OH = new ChemicalCompound("C2H5OH", Map.of(
            new Element(ElementType.CARBON), 2D,
            new Element(ElementType.HYDROGEN), 6D,
            new Element(ElementType.OXYGEN), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Other chemicals">
    public static @NotNull ChemicalCompound KMnO4 = new ChemicalCompound("KMnO4", Map.of(
            new Element(ElementType.POTASSIUM), 1D,
            new Chemical(MnO4), 1D
    ));
    public static @NotNull ChemicalCompound K2MnO4 = new ChemicalCompound("K2MnO4", Map.of(
            new Element(ElementType.POTASSIUM), 2D,
            new Chemical(MnO4), 1D
    ));
    public static @NotNull ChemicalCompound KClO3 = new ChemicalCompound("KClO3", Map.of(
            new Element(ElementType.POTASSIUM), 1D,
            new Chemical(ClO3), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Imagines">
    public static @NotNull ChemicalCompound AuFe = new ChemicalCompound("AuFe", Map.of(
            new Element(ElementType.GOLD), 1D,
            new Element(ElementType.IRON), 1D
    ));
    //</editor-fold>

    /**
     * Initializes the chemical compounds above.
     */
    public static void load() {
        loaded = true;
        Debug.log("Loaded chemical compounds");
    }
}
