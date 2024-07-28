package org.irmc.industrialrevival.core.utils;

import org.bukkit.NamespacedKey;
import org.irmc.industrialrevival.core.IndustrialRevival;

public class Keys {
    public static NamespacedKey customKey(String key) {
        return new NamespacedKey(IndustrialRevival.getInstance(), key);
    }
}
