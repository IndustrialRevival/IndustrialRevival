package org.irmc.industrialrevival.utils;

import org.bukkit.ChatColor;

public class StringUtil {
    public static String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
