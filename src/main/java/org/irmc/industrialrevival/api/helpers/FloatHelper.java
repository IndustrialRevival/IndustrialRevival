package org.irmc.industrialrevival.api.helpers;

public class FloatHelper {
    public static float round(float value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (float) tmp / factor;
    }

    public static float parseFloat(String value) {
        return Float.parseFloat(value);
    }

    public static float parseFloat(String value, float defaultValue) {
        try {
            return parseFloat(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static double doubleValue(float value) {
        return value;
    }

    public static float floatValue(float value) {
        return value;
    }

    public static long longValue(float value) {
        return (long) value;
    }

    public static int intValue(float value) {
        return (int) value;
    }

    public static short shortValue(float value) {
        return (short) value;
    }

    public static byte byteValue(float value) {
        return (byte) value;
    }

    public static boolean booleanValue(float value) {
        return value!= 0;
    }

    public static boolean isNaN(float value) {
        return Float.isNaN(value);
    }

    public static boolean isInfinite(float value) {
        return Float.isInfinite(value);
    }

    public static boolean isFinite(float value) {
        return!isNaN(value) &&!isInfinite(value);
    }

    public static boolean isZero(float value) {
        return value == 0.0;
    }

    public static boolean isNotZero(float value) {
        return value!= 0.0;
    }

    public static boolean isPositive(float value) {
        return value > 0.0;
    }

    public static boolean isNegative(float value) {
        return value < 0.0;
    }

    public static boolean isNonNegative(float value) {
        return value >= 0.0;
    }

    public static boolean isNonPositive(float value) {
        return value <= 0.0;
    }

    public static boolean isEqual(float value1, float value2) {
        return value1 == value2;
    }

    public static boolean isNotEqual(float value1, float value2) {
        return value1!= value2;
    }

    public static boolean isGreaterThan(float value1, float value2) {
        return value1 > value2;
    }

    public static boolean isGreaterThanOrEqual(float value1, float value2) {
        return value1 >= value2;
    }

    public static boolean isLessThan(float value1, float value2) {
        return value1 < value2;
    }

    public static boolean isLessThanOrEqual(float value1, float value2) {
        return value1 <= value2;
    }

    public static boolean isBetween(float value, float min, float max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenOrEqual(float value, float min, float max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenExclusive(float value, float min, float max) {
        return isGreaterThan(value, min) && isLessThan(value, max);
    }

    public static boolean isBetweenExclusiveOrEqual(float value, float min, float max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenInclusive(float value, float min, float max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenInclusiveOrEqual(float value, float min, float max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetween(float value, float min, float max, boolean inclusive) {
        if (inclusive) {
            return isBetweenInclusive(value, min, max);
        } else {
            return isBetweenExclusive(value, min, max);
        }
    }

    public static boolean isBetweenOrEqual(float value, float min, float max, boolean inclusive) {
        if (inclusive) {
            return isBetweenInclusiveOrEqual(value, min, max);
        } else {
            return isBetweenExclusiveOrEqual(value, min, max);
        }
    }

    public static boolean isBetween(float value, float[] range) {
        return isBetween(value, range[0], range[1]);
    }

    public static boolean isBetweenOrEqual(float value, float[] range) {
        return isBetweenOrEqual(value, range[0], range[1]);
    }

    public static boolean isBetween(float value, float[] range, boolean inclusive) {
        return isBetween(value, range[0], range[1], inclusive);
    }

    public static boolean isBetweenOrEqual(float value, float[] range, boolean inclusive) {
        return isBetweenOrEqual(value, range[0], range[1], inclusive);
    }

    public static float add(float value1, float value2) {
        return value1 + value2;
    }

    public static float subtract(float value1, float value2) {
        return value1 - value2;
    }

    public static float multiply(float value1, float value2) {
        return value1 * value2;
    }

    public static float divide(float value1, float value2) {
        return value1 / value2;
    }

    public static float modulus(float value1, float value2) {
        return value1 % value2;
    }

    public static float power(float value1, float value2) {
        return (float) Math.pow(value1, value2);
    }

    public static float squareRoot(float value) {
        return (float) Math.sqrt(value);
    }

    public static float absoluteValue(float value) {
        return Math.abs(value);
    }

    public static float negate(float value) {
        return -value;
    }

    public static float increment(float value) {
        return (float) (value + 1.0);
    }

    public static float decrement(float value) {
        return (float) (value - 1.0);
    }

    public static float min(float value1, float value2) {
        return Math.min(value1, value2);
    }

    public static float max(float value1, float value2) {
        return Math.max(value1, value2);
    }

    public static float clamp(float value, float min, float max) {
        return Math.min(Math.max(value, min), max);
    }

    public static float clamp(float value, float[] range) {
        return clamp(value, range[0], range[1]);
    }
}
