package org.irmc.industrialrevival.utils;

/**
 * This utility class provides methods for performing various number-related operations,
 * including rounding, and converting between different units of time (e.g., nanoseconds to milliseconds, seconds to milliseconds, etc.).
 *
 * @author Unknown
 */
public class NumberUtils {

    /**
     * Rounds a double value to the specified number of decimal places.
     *
     * @param value The value to round.
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