package org.irmc.industrialrevival.api.helpers;

public class DotNumberHelper {
    public static double round(double value) {
        return Math.round(value);
    }

    public static double round(double value, int places) {
        double factor = Math.pow(10, places);
        return Math.round(value * factor) / factor;
    }

    public static float round(float value) {
        return Math.round(value);
    }

    public static float round(float value, int places) {
        float factor = (float) Math.pow(10, places);
        return Math.round(value * factor) / factor;
    }
}
