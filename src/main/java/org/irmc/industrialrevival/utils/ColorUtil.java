package org.irmc.industrialrevival.utils;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalFormula;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * A utility class providing string manipulation methods specifically for Minecraft color code handling.
 * <p>
 * This class contains static methods to process strings with Minecraft-style color codes. It is designed
 * to be used as a helper for formatting in-game text displays.
 *
 * @author balugaq
 */
@SuppressWarnings("deprecation")
public class ColorUtil {
    public static Map<ChemicalFormula, Integer> formulaColors = new HashMap<>();
    public static Map<ChemicalCompound, Integer> moleColors = new HashMap<>();
    public static Map<ElementType, Integer> atomicColors = new EnumMap<>(ElementType.class);

    /**
     * Converts alternate color codes in a string to Minecraft's internal color codes.
     * <p>
     * Replaces all instances of the '&' character followed by a color code character with
     * the corresponding {@link ChatColor} formatting sequence. This allows using human-readable
     * color codes in configuration files or user inputs that get translated to actual game colors.
     *
     * @param input The raw string containing alternate color codes (using '&' as prefix)
     * @return The formatted string with valid Minecraft color codes
     * @see ChatColor#translateAlternateColorCodes(char, String)
     */
    public static String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    /**
     * Converts an HSV color code to an RGB integer value.
     *
     * @param h The hue value (0-1)
     * @param s The saturation value (0-1)
     * @param v The value (0-1)
     * @return The RGB value as an integer
     */
    public static int getRGBFromHSV(float h, float s, float v) {
        float c = v * s;
        float x = c * (1 - Math.abs((h * 6) % 2 - 1));
        float m = v - c;

        float r = 0, g = 0, b = 0;
        if (0 <= h && h < 1 / 6f) {
            r = c;
            g = x;
            b = 0;
        } else if (1 / 6f <= h && h < 1 / 3f) {
            r = x;
            g = c;
            b = 0;
        } else if (1 / 3f <= h && h < 1 / 2f) {
            r = 0;
            g = c;
            b = x;
        } else if (1 / 2f <= h && h < 2 / 3f) {
            r = 0;
            g = x;
            b = c;
        } else if (2 / 3f <= h && h < 5 / 6f) {
            r = x;
            g = 0;
            b = c;
        } else if (5 / 6f <= h && h < 1f) {
            r = c;
            g = 0;
            b = x;
        }

        int red = (int) ((r + m) * 255);
        int green = (int) ((g + m) * 255);
        int blue = (int) ((b + m) * 255);

        return (red << 16) | (green << 8) | blue;
    }

    public static int generateFormulaColor(@NotNull ChemicalFormula formula) {
        Integer cached = formulaColors.get(formula);
        if (cached != null) {
            return cached;
        }

        double totalWeight = 0;
        int r = 0, g = 0, b = 0;

        for (Map.Entry<ChemicalCompound, Integer> entry : formula.getInput().entrySet()) {
            int color = generateMoleColor(entry.getKey());
            int weight = entry.getValue();
            r += ((color >> 16) & 0xFF) * weight;
            g += ((color >> 8) & 0xFF) * weight;
            b += (color & 0xFF) * weight;
            totalWeight += weight;
        }

        for (Map.Entry<ChemicalCompound, Integer> entry : formula.getOutput().entrySet()) {
            int color = generateMoleColor(entry.getKey());
            int weight = entry.getValue();
            r += ((color >> 16) & 0xFF) * weight;
            g += ((color >> 8) & 0xFF) * weight;
            b += (color & 0xFF) * weight;
            totalWeight += weight;
        }

        if (totalWeight == 0) {
            return getRGBFromHSV(0.5f, 0.5f, 0.5f);
        }

        int avgR = (int) (r / totalWeight);
        int avgG = (int) (g / totalWeight);
        int avgB = (int) (b / totalWeight);

        int value = (avgR << 16) | (avgG << 8) | avgB;

        formulaColors.put(formula, value);
        return value;
    }

    public static int generateMoleColor(@NotNull ChemicalCompound compound) {
        Integer cached = moleColors.get(compound);
        if (cached != null) {
            return cached;
        }

        double totalAtomic = 0;
        for (var entry : compound.toAtomic().entrySet()) {
            totalAtomic += entry.getValue();
        }

        Color baseColor = Color.fromRGB(0xf7895a);
        for (var entry : compound.toAtomic().entrySet()) {
            Color atomic = Color.fromRGB(generateAtomicColor(entry.getKey()));
            double proportion = entry.getValue() / totalAtomic;
            atomic = Color.fromRGB(
                    (int) (atomic.getRed() * proportion),
                    (int) (atomic.getGreen() * proportion),
                    (int) (atomic.getBlue() * proportion)
            );
            baseColor = baseColor.mixColors(atomic);
        }

        int value = baseColor.asRGB();
        moleColors.put(compound, value);
        return value;
    }

    /**
     * A useful method to get a color for an element based on its properties.
     * Just prevents code duplication.
     *
     * @param elementType the element type to get the color for
     * @return the color as an integer in the format 0xRRGGBB
     * @author balugaq
     */
    public static int generateAtomicColor(@NotNull ElementType elementType) {
        Integer cached = atomicColors.get(elementType);
        if (cached != null) {
            return cached;
        }

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

        int value = getRGBFromHSV(h, s, v);
        atomicColors.put(elementType, value);
        return value;
    }

    /**
     * Gets the base HSV values for an element based on its properties.
     *
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