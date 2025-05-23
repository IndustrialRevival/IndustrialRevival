package org.irmc.industrialrevival.api.elements;

/**
 * Enum for element groups
 *
 * @author balugaq
 * @see ElementType
 */
public enum ElementGroup {
    IA,
    IIA,
    IIIB,
    IVB,
    VB,
    VIB,
    VIIB,
    VIIIB,
    IB,
    IIB,
    IIIA,
    IVA,
    VA,
    VIA,
    VIIA,
    VIIIA;

    // Alias
    public static final ElementGroup ALKALI_METALS = IA;
    public static final ElementGroup ALKALINE_EARTH_METALS = IIA;
    public static final ElementGroup CARBON_GROUP = IVA;
    public static final ElementGroup NITROGEN_GROUP = VA;
    public static final ElementGroup CHALCOGENS = VIA;
    public static final ElementGroup HALOGENS = VIIA;
    public static final ElementGroup NOBLE_GASES = VIIIB;
    public static final ElementGroup O = VIIIB;
}
