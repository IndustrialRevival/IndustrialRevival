package org.irmc.industrialrevival.api.data;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DataStorePoints {
    private static final Map<String, DataStorePoint> points = new ConcurrentHashMap<>();

    public static void register(String name, DataStorePoint point) {
        Preconditions.checkArgument(!points.containsKey(name), "DataStorePoint with name %s already exists", name);
        Preconditions.checkArgument(!name.isBlank(), "DataStorePoint name cannot be null or blank");
        Preconditions.checkArgument(point != null, "DataStorePoint cannot be null");
        points.put(name, point);
    }

    public static DataStorePoint get(String name) {
        Preconditions.checkArgument(!name.isBlank(), "DataStorePoint name cannot be null or blank");
        return points.get(name);
    }

    public static Collection<DataStorePoint> getAll() {
        return Collections.unmodifiableCollection(points.values());
    }
}
