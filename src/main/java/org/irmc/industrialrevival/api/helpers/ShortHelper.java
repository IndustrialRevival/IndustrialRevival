package org.irmc.industrialrevival.api.helpers;

public class ShortHelper {

    public static short parseShort(String value) {
        return Short.parseShort(value);
    }

    public static short parseShort(String value, short defaultValue) {
        try {
            return parseShort(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static double doubleValue(short value) {
        return value;
    }

    public static long longValue(short value) {
        return  value;
    }

    public static int intValue(short value) {
        return value;
    }

    public static short shortValue(short value) {
        return value;
    }

    public static byte byteValue(short value) {
        return (byte) value;
    }

    public static boolean booleanValue(short value) {
        return value!= 0;
    }

    public static boolean isZero(short value) {
        return value == 0.0;
    }

    public static boolean isNotZero(short value) {
        return value!= 0.0;
    }

    public static boolean isPositive(short value) {
        return value > 0.0;
    }

    public static boolean isNegative(short value) {
        return value < 0.0;
    }

    public static boolean isNonNegative(short value) {
        return value >= 0.0;
    }

    public static boolean isNonPositive(short value) {
        return value <= 0.0;
    }

    public static boolean isEqual(short value1, short value2) {
        return value1 == value2;
    }

    public static boolean isNotEqual(short value1, short value2) {
        return value1!= value2;
    }

    public static boolean isGreaterThan(short value1, short value2) {
        return value1 > value2;
    }

    public static boolean isGreaterThanOrEqual(short value1, short value2) {
        return value1 >= value2;
    }

    public static boolean isLessThan(short value1, short value2) {
        return value1 < value2;
    }

    public static boolean isLessThanOrEqual(short value1, short value2) {
        return value1 <= value2;
    }

    public static boolean isBetween(short value, short min, short max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenOrEqual(short value, short min, short max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenExclusive(short value, short min, short max) {
        return isGreaterThan(value, min) && isLessThan(value, max);
    }

    public static boolean isBetweenExclusiveOrEqual(short value, short min, short max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenInclusive(short value, short min, short max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenInclusiveOrEqual(short value, short min, short max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetween(short value, short min, short max, boolean inclusive) {
        if (inclusive) {
            return isBetweenInclusive(value, min, max);
        } else {
            return isBetweenExclusive(value, min, max);
        }
    }

    public static boolean isBetweenOrEqual(short value, short min, short max, boolean inclusive) {
        if (inclusive) {
            return isBetweenInclusiveOrEqual(value, min, max);
        } else {
            return isBetweenExclusiveOrEqual(value, min, max);
        }
    }

    public static boolean isBetween(short value, short[] range) {
        return isBetween(value, range[0], range[1]);
    }

    public static boolean isBetweenOrEqual(short value, short[] range) {
        return isBetweenOrEqual(value, range[0], range[1]);
    }

    public static boolean isBetween(short value, short[] range, boolean inclusive) {
        return isBetween(value, range[0], range[1], inclusive);
    }

    public static boolean isBetweenOrEqual(short value, short[] range, boolean inclusive) {
        return isBetweenOrEqual(value, range[0], range[1], inclusive);
    }

    public static short add(short value1, short value2) {
        return (short) (value1 + value2);
    }

    public static short subtract(short value1, short value2) {
        return (short) (value1 - value2);
    }

    public static short multiply(short value1, short value2) {
        return (short) (value1 * value2);
    }

    public static short divide(short value1, short value2) {
        return (short) (value1 / value2);
    }

    public static short modulus(short value1, short value2) {
        return (short) (value1 % value2);
    }

    public static short power(short value1, short value2) {
        return (short) Math.pow(value1, value2);
    }

    public static short squareRoot(short value) {
        return (short) Math.sqrt(value);
    }

    public static short absoluteValue(short value) {
        return (short) Math.abs(value);
    }

    public static short negate(short value) {
        return (short) -value;
    }

    public static short increment(short value) {
        return (short) (value + 1);
    }

    public static short decrement(short value) {
        return (short) (value - 1);
    }

    public static short min(short value1, short value2) {
        return (short) Math.min(value1, value2);
    }

    public static short max(short value1, short value2) {
        return (short) Math.max(value1, value2);
    }

    public static short clamp(short value, short min, short max) {
        return (short) Math.min(Math.max(value, min), max);
    }

    public static short clamp(short value, short[] range) {
        return clamp(value, range[0], range[1]);
    }
}
