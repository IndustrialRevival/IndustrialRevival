package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import org.irmc.industrialrevival.api.elements.ElementGroup;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.melt.MeltedType;
import org.irmc.industrialrevival.api.elements.melt.OreMeltedType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides utility methods for working with {@link ElementType}s.
 *
 * @author balugaq
 */
@UtilityClass
public final class ElementUtils {
    /* A common value for unknown values */
    public static final int UNKNOWN = Integer.MIN_VALUE;

    /**
     * Returns the {@link ElementType} with the given symbol.
     * @param s the symbol of the element type to get
     * @return the element type with the given symbol, or null if not found
     */
    @Nullable
    public static ElementType getBySymbol(@NotNull String s) {
        try {
            return ElementType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Returns the {@link ElementType} with the given mass.
     * @param mass the atomic mass of the element type to get
     * @return the element type with the given mass, or null if not found
     */
    @Nullable
    public static ElementType getByRelativeAtomicMass(double mass) {
        for (ElementType e : ElementType.values()) {
            if (Math.abs(e.getRelativeAtomicMass() - mass) < 0.001) {
                return e;
            }
        }
        return null;
    }

    /**
     * Returns a list of all {@link ElementType}s with the given atomic mass range.
     * @param min the minimum atomic mass
     * @param max the maximum atomic mass
     * @return a list of all element types with the given atomic mass range
     */
    @NotNull
    public static List<ElementType> getByRelativeAtomicMass(double min, double max) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.getRelativeAtomicMass() >= min && e.getRelativeAtomicMass() <= max) {
                elements.add(e);
            }
        }
        return elements;
    }

    /**
     * Returns the {@link ElementType} with the given proton number.
     * @param protonNumber  the proton number of the element type to get
     * @return the element type with the given proton number, or null if not found
     */
    public static @Nullable ElementType getByProtonNumber(int protonNumber) {
        for (ElementType e : ElementType.values()) {
            if (e.getProtonNumber() == protonNumber) {
                return e;
            }
        }
        return null;
    }

    /**
     * Returns the {@link ElementType} with the given neutron number.
     * @param neutronNumber the neutron number of the element type to get
     * @return the element type with the given neutron number, or null if not found
     */
    public static @Nullable ElementType getByNeutronNumber(int neutronNumber) {
        for (ElementType e : ElementType.values()) {
            if (e.getNeutronNumber() == neutronNumber) {
                return e;
            }
        }
        return null;
    }

    /**
     * Returns a list of all {@link ElementType}s with the given valence.
     * @param valences the valences of the element types to get
     * @return a list of all {@link ElementType}s with the given valence
     * @see ElementType.Valence
     */
    public static @NotNull List<ElementType> getByValence(int... valences) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (Arrays.equals(e.getValence().valences(), valences)) {
                elements.add(e);
            }
        }
        return elements;
    }

    /**
     * Returns a list of all {@link ElementType}s with the given group.
     * @param group the group of the element types to get
     * @return a list of all {@link ElementType}s with the given group
     */
    public static @NotNull List<ElementType> getByGroup(@NotNull ElementGroup group) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.getElementGroup() == group) {
                elements.add(e);
            }
        }
        return elements;
    }

    /**
     * Returns a list of all {@link ElementType}s with the given period.
     * @param period the period of the element types to get
     * @return a list of all {@link ElementType}s with the given period
     */
    public static @NotNull List<ElementType> getByPeriod(int period) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.getPeriod() == period) {
                elements.add(e);
            }
        }
        return elements;
    }

    /**
     * Returns a list of all metallic {@link ElementType}s.
     * @return a list of all metallic {@link ElementType}s
     */
    @NotNull
    public static List<ElementType> getAllMetals() {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.isMetal()) {
                elements.add(e);
            }
        }
        return elements;
    }

    /**
     * Returns a list of all non-metallic {@link ElementType}s.
     * @return a list of all non-metallic {@link ElementType}s
     */
    @NotNull
    public static List<ElementType> getAllNonMetals() {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (!e.isMetal()) {
                elements.add(e);
            }
        }
        return elements;
    }

    /**
     * Returns a list of all gaseous {@link ElementType}s.
     * @return a list of all gaseous {@link ElementType}s
     */
    public static @NotNull List<ElementType> getAllGas() {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.isGas()) {
                elements.add(e);
            }
        }
        return elements;
    }

    /**
     * Returns a list of all non-gaseous {@link ElementType}s.
     * @return a list of all non-gaseous {@link ElementType}s
     */
    public static @NotNull List<ElementType> getAllNonGas() {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (!e.isGas()) {
                elements.add(e);
            }
        }
        return elements;
    }

    /**
     * Returns a list of all {@link ElementType}s with the given melting point.
     * @param meltingPoint the melting point of the element types to get
     * @return a list of all {@link ElementType}s with the given melting point
     */
    public static @NotNull List<ElementType> getByMeltingPoint(double meltingPoint) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (Math.abs(e.getMeltingPoint() - meltingPoint) < 0.001) {
                elements.add(e);
            }
        }
        return elements;
    }

    /**
     * Returns a list of all {@link ElementType}s with the given melting point.
     * @param min the minimum melting point
     * @param max the maximum melting point
     * @return
     */
    public static @NotNull List<ElementType> getByMeltingPoint(double min, double max) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.getMeltingPoint() >= min && e.getMeltingPoint() <= max) {
                elements.add(e);
            }
        }
        return elements;
    }

    /**
     * Returns a list of all {@link ElementType}s with the given boiling point.
     * @param boilingPoint the boiling point of the element types to get
     * @return a list of all {@link ElementType}s with the given boiling point
     */
    public static @NotNull List<ElementType> getByBoilingPoint(double boilingPoint) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (Math.abs(e.getBoilingPoint() - boilingPoint) < 0.001) {
                elements.add(e);
            }
        }
        return elements;
    }

    /**
     * Returns a list of all {@link ElementType}s with the given boiling point.
     * @param min the minimum boiling point
     * @param max the maximum boiling point
     * @return a list of all {@link ElementType}s with the given boiling point
     */
    public static @NotNull List<ElementType> getByBoilingPoint(double min, double max) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.getBoilingPoint() >= min && e.getBoilingPoint() <= max) {
                elements.add(e);
            }
        }
        return elements;
    }

    /**
     * Returns a list of all {@link ElementType}s with the given density.
     * @param density the density of the element types to get
     * @return a list of all {@link ElementType}s with the given density
     */
    public static @NotNull List<ElementType> getByDensity(double density) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (Math.abs(e.getDensity() - density) < 0.001) {
                elements.add(e);
            }
        }
        return elements;
    }

    /**
     * Returns a list of all {@link ElementType}s with the given density.
     * @param min the minimum density
     * @param max the maximum density
     * @return a list of all {@link ElementType}s with the given density
     */
    public static @NotNull List<ElementType> getByDensity(double min, double max) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.getDensity() >= min && e.getDensity() <= max) {
                elements.add(e);
            }
        }
        return elements;
    }

    /**
     * Returns a list of all {@link ElementType}s.
     * @return a list of all {@link ElementType}s
     */
    public static @NotNull List<ElementType> getAllElements() {
        return Arrays.asList(ElementType.values());
    }

    /**
     * Returns the melting type for the given element type.
     * @param e the element type to get the melting type for
     * @return the melting type for the given element type, or null if not found
     */
    public static @Nullable MeltedType toMeltedType(@NotNull ElementType e) {
        if (e.isMetal()) {
            return OreMeltedType.of(e);
        }

        return null;
    }

    /**
     * A useful method to get a color for an element based on its properties.
     * Just prevents code duplication.
     *
     * @param elementType the element type to get the color for
     * @return the color as an integer in the format 0xRRGGBB
     * @author balugaq
     */
    public static int getAtomicColor(@NotNull ElementType elementType) {
        double atomicMass = elementType.getRelativeAtomicMass();
        int neutrons = elementType.getNeutronNumber();
        boolean isMetal = elementType.isMetal();
        int group = elementType.getElementGroup().ordinal() + 1;
        int period = elementType.getPeriod();
        double meltingPoint = elementType.getMeltingPoint();
        double boilingPoint = elementType.getBoilingPoint();
        double density = elementType.getDensity();

        float[] baseHsv = getBaseHsv(elementType.ordinal() + 1, group, isMetal);

        float sBoost = (float) Math.min(atomicMass / 250.0, 1.0);
        float hShift = (neutrons % 10) * 0.002f;
        float vDamp = (float) (1.0 - Math.min(density / 25.0, 1.0));
        float hNoise = (float) ((boilingPoint - meltingPoint) / 5000.0);
        if (hNoise < 0) hNoise = 0;

        float h = (baseHsv[0] + hShift + hNoise) % 1.0f;
        float s = (float) Math.min(baseHsv[1] * (1.0 + sBoost), 1.0);
        float v = baseHsv[2] * vDamp;

        return ColorUtil.getRGBFromHSV(h, s, v);
    }

    /**
     * Gets the base HSV values for an element based on its properties.
     * @param n       the atomic number
     * @param group   the group
     * @param isMetal whether the element is metallic or not
     * @return the base HSV values as an array of floats
     */
    public static float[] getBaseHsv(int n, int group, boolean isMetal) {
        if (n == 1) {
            return new float[]{0.33f, 0.8f, 0.9f};
        } else if (n == 2 || n == 10 || n == 18 || n == 36 || n == 54 || n == 86 || n == 118) {
            return new float[]{0.91f, 0.3f, 1.0f};
        } else if (group == 1 || group == 2) {
            return new float[]{isMetal ? 0.12f : 0.8f, 0.9f, 0.8f};
        } else if (group >= 3 && group <= 12) {
            return new float[]{0.6f, 0.8f, 0.7f};
        } else if (group == 13 || group == 14) {
            return new float[]{isMetal ? 0.25f : 0.5f, 0.7f, 0.7f};
        } else if (group == 15 || group == 16) {
            return new float[]{isMetal ? 0.0f : 0.7f, 0.7f, 0.8f};
        } else {
            return new float[]{isMetal ? 0.9f : 0.4f, 0.6f, 0.6f};
        }
    }
}
