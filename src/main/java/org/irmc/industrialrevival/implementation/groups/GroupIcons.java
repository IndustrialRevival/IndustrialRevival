package org.irmc.industrialrevival.implementation.groups;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

public class GroupIcons {
    public static final ItemStack GROUP_DEBUG = new CustomItemStack(
            Material.BARRIER,
            IndustrialRevival.getInstance().getLanguageManager().getGroupName("debug"));
    public static final ItemStack GROUP_ORE = new CustomItemStack(
            Material.GOLDEN_PICKAXE,
            IndustrialRevival.getInstance().getLanguageManager().getGroupName("ore"));

    public static final ItemStack GROUP_MANUAL_MACHINES = new CustomItemStack(
            Material.BOOK, IndustrialRevival.getInstance().getLanguageManager().getGroupName("manual_machines"));

    public static final ItemStack GROUP_MATERIALS = new CustomItemStack(
            Material.IRON_INGOT,
            IndustrialRevival.getInstance().getLanguageManager().getGroupName("materials"));

    public static final ItemStack GROUP_SMELTING = new CustomItemStack(
            Material.GOLD_INGOT,
            IndustrialRevival.getInstance().getLanguageManager().getGroupName("smelting"));

    public static final ItemStack GROUP_ELECTRIC_MACHINES = new CustomItemStack(
            Material.REDSTONE_BLOCK,
            IndustrialRevival.getInstance().getLanguageManager().getGroupName("electric_machines"));

    public static final ItemStack GROUP_TOOLS = new CustomItemStack(
            Material.DIAMOND_PICKAXE,
            IndustrialRevival.getInstance().getLanguageManager().getGroupName("tools"));

    public static final ItemStack GROUP_ARMORS = new CustomItemStack(
            Material.DIAMOND_CHESTPLATE,
            IndustrialRevival.getInstance().getLanguageManager().getGroupName("armors"));

    public static final ItemStack GROUP_DEFENSE = new CustomItemStack(
            Material.IRON_SWORD,
            IndustrialRevival.getInstance().getLanguageManager().getGroupName("defense"));

    public static final ItemStack GROUP_FOOD = new CustomItemStack(
            Material.BREAD, IndustrialRevival.getInstance().getLanguageManager().getGroupName("food"));

    public static final ItemStack GROUP_MISC = new CustomItemStack(
            Material.PAPER, IndustrialRevival.getInstance().getLanguageManager().getGroupName("misc"));
    public static final ItemStack GROUP_COMPONENTS = new CustomItemStack(
            Material.IRON_INGOT,
            IndustrialRevival.getInstance().getLanguageManager().getGroupName("components"));
    public static final ItemStack GROUP_MULTIBLOCK = new CustomItemStack(
            Material.BRICK,
            IndustrialRevival.getInstance().getLanguageManager().getGroupName("multiblock"));
}
