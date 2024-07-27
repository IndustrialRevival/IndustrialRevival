package org.irmc.industrialrevival.core.implemention.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.core.implemention.items.IRItemStacks;

public class IRRecipes {
    public static final ItemStack[] EMPTY_RECIPE = new ItemStack[] {
            null, null, null,
            null, null, null,
            null, null, null
    };
    public static final ItemStack[] IR_TEST_ITEM = EMPTY_RECIPE;
    public static final ItemStack[] SLIVER_ORE = EMPTY_RECIPE;
    public static final ItemStack[] NICKEL_ORE = EMPTY_RECIPE;
    public static final ItemStack[] COBALT_ORE = EMPTY_RECIPE;
    public static final ItemStack[] CHROMIUM_ORE = EMPTY_RECIPE;
    public static final ItemStack[] MAGNET_ORE = EMPTY_RECIPE;
    public static final ItemStack[] URANIUM_ORE = EMPTY_RECIPE;
    public static final ItemStack[] ALUMINIUM_ORE = EMPTY_RECIPE;
    public static final ItemStack[] TIN_ORE = EMPTY_RECIPE;
    public static final ItemStack[] MAGNESIUM_ORE = EMPTY_RECIPE;
    public static final ItemStack[] LEAD_ORE = EMPTY_RECIPE;
    public static final ItemStack[] ZINC_ORE = EMPTY_RECIPE;
    public static final ItemStack[] TUNGSTEN_ORE = EMPTY_RECIPE;
    public static final ItemStack[] MERCURY_ORE = EMPTY_RECIPE;
    public static final ItemStack[] SALT_ORE = EMPTY_RECIPE;
    public static final ItemStack[] SULFUR = new ItemStack[] {
            null, null, null,
            null, new CustomItemStack(Material.LAPIS_ORE), null,
            null, null, null
    };
    public static final ItemStack[] PETROLEUM = new ItemStack[] {
            null, null, null,
            null, new CustomItemStack(Material.WATER), null,
            null, null, null
    };
    public static final ItemStack[] FLAWED_QUARTZ = new ItemStack[] {
            null, null, null,
            null, new CustomItemStack(Material.NETHER_QUARTZ_ORE), null,
            null, null, null
    };
    public static final ItemStack[] FLAWLESS_QUARTZ = new ItemStack[] {
            IRItemStacks.FLAWED_QUARTZ, null, null,
            null, null, null,
            null, null, null
    };
    public static final ItemStack[] LIMESTONE = new ItemStack[] {
            null, null, null,
            new CustomItemStack(Material.ANDESITE), new CustomItemStack(Material.GRANITE), new CustomItemStack(Material.DIORITE),
            null, null, null
    };
    public static final ItemStack[] LIMEWATER = new ItemStack[] {
            IRItemStacks.LIMEWATER, new CustomItemStack(Material.WATER_BUCKET), null,
            null, null, null,
            null, null, null
    };
    public static final ItemStack[] SALT = new ItemStack[] {
            IRItemStacks.SALT_ORE, null, null,
            null, null, null,
            null, null, null
    };
    public static final ItemStack[] MAGIC_CRYSTAL = new ItemStack[] {
            null, null, null,
            null, new CustomItemStack(Material.BUDDING_AMETHYST), null,
            null, null, null
    };
    public static final ItemStack[] CHARGED_STONE = new ItemStack[] {
            null, null, null,
            null, new CustomItemStack(Material.REDSTONE_ORE), null,
            null, null, null
    };
    public static final ItemStack[] CARBON = new ItemStack[]{
            null, null, null,
            null, new CustomItemStack(Material.AMETHYST_CLUSTER), null,
            null, null, null
    };
    public static final ItemStack[] LED = EMPTY_RECIPE;
    public static final ItemStack[] GAS = EMPTY_RECIPE;
    public static final ItemStack[] BORAX = EMPTY_RECIPE;
    public static final ItemStack[] SILICA = new ItemStack[] {
            new CustomItemStack(Material.SAND), null, null,
            null, null, null,
            null, null, null
    };
}
