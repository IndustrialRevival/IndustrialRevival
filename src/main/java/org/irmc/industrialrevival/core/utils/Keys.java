package org.irmc.industrialrevival.core.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.irmc.industrialrevival.core.IndustrialRevival;

public class Keys {

    public static final IndustrialRevival INSTANCE = IndustrialRevival.getInstance();
    public static final NamespacedKey NULL_ENCHANTMENT = customKey("null_enchantment");
    public static final NamespacedKey GROUP_ORE = customKey("group_ores");
    public static final NamespacedKey GROUP_MANUAL_MACHINES = customKey("group_manual_machines");
    public static final NamespacedKey GROUP_MATERIALS = customKey("group_materials");
    public static final NamespacedKey GROUP_SMELTING = customKey("group_smelting");
    public static final NamespacedKey GROUP_ELECTRIC_MACHINES = customKey("group_electric_machines");
    public static final NamespacedKey GROUP_TOOLS = customKey("group_tools");
    public static final NamespacedKey GROUP_ARMORS = customKey("group_armors");
    public static final NamespacedKey GROUP_DEFENSE = customKey("group_defense");
    public static final NamespacedKey GROUP_FOOD = customKey("group_food");
    public static final NamespacedKey GROUP_MISC = customKey("group_misc");
    public static final NamespacedKey RECIPE_TYPE_GRIDSTONE = customKey("recipe_type_gridstone");
    public static final NamespacedKey RECIPE_TYPE_SMELTING = customKey("recipe_type_smelting");
    public static final NamespacedKey RECIPE_TYPE_MINE = customKey("recipe_type_mine");
    public static final NamespacedKey RECIPE_TYPE_NULL = customKey("recipe_type_null");


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
