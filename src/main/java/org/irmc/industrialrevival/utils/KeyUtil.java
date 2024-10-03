package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

@UtilityClass
public class KeyUtil {
    public static NamespacedKey customKey(String key) {
        return new NamespacedKey(IndustrialRevival.getInstance(), key);
    }

    public static NamespacedKey customKey(String namespace, String key) {
        return new NamespacedKey(namespace, key);
    }

    public static NamespacedKey customKey(Plugin plugin, String key) {
        return new NamespacedKey(plugin, key);
    }
}
