package org.irmc.industrialrevival.api.elements;

import lombok.Getter;
import org.irmc.industrialrevival.utils.ElementUtils;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an element type.
 * <p>
 * An element type is defined by its symbol, proton number, neutron number, valence, group, period,
 * density, melting point, boiling point, and other properties.
 *
 * @author balugaq
 * @author lijinhong11
 * @since 1.0
 */
@SuppressWarnings("unused")
@Getter
public enum ElementType {
    HYDROGEN("H", 1.008, 0, Valence.of(1, -1), false, ElementGroup.IA, 1, -259.2, -252.9, 0.08),
    HELIUM("He", 4.003, 0, Valence.of(0), false, ElementGroup.VIII, 1, -272.2, -268.9, 0.14),
    LITHIUM("Li", 6.941, 3, Valence.of(1), true, ElementGroup.IA, 2, 180.54, 1342, 0.534),
    BERYLLIUM("Be", 9.012, 4, Valence.of(2), true, ElementGroup.IIA, 2, 1278, 2970, 1.85),
    BORON("B", 10.81, 5, Valence.of(3), false, ElementGroup.IIIA, 2, 2076, 4000, 2.34),
    CARBON("C", 12.01, 4, Valence.of(4, 2, -4), false, ElementGroup.IVA, 2, 3550, 4300, 2.26),
    NITROGEN("N", 14.01, 5, Valence.of(3, 5, 1, -3, 2), false, ElementGroup.VA, 2, -210.01, -195.8, 1.25),
    OXYGEN("O", 16.00, 6, Valence.of(2, -2), false, ElementGroup.VIA, 2, -218.4, -182.96, 1.43),
    FLUORINE("F", 19.00, 7, Valence.of(1, -1), false, ElementGroup.VIIA, 2, -219.62, -188.14, 1.71),
    NEON("Ne", 20.18, 8, Valence.of(0), false, ElementGroup.VIII, 2, -248.59, -246.08, 0.8999),
    SODIUM("Na", 22.99, 10, Valence.of(1), true, ElementGroup.IA, 3, 97.72, 1156, 0.97),
    MAGNESIUM("Mg", 24.31, 12, Valence.of(2), true, ElementGroup.IIA, 3, 650, 1090, 1.74),
    ALUMINIUM("Al", 26.98, 13, Valence.of(3), true, ElementGroup.IIIA, 3, 660.3, 2467, 2.7),
    SILICON("Si", 28.09, 14, Valence.of(4, -4), false, ElementGroup.IVA, 3, 1414, 3265, 2.33),
    PHOSPHORUS("P", 30.97, 15, Valence.of(3, 5, -3), false, ElementGroup.VA, 3, 44.15, 280, 1.82),
    SULPHUR("S", 32.06, 16, Valence.of(6, 4, 2), false, ElementGroup.VIA, 3, 115.21, 444.6, 2.07),
    CHLORINE("Cl", 35.45, 17, Valence.of(7, 1, 3, 5), false, ElementGroup.VIIA, 3, -101.5, -34.04, 3.21),
    ARGON("Ar", 39.95, 18, Valence.of(0), false, ElementGroup.VIII, 3, -189.2, -185.7, 1.63),
    POTASSIUM("K", 39.10, 19, Valence.of(1), true, ElementGroup.IA, 4, 63.65, 759, 0.86),
    CALCIUM("Ca", 40.08, 20, Valence.of(2), true, ElementGroup.IIA, 4, 842, 1484, 1.55),
    SCANDIUM("Sc", 44.96, 21, Valence.of(3), true, ElementGroup.IIIB, 3, 1539, 2832, 2.99),
    TITANIUM("Ti", 47.87, 22, Valence.of(4, 3), true, ElementGroup.IIIB, 4, 1668, 3287, 4.51),
    VANADIUM("V", 50.94, 23, Valence.of(5, 3), true, ElementGroup.IIIB, 4, 1910, 3407, 6.11),
    CHROMIUM("Cr", 52.00, 24, Valence.of(6, 3, 4), true, ElementGroup.VIB, 4, 1857, 2671, 7.19),
    MANGANESE("Mn", 54.94, 25, Valence.of(7, 2, 3, 4, 5, 6), true, ElementGroup.VIIB, 4, 1244, 2095, 7.44),
    IRON("Fe", 55.85, 26, Valence.of(2, 3), true, ElementGroup.VIII, 4, 1538, 2862, 7.87),
    COBALT("Co", 58.93, 27, Valence.of(2, 3), true, ElementGroup.VIII, 4, 1495, 2870, 8.9),
    NICKEL("Ni", 58.69, 28, Valence.of(2, 3), true, ElementGroup.VIII, 4, 1453, 2732, 8.91),
    COPPER("Cu", 63.55, 29, Valence.of(1, 2), true, ElementGroup.IB, 4, 1085, 2567, 8.96),
    ZINC("Zn", 65.39, 30, Valence.of(2), true, ElementGroup.IIA, 4, 419.53, 907, 7.14),
    GALLIUM("Ga", 69.72, 31, Valence.of(3), true, ElementGroup.IIIA, 4, 29.76, 2477, 5.91),
    GERMANIUM("Ge", 72.64, 32, Valence.of(4, 2), false, ElementGroup.IVA, 4, 937, 2830, 5.32),
    ARSENIC("As", 74.92, 33, Valence.of(3, 5, -3), false, ElementGroup.VA, 4, 817, 613, 5.73),
    SELENIUM("Se", 78.96, 34, Valence.of(6, 4, 2), false, ElementGroup.VIA, 4, 220, 685, 4.82),
    BROMINE("Br", 79.90, 35, Valence.of(7, 5, 1), false, ElementGroup.VIIA, 4, -7.2, 58.8, 3.12),
    KRYPTON("Kr", 83.80, 36, Valence.of(0), false, ElementGroup.VIII, 4, -157.36, -153.22, 3.75),
    RUBIDIUM("Rb", 85.47, 37, Valence.of(1), true, ElementGroup.IA, 5, 39.3, 688, 1.53),
    STRONTIUM("Sr", 87.62, 38, Valence.of(2), true, ElementGroup.IIA, 5, 769, 1382, 2.54),
    YTTRIUM("Y", 88.91, 39, Valence.of(3), true, ElementGroup.IIIB, 5, 1522, 3345, 4.47),
    ZIRCONIUM("Zr", 91.22, 40, Valence.of(4), true, ElementGroup.IVB, 5, 1852, 4377, 6.51),
    NIOBIUM("Nb", 92.91, 41, Valence.of(5), true, ElementGroup.VB, 5, 2468, 4741, 8.57),
    MOLYBDENUM("Mo", 95.96, 42, Valence.of(6, 4), true, ElementGroup.VIB, 5, 2623, 4612, 10.28),
    TECHNETIUM("Tc", 98, 43, Valence.of(7, 4), true, ElementGroup.VIIB, 5, 2157, 4265, 11.49),
    RUTHENIUM("Ru", 101.1, 44, Valence.of(8, 4, 1), true, ElementGroup.VIII, 5, 2263, 3900, 12.37),
    RHODIUM("Rh", 102.9, 45, Valence.of(3, 4), true, ElementGroup.VIII, 5, 1966, 3727, 12.45),
    PALLADIUM("Pd", 106.4, 46, Valence.of(2, 4), true, ElementGroup.VIII, 5, 1554, 2930, 12.02),
    SILVER("Ag", 107.9, 47, Valence.of(1), true, ElementGroup.IB, 5, 961.93, 2212, 10.49),
    CADMIUM("Cd", 112.4, 48, Valence.of(2), true, ElementGroup.IIA, 5, 321.07, 765, 8.65),
    INDIUM("In", 114.8, 49, Valence.of(3), true, ElementGroup.IIIA, 5, 156.61, 2345, 7.31),
    TIN("Sn", 118.7, 50, Valence.of(4, 2), true, ElementGroup.IVA, 5, 231.93, 2270, 7.31),
    ANTIMONY("Sb", 121.8, 51, Valence.of(3, 5, -3), false, ElementGroup.VA, 5, 630.63, 1750, 6.69),
    TELLURIUM("Te", 127.6, 52, Valence.of(6, 4, 2), false, ElementGroup.VIA, 5, 449.51, 990, 6.24),
    IODINE("I", 126.9, 53, Valence.of(7, 5, 1), false, ElementGroup.VIIA, 5, 113.5, 184.35, 4.93),
    XENON("Xe", 131.3, 54, Valence.of(8, 6, 4), false, ElementGroup.VIII, 5, -111.76, -108.08, 5.49),
    CESIUM("Cs", 133, 55, Valence.of(1), true, ElementGroup.IA, 6, 28.44, 671, 1.93),
    BARIUM("Ba", 137.3, 56, Valence.of(2), true, ElementGroup.IIA, 6, 725, 1640, 3.59),
    LANTHANUM("La", 139, 57, Valence.of(3), true, ElementGroup.IIIB, 6, 920, 3469, 6.15),
    CERIUM("Ce", 140, 58, Valence.of(3, 4), true, ElementGroup.IIIB, 6, 795, 3257, 6.77),
    PRASEODYMIUM("Pr", 141, 59, Valence.of(3), true, ElementGroup.IIIB, 6, 931, 3128, 6.76),
    NEODYMIUM("Nd", 144, 60, Valence.of(3), true, ElementGroup.IIIB, 6, 1021, 3074, 7.01),
    PROMETHIUM("Pm", 145, 61, Valence.of(3), true, ElementGroup.IIIB, 6, 1192, 2800, 7.26),
    SAMARIUM("Sm", 150.5, 62, Valence.of(3), true, ElementGroup.IIIB, 6, 1074, 1900, 7.52),
    EUROPIUM("Eu", 152, 63, Valence.of(3), true, ElementGroup.IIIB, 6, 822, 1600, 5.25),
    GADOLINIUM("Gd", 157, 64, Valence.of(3), true, ElementGroup.IIIB, 6, 1313, 3243, 7.9),
    TERBIUM("Tb", 159, 65, Valence.of(3), true, ElementGroup.IIIB, 6, 1356, 3296, 8.23),
    DYSPROSIUM("Dy", 162.5, 66, Valence.of(3), true, ElementGroup.IIIB, 6, 1412, 2562, 8.55),
    HOLMIUM("Ho", 165, 67, Valence.of(3), true, ElementGroup.IIIB, 6, 1474, 2700, 8.8),
    ERBIUM("Er", 167, 68, Valence.of(3), true, ElementGroup.IIIB, 6, 1529, 2868, 9.07),
    THULIUM("Tm", 169, 69, Valence.of(3), true, ElementGroup.IIIB, 6, 1545, 1730, 9.32),
    YTTERBIUM("Yb", 173, 70, Valence.of(3, 2), true, ElementGroup.IIIB, 6, 824, 1469, 6.98),
    LUTETIUM("Lu", 175, 71, Valence.of(3), true, ElementGroup.IIIB, 6, 1656, 3395, 9.84),
    HAFNIUM("Hf", 178.5, 72, Valence.of(4), true, ElementGroup.IVB, 6, 2233, 4603, 13.31),
    TANTALUM("Ta", 181, 73, Valence.of(5), true, ElementGroup.VB, 6, 3017, 5425, 16.69),
    TUNGSTEN("W", 184, 74, Valence.of(6, 4), true, ElementGroup.VIB, 6, 3422, 5660, 19.3),
    RHENIUM("Re", 186, 75, Valence.of(7), true, ElementGroup.VIIB, 6, 3180, 5596, 21.02),
    OSMIUM("Os", 190, 76, Valence.of(8, 6, 4), true, ElementGroup.VIII, 6, 3045, 5300, 22.59),
    IRIDIUM("Ir", 192, 77, Valence.of(9, 6, 4, 3), true, ElementGroup.VIII, 6, 2410, 4403, 22.56),
    PLATINUM("Pt", 195, 78, Valence.of(4, 2), true, ElementGroup.VIII, 6, 1764, 3825, 21.45),
    GOLD("Au", 197, 79, Valence.of(3, 1), true, ElementGroup.IB, 6, 1064, 2856, 19.32),
    MERCURY("Hg", 200.6, 80, Valence.of(2, 1), true, ElementGroup.IIB, 6, -38.83, 356.73, 13.53),
    THALLIUM("Tl", 204.5, 81, Valence.of(3), true, ElementGroup.IIIA, 6, 303.5, 1457, 11.85),
    LEAD("Pb", 207, 82, Valence.of(4, 2), true, ElementGroup.IVA, 6, 327.46, 1740, 11.34),
    BISMUTH("Bi", 209, 83, Valence.of(5, 3), true, ElementGroup.VA, 6, 271.3, 1564, 9.78),
    POLONIUM("Po", 209, 84, Valence.of(6, 2), false, ElementGroup.VIA, 6, 254, 962, 9.5),
    ASTATINE("At", 210, 85, Valence.of(5, 7), false, ElementGroup.VIIA, 6, 302, 337, 7.0),
    RADON("Rn", 222, 86, Valence.of(0), false, ElementGroup.VIII, 6, -71, -61.7, 9.73),
    FRANCIUM("Fr", 223, 87, Valence.of(1), true, ElementGroup.IA, 7, 27, 677, 1.87),
    RADIUM("Ra", 226, 88, Valence.of(2), true, ElementGroup.IIA, 7, 700, 1737, 5.5),
    ACTINIUM("Ac", 227, 89, Valence.of(3), true, ElementGroup.IIIA, 7, 1050, 3275, 10.07),
    THORIUM("Th", 232, 90, Valence.of(4), true, ElementGroup.IVB, 7, 1750, 4790, 11.72),
    PROTACTINIUM("Pa", 231, 91, Valence.of(5), true, ElementGroup.VB, 7, 1572, 4000, 15.37),
    URANIUM("U", 238, 92, Valence.of(6, 3, 4), true, ElementGroup.VIB, 7, 1132.3, 3818, 19.1),
    NEPTUNIUM("Np", 237, 93, Valence.of(7, 5), true, ElementGroup.VIIB, 7, 640, 3902, 20.45),
    PLUTONIUM("Pu", 244, 94, Valence.of(6, 4, 3), true, ElementGroup.VIII, 7, 640.0, 3505, 19.82),
    AMERICIUM("Am", 243, 95, Valence.of(3, 5, 7), true, ElementGroup.VIII, 7, 994, 1757, 13.67),
    CURIUM("Cm", 247, 96, Valence.of(3), true, ElementGroup.IIIA, 7, 1340, 3818, 13.51),
    BERKELIUM("Bk", 247, 97, Valence.of(3, 4), true, ElementGroup.IIIA, 7, 986, 3243, 14.78),
    CALIFORNIUM("Cf", 251, 98, Valence.of(3, 4), true, ElementGroup.IIIA, 7, 900, 3700, 15.1),
    EINSTEINIUM("Es", 252, 99, Valence.of(3), true, ElementGroup.IIIA, 7, 860, 1169, 8.54),
    FERMIUM("Fm", 257, 100, Valence.of(3), true, ElementGroup.IIIA, 7, 1800, 3800, 10.5),
    MENDELEVIUM("Md", 258, 101, Valence.of(3), true, ElementGroup.IIIA, 7, 1100, 2700, 10.47),
    NOBELIUM("No", 259, 102, Valence.of(3), true, ElementGroup.IIIA, 7, 1100, 3400, 9.9),
    LAWRENCIUM("Lr", 266, 103, Valence.of(3), true, ElementGroup.IIIB, 7, 1620, 3500, 14.8),
    RUTHERFORDIUM("Rf", 261, 104, Valence.of(4), true, ElementGroup.IVB, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN),
    DUBNIUM("Db", 262, 105, Valence.of(5), true, ElementGroup.VB, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN),
    SEABORGIUM("Sg", 263, 106, Valence.of(6), true, ElementGroup.VIB, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN),
    BOHRIUM("Bh", 264, 107, Valence.of(7), true, ElementGroup.VIIB, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN),
    HASSIUM("Hs", 265, 108, Valence.of(8), true, ElementGroup.VIII, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN),
    MEITNERIUM("Mt", 266, 109, Valence.of(0), true, ElementGroup.VIII, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN),
    DARMSTADTIUM("Ds", 269, 110, Valence.of(0), true, ElementGroup.VIII, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN),
    ROENTGENIUM("Rg", 272, 111, Valence.of(0), true, ElementGroup.IB, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN),
    COPERNICIUM("Cn", 277, 112, Valence.of(0), true, ElementGroup.IIB, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN),
    NIHONIUM("Nh", 286, 113, Valence.of(3, 1), true, ElementGroup.IIIA, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN),
    FLEROVIUM("Fl", 289, 114, Valence.of(3, 0), true, ElementGroup.IVA, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN),
    MOSCOVIUM("Mc", 289, 115, Valence.of(1, 3), true, ElementGroup.VA, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN),
    LIVERMORIUM("Lv", 293, 116, Valence.of(4), true, ElementGroup.VIA, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN),
    TENNESSINE("Ts", 294, 117, Valence.of(-1), false, ElementGroup.VIIA, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN),
    OGANESSON("Og", 294, 118, Valence.of(0), false, ElementGroup.O, 7, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN, ElementUtils.UNKNOWN);

    private final String symbol;
    private final double relativeAtomicMass;
    private final int protonNumber;
    private final int neutronNumber;
    private final Valence valence;
    private final boolean isMetal;
    private final ElementGroup elementGroup;
    private final int period;
    private final double meltingPoint;
    private final double boilingPoint;
    private final double density;

    ElementType(String symbol, double relativeAtomicMass, int neutronNumber, Valence valence, boolean isMetal, ElementGroup elementGroup, int period, double meltingPoint, double boilingPoint, double density) {
        this.symbol = symbol;
        this.relativeAtomicMass = relativeAtomicMass;
        this.protonNumber = this.ordinal() + 1;
        this.neutronNumber = neutronNumber;
        this.valence = valence;
        this.isMetal = isMetal;
        this.elementGroup = elementGroup;
        this.period = period;
        this.meltingPoint = meltingPoint;
        this.boilingPoint = boilingPoint;
        this.density = density;
    }

    public ElementType getNext() {
        return ElementType.values()[(this.ordinal() + 1) % ElementType.values().length];
    }

    public ElementType getPrevious() {
        return ElementType.values()[(this.ordinal() - 1 + ElementType.values().length) % ElementType.values().length];
    }

    public boolean isRadioactive() {
        if (this.ordinal() > 91) {
            return true;
        }
        return switch (this) {
            case URANIUM, THORIUM, RADIUM, POLONIUM, RADON, ACTINIUM, PROTACTINIUM, FRANCIUM, ASTATINE -> true;
            default -> false;
        };
    }

    public boolean isGas() {
        return switch (this) {
            case HYDROGEN, NITROGEN, OXYGEN, FLUORINE, CHLORINE, HELIUM, NEON, ARGON, KRYPTON, XENON, RADON -> true;
            default -> false;
        };
    }

    public int getRoundedRelativeAtomicMass() {
        return (int) Math.round(this.relativeAtomicMass);
    }

    public int getMassByRelativeAtomicMass(double amount) {
        return (int) (getRoundedRelativeAtomicMass() * amount);
    }

    public record Valence(int... valences) {
        public static @NotNull Valence of(int... valences) {
            return new Valence(valences);
        }
    }
}
