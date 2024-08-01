package org.irmc.industrialrevival.core.implemention.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.implemention.items.IRItemStacks;
import org.irmc.industrialrevival.core.utils.ItemUtils;

public class IRRecipes {
    public static final ItemStack[] EMPTY_RECIPE = new ItemStack[] {
        null, null, null,
        null, null, null,
        null, null, null
    };

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

    public static final ItemStack[] FLAWLESS_QUARTZ =
            new ItemStack[] {IRItemStacks.FLAWED_QUARTZ, null, null, null, null, null, null, null, null};

    public static final ItemStack[] LIMESTONE = new ItemStack[] {
        null,
        null,
        null,
        new CustomItemStack(Material.ANDESITE),
        new CustomItemStack(Material.GRANITE),
        new CustomItemStack(Material.DIORITE),
        null,
        null,
        null
    };

    public static final ItemStack[] LIMEWATER = new ItemStack[] {
        IRItemStacks.LIMEWATER, new CustomItemStack(Material.WATER_BUCKET), null, null, null, null, null, null, null
    };

    public static final ItemStack[] SALT =
            new ItemStack[] {IRItemStacks.SALT_ORE, null, null, null, null, null, null, null, null};

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

    public static final ItemStack[] CARBON = new ItemStack[] {
        null, null, null,
        null, new CustomItemStack(Material.AMETHYST_CLUSTER), null,
        null, null, null
    };

    public static final ItemStack[] LED = EMPTY_RECIPE;
    public static final ItemStack[] GAS = EMPTY_RECIPE;
    public static final ItemStack[] BORAX = EMPTY_RECIPE;

    public static final ItemStack[] SILICA =
            new ItemStack[] {new CustomItemStack(Material.SAND), null, null, null, null, null, null, null, null};
    public static final ItemStack[] LAVA_ALLOY = new ItemStack[] {
        new ItemStack(Material.MAGMA_BLOCK),
        new ItemStack(Material.IRON_NUGGET),
        null,
        new ItemStack(Material.IRON_NUGGET),
        new ItemStack(Material.MAGMA_BLOCK),
        null,
        null,
        null,
        null
    };
    public static final ItemStack[] ROCK_IRON_ALLOY = new ItemStack[] {
        new ItemStack(Material.STONE),
        new ItemStack(Material.IRON_NUGGET),
        null,
        new ItemStack(Material.IRON_NUGGET),
        new ItemStack(Material.STONE),
        null,
        null,
        null,
        null
    };
    public static final ItemStack[] ALUMINIUM_ALLOY = new ItemStack[] {
        IRItemStacks.ALUMINIUM, IRItemStacks.FINE_IRON, IRItemStacks.MAGNESIUM, null, null, null, null, null, null
    }; // Output: Item x 3
    public static final ItemStack[] COPPER_NICKEL_ALLOY = new ItemStack[] {
        ItemUtils.cloneItem(IRItemStacks.NICKEL, 2),
        new ItemStack(Material.COPPER_INGOT),
        null,
        null,
        null,
        null,
        null,
        null,
        null
    }; // Output: Item x 3
    public static final ItemStack[] NICHROM = new ItemStack[] {
        ItemUtils.cloneItem(IRItemStacks.NICKEL, 4), IRItemStacks.CHROMIUM, null, null, null, null, null, null, null
    }; // Output: Item x 5
    public static final ItemStack[] SILICON_STEEL = new ItemStack[] {
        ItemUtils.cloneItem(IRItemStacks.FINE_IRON, 2), IRItemStacks.SILICON, null, null, null, null, null, null, null
    }; // Output: Item x 2
    public static final ItemStack[] WOLFRAM_STEEL = new ItemStack[] {
        ItemUtils.cloneItem(IRItemStacks.CHROMIUM, 3),
        ItemUtils.cloneItem(IRItemStacks.NICKEL, 3),
        ItemUtils.cloneItem(IRItemStacks.COBALT, 3),
        IRItemStacks.TUNGSTEN,
        null,
        null,
        null,
        null,
        null
    }; // Output: Item x 10
    public static final ItemStack[] SILICON = EMPTY_RECIPE;
    public static final ItemStack[] RAW_SILICON = EMPTY_RECIPE;
    public static final ItemStack[] SILICON_TETRACHLORIDE = EMPTY_RECIPE;
    // 二氧化硅 --超热炉(碳晶+硼砂)--> 粗硅 --超热炉(4氯气)--> 四氯化硅 --电炉(2氢气+水)--> 硅 + 4HCl
    public static final ItemStack[] SLAG = new ItemStack[] {
        null, null, null,
        null,
                new CustomItemStack(
                        Material.PAPER,
                        IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(null, "recipe.slag")),
                null,
        null, null, null
    };
    public static final ItemStack[] BRASS = new ItemStack[] {
        ItemUtils.cloneItem(IRItemStacks.ZINC, 2),
        new ItemStack(Material.COPPER_INGOT),
        null,
        null,
        null,
        null,
        null,
        null,
        null
    }; // Output: Item x 3
    public static final ItemStack[] FINE_IRON =
            new ItemStack[] {new ItemStack(Material.IRON_INGOT, 4), null, null, null, null, null, null, null, null};
    public static final ItemStack[] FINE_GOLD =
            new ItemStack[] {new ItemStack(Material.GOLD_INGOT, 8), null, null, null, null, null, null, null, null};
    public static final ItemStack[] TUNGSTEN =
            new ItemStack[] {IRItemStacks.TUNGSTEN_ORE, null, null, null, null, null, null, null, null};
    public static final ItemStack[] SLIVER =
            new ItemStack[] {IRItemStacks.SLIVER_ORE, null, null, null, null, null, null, null, null};
    public static final ItemStack[] NICKEL =
            new ItemStack[] {IRItemStacks.NICKEL_ORE, null, null, null, null, null, null, null, null};
    public static final ItemStack[] COBALT =
            new ItemStack[] {IRItemStacks.COBALT_ORE, null, null, null, null, null, null, null, null};
    public static final ItemStack[] CHROMIUM =
            new ItemStack[] {IRItemStacks.CHROMIUM_ORE, null, null, null, null, null, null, null, null};
    public static final ItemStack[] MAGNET =
            new ItemStack[] {IRItemStacks.MAGNET_ORE, null, null, null, null, null, null, null, null};
    public static final ItemStack[] URANIUM =
            new ItemStack[] {IRItemStacks.URANIUM_ORE, null, null, null, null, null, null, null, null};
    public static final ItemStack[] ALUMINIUM =
            new ItemStack[] {IRItemStacks.ALUMINIUM_ORE, null, null, null, null, null, null, null, null};
    public static final ItemStack[] TIN =
            new ItemStack[] {IRItemStacks.TIN_ORE, null, null, null, null, null, null, null, null};
    public static final ItemStack[] MAGNESIUM =
            new ItemStack[] {IRItemStacks.MAGNESIUM_ORE, null, null, null, null, null, null, null, null};
    public static final ItemStack[] LEAD =
            new ItemStack[] {IRItemStacks.LEAD_ORE, null, null, null, null, null, null, null, null};
    public static final ItemStack[] ZINC =
            new ItemStack[] {IRItemStacks.ZINC_ORE, null, null, null, null, null, null, null, null};
}
