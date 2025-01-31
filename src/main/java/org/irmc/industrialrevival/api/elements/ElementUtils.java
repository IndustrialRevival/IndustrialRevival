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
            if (Arrays.equals(e.getValence().valences(), valences)) {
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

    public static int getRGBFromHSV(float h, float s, float v) {
        float c = v * s;
        float x = c * (1 - Math.abs((h * 6) % 2 - 1));
        float m = v - c;

        float r = 0, g = 0, b = 0;
        if (0 <= h && h < 1/6f) {
            r = c; g = x; b = 0;
        } else if (1/6f <= h && h < 1/3f) {
            r = x; g = c; b = 0;
        } else if (1/3f <= h && h < 1/2f) {
            r = 0; g = c; b = x;
        } else if (1/2f <= h && h < 2/3f) {
            r = 0; g = x; b = c;
        } else if (2/3f <= h && h < 5/6f) {
            r = x; g = 0; b = c;
        } else if (5/6f <= h && h < 1f) {
            r = c; g = 0; b = x;
        }

        int red = (int)((r + m) * 255);
        int green = (int)((g + m) * 255);
        int blue = (int)((b + m) * 255);

        return (red << 16) | (green << 8) | blue;
    }

    public static int getAtomicColor(ElementType elementType) {
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

        return getRGBFromHSV(h, s, v);
    }

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
