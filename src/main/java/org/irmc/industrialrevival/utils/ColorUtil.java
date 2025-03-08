package org.irmc.industrialrevival.utils;

import org.bukkit.ChatColor;

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
     * Converts a HSV color code to an RGB integer value.
     * @param h The hue value (0-1)
     * @param s The saturation value (0-1)
     * @param v The value (0-1)
     * @return
     */
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
}