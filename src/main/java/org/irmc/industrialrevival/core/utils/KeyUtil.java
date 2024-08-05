package org.irmc.industrialrevival.core.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.irmc.industrialrevival.core.IndustrialRevival;

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
