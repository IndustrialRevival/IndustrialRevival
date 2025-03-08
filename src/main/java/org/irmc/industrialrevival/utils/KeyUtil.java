package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
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
        return new NamespacedKey(IndustrialRevival.getInstance(), key);
    }

    /**
     * Creates a {@link NamespacedKey} using a custom namespace and the specified key.
     *
     * @param namespace The namespace to use for the {@link NamespacedKey}.
     * @param key The key to use for the {@link NamespacedKey}.
     * @return A {@link NamespacedKey} with the specified namespace and key.
     */
    public static NamespacedKey customKey(String namespace, String key) {
        return new NamespacedKey(namespace, key);
    }

    /**
     * Creates a {@link NamespacedKey} using the namespace of the specified plugin and the provided key.
     *
     * @param plugin The plugin whose namespace will be used for the {@link NamespacedKey}.
     * @param key The key to use for the {@link NamespacedKey}.
     * @return A {@link NamespacedKey} with the plugin's namespace and the provided key.
     */
    public static NamespacedKey customKey(Plugin plugin, String key) {
        return new NamespacedKey(plugin, key);
    }
}
