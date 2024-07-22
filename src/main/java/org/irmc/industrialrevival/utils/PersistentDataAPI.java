package org.irmc.industrialrevival.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PersistentDataAPI {
    private static final PersistentDataType<String, Location> LOCATION_DATA_TYPE = new LocationDataType();

    private PersistentDataAPI() {}

    public static int getInt(PersistentDataHolder holder, NamespacedKey key) {
        return getInt(holder, key, 0);
    }

    public static int getInt(PersistentDataHolder holder, NamespacedKey key, int def) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.INTEGER, def);
    }

    public static void setInt(PersistentDataHolder holder, NamespacedKey key, int value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, value);
    }

    public static boolean getBoolean(PersistentDataHolder holder, NamespacedKey key) {
        return getBoolean(holder, key, false);
    }

    public static boolean getBoolean(PersistentDataHolder holder, NamespacedKey key, boolean def) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.BOOLEAN, def);
    }

    public static void setBoolean(PersistentDataHolder holder, NamespacedKey key, boolean value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, value);
    }

    public static String getString(PersistentDataHolder holder, NamespacedKey key) {
        return getString(holder, key, "");
    }

    public static String getString(PersistentDataHolder holder, NamespacedKey key, String def) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.STRING, def);
    }

    public static void setString(PersistentDataHolder holder, NamespacedKey key, String value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
    }

    public static double getDouble(PersistentDataHolder holder, NamespacedKey key) {
        return getDouble(holder, key, 0.0);
    }

    public static double getDouble(PersistentDataHolder holder, NamespacedKey key, double def) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.DOUBLE, def);
    }

    public static void setDouble(PersistentDataHolder holder, NamespacedKey key, double value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE, value);
    }

    public static long getLong(PersistentDataHolder holder, NamespacedKey key) {
        return getLong(holder, key, 0L);
    }

    public static long getLong(PersistentDataHolder holder, NamespacedKey key, long def) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.LONG, def);
    }

    public static void setLong(PersistentDataHolder holder, NamespacedKey key, long value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.LONG, value);
    }

    public static short getShort(PersistentDataHolder holder, NamespacedKey key) {
        return getShort(holder, key, (short) 0);
    }

    public static short getShort(PersistentDataHolder holder, NamespacedKey key, short def) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.SHORT, def);
    }

    public static void setShort(PersistentDataHolder holder, NamespacedKey key, short value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.SHORT, value);
    }

    public static byte getByte(PersistentDataHolder holder, NamespacedKey key) {
        return getByte(holder, key, (byte) 0);
    }

    public static byte getByte(PersistentDataHolder holder, NamespacedKey key, byte def) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.BYTE, def);
    }

    public static void setByte(PersistentDataHolder holder, NamespacedKey key, byte value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.BYTE, value);
    }

    public static float getFloat(PersistentDataHolder holder, NamespacedKey key) {
        return getFloat(holder, key, 0.0f);
    }

    public static float getFloat(PersistentDataHolder holder, NamespacedKey key, float def) {
        return holder.getPersistentDataContainer().getOrDefault(key, PersistentDataType.FLOAT, def);
    }

    public static void setFloat(PersistentDataHolder holder, NamespacedKey key, float value) {
        holder.getPersistentDataContainer().set(key, PersistentDataType.FLOAT, value);
    }

    @Nullable public static Location getLocation(PersistentDataHolder holder, NamespacedKey key) {
        return getLocation(holder, key, null);
    }

    @Nullable public static Location getLocation(PersistentDataHolder holder, NamespacedKey key, @Nullable Location def) {
        if (def == null) {
            return holder.getPersistentDataContainer().get(key, LOCATION_DATA_TYPE);
        }
        return holder.getPersistentDataContainer().getOrDefault(key, LOCATION_DATA_TYPE, def);
    }

    public static void setLocation(PersistentDataHolder holder, NamespacedKey key, Location value) {
        holder.getPersistentDataContainer().set(key, LOCATION_DATA_TYPE, value);
    }

    private static class LocationDataType implements PersistentDataType<String, Location> {
        @Override
        public @NotNull Class<String> getPrimitiveType() {
            return String.class;
        }

        @Override
        public @NotNull Class<Location> getComplexType() {
            return Location.class;
        }

        @Override
        public @NotNull String toPrimitive(
                @NotNull Location location, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
            return location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ()
                    + "," + location.getYaw() + "," + location.getPitch();
        }

        @Override
        public @NotNull Location fromPrimitive(
                @NotNull String string, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
            String[] split = string.split(",");
            String worldName = split[0];
            double x = Double.parseDouble(split[1]);
            double y = Double.parseDouble(split[2]);
            double z = Double.parseDouble(split[3]);
            float yaw = Float.parseFloat(split[4]);
            float pitch = Float.parseFloat(split[5]);
            return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
        }
    }
}
