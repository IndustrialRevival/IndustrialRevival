package org.irmc.industrialrevival.api.helpers;

public class ByteHelper {

    public static byte parseByte(String value) {
        return Byte.parseByte(value);
    }

    public static byte parseByte(String value, byte defaultValue) {
        try {
            return parseByte(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static double doubleValue(byte value) {
        return value;
    }

    public static long longValue(byte value) {
        return  value;
    }

    public static int intValue(byte value) {
        return value;
    }

    public static short shortValue(byte value) {
        return value;
    }

    public static byte byteValue(byte value) {
        return value;
    }

    public static boolean booleanValue(byte value) {
        return value!= 0;
    }

    public static boolean isZero(byte value) {
        return value == 0.0;
    }

    public static boolean isNotZero(byte value) {
        return value!= 0.0;
    }

    public static boolean isPositive(byte value) {
        return value > 0.0;
    }

    public static boolean isNegative(byte value) {
        return value < 0.0;
    }

    public static boolean isNonNegative(byte value) {
        return value >= 0.0;
    }

    public static boolean isNonPositive(byte value) {
        return value <= 0.0;
    }

    public static boolean isEqual(byte value1, byte value2) {
        return value1 == value2;
    }

    public static boolean isNotEqual(byte value1, byte value2) {
        return value1!= value2;
    }

    public static boolean isGreaterThan(byte value1, byte value2) {
        return value1 > value2;
    }

    public static boolean isGreaterThanOrEqual(byte value1, byte value2) {
        return value1 >= value2;
    }

    public static boolean isLessThan(byte value1, byte value2) {
        return value1 < value2;
    }

    public static boolean isLessThanOrEqual(byte value1, byte value2) {
        return value1 <= value2;
    }

    public static boolean isBetween(byte value, byte min, byte max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenOrEqual(byte value, byte min, byte max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenExclusive(byte value, byte min, byte max) {
        return isGreaterThan(value, min) && isLessThan(value, max);
    }

    public static boolean isBetweenExclusiveOrEqual(byte value, byte min, byte max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenInclusive(byte value, byte min, byte max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetweenInclusiveOrEqual(byte value, byte min, byte max) {
        return isGreaterThanOrEqual(value, min) && isLessThanOrEqual(value, max);
    }

    public static boolean isBetween(byte value, byte min, byte max, boolean inclusive) {
        if (inclusive) {
            return isBetweenInclusive(value, min, max);
        } else {
            return isBetweenExclusive(value, min, max);
        }
    }

    public static boolean isBetweenOrEqual(byte value, byte min, byte max, boolean inclusive) {
        if (inclusive) {
            return isBetweenInclusiveOrEqual(value, min, max);
        } else {
            return isBetweenExclusiveOrEqual(value, min, max);
        }
    }

    public static boolean isBetween(byte value, byte[] range) {
        return isBetween(value, range[0], range[1]);
    }

    public static boolean isBetweenOrEqual(byte value, byte[] range) {
        return isBetweenOrEqual(value, range[0], range[1]);
    }

    public static boolean isBetween(byte value, byte[] range, boolean inclusive) {
        return isBetween(value, range[0], range[1], inclusive);
    }

    public static boolean isBetweenOrEqual(byte value, byte[] range, boolean inclusive) {
        return isBetweenOrEqual(value, range[0], range[1], inclusive);
    }

    public static byte add(byte value1, byte value2) {
        return (byte) (value1 + value2);
    }

    public static byte subtract(byte value1, byte value2) {
        return (byte) (value1 - value2);
    }

    public static byte multiply(byte value1, byte value2) {
        return (byte) (value1 * value2);
    }

    public static byte divide(byte value1, byte value2) {
        return (byte) (value1 / value2);
    }

    public static byte modulus(byte value1, byte value2) {
        return (byte) (value1 % value2);
    }

    public static byte power(byte value1, byte value2) {
        return (byte) Math.pow(value1, value2);
    }

    public static byte squareRoot(byte value) {
        return (byte) Math.sqrt(value);
    }

    public static byte absoluteValue(byte value) {
        return (byte) Math.abs(value);
    }

    public static byte negate(byte value) {
        return (byte) -value;
    }

    public static byte increment(byte value) {
        return (byte) (value + 1);
    }

    public static byte decrement(byte value) {
        return (byte) (value - 1);
    }

    public static byte min(byte value1, byte value2) {
        return (byte) Math.min(value1, value2);
    }

    public static byte max(byte value1, byte value2) {
        return (byte) Math.max(value1, value2);
    }

    public static byte clamp(byte value, byte min, byte max) {
        return (byte) Math.min(Math.max(value, min), max);
    }

    public static byte clamp(byte value, byte[] range) {
        return clamp(value, range[0], range[1]);
    }
}
