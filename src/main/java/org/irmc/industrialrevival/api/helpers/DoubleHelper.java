package org.irmc.industrialrevival.api.helpers;

public class DoubleHelper {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static double parseDouble(String value) {
        return Double.parseDouble(value);
    }

    public static double parseDouble(String value, double defaultValue) {
        try {
            return parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static double doubleValue(double value) {
        return value;
    }

    public static float floatValue(double value) {
        return (float) value;
    }

    public static long longValue(double value) {
        return Double.doubleToLongBits(value);
    }

    public static int intValue(double value) {
        return (int) value;
    }

    public static short shortValue(double value) {
        return (short) value;
    }

    public static byte byteValue(double value) {
        return (byte) value;
    }

    public static boolean booleanValue(double value) {
        return value!= 0;
    }

    public static boolean isNaN(double value) {
        return Double.isNaN(value);
    }

    public static boolean isInfinite(double value) {
        return Double.isInfinite(value);
    }

    public static boolean isFinite(double value) {
        return!isNaN(value) &&!isInfinite(value);
    }

    public static boolean isZero(double value) {
        return value == 0.0;
    }

    public static boolean isNotZero(double value) {
        return value!= 0.0;
    }

    public static boolean isPositive(double value) {
        return value > 0.0;
    }

    public static boolean isNegative(double value) {
        return value < 0.0;
    }

    public static boolean isNonNegative(double value) {
        return value >= 0.0;
    }

    public static boolean isNonPositive(double value) {
        return value <= 0.0;
    }

    public static boolean isEqual(double value1, double value2) {
        return value1 == value2;
    }

    public static boolean isNotEqual(double value1, double value2) {
        return value1!= value2;
    }

    public static boolean isGreaterThan(double value1, double value2) {
        return value1 > value2;
    }

    public static boolean isGreaterThanOrEqual(double value1, double value2) {
        return value1 >= value2;
    }

    public static boolean isLessThan(double value1, double value2) {
        return value1 < value2;
    }

    public static boolean isLessThanOrEqual(double value1, double value2) {
        return value1 <= value2;
    }

    public static boolean isBetween(double value, double min, double max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenOrEqual(double value, double min, double max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenExclusive(double value, double min, double max) {
        return isGreaterThan(value, min) && isLessThan(value, max);
    }

    public static boolean isBetweenExclusiveOrEqual(double value, double min, double max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenInclusive(double value, double min, double max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenInclusiveOrEqual(double value, double min, double max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetween(double value, double min, double max, boolean inclusive) {
        if (inclusive) {
            return isBetweenInclusive(value, min, max);
        } else {
            return isBetweenExclusive(value, min, max);
        }
    }

    public static boolean isBetweenOrEqual(double value, double min, double max, boolean inclusive) {
        if (inclusive) {
            return isBetweenInclusiveOrEqual(value, min, max);
        } else {
            return isBetweenExclusiveOrEqual(value, min, max);
        }
    }

    public static boolean isBetween(double value, double[] range) {
        return isBetween(value, range[0], range[1]);
    }

    public static boolean isBetweenOrEqual(double value, double[] range) {
        return isBetweenOrEqual(value, range[0], range[1]);
    }

    public static boolean isBetween(double value, double[] range, boolean inclusive) {
        return isBetween(value, range[0], range[1], inclusive);
    }

    public static boolean isBetweenOrEqual(double value, double[] range, boolean inclusive) {
        return isBetweenOrEqual(value, range[0], range[1], inclusive);
    }

    public static double add(double value1, double value2) {
        return value1 + value2;
    }

    public static double subtract(double value1, double value2) {
        return value1 - value2;
    }

    public static double multiply(double value1, double value2) {
        return value1 * value2;
    }

    public static double divide(double value1, double value2) {
        return value1 / value2;
    }

    public static double modulus(double value1, double value2) {
        return value1 % value2;
    }

    public static double power(double value1, double value2) {
        return Math.pow(value1, value2);
    }

    public static double squareRoot(double value) {
        return Math.sqrt(value);
    }

    public static double absoluteValue(double value) {
        return Math.abs(value);
    }

    public static double negate(double value) {
        return -value;
    }

    public static double increment(double value) {
        return value + 1.0;
    }

    public static double decrement(double value) {
        return value - 1.0;
    }

    public static double min(double value1, double value2) {
        return Math.min(value1, value2);
    }

    public static double max(double value1, double value2) {
        return Math.max(value1, value2);
    }

    public static double clamp(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

    public static double clamp(double value, double[] range) {
        return clamp(value, range[0], range[1]);
    }
}
