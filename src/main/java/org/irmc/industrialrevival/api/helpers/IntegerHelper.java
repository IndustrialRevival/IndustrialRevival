package org.irmc.industrialrevival.api.helpers;

public class IntegerHelper {

    public static int parseInteger(String value) {
        return Integer.parseInt(value);
    }

    public static int parseInteger(String value, int defaultValue) {
        try {
            return parseInteger(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static double doubleValue(int value) {
        return value;
    }

    public static long longValue(int value) {
        return value;
    }

    public static int intValue(int value) {
        return value;
    }

    public static short shortValue(int value) {
        return (short) value;
    }

    public static byte byteValue(int value) {
        return (byte) value;
    }

    public static boolean booleanValue(int value) {
        return value!= 0;
    }

    public static boolean isZero(int value) {
        return value == 0.0;
    }

    public static boolean isNotZero(int value) {
        return value!= 0.0;
    }

    public static boolean isPositive(int value) {
        return value > 0.0;
    }

    public static boolean isNegative(int value) {
        return value < 0.0;
    }

    public static boolean isNonNegative(int value) {
        return value >= 0.0;
    }

    public static boolean isNonPositive(int value) {
        return value <= 0.0;
    }

    public static boolean isEqual(int value1, int value2) {
        return value1 == value2;
    }

    public static boolean isNotEqual(int value1, int value2) {
        return value1!= value2;
    }

    public static boolean isGreaterThan(int value1, int value2) {
        return value1 > value2;
    }

    public static boolean isGreaterThanOrEqual(int value1, int value2) {
        return value1 >= value2;
    }

    public static boolean isLessThan(int value1, int value2) {
        return value1 < value2;
    }

    public static boolean isLessThanOrEqual(int value1, int value2) {
        return value1 <= value2;
    }

    public static boolean isBetween(int value, int min, int max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenOrEqual(int value, int min, int max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenExclusive(int value, int min, int max) {
        return isGreaterThan(value, min) && isLessThan(value, max);
    }

    public static boolean isBetweenExclusiveOrEqual(int value, int min, int max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenInclusive(int value, int min, int max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenInclusiveOrEqual(int value, int min, int max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetween(int value, int min, int max, boolean inclusive) {
        if (inclusive) {
            return isBetweenInclusive(value, min, max);
        } else {
            return isBetweenExclusive(value, min, max);
        }
    }

    public static boolean isBetweenOrEqual(int value, int min, int max, boolean inclusive) {
        if (inclusive) {
            return isBetweenInclusiveOrEqual(value, min, max);
        } else {
            return isBetweenExclusiveOrEqual(value, min, max);
        }
    }

    public static boolean isBetween(int value, int[] range) {
        return isBetween(value, range[0], range[1]);
    }

    public static boolean isBetweenOrEqual(int value, int[] range) {
        return isBetweenOrEqual(value, range[0], range[1]);
    }

    public static boolean isBetween(int value, int[] range, boolean inclusive) {
        return isBetween(value, range[0], range[1], inclusive);
    }

    public static boolean isBetweenOrEqual(int value, int[] range, boolean inclusive) {
        return isBetweenOrEqual(value, range[0], range[1], inclusive);
    }

    public static int add(int value1, int value2) {
        return value1 + value2;
    }

    public static int subtract(int value1, int value2) {
        return value1 - value2;
    }

    public static int multiply(int value1, int value2) {
        return value1 * value2;
    }

    public static int divide(int value1, int value2) {
        return value1 / value2;
    }

    public static int modulus(int value1, int value2) {
        return value1 % value2;
    }

    public static int power(int value1, int value2) {
        return (int) Math.pow(value1, value2);
    }

    public static int squareRoot(int value) {
        return (int) Math.sqrt(value);
    }

    public static int absoluteValue(int value) {
        return Math.abs(value);
    }

    public static int negate(int value) {
        return -value;
    }

    public static int increment(int value) {
        return value + 1;
    }

    public static int decrement(int value) {
        return value - 1;
    }

    public static int min(int value1, int value2) {
        return Math.min(value1, value2);
    }

    public static int max(int value1, int value2) {
        return Math.max(value1, value2);
    }

    public static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    public static int clamp(int value, int[] range) {
        return clamp(value, range[0], range[1]);
    }
}
