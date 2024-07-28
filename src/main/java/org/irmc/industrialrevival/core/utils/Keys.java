package org.irmc.industrialrevival.core.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.irmc.industrialrevival.core.IndustrialRevival;

public class Keys {

    public static final IndustrialRevival INSTANCE = IndustrialRevival.getInstance();

    public static NamespacedKey customKey(String key) {
        return new NamespacedKey(INSTANCE, "custom_key");
    }

    public static NamespacedKey customKey(Plugin plugin, String key) {
        return new NamespacedKey(plugin, "custom_key");
    }

    public static NamespacedKey customKey(String namespace, String key) {
        return new NamespacedKey(namespace, "custom_key");
    }
}
