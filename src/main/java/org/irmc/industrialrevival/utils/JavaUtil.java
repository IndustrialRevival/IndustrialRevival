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
 * @author Final_ROOT
 * @author balugaq
 */
public class JavaUtil {

    @SafeVarargs
    @Nonnull
    public static <T> Set<T> toSet(@Nonnull T... objects) {
        Set<T> result = new HashSet<>(objects.length);
        result.addAll(Arrays.asList(objects));
        return result;
    }

    @SafeVarargs
    @Nonnull
    public static <T> List<T> toList(@Nonnull T... objects) {
        List<T> result = new ArrayList<>(objects.length);
        result.addAll(Arrays.asList(objects));
        return result;
    }

    @Nonnull
    public static int[] toArray(@Nonnull List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    @Nonnull
    public static int[] reserve(@Nonnull int[] objects) {
        int[] result = objects.clone();
        for (int i = 0; i < objects.length; i++) {
            result[i] = objects[objects.length - 1 - i];
        }
        return result;
    }

    @Nonnull
    public static <T> List<T> reserve(@Nonnull List<T> objectList) {
        List<T> result = new ArrayList<>(objectList);
        for (int i = 0; i < objectList.size(); i++) {
            result.set(i, objectList.get(objectList.size() - 1 - i));
        }
        return result;
    }

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

    @Nonnull
    public static <T> List<T> shuffle(@Nonnull List<T> objectList) {
        List<T> list = new ArrayList<>(objectList);
        Collections.shuffle(list);
        return list;
    }

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

    @Nonnull
    public static String[] split(@Nonnull String string) {
        String[] result = new String[string.length()];
        for (int i = 0; i < string.length(); i++) {
            result[i] = String.valueOf(string.charAt(i));
        }
        return result;
    }

    /**
     * Generate random int[] contains 0 1 2 ...... length-1
     * for example, while the input length = 3, the output may be [0, 1, 2] or [0, 2, 1] or [1, 0, 2] or [1, 2, 0] or [2, 0, 1] or [2, 1, 0]
     *
     * @param length the length of the int[]
     * @return a random int[]
     */
    @Nonnull
    public static int[] generateRandomInts(int length) {
        int[] result = new int[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        return JavaUtil.shuffle(result);
    }

    @Nonnull
    public static int[] generateInts(int length) {
        int[] result = new int[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        return result;
    }

    /**
     * shuffle a list by int[]
     * outputList[0] = inputList[int[0]]
     * for example:
     * input:
     * list: [a, b, c, d, e, f]
     * int:[2, 1, 0, 4, 5, 3]
     * output:
     * list: [c, b, a, e, f, d]
     *
     * @param list the input list
     * @param ints the int[]
     * @param <T>  the type of the list
     * @return the shuffled list
     */
    @Nonnull
    public static <T> List<T> shuffleByInts(@Nonnull List<T> list, @Nonnull int[] ints) {
        List<T> result = new ArrayList<>(list.size());
        for (int anInt : ints) {
            result.add(list.get(anInt));
        }
        return result;
    }

    @Nonnull
    public static String[] addToFirst(@Nonnull String value, @Nonnull String... values) {
        String[] result = new String[values.length + 1];
        result[0] = value;
        System.arraycopy(values, 0, result, 1, values.length);
        return result;
    }

    @SafeVarargs
    public static <T> boolean matchOnce(@Nonnull T source, @Nonnull T... targets) {
        for (T object : targets) {
            if (object.equals(source)) {
                return true;
            }
        }
        return false;
    }

    public static long testTime(@Nonnull Runnable runnable) {
        long beginTime = System.nanoTime();
        runnable.run();
        return System.nanoTime() - beginTime;
    }

    /**
     * @return In most case, it will not return null. (￣▽￣)"
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