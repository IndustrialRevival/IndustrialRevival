package org.irmc.industrialrevival.api.objects;

import java.util.function.Function;

public class DataType<T> {
    public static final DataType<String> STRING = new DataType<>(s -> s, s -> s);
    public static final DataType<Integer> INTEGER = new DataType<>(Integer::parseInt, Object::toString);
    public static final DataType<Double> DOUBLE = new DataType<>(Double::parseDouble, Object::toString);
    public static final DataType<Boolean> BOOLEAN = new DataType<>(Boolean::parseBoolean, Object::toString);

    private final Function<String, T> converter;
    private final Function<T, String> backConverter;

    private DataType(Function<String, T> converter, Function<T, String> backConverter) {
        this.converter = converter;
        this.backConverter = backConverter;
    }

    public T convert(String value) {
        return converter.apply(value);
    }

    public String convertBack(T value) {
        return backConverter.apply(value);
    }
}
