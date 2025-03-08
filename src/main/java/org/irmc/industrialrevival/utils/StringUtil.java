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
public class StringUtil {
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
}