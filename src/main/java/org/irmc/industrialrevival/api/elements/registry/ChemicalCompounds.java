package org.irmc.industrialrevival.api.elements.registry;

import com.google.common.base.Preconditions;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.compounds.Chemical;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.Compound;
import org.irmc.industrialrevival.api.elements.compounds.Element;
import org.irmc.industrialrevival.api.elements.compounds.types.AcidCompound;
import org.irmc.industrialrevival.api.elements.compounds.types.IonCompound;
import org.irmc.industrialrevival.api.elements.compounds.types.OxideCompound;
import org.irmc.industrialrevival.utils.Debug;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
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
    public static final List<Runnable> onLoad = new ArrayList<>();
    public static boolean loaded = false;

    public static void onLoad(@NotNull Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        onLoad.add(runnable);
    }

    //<editor-fold desc="Compounds elements">
    static {
        for (ElementType elementType : ElementType.values()) {
            new ChemicalCompound(
                    elementType.getSymbol(),
                    Map.of(asCompound(elementType), 1D));
        }
    }
    //</editor-fold>

    //<editor-fold desc="Compounds ions">
    public static final @NotNull IonCompound SO4 = new IonCompound("SO4", Map.of(
            asCompound(ElementType.S), 1D,
            asCompound(ElementType.O), 4D
    ));
    public static final @NotNull IonCompound NO3 = new IonCompound("NO3", Map.of(
            asCompound(ElementType.N), 1D,
            asCompound(ElementType.O), 3D
    ));
    public static final @NotNull IonCompound SO3 = new IonCompound("SO3", Map.of(
            asCompound(ElementType.S), 1D,
            asCompound(ElementType.O), 3D
    ));
    public static final @NotNull IonCompound OH = new IonCompound("OH", Map.of(
            asCompound(ElementType.O), 1D,
            asCompound(ElementType.H), 1D
    ));
    public static final @NotNull IonCompound CO3 = new IonCompound("CO3", Map.of(
            asCompound(ElementType.C), 1D,
            asCompound(ElementType.O), 3D
    ));
    public static final @NotNull IonCompound HCO3 = new IonCompound("HCO3", Map.of(
            asCompound(ElementType.H), 1D,
            asCompound(ElementType.C), 1D,
            asCompound(ElementType.O), 3D
    ));
    public static final @NotNull IonCompound PO4 = new IonCompound("PO4", Map.of(
            asCompound(ElementType.P), 1D,
            asCompound(ElementType.O), 4D
    ));
    public static final @NotNull IonCompound MnO4 = new IonCompound("MnO4", Map.of(
            asCompound(ElementType.Mn), 1D,
            asCompound(ElementType.O), 4D
    ));
    public static final @NotNull IonCompound CrO4 = new IonCompound("CrO4", Map.of(
            asCompound(ElementType.Cr), 1D,
            asCompound(ElementType.O), 4D
    ));
    public static final @NotNull IonCompound S2O3 = new IonCompound("S2O3", Map.of(
            asCompound(ElementType.S), 2D,
            asCompound(ElementType.O), 3D
    ));
    public static final @NotNull IonCompound CH3COO = new IonCompound("CH3COO", Map.of(
            asCompound(ElementType.C), 2D,
            asCompound(ElementType.H), 3D,
            asCompound(ElementType.O), 2D
    ));
    public static final @NotNull IonCompound C2O4 = new IonCompound("C2O4", Map.of(
            asCompound(ElementType.C), 2D,
            asCompound(ElementType.O), 4D
    ));
    public static final @NotNull IonCompound HCOO = new IonCompound("HCOO", Map.of(
            asCompound(ElementType.H), 1D,
            asCompound(ElementType.C), 1D,
            asCompound(ElementType.O), 2D
    ));
    public static final @NotNull IonCompound ClO = new IonCompound("ClO", Map.of(
            asCompound(ElementType.Cl), 1D,
            asCompound(ElementType.O), 1D
    ));
    public static final @NotNull IonCompound ClO3 = new IonCompound("ClO3", Map.of(
            asCompound(ElementType.Cl), 1D,
            asCompound(ElementType.O), 3D
    ));
    public static final @NotNull IonCompound BOH_4 = new IonCompound("B(OH)_4", Map.of(
            asCompound(ElementType.B), 1D,
            asCompound(ElementType.H), 4D
    ));
    public static final @NotNull IonCompound WO4 = new IonCompound("WO4", Map.of(
            asCompound(ElementType.W), 1D,
            asCompound(ElementType.O), 4D
    ));
    public static final @NotNull IonCompound SeO4 = new IonCompound("SeO4", Map.of(
            asCompound(ElementType.Se), 1D,
            asCompound(ElementType.O), 4D
    ));
    public static final @NotNull IonCompound FeO4 = new IonCompound("FeO4", Map.of(
            asCompound(ElementType.Fe), 1D,
            asCompound(ElementType.O), 4D
    ));
    public static final @NotNull IonCompound AlO2 = new IonCompound("AlO2", Map.of(
            asCompound(ElementType.Al), 1D,
            asCompound(ElementType.O), 2D
    ));
    public static final @NotNull IonCompound TiO3 = new IonCompound("TiO3", Map.of(
            asCompound(ElementType.Ti), 1D,
            asCompound(ElementType.O), 3D
    ));
    public static final @NotNull IonCompound CN = new IonCompound("CN", Map.of(
            asCompound(ElementType.C), 1D,
            asCompound(ElementType.N), 1D
    ));
    public static final @NotNull IonCompound SCN = new IonCompound("SCN", Map.of(
            asCompound(ElementType.S), 1D,
            asCompound(ElementType.C), 1D,
            asCompound(ElementType.N), 1D
    ));
    public static final @NotNull IonCompound XeO6 = new IonCompound("XeO6", Map.of(
            asCompound(ElementType.Xe), 1D,
            asCompound(ElementType.O), 6D
    ));
    public static final @NotNull IonCompound VO4 = new IonCompound("VO4", Map.of(
            asCompound(ElementType.V), 1D,
            asCompound(ElementType.O), 4D
    ));
    public static final @NotNull IonCompound TcO4 = new IonCompound("TcO4", Map.of(
            asCompound(ElementType.Tc), 1D,
            asCompound(ElementType.O), 4D
    ));
    public static final @NotNull IonCompound ReO4 = new IonCompound("ReO4", Map.of(
            asCompound(ElementType.Re), 1D,
            asCompound(ElementType.O), 4D
    ));
    public static final @NotNull IonCompound HPO4 = new IonCompound("HPO4", Map.of(
            asCompound(ElementType.H), 1D,
            asCompound(ElementType.P), 1D,
            asCompound(ElementType.O), 4D
    ));
    public static final @NotNull IonCompound HSO3 = new IonCompound("HSO3", Map.of(
            asCompound(ElementType.H), 1D,
            asCompound(ElementType.S), 1D,
            asCompound(ElementType.O), 3D
    ));
    public static final @NotNull IonCompound BrO3 = new IonCompound("BrO3", Map.of(
            asCompound(ElementType.Br), 1D,
            asCompound(ElementType.O), 3D
    ));
    public static final @NotNull IonCompound IO3 = new IonCompound("IO3", Map.of(
            asCompound(ElementType.I), 1D,
            asCompound(ElementType.O), 3D
    ));
    public static final @NotNull IonCompound SiO3 = new IonCompound("SiO3", Map.of(
            asCompound(ElementType.Si), 1D,
            asCompound(ElementType.O), 3D
    ));
    public static final @NotNull IonCompound Cr2O7 = new IonCompound("Cr2O7", Map.of(
            asCompound(ElementType.Cr), 2D,
            asCompound(ElementType.O), 7D
    ));
    public static final @NotNull IonCompound NH4 = new IonCompound("NH4", Map.of(
            asCompound(ElementType.N), 1D,
            asCompound(ElementType.H), 4D
    ));
    public static final @NotNull IonCompound SiO4 = new IonCompound("SiO4", Map.of(
            asCompound(ElementType.Si), 1D,
            asCompound(ElementType.O), 4D
    ));
    //</editor-fold>

    //<editor-fold desc="Acids">
    public static final @NotNull ChemicalCompound H2SO4 = new AcidCompound("H2SO4", Map.of(
            asCompound(ElementType.H), 2D,
            asCompound(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound HNO3 = new AcidCompound("HNO3", Map.of(
            asCompound(ElementType.H), 1D,
            asCompound(NO3), 1D
    ));
    public static final @NotNull ChemicalCompound H2CO3 = new AcidCompound("H2CO3", Map.of(
            asCompound(ElementType.H), 2D,
            asCompound(CO3), 1D
    ));
    public static final @NotNull ChemicalCompound HCl = new AcidCompound("HCl", Map.of(
            asCompound(ElementType.H), 1D,
            asCompound(ElementType.Cl), 1D
    ));
    public static final @NotNull ChemicalCompound HF = new AcidCompound("HF", Map.of(
            asCompound(ElementType.H), 1D,
            asCompound(ElementType.F), 1D
    ));
    public static final @NotNull ChemicalCompound HI = new AcidCompound("HI", Map.of(
            asCompound(ElementType.H), 1D,
            asCompound(ElementType.I), 1D
    ));
    public static final @NotNull ChemicalCompound H2SO3 = new ChemicalCompound("H2SO3", Map.of(
            asCompound(ElementType.H), 2D,
            asCompound(SO3), 1D
    ));
    public static final @NotNull ChemicalCompound H4SiO4 = new ChemicalCompound("H4SiO4", Map.of(
            asCompound(ElementType.H), 4D,
            asCompound(SiO4), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Oxides">
    public static final @NotNull ChemicalCompound P2O5 = new OxideCompound("P2O5", Map.of(
            asCompound(ElementType.P), 2D,
            asCompound(ElementType.O), 5D
    ));
    public static final @NotNull ChemicalCompound H2O = new OxideCompound("H2O", Map.of(
            asCompound(ElementType.H), 2D,
            asCompound(ElementType.O), 1D
    ));
    public static final @NotNull ChemicalCompound CO2 = new OxideCompound("CO2", Map.of(
            asCompound(ElementType.C), 1D,
            asCompound(ElementType.O), 2D
    ));
    public static final @NotNull ChemicalCompound CO = new OxideCompound("CO", Map.of(
            asCompound(ElementType.C), 1D,
            asCompound(ElementType.O), 1D
    ));
    public static final @NotNull ChemicalCompound N2O = new OxideCompound("N2O", Map.of(
            asCompound(ElementType.N), 2D,
            asCompound(ElementType.O), 1D
    ));
    public static final @NotNull ChemicalCompound H2O2 = new ChemicalCompound("H2O2", Map.of(
            asCompound(ElementType.H), 2D,
            asCompound(ElementType.O), 2D
    ));
    public static final @NotNull ChemicalCompound MnO2 = new OxideCompound("MnO2", Map.of(
            asCompound(ElementType.Mn), 1D,
            asCompound(ElementType.O), 2D
    ));
    public static final @NotNull ChemicalCompound CaO = new OxideCompound("CaO", Map.of(
            asCompound(ElementType.Ca), 1D,
            asCompound(ElementType.O), 1D
    ));
    public static final @NotNull ChemicalCompound FeO = new OxideCompound("FeO", Map.of(
            asCompound(ElementType.Fe), 1D,
            asCompound(ElementType.O), 1D
    ));
    public static final @NotNull ChemicalCompound Fe2O3 = new OxideCompound("Fe2O3", Map.of(
            asCompound(ElementType.Fe), 2D,
            asCompound(ElementType.O), 3D
    ));
    public static final @NotNull ChemicalCompound Fe3O4 = new OxideCompound("Fe3O4", Map.of(
            asCompound(ElementType.Fe), 3D,
            asCompound(ElementType.O), 4D
    ));
    public static final @NotNull ChemicalCompound Al2O3 = new OxideCompound("Al2O3", Map.of(
            asCompound(ElementType.Al), 2D,
            asCompound(ElementType.O), 3D
    ));
    public static final @NotNull ChemicalCompound MnO = new OxideCompound("MnO", Map.of(
            asCompound(ElementType.Mn), 1D,
            asCompound(ElementType.O), 1D
    ));
    public static final @NotNull ChemicalCompound MgO = new ChemicalCompound("MgO", Map.of(
            asCompound(ElementType.Mg), 1D,
            asCompound(ElementType.O), 1D
    ));
    public static final @NotNull ChemicalCompound CuO = new ChemicalCompound("CuO", Map.of(
            asCompound(ElementType.Cu), 1D,
            asCompound(ElementType.O), 1D
    ));
    public static final @NotNull ChemicalCompound HgO = new ChemicalCompound("HgO", Map.of(
            asCompound(ElementType.Hg), 1D,
            asCompound(ElementType.O), 1D
    ));
    public static final @NotNull ChemicalCompound SiO2 = new ChemicalCompound("SiO2", Map.of(
            asCompound(ElementType.Si), 1D,
            asCompound(ElementType.O), 2D
    ));
    //</editor-fold>

    //<editor-fold desc="SO4s">
    public static final @NotNull ChemicalCompound MgSO4 = new ChemicalCompound("MgSO4", Map.of(
            asCompound(ElementType.Mg), 1D,
            asCompound(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound FeSO4 = new ChemicalCompound("FeSO4", Map.of(
            asCompound(ElementType.Fe), 1D,
            asCompound(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound Na2SO4 = new ChemicalCompound("Na2SO4", Map.of(
            asCompound(ElementType.Na), 2D,
            asCompound(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound CaSO4 = new ChemicalCompound("CaSO4", Map.of(
            asCompound(ElementType.Ca), 1D,
            asCompound(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound K2SO4 = new ChemicalCompound("K2SO4", Map.of(
            asCompound(ElementType.K), 2D,
            asCompound(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound MnSO4 = new ChemicalCompound("Cl2SO4", Map.of(
            asCompound(ElementType.Mn), 1D,
            asCompound(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound ZnSO4 = new ChemicalCompound("ZnSO4", Map.of(
            asCompound(ElementType.Zn), 1D,
            asCompound(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound Al2SO4_3 = new ChemicalCompound("Al2(SO4)_3", Map.of(
            asCompound(ElementType.Al), 2D,
            asCompound(SO4), 3D
    ));
    public static final @NotNull ChemicalCompound CuSO4 = new ChemicalCompound("CuSO4", Map.of(
            asCompound(ElementType.Cu), 1D,
            asCompound(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound NH4_2SO4 = new ChemicalCompound("(NH4)_2SO4", Map.of(
            asCompound(NH4), 2D,
            asCompound(SO4), 1D
    ));
    public static final @NotNull ChemicalCompound BaSO4 = new ChemicalCompound("BaSO4", Map.of(
            asCompound(ElementType.Ba), 1D,
            asCompound(SO4), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="NO3s">
    public static final @NotNull ChemicalCompound KNO3 = new ChemicalCompound("KNO3", Map.of(
            asCompound(ElementType.K), 1D,
            asCompound(NO3), 1D
    ));
    public static final @NotNull ChemicalCompound MgNO3_2 = new ChemicalCompound("Mg(NO3)_2", Map.of(
            asCompound(ElementType.Mg), 1D,
            asCompound(NO3), 2D
    ));
    public static final @NotNull ChemicalCompound FeNO3_2 = new ChemicalCompound("Fe(NO3)_2", Map.of(
            asCompound(ElementType.Fe), 1D,
            asCompound(NO3), 2D
    ));
    public static final @NotNull ChemicalCompound CaNO3_2 = new ChemicalCompound("Ca(NO3)_2", Map.of(
            asCompound(ElementType.Ca), 1D,
            asCompound(NO3), 2D
    ));
    public static final @NotNull ChemicalCompound NaNO3 = new ChemicalCompound("NaNO3", Map.of(
            asCompound(ElementType.Na), 1D,
            asCompound(NO3), 1D
    ));
    public static final @NotNull ChemicalCompound Fe2SO4_3 = new ChemicalCompound("Fe2(SO4)_3", Map.of(
            asCompound(ElementType.Fe), 2D,
            asCompound(SO4), 3D
    ));
    public static final @NotNull ChemicalCompound KNO3_2 = new ChemicalCompound("K(NO3)_2", Map.of(
            asCompound(ElementType.K), 1D,
            asCompound(NO3), 2D
    ));
    public static final @NotNull ChemicalCompound MnNO3 = new ChemicalCompound("MnNO3", Map.of(
            asCompound(ElementType.Mn), 1D,
            asCompound(NO3), 1D
    ));
    public static final @NotNull ChemicalCompound Na2CO3 = new ChemicalCompound("Na2CO3", Map.of(
            asCompound(ElementType.Na), 2D,
            asCompound(CO3), 1D
    ));
    public static final @NotNull ChemicalCompound AgNO3 = new ChemicalCompound("AgNO3", Map.of(
            asCompound(ElementType.Ag), 1D,
            asCompound(NO3), 1D
    ));
    public static final @NotNull ChemicalCompound AlNO3_3 = new ChemicalCompound("Al(NO3)_3", Map.of(
            asCompound(ElementType.Al), 1D,
            asCompound(NO3), 3D
    ));
    public static final @NotNull ChemicalCompound ZnNO3_2 = new ChemicalCompound("Zn(NO3)_2", Map.of(
            asCompound(ElementType.Zn), 1D,
            asCompound(NO3), 2D
    ));
    public static final @NotNull ChemicalCompound CuNO3_2 = new ChemicalCompound("Cu(NO3)_2", Map.of(
            asCompound(ElementType.Cu), 1D,
            asCompound(NO3), 2D
    ));
    public static final @NotNull ChemicalCompound BaNO3_2 = new ChemicalCompound("Ba(NO3)_2", Map.of(
            asCompound(ElementType.Ba), 1D,
            asCompound(NO3), 2D
    ));
    //</editor-fold>

    //<editor-fold desc="HCO3s">
    public static final @NotNull ChemicalCompound NaHCO3 = new ChemicalCompound("NaHCO3", Map.of(
            asCompound(ElementType.Na), 1D,
            asCompound(HCO3), 1D
    ));
    public static final @NotNull ChemicalCompound CaHCO3_2 = new ChemicalCompound("Ca(HCO3)_2", Map.of(
            asCompound(ElementType.Ca), 1D,
            asCompound(HCO3), 2D
    ));
    public static final @NotNull ChemicalCompound MgHCO3_2 = new ChemicalCompound("Mg(HCO3)_2", Map.of(
            asCompound(ElementType.Mg), 1D,
            asCompound(HCO3), 2D
    ));
    public static final @NotNull ChemicalCompound NH4HCO3 = new ChemicalCompound("NH4HCO3", Map.of(
            asCompound(NH4), 1D,
            asCompound(HCO3), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Cls">
    public static final @NotNull ChemicalCompound KCL = new ChemicalCompound("KCl", Map.of(
            asCompound(ElementType.K), 1D,
            asCompound(ElementType.Cl), 1D
    ));
    public static final @NotNull ChemicalCompound CaCl2 = new ChemicalCompound("CaCl2", Map.of(
            asCompound(ElementType.Ca), 1D,
            asCompound(ElementType.Cl), 2D
    ));
    public static final @NotNull ChemicalCompound MgCl2 = new ChemicalCompound("MgCl2", Map.of(
            asCompound(ElementType.Mg), 1D,
            asCompound(ElementType.Cl), 2D
    ));
    public static final @NotNull ChemicalCompound FeCl2 = new ChemicalCompound("FeCl2", Map.of(
            asCompound(ElementType.Fe), 1D,
            asCompound(ElementType.Cl), 2D
    ));
    public static final @NotNull ChemicalCompound NaCl = new ChemicalCompound("NaCl", Map.of(
            asCompound(ElementType.Na), 1D,
            asCompound(ElementType.Cl), 1D
    ));
    public static final @NotNull ChemicalCompound AgCl = new ChemicalCompound("AgCl", Map.of(
            asCompound(ElementType.Ag), 1D,
            asCompound(ElementType.Cl), 1D
    ));
    public static final @NotNull ChemicalCompound ZnCl2 = new ChemicalCompound("ZnCl2", Map.of(
            asCompound(ElementType.Zn), 1D,
            asCompound(ElementType.Cl), 2D
    ));
    public static final @NotNull ChemicalCompound AlCl3 = new ChemicalCompound("AlCl3", Map.of(
            asCompound(ElementType.Al), 1D,
            asCompound(ElementType.Cl), 3D
    ));
    public static final @NotNull ChemicalCompound CuCl2 = new ChemicalCompound("CuCl2", Map.of(
            asCompound(ElementType.Cu), 1D,
            asCompound(ElementType.Cl), 2D
    ));
    public static final @NotNull ChemicalCompound FeCl3 = new ChemicalCompound("FeCl3", Map.of(
            asCompound(ElementType.Fe), 1D,
            asCompound(ElementType.Cl), 3D
    ));
    public static final @NotNull ChemicalCompound BaCl2 = new ChemicalCompound("BaCl2", Map.of(
            asCompound(ElementType.Ba), 1D,
            asCompound(ElementType.Cl), 2D
    ));
    public static final @NotNull ChemicalCompound NH4Cl = new ChemicalCompound("NH4Cl", Map.of(
            asCompound(NH4), 1D,
            asCompound(ElementType.Cl), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="OHs">
    public static final @NotNull ChemicalCompound CaOH_2 = new ChemicalCompound("Ca(OH)_2", Map.of(
            asCompound(ElementType.Ca), 1D,
            asCompound(OH), 2D
    ));
    public static final @NotNull ChemicalCompound MgOH_2 = new ChemicalCompound("Mg(OH)_2", Map.of(
            asCompound(ElementType.Mg), 1D,
            asCompound(OH), 2D
    ));
    public static final @NotNull ChemicalCompound FeOH_2 = new ChemicalCompound("Fe(OH)_2", Map.of(
            asCompound(ElementType.Fe), 1D,
            asCompound(OH), 2D
    ));
    public static final @NotNull ChemicalCompound NaOH = new ChemicalCompound("NaOH", Map.of(
            asCompound(ElementType.Na), 1D,
            asCompound(OH), 1D
    ));
    public static final @NotNull ChemicalCompound BaOH_2 = new ChemicalCompound("Ba(OH)_2", Map.of(
            asCompound(ElementType.Ba), 1D,
            asCompound(OH), 2D
    ));
    public static final @NotNull ChemicalCompound AlOH_3 = new ChemicalCompound("Al(OH)_3", Map.of(
            asCompound(ElementType.Al), 1D,
            asCompound(OH), 3D
    ));
    public static final @NotNull ChemicalCompound CuOH_2 = new ChemicalCompound("Cu(OH)_2", Map.of(
            asCompound(ElementType.Cu), 1D,
            asCompound(OH), 2D
    ));
    public static final @NotNull ChemicalCompound FeOH_3 = new ChemicalCompound("Fe(OH)_3", Map.of(
            asCompound(ElementType.Fe), 1D,
            asCompound(OH), 3D
    ));
    public static final @NotNull ChemicalCompound MnOH_2 = new ChemicalCompound("Mn(OH)_2", Map.of(
            asCompound(ElementType.Mn), 1D,
            asCompound(OH), 2D
    ));
    //</editor-fold>

    //<editor-fold desc="CO3s">
    public static final @NotNull ChemicalCompound CaCO3 = new ChemicalCompound("CaCO3", Map.of(
            asCompound(ElementType.Ca), 1D,
            asCompound(CO3), 1D
    ));
    public static final @NotNull ChemicalCompound BaCO3 = new ChemicalCompound("BaCO3", Map.of(
            asCompound(ElementType.Ba), 1D,
            asCompound(CO3), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="ClO3s"
    public static final @NotNull ChemicalCompound KClO3 = new ChemicalCompound("KClO3", Map.of(
            asCompound(ElementType.K), 1D,
            asCompound(ClO3), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="SO3s"
    public static final @NotNull ChemicalCompound Na2SO3 = new ChemicalCompound("Na2SO3", Map.of(
            asCompound(ElementType.Na), 2D,
            asCompound(SO3), 1D
    ));
    public static final @NotNull ChemicalCompound CaSO3 = new ChemicalCompound("CaSO3", Map.of(
            asCompound(ElementType.Ca), 1D,
            asCompound(SO3), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Gases">
    public static final @NotNull ChemicalCompound O2 = new ChemicalCompound("O2", Map.of(
            asCompound(ElementType.O), 2D
    ));
    public static final @NotNull ChemicalCompound H2 = new ChemicalCompound("H2", Map.of(
            asCompound(ElementType.H), 2D
    ));
    public static final @NotNull ChemicalCompound N2 = new ChemicalCompound("N2", Map.of(
            asCompound(ElementType.N), 2D
    ));
    public static final @NotNull ChemicalCompound Cl2 = new ChemicalCompound("Cl2", Map.of(
            asCompound(ElementType.Cl), 2D
    ));
    public static final @NotNull ChemicalCompound NO2 = new ChemicalCompound("NO2", Map.of(
            asCompound(ElementType.N), 1D,
            asCompound(ElementType.O), 2D
    ));
    public static final @NotNull ChemicalCompound NH3 = new ChemicalCompound("NH3", Map.of(
            asCompound(ElementType.N), 1D,
            asCompound(ElementType.H), 3D
    ));
    public static final @NotNull ChemicalCompound SO2 = new ChemicalCompound("SO2", Map.of(
            asCompound(ElementType.S), 1D,
            asCompound(ElementType.O), 2D
    ));
    //</editor-fold>

    //<editor-fold desc="Organic compounds"
    public static final @NotNull ChemicalCompound CH4 = new ChemicalCompound("CH4", Map.of(
            asCompound(ElementType.C), 1D,
            asCompound(ElementType.H), 4D
    ));
    public static final @NotNull ChemicalCompound C2H5OH = new ChemicalCompound("C2H5OH", Map.of(
            asCompound(ElementType.C), 2D,
            asCompound(ElementType.H), 6D,
            asCompound(ElementType.O), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Other compounds">
    public static final @NotNull ChemicalCompound KMnO4 = new ChemicalCompound("KMnO4", Map.of(
            asCompound(ElementType.K), 1D,
            asCompound(MnO4), 1D
    ));
    public static final @NotNull ChemicalCompound K2MnO4 = new ChemicalCompound("K2MnO4", Map.of(
            asCompound(ElementType.K), 2D,
            asCompound(MnO4), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Imagines">
    public static final @NotNull ChemicalCompound Au2Fe = new ChemicalCompound("Au2Fe", Map.of(
            asCompound(ElementType.Au), 2D,
            asCompound(ElementType.Fe), 1D
    ));
    public static final @NotNull ChemicalCompound Ag2Cu = new ChemicalCompound("Ag2Cu", Map.of(
            asCompound(ElementType.Ag), 2D,
            asCompound(ElementType.Cu), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Unsorted"

    //</editor-fold>

    @Contract("null -> null; !null -> new")
    public static Compound asCompound(@Nullable ElementType elementType) {
        if (elementType == null) {
            return null;
        }

        return new Element(elementType);
    }

    @Contract("null -> null; !null -> new")
    public static Compound asCompound(@Nullable ChemicalCompound compound) {
        if (compound == null) {
            return null;
        }

        return new Chemical(compound);
    }

    /**
     * Initializes the chemical compounds above.
     */
    public static void load() {
        loaded = true;
        for (Runnable runnable : onLoad) {
            try {
                runnable.run();
            } catch (Exception e) {
                Debug.error(e);
            }
        }
        Debug.log("Loaded chemical compounds");
    }
}
