package org.irmc.industrialrevival.api.elements;

import org.irmc.industrialrevival.api.elements.melt_types.OreMeltedType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ElementUtils {
    public static final int UNKNOWN = Integer.MIN_VALUE;
    public static ElementType getBySymbol(String s) {
        try {
            return ElementType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static ElementType getByRelativeAtomicMass(double mass) {
        for (ElementType e : ElementType.values()) {
            if (Math.abs(e.getRelativeAtomicMass() - mass) < 0.001) {
                return e;
            }
        }
        return null;
    }

    public static List<ElementType> getByRelativeAtomicMass(double min, double max) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.getRelativeAtomicMass() >= min && e.getRelativeAtomicMass() <= max) {
                elements.add(e);
            }
        }
        return elements;
    }

    public static ElementType getByProtonNumber(int protonNumber) {
        for (ElementType e : ElementType.values()) {
            if (e.getProtonNumber() == protonNumber) {
                return e;
            }
        }
        return null;
    }

    public static ElementType getByNeutronNumber(int neutronNumber) {
        for (ElementType e : ElementType.values()) {
            if (e.getNeutronNumber() == neutronNumber) {
                return e;
            }
        }
        return null;
    }

    public static List<ElementType> getByValence(int... valences) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (Arrays.equals(e.getValence().getValences(), valences)) {
                elements.add(e);
            }
        }
        return elements;
    }

    public static List<ElementType> getByGroup(ElementType.ElementGroup group) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.getElementGroup() == group) {
                elements.add(e);
            }
        }
        return elements;
    }

    public static List<ElementType> getByPeriod(int period) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.getPeriod() == period) {
                elements.add(e);
            }
        }
        return elements;
    }

    public static List<ElementType> getByIsMetal(boolean isMetal) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.isMetal() == isMetal) {
                elements.add(e);
            }
        }
        return elements;
    }

    public static List<ElementType> getByMeltingPoint(double meltingPoint) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (Math.abs(e.getMeltingPoint() - meltingPoint) < 0.001) {
                elements.add(e);
            }
        }
        return elements;
    }

    public static List<ElementType> getByMeltingPoint(double min, double max) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.getMeltingPoint() >= min && e.getMeltingPoint() <= max) {
                elements.add(e);
            }
        }
        return elements;
    }

    public static List<ElementType> getByBoilingPoint(double boilingPoint) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (Math.abs(e.getBoilingPoint() - boilingPoint) < 0.001) {
                elements.add(e);
            }
        }
        return elements;
    }

    public static List<ElementType> getByBoilingPoint(double min, double max) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.getBoilingPoint() >= min && e.getBoilingPoint() <= max) {
                elements.add(e);
            }
        }
        return elements;
    }

    public static List<ElementType> getByDensity(double density) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (Math.abs(e.getDensity() - density) < 0.001) {
                elements.add(e);
            }
        }
        return elements;
    }

    public static List<ElementType> getByDensity(double min, double max) {
        List<ElementType> elements = new ArrayList<>();
        for (ElementType e : ElementType.values()) {
            if (e.getDensity() >= min && e.getDensity() <= max) {
                elements.add(e);
            }
        }
        return elements;
    }

    public static List<ElementType> getAllElements() {
        return Arrays.asList(ElementType.values());
    }
    public static MeltedType toMeltedType(ElementType e) {
        if (e.isMetal()) {
            return OreMeltedType.of(e);
        }

        return null;
    }
}
