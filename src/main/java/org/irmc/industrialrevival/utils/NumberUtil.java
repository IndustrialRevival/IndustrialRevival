package org.irmc.industrialrevival.utils;

import java.util.Map;

/**
 * This utility class provides methods for performing various number-related operations,
 * including rounding, and converting between different units of time (e.g., nanoseconds to milliseconds, seconds to milliseconds, etc.).
 *
 * @author Unknown
 */
public class NumberUtil {
    public static Map<Integer, String> subscripts = Map.of(
            0, "\u2080",
            1, "\u2081",
            2, "\u2082",
            3, "\u2083",
            4, "\u2084",
            5, "\u2085",
            6, "\u2086",
            7, "\u2087",
            8, "\u2088",
            9, "\u2089"
    );

    public static Map<Integer, String> superscripts = Map.of(
            0, "\u2070",
            1, "\u00B9",
            2, "\u00B2",
            3, "\u00B3",
            4, "\u2074",
            5, "\u2075",
            6, "\u2076",
            7, "\u2077",
            8, "\u2078",
            9, "\u2079"
    );

    /**
     * Converts a string of digits into a subscript representation.
     *
     * @param s The string of digits to convert.
     * @return The subscript representation of the string.
     */
    public static String toSubscript(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(subscripts.get(c - '0'));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Converts a string of digits into a superscript representation.
     *
     * @param s The string of digits to convert.
     * @return The superscript representation of the string.
     */
    public static String toSuperscript(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(superscripts.get(c - '0'));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Rounds a double value to the specified number of decimal places.
     *
     * @param value  The value to round.
     * @param places The number of decimal places to round to.
     * @return The rounded value.
     * @throws IllegalArgumentException If the number of decimal places is negative.
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * Converts a time value from nanoseconds to milliseconds.
     *
     * @param ns The time value in nanoseconds.
     * @return The time value in milliseconds.
     */
    public static double ns2Ms(long ns) {
        return (double) ns / 1000000;
    }

    /**
     * Converts a time value from seconds to milliseconds.
     *
     * @param s The time value in seconds.
     * @return The time value in milliseconds.
     */
    public static double s2Ms(double s) {
        return s * 1000;
    }

    /**
     * Converts a time value from milliseconds to seconds.
     *
     * @param ms The time value in milliseconds.
     * @return The time value in seconds.
     */
    public static double ms2S(double ms) {
        return ms / 1000;
    }
}