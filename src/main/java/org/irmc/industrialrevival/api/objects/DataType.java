package org.irmc.industrialrevival.api.objects;

import java.util.List;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;

public class DataType<T> {
    public static final DataType<String> STRING = new DataType<>(MemorySection::set, MemorySection::getString);
    public static final DataType<Integer> INTEGER = new DataType<>(MemorySection::set, MemorySection::getInt);
    public static final DataType<Double> DOUBLE = new DataType<>(MemorySection::set, MemorySection::getDouble);
    public static final DataType<Boolean> BOOLEAN = new DataType<>(MemorySection::set, MemorySection::getBoolean);
    public static final DataType<Long> LONG = new DataType<>(MemorySection::set, MemorySection::getLong);
    public static final DataType<Float> FLOAT =
            new DataType<>(MemorySection::set, ((config, key) -> (float) config.getDouble(key)));
    public static final DataType<List<String>> STRING_LIST =
            new DataType<>(MemorySection::set, MemorySection::getStringList);
    public static final DataType<List<Integer>> INTEGER_LIST =
            new DataType<>(MemorySection::set, MemorySection::getIntegerList);
    public static final DataType<List<Double>> DOUBLE_LIST =
            new DataType<>(MemorySection::set, MemorySection::getDoubleList);
    public static final DataType<List<Boolean>> BOOLEAN_LIST =
            new DataType<>(MemorySection::set, MemorySection::getBooleanList);
    public static final DataType<List<Long>> LONG_LIST = new DataType<>(MemorySection::set, MemorySection::getLongList);

    private final DataSetterConsumer<T> setter;
    private final DataGetterFunction<T> getter;

    private DataType(DataSetterConsumer<T> setter, DataGetterFunction<T> getter) {
        this.setter = setter;
        this.getter = getter;
    }

    public void set(YamlConfiguration config, String key, T value) {
        setter.accept(config, key, value);
    }

    public T get(YamlConfiguration config, String key) {
        return getter.apply(config, key);
    }

    @FunctionalInterface
    public interface DataSetterConsumer<T> {
        void accept(YamlConfiguration config, String key, T value);
    }

    @FunctionalInterface
    public interface DataGetterFunction<T> {
        T apply(YamlConfiguration config, String key);
    }
}
