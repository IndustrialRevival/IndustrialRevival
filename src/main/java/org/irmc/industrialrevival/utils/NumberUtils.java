package org.irmc.industrialrevival.utils;

public class NumberUtils {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static double nsToMs(long ns) {
        return (double) ns / 1000000;
    }

    public static double sToMs(double s) {
        return s * 1000;
    }

    public static double msToS(double ms) {
        return ms / 1000;
    }
}
