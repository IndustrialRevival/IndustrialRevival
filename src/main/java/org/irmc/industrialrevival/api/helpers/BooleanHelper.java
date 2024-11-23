package org.irmc.industrialrevival.api.helpers;

public class BooleanHelper {
    public static boolean parseBoolean(String value) {
        return Boolean.parseBoolean(value);
    }

    public static String toYN(boolean value) {
        return value ? "Yes" : "No";
    }

    public static String toSymbol(boolean value) {
        return value ? "√" : "×";
    }

    public static String toPNS(boolean value) {
        return value ? "+" : "-";
    }

    public static String toOO(boolean value) {
        return value ? "On" : "Off";
    }

    public static String toPN(boolean value) {
        return value ? "Positive" : "Negative";
    }

    public static double doubleValue(boolean value) {
        return value ? 1.0 : 0.0;
    }

    public static long longValue(boolean value) {
        return value ? 1 : 0;
    }

    public static int intValue(boolean value) {
        return value ? 1 : 0;
    }

    public static short shortValue(boolean value) {
        return value ? (short) 1 : (short) 0;
    }

    public static byte byteValue(boolean value) {
        return value ? (byte) 1 : (byte) 0;
    }

    public static boolean booleanValue(boolean value) {
        return value;
    }

    public static boolean isZero(boolean value) {
        return !value;
    }

    public static boolean isNotZero(boolean value) {
        return value;
    }

    public static boolean isPositive(boolean value) {
        return value;
    }

    public static boolean isNegative(boolean value) {
        return !value;
    }

    public static boolean isNonNegative(boolean value) {
        return value;
    }

    public static boolean isNonPositive(boolean value) {
        return !value;
    }

    public static boolean isEqual(boolean value1, boolean value2) {
        return value1 == value2;
    }

    public static boolean isNotEqual(boolean value1, boolean value2) {
        return value1!= value2;
    }
    public static boolean isTrue(boolean value) {
        return value;
    }

    public static boolean isFalse(boolean value) {
        return !value;
    }

    public static boolean logicalAnd(boolean value1, boolean value2) {
        return value1 && value2;
    }

    public static boolean logicalOr(boolean value1, boolean value2) {
        return value1 || value2;
    }

    public static boolean logicalXor(boolean value1, boolean value2) {
        return value1 ^ value2;
    }

    public static boolean logicalNot(boolean value) {
        return !value;
    }

    public static boolean logicalNand(boolean value1, boolean value2) {
        return !logicalAnd(value1, value2);
    }

    public static boolean logicalNor(boolean value1, boolean value2) {
        return !logicalOr(value1, value2);
    }

    public static boolean logicalXnor(boolean value1, boolean value2) {
        return !logicalXor(value1, value2);
    }

    public static boolean logicalImplies(boolean value1, boolean value2) {
        return !value1 || value2;
    }

    public static boolean logicalEquivalence(boolean value1, boolean value2) {
        return logicalImplies(value1, value2) && logicalImplies(value2, value1);
    }

    public static boolean logicalConverse(boolean value) {
        return !value;
    }

    public static boolean logicalReverse(boolean value) {
        return !value;
    }

    public static boolean logicalAlternate(boolean value) {
        return !value;
    }

    public static boolean logicalMerge(boolean value1, boolean value2) {
        return value1 && value2;
    }
}
