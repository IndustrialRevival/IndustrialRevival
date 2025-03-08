package org.irmc.industrialrevival.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This utility class provides various Java-related helper methods for array, list, and set operations,
 * including conversions, shuffling, and other common manipulations. It is designed to simplify common
 * Java tasks such as converting arrays to sets or lists, reversing arrays or lists, and generating
 * random or sequential arrays.
 *
 * @author Final_ROOT
 * @author balugaq
 */
public class JavaUtil {

    /**
     * Converts a variable number of objects into a Set.
     *
     * @param objects The objects to convert into a Set.
     * @param <T> The type of the objects.
     * @return A Set containing the provided objects.
     */
    @SafeVarargs
    @Nonnull
    public static <T> Set<T> toSet(@Nonnull T... objects) {
        Set<T> result = new HashSet<>(objects.length);
        result.addAll(Arrays.asList(objects));
        return result;
    }

    /**
     * Converts a variable number of objects into a List.
     *
     * @param objects The objects to convert into a List.
     * @param <T> The type of the objects.
     * @return A List containing the provided objects.
     */
    @SafeVarargs
    @Nonnull
    public static <T> List<T> toList(@Nonnull T... objects) {
        List<T> result = new ArrayList<>(objects.length);
        result.addAll(Arrays.asList(objects));
        return result;
    }

    /**
     * Converts a List of Integers into an array of ints.
     *
     * @param list The List of Integers to convert.
     * @return An array of ints containing the elements of the List.
     */
    @Nonnull
    public static int[] toArray(@Nonnull List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * Reverses the order of elements in an array of ints.
     *
     * @param objects The array of ints to reverse.
     * @return A new array with the elements in reverse order.
     */
    @Nonnull
    public static int[] reserve(@Nonnull int[] objects) {
        int[] result = objects.clone();
        for (int i = 0; i < objects.length; i++) {
            result[i] = objects[objects.length - 1 - i];
        }
        return result;
    }

    /**
     * Reverses the order of elements in a List.
     *
     * @param objectList The List to reverse.
     * @param <T> The type of the elements in the List.
     * @return A new List with the elements in reverse order.
     */
    @Nonnull
    public static <T> List<T> reserve(@Nonnull List<T> objectList) {
        List<T> result = new ArrayList<>(objectList);
        for (int i = 0; i < objectList.size(); i++) {
            result.set(i, objectList.get(objectList.size() - 1 - i));
        }
        return result;
    }

    /**
     * Shuffles the elements of an array of ints.
     *
     * @param objects The array of ints to shuffle.
     * @return A new array with the elements shuffled.
     */
    @Nonnull
    public static int[] shuffle(@Nonnull int[] objects) {
        List<Integer> collect = Arrays.stream(objects).boxed().collect(Collectors.toList());
        Collections.shuffle(collect);
        int[] result = objects.clone();
        for (int i = 0; i < collect.size(); i++) {
            result[i] = collect.get(i);
        }
        return result;
    }

    /**
     * Shuffles the elements of an array of objects.
     *
     * @param objects The array of objects to shuffle.
     * @param <T> The type of the objects in the array.
     * @return A new array with the elements shuffled.
     */
    @Nonnull
    public static <T> T[] shuffle(@Nonnull T[] objects) {
        List<T> collect = Arrays.stream(objects).collect(Collectors.toList());
        Collections.shuffle(collect);
        T[] result = objects.clone();
        for (int i = 0; i < collect.size(); i++) {
            result[i] = collect.get(i);
        }
        return result;
    }

    /**
     * Shuffles the elements of a List.
     *
     * @param objectList The List to shuffle.
     * @param <T> The type of the elements in the List.
     * @return A new List with the elements shuffled.
     */
    @Nonnull
    public static <T> List<T> shuffle(@Nonnull List<T> objectList) {
        List<T> list = new ArrayList<>(objectList);
        Collections.shuffle(list);
        return list;
    }

    /**
     * Creates a dispersed array of double values based on the provided size and values.
     *
     * @param size The size of the resulting array.
     * @param value The values to disperse.
     * @return An array of double values dispersed based on the provided size and values.
     */
    @Nonnull
    public static double[] disperse(int size, @Nonnull Number... value) {
        if (size == 1 && value.length > 0) {
            return new double[]{value[0].doubleValue()};
        } else if (size == 0 || value.length == 0) {
            return new double[0];
        }
        double[] result = new double[size--];
        for (int i = 0; i <= size; i++) {
            double p = ((double) i) / size * (value.length - 1);
            double value1 = value[(int) Math.floor(p)].doubleValue() * (1 - p + Math.floor(p));
            double value2 = value[(int) Math.ceil(p)].doubleValue() * (p - Math.floor(p));
            result[i] = value1 + value2;
        }
        return result;
    }

    /**
     * Splits a string into an array of individual characters.
     *
     * @param string The string to split.
     * @return An array of strings, each representing a single character from the input string.
     */
    @Nonnull
    public static String[] split(@Nonnull String string) {
        String[] result = new String[string.length()];
        for (int i = 0; i < string.length(); i++) {
            result[i] = String.valueOf(string.charAt(i));
        }
        return result;
    }

    /**
     * Generates an array of random integers from 0 to length-1.
     *
     * @param length The length of the array.
     * @return An array of integers containing values from 0 to length-1 in random order.
     */
    @Nonnull
    public static int[] generateRandomInts(int length) {
        int[] result = new int[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        return JavaUtil.shuffle(result);
    }

    /**
     * Generates an array of sequential integers from 0 to length-1.
     *
     * @param length The length of the array.
     * @return An array of integers containing values from 0 to length-1 in order.
     */
    @Nonnull
    public static int[] generateInts(int length) {
        int[] result = new int[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        return result;
    }

    /**
     * Shuffles a List based on the provided array of indices.
     *
     * @param list The List to shuffle.
     * @param ints The array of indices to use for shuffling.
     * @param <T> The type of the elements in the List.
     * @return A new List with the elements shuffled according to the provided indices.
     */
    @Nonnull
    public static <T> List<T> shuffleByInts(@Nonnull List<T> list, @Nonnull int[] ints) {
        List<T> result = new ArrayList<>(list.size());
        for (int anInt : ints) {
            result.add(list.get(anInt));
        }
        return result;
    }

    /**
     * Adds a value to the beginning of an array of strings.
     *
     * @param value The value to add.
     * @param values The array of strings to which the value will be added.
     * @return A new array with the value added to the beginning.
     */
    @Nonnull
    public static String[] addToFirst(@Nonnull String value, @Nonnull String... values) {
        String[] result = new String[values.length + 1];
        result[0] = value;
        System.arraycopy(values, 0, result, 1, values.length);
        return result;
    }

    /**
     * Checks if a source object matches any of the target objects.
     *
     * @param source The source object to compare.
     * @param targets The target objects to compare against.
     * @param <T> The type of the objects.
     * @return True if the source object matches any of the target objects, false otherwise.
     */
    @SafeVarargs
    public static <T> boolean matchOnce(@Nonnull T source, @Nonnull T... targets) {
        for (T object : targets) {
            if (object.equals(source)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Measures the execution time of a Runnable task.
     *
     * @param runnable The task to measure.
     * @return The execution time in nanoseconds.
     */
    public static long testTime(@Nonnull Runnable runnable) {
        long beginTime = System.nanoTime();
        runnable.run();
        return System.nanoTime() - beginTime;
    }

    /**
     * Returns the first non-null object from the provided array of objects.
     *
     * @param objects The array of objects to search.
     * @param <T> The type of the objects.
     * @return The first non-null object, or null if all objects are null.
     */
    @SafeVarargs
    @Nullable
    public static <T> T getFirstNotNull(@Nonnull T... objects) {
        for (T object : objects) {
            if (object != null) {
                return object;
            }
        }
        return null;
    }
}
