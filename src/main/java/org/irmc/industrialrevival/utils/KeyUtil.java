package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

/**
 * This utility class provides methods for creating custom {@link NamespacedKey} instances.
 * It simplifies the process of generating keys by allowing the use of a default namespace (the plugin's name)
 * or specifying a custom namespace.
 *
 * @author balugaq
 */
@UtilityClass
public class KeyUtil {

    /**
     * Creates a {@link NamespacedKey} using the default namespace (the plugin's name) and the specified key.
     *
     * @param key The key to use for the {@link NamespacedKey}.
     * @return A {@link NamespacedKey} with the plugin's namespace and the provided key.
     */
    public static NamespacedKey customKey(String key) {
        return new NamespacedKey(IRDock.getPlugin(), key.toLowerCase());
    }

    public static NamespacedKey appendOnKey(NamespacedKey key, String append) {
        return new NamespacedKey(key.getNamespace(), key.getKey() + "_" + append.toLowerCase());
    }
}
