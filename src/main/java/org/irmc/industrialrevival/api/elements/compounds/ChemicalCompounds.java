package org.irmc.industrialrevival.api.elements.compounds;

import net.kyori.adventure.text.Component;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ChemicalCompounds {
    //<editor-fold desc="Compounds ions">
    public static @NotNull ChemicalCompound SO4 = new IonCompound(Component.text("SO4"), Map.of(
            new Element(ElementType.SULPHUR), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static @NotNull ChemicalCompound NO3 = new IonCompound(Component.text("NO3"), Map.of(
            new Element(ElementType.NITROGEN), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static @NotNull ChemicalCompound SO3 = new IonCompound(Component.text("SO3"), Map.of(
            new Element(ElementType.SULPHUR), 1D,
            new Element(ElementType.OXYGEN), 3D
    ));
    public static @NotNull ChemicalCompound OH = new IonCompound(Component.text("OH"), Map.of(
            new Element(ElementType.OXYGEN), 1D,
            new Element(ElementType.HYDROGEN), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="Oxides">
    public static @NotNull ChemicalCompound PO4 = new OxideCompound(Component.text("PO4"), Map.of(
            new Element(ElementType.PHOSPHORUS), 1D,
            new Element(ElementType.OXYGEN), 4D
    ));
    public static @NotNull ChemicalCompound P2O5 = new OxideCompound(Component.text("P2O5"), Map.of(
            new Element(ElementType.PHOSPHORUS), 2D,
            new Element(ElementType.OXYGEN), 5D
    ));
    //</editor-fold>

    //<editor-fold desc="SO4s">
    public static @NotNull ChemicalCompound MgSO4 = new ChemicalCompound(Component.text("MgSO4"), Map.of(
            new Element(ElementType.MAGNESIUM), 1D,
            new Chemical(SO4), 1D
    ));
    public static @NotNull ChemicalCompound FeSO4 = new ChemicalCompound(Component.text("FeSO4"), Map.of(
            new Element(ElementType.IRON), 1D,
            new Chemical(SO4), 1D
    ));
    public static @NotNull ChemicalCompound Na2SO4 = new ChemicalCompound(Component.text("Na2SO4"), Map.of(
            new Element(ElementType.SODIUM), 2D,
            new Chemical(SO4), 1D
    ));
    public static @NotNull ChemicalCompound CaSO4 = new ChemicalCompound(Component.text("CaSO4"), Map.of(
            new Element(ElementType.CALCIUM), 1D,
            new Chemical(SO4), 1D
    ));
    //</editor-fold>

    //<editor-fold desc="NO3s">
    public static @NotNull ChemicalCompound KNO3 = new ChemicalCompound(Component.text("KNO3"), Map.of(
            new Element(ElementType.POTASSIUM), 1D,
            new Chemical(NO3), 1D
    ));
    public static @NotNull ChemicalCompound MgNO3_2 = new ChemicalCompound(Component.text("Mg(NO3)2"), Map.of(
            new Element(ElementType.MAGNESIUM), 1D,
            new Chemical(NO3), 2D
    ));
    public static @NotNull ChemicalCompound FeNO3_2 = new ChemicalCompound(Component.text("Fe(NO3)2"), Map.of(
            new Element(ElementType.IRON), 1D,
            new Chemical(NO3), 2D
    ));
    public static @NotNull ChemicalCompound CaNO3_2 = new ChemicalCompound(Component.text("Ca(NO3)2"), Map.of(
            new Element(ElementType.CALCIUM), 1D,
            new Chemical(NO3), 2D
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
    public static @NotNull ChemicalCompound CaOH2 = new ChemicalCompound(Component.text("Ca(OH)2"), Map.of(
            new Element(ElementType.CALCIUM), 1D,
            new Chemical(OH), 2D
    ));
    public static @NotNull ChemicalCompound MgOH2 = new ChemicalCompound(Component.text("Mg(OH)2"), Map.of(
            new Element(ElementType.MAGNESIUM), 1D,
            new Chemical(OH), 2D
    ));
    //</editor-fold>

    //<editor-fold desc="Other compounds">
    public static @NotNull ChemicalCompound NH4 = new ChemicalCompound(Component.text("NH4"), Map.of(
            new Element(ElementType.NITROGEN), 1D,
            new Element(ElementType.HYDROGEN), 4D
    ));
    //</editor-fold>
}
