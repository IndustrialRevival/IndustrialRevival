package org.irmc.industrialrevival.core.implemention.items;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalOreBlock;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.implemention.groups.IRItemGroups;
import org.irmc.industrialrevival.core.implemention.recipes.IRRecipes;

public class IRItems {
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

    // 材料 - 合金与化学物品
    public static final IndustrialRevivalItem LAVA_ALLOY;
    public static final IndustrialRevivalItem ROCK_IRON_ALLOY;
    public static final IndustrialRevivalItem ALUMINIUM_ALLOY;
    public static final IndustrialRevivalItem COPPER_NICKEL_ALLOY;
    public static final IndustrialRevivalItem NICHROM;
    public static final IndustrialRevivalItem SILICON_STEEL;
    public static final IndustrialRevivalItem WOLFRAM_STEEL;
    public static final IndustrialRevivalItem SILICON;
    public static final IndustrialRevivalItem RAW_SILICON;
    public static final IndustrialRevivalItem SILICON_TETRACHLORIDE;
    public static final IndustrialRevivalItem SLAG;
    public static final IndustrialRevivalItem BRASS;
    public static final IndustrialRevivalItem FINE_IRON;
    public static final IndustrialRevivalItem FINE_GOLD;
    public static final IndustrialRevivalItem SLIVER;
    public static final IndustrialRevivalItem NICKEL;
    public static final IndustrialRevivalItem COBALT;
    public static final IndustrialRevivalItem CHROMIUM;
    public static final IndustrialRevivalItem MAGNET;
    public static final IndustrialRevivalItem URANIUM;
    public static final IndustrialRevivalItem ALUMINIUM;
    public static final IndustrialRevivalItem TIN;
    public static final IndustrialRevivalItem MAGNESIUM;
    public static final IndustrialRevivalItem LEAD;
    public static final IndustrialRevivalItem ZINC;
    public static final IndustrialRevivalItem TUNGSTEN;

    static {
        // Metal and Other Materials
        SLIVER = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.SLIVER, RecipeType.VANILLA_SMELTING, IRRecipes.SLIVER);
        NICKEL = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.NICKEL, RecipeType.VANILLA_SMELTING, IRRecipes.NICKEL);
        COBALT = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.COBALT, RecipeType.VANILLA_SMELTING, IRRecipes.COBALT);
        CHROMIUM = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.CHROMIUM, RecipeType.VANILLA_SMELTING, IRRecipes.CHROMIUM);
        MAGNET = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.MAGNET, RecipeType.VANILLA_SMELTING, IRRecipes.MAGNET);
        URANIUM = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.URANIUM, RecipeType.VANILLA_SMELTING, IRRecipes.URANIUM);
        ALUMINIUM = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.ALUMINIUM, RecipeType.VANILLA_SMELTING, IRRecipes.ALUMINIUM);
        TIN = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.TIN, RecipeType.VANILLA_SMELTING, IRRecipes.TIN);
        MAGNESIUM = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.MAGNESIUM, RecipeType.VANILLA_SMELTING, IRRecipes.MAGNESIUM);
        LEAD = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.LEAD, RecipeType.VANILLA_SMELTING, IRRecipes.LEAD);
        ZINC = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.ZINC, RecipeType.VANILLA_SMELTING, IRRecipes.ZINC);
        TUNGSTEN = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.TUNGSTEN, RecipeType.VANILLA_SMELTING, IRRecipes.TUNGSTEN);
        LAVA_ALLOY = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.LAVA_ALLOY, RecipeType.VANILLA_CRAFTING, IRRecipes.LAVA_ALLOY);
        ROCK_IRON_ALLOY = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.ROCK_IRON_ALLOY,
                RecipeType.VANILLA_CRAFTING,
                IRRecipes.ROCK_IRON_ALLOY);
        ALUMINIUM_ALLOY = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.ALUMINIUM_ALLOY,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.ALUMINIUM_ALLOY);
        COPPER_NICKEL_ALLOY = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.COPPER_NICKEL_ALLOY,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.COPPER_NICKEL_ALLOY);
        NICHROM = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.NICHROM, RecipeType.VANILLA_SMELTING, IRRecipes.NICHROM);
        SILICON_STEEL = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.SILICON_STEEL,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.SILICON_STEEL);
        WOLFRAM_STEEL = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.WOLFRAM_STEEL,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.WOLFRAM_STEEL);
        // Haven't Finished Yet...
        SILICON = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.SILICON, RecipeType.NULL, IRRecipes.SILICON);
        RAW_SILICON = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.RAW_SILICON, RecipeType.NULL, IRRecipes.RAW_SILICON);
        SILICON_TETRACHLORIDE = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.SILICON_TETRACHLORIDE,
                RecipeType.NULL,
                IRRecipes.SILICON_TETRACHLORIDE);
        //
        SLAG = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.SLAG, RecipeType.VANILLA_SMELTING, IRRecipes.SLAG);
        BRASS = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.BRASS, RecipeType.VANILLA_SMELTING, IRRecipes.BRASS);
        FINE_IRON = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.FINE_IRON, RecipeType.VANILLA_SMELTING, IRRecipes.FINE_IRON);
        FINE_GOLD = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS, IRItemStacks.FINE_GOLD, RecipeType.VANILLA_SMELTING, IRRecipes.FINE_GOLD);
        // Ores and Other Natural Things
        SLIVER_ORE = new IndustrialRevivalOreBlock(
                IRItemGroups.ORES,
                IRItemStacks.SLIVER_ORE,
                RecipeType.MINE,
                IRRecipes.SLIVER_ORE,
                10f,
                5 * 20,
                IRItemStacks.SLIVER);
        NICKEL_ORE = new IndustrialRevivalOreBlock(
                IRItemGroups.ORES,
                IRItemStacks.NICKEL_ORE,
                RecipeType.MINE,
                IRRecipes.NICKEL_ORE,
                10f,
                5 * 20,
                IRItemStacks.NICKEL);
        COBALT_ORE = new IndustrialRevivalOreBlock(
                IRItemGroups.ORES,
                IRItemStacks.COBALT_ORE,
                RecipeType.MINE,
                IRRecipes.COBALT_ORE,
                10f,
                5 * 20,
                IRItemStacks.COBALT);
        CHROMIUM_ORE = new IndustrialRevivalOreBlock(
                IRItemGroups.ORES,
                IRItemStacks.CHROMIUM_ORE,
                RecipeType.MINE,
                IRRecipes.CHROMIUM_ORE,
                10f,
                5 * 20,
                IRItemStacks.CHROMIUM);
        MAGNET_ORE = new IndustrialRevivalOreBlock(
                IRItemGroups.ORES, 
                IRItemStacks.MAGNET_ORE, 
                RecipeType.MINE, 
                IRRecipes.MAGNET_ORE,
                10f,
                10 * 20,
                IRItemStacks.MAGNET);
        URANIUM_ORE = new IndustrialRevivalOreBlock(
                IRItemGroups.ORES, 
                IRItemStacks.URANIUM_ORE, 
                RecipeType.MINE, 
                IRRecipes.URANIUM_ORE,
                10f,
                20 * 20,
                IRItemStacks.URANIUM);
        ALUMINIUM_ORE = new IndustrialRevivalOreBlock(
                IRItemGroups.ORES, 
                IRItemStacks.ALUMINIUM_ORE, 
                RecipeType.MINE, 
                IRRecipes.ALUMINIUM_ORE,
                10f,
                5 * 20,
                IRItemStacks.ALUMINIUM);
        TIN_ORE = new IndustrialRevivalOreBlock(
                IRItemGroups.ORES, 
                IRItemStacks.TIN_ORE,
                RecipeType.MINE,
                IRRecipes.TIN_ORE,
                10f,
                5 * 20,
                IRItemStacks.TIN);
        MAGNESIUM_ORE = new IndustrialRevivalOreBlock(
                IRItemGroups.ORES, 
                IRItemStacks.MAGNESIUM_ORE,
                RecipeType.MINE,
                IRRecipes.MAGNESIUM_ORE,
                10f,
                5 * 20,
                IRItemStacks.MAGNESIUM);
        LEAD_ORE = new IndustrialRevivalOreBlock(
                IRItemGroups.ORES, 
                IRItemStacks.LEAD_ORE,
                RecipeType.MINE,
                IRRecipes.LEAD_ORE,
                10f,
                5 * 20,
                IRItemStacks.LEAD);
        ZINC_ORE = new IndustrialRevivalOreBlock(
                IRItemGroups.ORES,
                IRItemStacks.ZINC_ORE,
                RecipeType.MINE,
                IRRecipes.ZINC_ORE,
                10f,
                5 * 20,
                IRItemStacks.ZINC);
        TUNGSTEN_ORE = new IndustrialRevivalOreBlock(
                IRItemGroups.ORES, 
                IRItemStacks.TUNGSTEN_ORE,
                RecipeType.MINE,
                IRRecipes.TUNGSTEN_ORE,
                10f,
                5 * 20,
                IRItemStacks.TUNGSTEN);
        MERCURY_ORE = new IndustrialRevivalItem(
                IRItemGroups.ORES, 
                IRItemStacks.MERCURY_ORE,
                RecipeType.MINE,
                IRRecipes.MERCURY_ORE);
        //DO NOT HAVE MERCURY INGOT!!
        SALT_ORE = new IndustrialRevivalOreBlock(
                IRItemGroups.ORES, 
                IRItemStacks.SALT_ORE,
                RecipeType.MINE,
                IRRecipes.SALT_ORE,
                10f,
                5 * 20,
                IRItemStacks.SALT);
        SULFUR = new IndustrialRevivalItem(IRItemGroups.ORES, IRItemStacks.SULFUR, RecipeType.MINE, IRRecipes.SULFUR);
        PETROLEUM = new IndustrialRevivalItem(
                IRItemGroups.ORES, IRItemStacks.PETROLEUM, RecipeType.MINE, IRRecipes.PETROLEUM);
        FLAWED_QUARTZ = new IndustrialRevivalItem(
                IRItemGroups.ORES, IRItemStacks.FLAWED_QUARTZ, RecipeType.MINE, IRRecipes.FLAWED_QUARTZ);
        FLAWLESS_QUARTZ = new IndustrialRevivalItem(
                IRItemGroups.ORES, IRItemStacks.FLAWLESS_QUARTZ, RecipeType.MINE, IRRecipes.FLAWLESS_QUARTZ);
        LIMESTONE = new IndustrialRevivalItem(
                IRItemGroups.ORES, IRItemStacks.LIMESTONE, RecipeType.MINE, IRRecipes.LIMESTONE);
        LIMEWATER = new IndustrialRevivalItem(
                IRItemGroups.ORES, IRItemStacks.LIMEWATER, RecipeType.MINE, IRRecipes.LIMEWATER);
        SALT = new IndustrialRevivalItem(IRItemGroups.ORES, IRItemStacks.SALT, RecipeType.MINE, IRRecipes.SALT);
        MAGIC_CRYSTAL = new IndustrialRevivalItem(
                IRItemGroups.ORES, IRItemStacks.MAGIC_CRYSTAL, RecipeType.MINE, IRRecipes.MAGIC_CRYSTAL);
        CHARGED_STONE = new IndustrialRevivalItem(
                IRItemGroups.ORES, IRItemStacks.CHARGED_STONE, RecipeType.MINE, IRRecipes.CHARGED_STONE);
        CARBON = new IndustrialRevivalItem(IRItemGroups.ORES, IRItemStacks.CARBON, RecipeType.MINE, IRRecipes.CARBON);
        LED = new IndustrialRevivalItem(IRItemGroups.ORES, IRItemStacks.LED, RecipeType.MINE, IRRecipes.LED);
        GAS = new IndustrialRevivalItem(IRItemGroups.ORES, IRItemStacks.GAS, RecipeType.MINE, IRRecipes.GAS);
        BORAX = new IndustrialRevivalItem(IRItemGroups.ORES, IRItemStacks.BORAX, RecipeType.MINE, IRRecipes.BORAX);
        SILICA = new IndustrialRevivalItem(IRItemGroups.ORES, IRItemStacks.SILICA, RecipeType.MINE, IRRecipes.SILICA);
    }

    public static void setup() {
        IndustrialRevival INSTANCE = IndustrialRevival.getInstance();

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
        LAVA_ALLOY.register(INSTANCE);
        ROCK_IRON_ALLOY.register(INSTANCE);
        ALUMINIUM_ALLOY.register(INSTANCE);
        COPPER_NICKEL_ALLOY.register(INSTANCE);
        NICHROM.register(INSTANCE);
        SILICON_STEEL.register(INSTANCE);
        WOLFRAM_STEEL.register(INSTANCE);
        SILICON.register(INSTANCE);
        RAW_SILICON.register(INSTANCE);
        SILICON_TETRACHLORIDE.register(INSTANCE);
        SLAG.register(INSTANCE);
        BRASS.register(INSTANCE);
        FINE_IRON.register(INSTANCE);
        FINE_GOLD.register(INSTANCE);
        SLIVER.register(INSTANCE);
        NICKEL.register(INSTANCE);
        COBALT.register(INSTANCE);
        CHROMIUM.register(INSTANCE);
        MAGNET.register(INSTANCE);
        URANIUM.register(INSTANCE);
        ALUMINIUM.register(INSTANCE);
        TIN.register(INSTANCE);
        MAGNESIUM.register(INSTANCE);
        LEAD.register(INSTANCE);
        ZINC.register(INSTANCE);
        TUNGSTEN.register(INSTANCE);
    }
}
