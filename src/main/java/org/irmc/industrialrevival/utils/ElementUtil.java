package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import org.irmc.industrialrevival.api.elements.ElementGroup;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.Valence;
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
public final class ElementUtil {
    /* A common value for unknown values */
    public static final int UNKNOWN = Integer.MIN_VALUE;

    /**
     * Returns the {@link ElementType} with the given name.
     *
     * @param name the name of the element type to get
     * @return the element type with the given name, or null if not found
     */
    @Nullable
    public static ElementType forName(@NotNull String name) {
        return getBySymbol(name);
    }

    /**
     * Returns the {@link ElementType} with the given symbol.
     *
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
     *
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
     *
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
     *
     * @param protonNumber the proton number of the element type to get
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
     *
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
     *
     * @param valences the valences of the element types to get
     * @return a list of all {@link ElementType}s with the given valence
     * @see Valence
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
     * @param min the minimum melting point
     * @param max the maximum melting point
     * @return a list of all {@link ElementType}s with the given melting point
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
     *
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
     *
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
     *
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
     *
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
     *
     * @return a list of all {@link ElementType}s
     */
    public static @NotNull List<ElementType> getAllElements() {
        return Arrays.asList(ElementType.values());
    }

    /**
     * Returns the melting type for the given element type.
     *
     * @param e the element type to get the melting type for
     * @return the melting type for the given element type, or null if not found
     */
    public static @Nullable MeltedType toMeltedType(@NotNull ElementType e) {
        if (e.isMetal()) {
            return OreMeltedType.of(e);
        }

        return null;
    }

}
