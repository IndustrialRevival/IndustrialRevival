package org.irmc.industrialrevival.core.implemention.items;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.implemention.groups.IRItemGroups;
import org.irmc.industrialrevival.core.implemention.recipes.IRRecipes;

public class IRItems {
    public static final IndustrialRevivalItem TEST_ITEM;
    public static final IndustrialRevivalItem SLIVER_ORE;
    public static final IndustrialRevivalItem NICKEL_ORE;
    public static final IndustrialRevivalItem COBALT_ORE;
    public static final IndustrialRevivalItem CHROMIUM_ORE;
    public static final IndustrialRevivalItem MAGNET_ORE;
    public static final IndustrialRevivalItem URANIUM_ORE;
    public static final IndustrialRevivalItem ALUMINIUM_ORE;
    public static final IndustrialRevivalItem TIN_ORE;
    public static final IndustrialRevivalItem MAGNESIUM_ORE;
    public static final IndustrialRevivalItem LEAD_ORE;
    public static final IndustrialRevivalItem ZINC_ORE;
    public static final IndustrialRevivalItem TUNGSTEN_ORE;
    public static final IndustrialRevivalItem MERCURY_ORE;
    public static final IndustrialRevivalItem SALT_ORE;
    public static final IndustrialRevivalItem SULFUR;
    public static final IndustrialRevivalItem PETROLEUM;
    public static final IndustrialRevivalItem FLAWED_QUARTZ;
    public static final IndustrialRevivalItem FLAWLESS_QUARTZ;
    public static final IndustrialRevivalItem LIMESTONE;
    public static final IndustrialRevivalItem LIMEWATER;
    public static final IndustrialRevivalItem SALT;
    public static final IndustrialRevivalItem MAGIC_CRYSTAL;
    public static final IndustrialRevivalItem CHARGED_STONE;
    public static final IndustrialRevivalItem CARBON;
    public static final IndustrialRevivalItem LED;
    public static final IndustrialRevivalItem GAS;
    public static final IndustrialRevivalItem BORAX;
    public static final IndustrialRevivalItem SILICA;
    static {
        TEST_ITEM = new IndustrialRevivalItem(
                IRItemGroups.MISC,
                IRItemStacks.IR_TEST_ITEM,
                RecipeType.NULL,
                IRRecipes.IR_TEST_ITEM
        );
        SLIVER_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.SLIVER_ORE,
                RecipeType.MINE,
                IRRecipes.SLIVER_ORE
        );
        NICKEL_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.NICKEL_ORE,
                RecipeType.MINE,
                IRRecipes.NICKEL_ORE
        );
        COBALT_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.COBALT_ORE,
                RecipeType.MINE,
                IRRecipes.COBALT_ORE
        );
        CHROMIUM_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.CHROMIUM_ORE,
                RecipeType.MINE,
                IRRecipes.CHROMIUM_ORE
        );
        MAGNET_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.MAGNET_ORE,
                RecipeType.MINE,
                IRRecipes.MAGNET_ORE
        );
        URANIUM_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.URANIUM_ORE,
                RecipeType.MINE,
                IRRecipes.URANIUM_ORE
        );
        ALUMINIUM_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.ALUMINIUM_ORE,
                RecipeType.MINE,
                IRRecipes.ALUMINIUM_ORE
        );
        TIN_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.TIN_ORE,
                RecipeType.MINE,
                IRRecipes.TIN_ORE
        );
        MAGNESIUM_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.MAGNESIUM_ORE,
                RecipeType.MINE,
                IRRecipes.MAGNESIUM_ORE
        );
        LEAD_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.LEAD_ORE,
                RecipeType.MINE,
                IRRecipes.LEAD_ORE
        );
        ZINC_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.ZINC_ORE,
                RecipeType.MINE,
                IRRecipes.ZINC_ORE
        );
        TUNGSTEN_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.TUNGSTEN_ORE,
                RecipeType.MINE,
                IRRecipes.TUNGSTEN_ORE
        );
        MERCURY_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.MERCURY_ORE,
                RecipeType.MINE,
                IRRecipes.MERCURY_ORE
        );

        SALT_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.SALT_ORE,
                RecipeType.MINE,
                IRRecipes.SALT_ORE
        );
        SULFUR = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.SULFUR,
                RecipeType.MINE,
                IRRecipes.SULFUR
        );
        PETROLEUM = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.PETROLEUM,
                RecipeType.MINE,
                IRRecipes.PETROLEUM
        );
        FLAWED_QUARTZ = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.FLAWED_QUARTZ,
                RecipeType.MINE,
                IRRecipes.FLAWED_QUARTZ
        );
        FLAWLESS_QUARTZ = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.FLAWLESS_QUARTZ,
                RecipeType.MINE,
                IRRecipes.FLAWLESS_QUARTZ
        );
        LIMESTONE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.LIMESTONE,
                RecipeType.MINE,
                IRRecipes.LIMESTONE
        );
        LIMEWATER = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.LIMEWATER,
                RecipeType.MINE,
                IRRecipes.LIMEWATER
        );
        SALT = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.SALT,
                RecipeType.MINE,
                IRRecipes.SALT
        );
        MAGIC_CRYSTAL = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.MAGIC_CRYSTAL,
                RecipeType.MINE,
                IRRecipes.MAGIC_CRYSTAL
        );
        CHARGED_STONE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.CHARGED_STONE,
                RecipeType.MINE,
                IRRecipes.CHARGED_STONE
        );
        CARBON = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.CARBON,
                RecipeType.MINE,
                IRRecipes.CARBON
        );
        LED = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.LED,
                RecipeType.MINE,
                IRRecipes.LED
        );
        GAS = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.GAS,
                RecipeType.MINE,
                IRRecipes.GAS
        );
        BORAX = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.BORAX,
                RecipeType.MINE,
                IRRecipes.BORAX
        );
        SILICA = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.SILICA,
                RecipeType.MINE,
                IRRecipes.SILICA
        );
    }
    public static void setup() {
        IndustrialRevival INSTANCE = IndustrialRevival.getInstance();
        TEST_ITEM.register(INSTANCE);
        SLIVER_ORE.register(INSTANCE);
        NICKEL_ORE.register(INSTANCE);
        COBALT_ORE.register(INSTANCE);
        CHROMIUM_ORE.register(INSTANCE);
        MAGNET_ORE.register(INSTANCE);
        URANIUM_ORE.register(INSTANCE);
        ALUMINIUM_ORE.register(INSTANCE);
        TIN_ORE.register(INSTANCE);
        MAGNESIUM_ORE.register(INSTANCE);
        LEAD_ORE.register(INSTANCE);
        ZINC_ORE.register(INSTANCE);
        TUNGSTEN_ORE.register(INSTANCE);
        MERCURY_ORE.register(INSTANCE);
        SALT_ORE.register(INSTANCE);
        SULFUR.register(INSTANCE);
        PETROLEUM.register(INSTANCE);
        FLAWED_QUARTZ.register(INSTANCE);
        FLAWLESS_QUARTZ.register(INSTANCE);
        LIMESTONE.register(INSTANCE);
        LIMEWATER.register(INSTANCE);
        SALT.register(INSTANCE);
        MAGIC_CRYSTAL.register(INSTANCE);
        CHARGED_STONE.register(INSTANCE);
        CARBON.register(INSTANCE);
        LED.register(INSTANCE);
        GAS.register(INSTANCE);
        BORAX.register(INSTANCE);
        SILICA.register(INSTANCE);
    }
}
