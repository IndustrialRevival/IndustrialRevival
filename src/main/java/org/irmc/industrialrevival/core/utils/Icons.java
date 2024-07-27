package org.irmc.industrialrevival.core.utils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CustomItemStack;

public class Icons {
    public static final ItemStack GROUP_ORE = new CustomItemStack(
            Material.GOLDEN_PICKAXE,
            "&6矿产资源"
    );

    public static final ItemStack GROUP_MANUAL_MACHINES = new CustomItemStack(
            Material.BOOK,
            "&6工艺机械"
    );

    public static final ItemStack GROUP_MATERIALS = new CustomItemStack(
            Material.IRON_INGOT,
            "&6材料"
    );

    public static final ItemStack GROUP_SMELTING = new CustomItemStack(
            Material.GOLD_INGOT,
            "&6合金"
    );

    public static final ItemStack GROUP_ELECTRIC_MACHINES = new CustomItemStack(
            Material.REDSTONE_BLOCK,
            "&6电力机械"
    );

    public static final ItemStack GROUP_TOOLS = new CustomItemStack(
            Material.DIAMOND_PICKAXE,
            "&6工具"
    );

    public static final ItemStack GROUP_ARMORS = new CustomItemStack(
            Material.DIAMOND_CHESTPLATE,
            "&6护甲"
    );

    public static final ItemStack GROUP_DEFENSE = new CustomItemStack(
            Material.IRON_SWORD,
            "&6防御工事"
    );

    public static final ItemStack GROUP_FOOD = new CustomItemStack(
            Material.BREAD,
            "&6食品"
    );

    public static final ItemStack GROUP_MISC = new CustomItemStack(
            Material.PAPER,
            "&6杂项"
    );


}
