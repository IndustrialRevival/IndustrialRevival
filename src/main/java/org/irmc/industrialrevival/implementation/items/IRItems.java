package org.irmc.industrialrevival.implementation.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.ArmorSet;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalOreBlock;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.irmc.industrialrevival.implementation.items.components.Battery;
import org.irmc.pigeonlib.items.ItemUtils;

public class IRItems {
    // armor sets
    public static final ArmorSet SILVER_ARMOR_SET =
            new ArmorSet(KeyUtil.customKey("silver_armor_set"), IRItemGroups.ARMORS);
    public static final ArmorSet TIN_ARMOR_SET = new ArmorSet(KeyUtil.customKey("tin_armor_set"), IRItemGroups.ARMORS);

    public static final IndustrialRevivalItem SILVER_ORE;
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

    // Materials
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

    // Tech components
    public static final Battery BATTERY_AA = new Battery(IRItemGroups.COMPONENTS, IRItemStacks.BATTERY_AA, new ItemStack[]{}, Battery.Type.NiMH, Battery.Size.AA);

    static {
        // Metal and Other Materials
        SLIVER = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.SLIVER,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.SLIVER);
        NICKEL = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.NICKEL,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.NICKEL);
        COBALT = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.COBALT,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.COBALT);
        CHROMIUM = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.CHROMIUM,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.CHROMIUM);
        MAGNET = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.MAGNET,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.MAGNET);
        URANIUM = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.URANIUM,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.URANIUM);
        ALUMINIUM = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.ALUMINIUM,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.ALUMINIUM);
        TIN = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.TIN,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.TIN);
        MAGNESIUM = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.MAGNESIUM,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.MAGNESIUM);
        LEAD = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.LEAD,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.LEAD);
        ZINC = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.ZINC,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.ZINC);
        TUNGSTEN = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.TUNGSTEN,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.TUNGSTEN);
        LAVA_ALLOY = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.LAVA_ALLOY,
                RecipeType.VANILLA_CRAFTING,
                IRRecipes.LAVA_ALLOY);
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
                IRItemGroups.MATERIALS,
                IRItemStacks.NICHROM,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.NICHROM);
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
                IRItemGroups.MATERIALS,
                IRItemStacks.SILICON,
                RecipeType.NULL,
                IRRecipes.SILICON);
        RAW_SILICON = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.RAW_SILICON,
                RecipeType.NULL,
                IRRecipes.RAW_SILICON);
        SILICON_TETRACHLORIDE = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.SILICON_TETRACHLORIDE,
                RecipeType.NULL,
                IRRecipes.SILICON_TETRACHLORIDE);
        //
        SLAG = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.SLAG,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.SLAG);
        BRASS = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.BRASS,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.BRASS);
        FINE_IRON = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.FINE_IRON,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.FINE_IRON);
        FINE_GOLD = new IndustrialRevivalItem(
                IRItemGroups.MATERIALS,
                IRItemStacks.FINE_GOLD,
                RecipeType.VANILLA_SMELTING,
                IRRecipes.FINE_GOLD);
        // Ores and Other Natural Things
        SILVER_ORE = new IndustrialRevivalOreBlock(
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
        // DO NOT HAVE MERCURY INGOT!!
        SALT_ORE = new IndustrialRevivalOreBlock(
                IRItemGroups.ORES,
                IRItemStacks.SALT_ORE,
                RecipeType.MINE,
                IRRecipes.SALT_ORE,
                10f,
                5 * 20,
                IRItemStacks.SALT);
        SULFUR = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.SULFUR,
                RecipeType.MINE,
                IRRecipes.SULFUR);
        PETROLEUM = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.PETROLEUM,
                RecipeType.MINE,
                IRRecipes.PETROLEUM);
        FLAWED_QUARTZ = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.FLAWED_QUARTZ,
                RecipeType.MINE,
                IRRecipes.FLAWED_QUARTZ);
        FLAWLESS_QUARTZ = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.FLAWLESS_QUARTZ,
                RecipeType.MINE,
                IRRecipes.FLAWLESS_QUARTZ);
        LIMESTONE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.LIMESTONE,
                RecipeType.MINE,
                IRRecipes.LIMESTONE);
        LIMEWATER = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.LIMEWATER,
                RecipeType.MINE,
                IRRecipes.LIMEWATER);
        SALT = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.SALT,
                RecipeType.MINE,
                IRRecipes.SALT);
        MAGIC_CRYSTAL = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.MAGIC_CRYSTAL,
                RecipeType.MINE,
                IRRecipes.MAGIC_CRYSTAL);
        CHARGED_STONE = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.CHARGED_STONE,
                RecipeType.MINE,
                IRRecipes.CHARGED_STONE);
        CARBON = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.CARBON,
                RecipeType.MINE,
                IRRecipes.CARBON);
        LED = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.LED,
                RecipeType.MINE,
                IRRecipes.LED);
        GAS = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.GAS,
                RecipeType.MINE,
                IRRecipes.GAS);
        BORAX = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.BORAX,
                RecipeType.MINE,
                IRRecipes.BORAX);
        SILICA = new IndustrialRevivalItem(
                IRItemGroups.ORES,
                IRItemStacks.SILICA,
                RecipeType.MINE,
                IRRecipes.SILICA);
    }

    public static void setup() {
        IndustrialRevival INSTANCE = IndustrialRevival.getInstance();

        SILVER_ORE.register(INSTANCE);
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

    public static class IRItemStacks {
        public static final IndustrialRevivalItemStack SLIVER_ORE;
        public static final IndustrialRevivalItemStack NICKEL_ORE;
        public static final IndustrialRevivalItemStack COBALT_ORE;
        public static final IndustrialRevivalItemStack CHROMIUM_ORE;
        public static final IndustrialRevivalItemStack MAGNET_ORE;
        public static final IndustrialRevivalItemStack URANIUM_ORE;
        public static final IndustrialRevivalItemStack ALUMINIUM_ORE;
        public static final IndustrialRevivalItemStack TIN_ORE;
        public static final IndustrialRevivalItemStack MAGNESIUM_ORE;
        public static final IndustrialRevivalItemStack LEAD_ORE;
        public static final IndustrialRevivalItemStack ZINC_ORE;
        public static final IndustrialRevivalItemStack TUNGSTEN_ORE;
        public static final IndustrialRevivalItemStack MERCURY_ORE;
        public static final IndustrialRevivalItemStack SALT_ORE;
        public static final IndustrialRevivalItemStack SULFUR;
        public static final IndustrialRevivalItemStack PETROLEUM;
        public static final IndustrialRevivalItemStack FLAWED_QUARTZ;
        public static final IndustrialRevivalItemStack FLAWLESS_QUARTZ;
        public static final IndustrialRevivalItemStack LIMESTONE;
        public static final IndustrialRevivalItemStack LIMEWATER;
        public static final IndustrialRevivalItemStack SALT;
        public static final IndustrialRevivalItemStack MAGIC_CRYSTAL;
        public static final IndustrialRevivalItemStack CHARGED_STONE;
        public static final IndustrialRevivalItemStack CARBON;
        public static final IndustrialRevivalItemStack LED;
        public static final IndustrialRevivalItemStack GAS;
        public static final IndustrialRevivalItemStack BORAX;
        public static final IndustrialRevivalItemStack SILICA;
        public static final IndustrialRevivalItemStack LAVA_ALLOY;
        public static final IndustrialRevivalItemStack ROCK_IRON_ALLOY;
        public static final IndustrialRevivalItemStack ALUMINIUM_ALLOY;
        public static final IndustrialRevivalItemStack COPPER_NICKEL_ALLOY;
        public static final IndustrialRevivalItemStack NICHROM;
        public static final IndustrialRevivalItemStack SILICON_STEEL;
        public static final IndustrialRevivalItemStack WOLFRAM_STEEL;
        public static final IndustrialRevivalItemStack SILICON;
        public static final IndustrialRevivalItemStack RAW_SILICON;
        public static final IndustrialRevivalItemStack SILICON_TETRACHLORIDE;
        public static final IndustrialRevivalItemStack SLAG;
        public static final IndustrialRevivalItemStack BRASS;
        public static final IndustrialRevivalItemStack FINE_IRON;
        public static final IndustrialRevivalItemStack FINE_GOLD;
        public static final IndustrialRevivalItemStack SLIVER;
        public static final IndustrialRevivalItemStack NICKEL;
        public static final IndustrialRevivalItemStack COBALT;
        public static final IndustrialRevivalItemStack CHROMIUM;
        public static final IndustrialRevivalItemStack MAGNET;
        public static final IndustrialRevivalItemStack URANIUM;
        public static final IndustrialRevivalItemStack ALUMINIUM;
        public static final IndustrialRevivalItemStack TIN;
        public static final IndustrialRevivalItemStack MAGNESIUM;
        public static final IndustrialRevivalItemStack LEAD;
        public static final IndustrialRevivalItemStack ZINC;
        public static final IndustrialRevivalItemStack TUNGSTEN;
        public static final IndustrialRevivalItemStack BATTERY_AA;
        public static final IndustrialRevivalItemStack NANOANTENNA;
        public static final IndustrialRevivalItemStack RECONFIGURABLE_NANOANTENNA;
        public static final IndustrialRevivalItemStack LIQUID_CRYSTAL_POLYMERS;
        public static final IndustrialRevivalItemStack GRAPHENE;
        public static final IndustrialRevivalItemStack MOLYBDENUM_DISULFIDE;
        public static final IndustrialRevivalItemStack GRAPHENE_OPTICAL_MODULATOR;
        public static final IndustrialRevivalItemStack HOLOGRAPHIC_PLATE;
        public static final IndustrialRevivalItemStack INDIUM_TIN_OXIDE;
        public static final IndustrialRevivalItemStack POLYIMIDE;
        public static final IndustrialRevivalItemStack POLYETHYLENE_TEREPHTHALATE;
        public static final IndustrialRevivalItemStack PHOTONIC;
        public static final IndustrialRevivalItemStack NEGATIVE_INDEX_MATERIAL;
        public static final IndustrialRevivalItemStack FREQUENCY_SELECTIVE_SURFACE;
        public static final IndustrialRevivalItemStack MICRO_ELECTRO_MECHANICAL_SYSTEM;
        public static final IndustrialRevivalItemStack EFFICIENT_SOLAR_MATERIALS;
        public static final IndustrialRevivalItemStack HOLOGRAPHIC_OPTICAL_ELEMENT;
        public static final IndustrialRevivalItemStack DIGITAL_MICROMIRROR_DEVICE;
        public static final IndustrialRevivalItemStack COHERENT_LIGHT_SOURCE;
        public static final IndustrialRevivalItemStack WAVEFRONT_SENSOR;
        public static final IndustrialRevivalItemStack QUANTUM_ENTANGLEMENT_LIGHT_SOURCE;
        public static final IndustrialRevivalItemStack STRUCTURAL_COLOR_HOLOGRAPHY;
        public static final IndustrialRevivalItemStack ELECTRON_BEAM_LITHOGRAPHY_MACHINE;
        public static final IndustrialRevivalItemStack ATOMIC_LAYER_DEPOSITION_MACHINE;
        public static final IndustrialRevivalItemStack POLYMERIAZTION_REACTOR;
        public static final IndustrialRevivalItemStack PRESSURE_CHAMBER;
        public static final IndustrialRevivalItemStack RAW_COPPER_FOIL;
        public static final IndustrialRevivalItemStack POLISHED_COPPER_FOIL;
        public static final IndustrialRevivalItemStack FLAWLESS_COPPER_FOIL;
        public static final IndustrialRevivalItemStack RAW_TIN_FOIL;
        public static final IndustrialRevivalItemStack POLISHED_TIN_FOIL;
        public static final IndustrialRevivalItemStack FLAWLESS_TIN_FOIL;
        public static final IndustrialRevivalItemStack RAW_LEAD_FOIL;
        public static final IndustrialRevivalItemStack POLISHED_LEAD_FOIL;
        public static final IndustrialRevivalItemStack FLAWLESS_LEAD_FOIL;
        public static final IndustrialRevivalItemStack RAW_ZINC_FOIL;
        public static final IndustrialRevivalItemStack POLISHED_ZINC_FOIL;
        public static final IndustrialRevivalItemStack FLAWLESS_ZINC_FOIL;
        public static final IndustrialRevivalItemStack RAW_TUNGSTEN_FOIL;
        public static final IndustrialRevivalItemStack POLISHED_TUNGSTEN_FOIL;
        public static final IndustrialRevivalItemStack FLAWLESS_TUNGSTEN_FOIL;
        public static final IndustrialRevivalItemStack RAW_MAGNESIUM_FOIL;
        public static final IndustrialRevivalItemStack POLISHED_MAGNESIUM_FOIL;
        public static final IndustrialRevivalItemStack FLAWLESS_MAGNESIUM_FOIL;
        public static final IndustrialRevivalItemStack RAW_ALUMINIUM_FOIL;
        public static final IndustrialRevivalItemStack POLISHED_ALUMINIUM_FOIL;
        public static final IndustrialRevivalItemStack FLAWLESS_ALUMINIUM_FOIL;
        public static final IndustrialRevivalItemStack RAW_CHROMIUM_FOIL;
        public static final IndustrialRevivalItemStack POLISHED_CHROMIUM_FOIL;
        public static final IndustrialRevivalItemStack FLAWLESS_CHROMIUM_FOIL;
        public static final IndustrialRevivalItemStack RAW_COBALT_FOIL;
        public static final IndustrialRevivalItemStack POLISHED_COBALT_FOIL;
        public static final IndustrialRevivalItemStack FLAWLESS_COBALT_FOIL;
        public static final IndustrialRevivalItemStack RAW_NICKEL_FOIL;
        public static final IndustrialRevivalItemStack POLISHED_NICKEL_FOIL;
        public static final IndustrialRevivalItemStack FLAWLESS_NICKEL_FOIL;
        public static final IndustrialRevivalItemStack RAW_IRON_FOIL;
        public static final IndustrialRevivalItemStack POLISHED_IRON_FOIL;
        public static final IndustrialRevivalItemStack FLAWLESS_IRON_FOIL;
        public static final IndustrialRevivalItemStack VACUUM_BOTTLE;
        public static final IndustrialRevivalItemStack METHANE_BOTTLE;
        public static final IndustrialRevivalItemStack HYDROGEN;
        public static final IndustrialRevivalItemStack OXYGEN;
        public static final IndustrialRevivalItemStack NITROGEN;
        public static final IndustrialRevivalItemStack HELIUM;
        public static final IndustrialRevivalItemStack LITHIUM;
        public static final IndustrialRevivalItemStack BERYLLIUM;
        public static final IndustrialRevivalItemStack BORON;
        public static final IndustrialRevivalItemStack HEATED_COPPER_FOIL;
        public static final IndustrialRevivalItemStack RAW_GRAPHENE;
        public static final IndustrialRevivalItemStack DEIONIZED_WATER;
        public static final IndustrialRevivalItemStack GRAPHENE_THIN_FILM;
        public static final IndustrialRevivalItemStack POTASSIUM_FERROCYANIDE_SOLUTION;
        public static final IndustrialRevivalItemStack STEAM_BOTTLE;
        public static final IndustrialRevivalItemStack PURE_WATER_BOTTLE;
        public static final IndustrialRevivalItemStack POLYMETHYL_METHACRYLATE;
        public static final IndustrialRevivalItemStack CLEANED_GRAPHENE_THIN_FILM;
        public static final IndustrialRevivalItemStack GRAPHENE_FILM_COATED_WITH_POLYMETHYL_METHACRYLATE;
        public static final IndustrialRevivalItemStack DIRTY_GRAPHENE;
        public static final IndustrialRevivalItemStack AUTO_GRIND;
        public static final IndustrialRevivalItemStack CHARGER;
        public static final IndustrialRevivalItemStack CHEMICAL_VAPOR_DEPOSITION_REACTION_CHAMBER;
        public static final IndustrialRevivalItemStack COLDER;
        public static final IndustrialRevivalItemStack ELECTROLYTIC_MACHINE;
        public static final IndustrialRevivalItemStack HEATER;
        public static final IndustrialRevivalItemStack HOT_PRESS;
        public static final IndustrialRevivalItemStack INDUCTION_COOKER;
        public static final IndustrialRevivalItemStack INDUSTRIAL_FURNACE;
        public static final IndustrialRevivalItemStack POLISHER;
        public static final IndustrialRevivalItemStack PRESSING_MACHINE;
        public static final IndustrialRevivalItemStack PRESSURE_COOKER;
        public static final IndustrialRevivalItemStack REACTION_CHAMBER;
        public static final IndustrialRevivalItemStack SPIN_COATER;
        public static final IndustrialRevivalItemStack VACUUM_EXTRACTOR;
        public static final IndustrialRevivalItemStack WASHER;
        public static final IndustrialRevivalItemStack WELDING_ROD_MACHINE;
        static {
            LAVA_ALLOY = new IndustrialRevivalItemStack("LAVA_ALLOY", Material.MAGMA_BLOCK);
            ROCK_IRON_ALLOY = new IndustrialRevivalItemStack("ROCK_IRON_ALLOY", Material.POLISHED_ANDESITE);
            ALUMINIUM_ALLOY = new IndustrialRevivalItemStack("ALUMINIUM_ALLOY", Material.IRON_INGOT);
            COPPER_NICKEL_ALLOY = new IndustrialRevivalItemStack("COPPER_NICKEL_ALLOY", Material.COPPER_INGOT);
            NICHROM = new IndustrialRevivalItemStack("NICHROM", Material.IRON_INGOT);
            SILICON_STEEL = new IndustrialRevivalItemStack("SILICON_STEEL", Material.IRON_INGOT);
            WOLFRAM_STEEL = new IndustrialRevivalItemStack("WOLFRAM_STEEL", Material.NETHERITE_INGOT);
            SILICON = new IndustrialRevivalItemStack("SILICON", Material.IRON_NUGGET);
            RAW_SILICON = new IndustrialRevivalItemStack("RAW_SILICON", Material.RAW_IRON);
            SILICON_TETRACHLORIDE = new IndustrialRevivalItemStack("SILICON_TETRACHLORIDE", Material.WATER_BUCKET);
            SLAG = new IndustrialRevivalItemStack("SLAG", Material.GUNPOWDER);
            BRASS = new IndustrialRevivalItemStack("BRASS", Material.GOLD_INGOT);
            FINE_IRON = new IndustrialRevivalItemStack("FINE_IRON", Material.IRON_INGOT);
            FINE_GOLD = new IndustrialRevivalItemStack("FINE_GOLD", Material.GOLD_INGOT);
            TUNGSTEN = new IndustrialRevivalItemStack("SLIVER", Material.IRON_INGOT);
            SLIVER = new IndustrialRevivalItemStack("NICKEL", Material.IRON_INGOT);
            NICKEL = new IndustrialRevivalItemStack("COBALT", Material.IRON_INGOT);
            COBALT = new IndustrialRevivalItemStack("CHROMIUM", Material.IRON_INGOT);
            CHROMIUM = new IndustrialRevivalItemStack("MAGNET", Material.IRON_INGOT);
            MAGNET = new IndustrialRevivalItemStack("URANIUM", Material.IRON_INGOT);
            URANIUM = new IndustrialRevivalItemStack("ALUMINIUM", Material.IRON_INGOT);
            ALUMINIUM = new IndustrialRevivalItemStack("TIN", Material.IRON_INGOT);
            TIN = new IndustrialRevivalItemStack("MAGNESIUM", Material.IRON_INGOT);
            MAGNESIUM = new IndustrialRevivalItemStack("LEAD", Material.IRON_INGOT);
            LEAD = new IndustrialRevivalItemStack("ZINC", Material.IRON_INGOT);
            ZINC = new IndustrialRevivalItemStack("TUNGSTEN", Material.IRON_INGOT);
            SLIVER_ORE = new IndustrialRevivalItemStack("SLIVER_ORE", Material.GRAY_CONCRETE_POWDER);
            NICKEL_ORE = new IndustrialRevivalItemStack("NICKEL_ORE", Material.GRAY_CONCRETE_POWDER);
            COBALT_ORE = new IndustrialRevivalItemStack("COBALT_ORE", Material.GRAY_CONCRETE_POWDER);
            CHROMIUM_ORE = new IndustrialRevivalItemStack("CHROMIUM_ORE", Material.GRAY_CONCRETE_POWDER);
            MAGNET_ORE = new IndustrialRevivalItemStack("MAGNET_ORE", Material.GRAY_CONCRETE_POWDER);
            URANIUM_ORE = new IndustrialRevivalItemStack("URANIUM_ORE", Material.GRAY_CONCRETE_POWDER);
            ALUMINIUM_ORE = new IndustrialRevivalItemStack("ALUMINIUM_ORE", Material.GRAY_CONCRETE_POWDER);
            TIN_ORE = new IndustrialRevivalItemStack("TIN_ORE", Material.GRAY_CONCRETE_POWDER);
            MAGNESIUM_ORE = new IndustrialRevivalItemStack("MAGNESIUM_ORE", Material.GRAY_CONCRETE_POWDER);
            LEAD_ORE = new IndustrialRevivalItemStack("LEAD_ORE", Material.GRAY_CONCRETE_POWDER);
            ZINC_ORE = new IndustrialRevivalItemStack("ZINC_ORE", Material.GRAY_CONCRETE_POWDER);
            TUNGSTEN_ORE = new IndustrialRevivalItemStack("TUNGSTEN_ORE", Material.BLACK_CONCRETE_POWDER);
            MERCURY_ORE = new IndustrialRevivalItemStack("MERCURY_ORE", Material.LIGHT_GRAY_CONCRETE_POWDER);
            SALT_ORE = new IndustrialRevivalItemStack("SALT_ORE", Material.WHITE_CONCRETE_POWDER);
            SULFUR = new IndustrialRevivalItemStack("SULFUR", Material.YELLOW_CONCRETE);
            PETROLEUM = new IndustrialRevivalItemStack("PETROLEUM", Material.BLACK_CONCRETE);
            FLAWED_QUARTZ = new IndustrialRevivalItemStack("FLAWED_QUARTZ", Material.QUARTZ);
            FLAWLESS_QUARTZ = new IndustrialRevivalItemStack("FLAWLESS_QUARTZ", Material.QUARTZ);
            LIMESTONE = new IndustrialRevivalItemStack("LIMESTONE", Material.GRAY_CONCRETE);
            LIMEWATER = new IndustrialRevivalItemStack("LIMEWATER", Material.BUCKET);
            SALT = new IndustrialRevivalItemStack("SALT", Material.SUGAR);
            MAGIC_CRYSTAL = new IndustrialRevivalItemStack("MAGIC_CRYSTAL", Material.DIAMOND);
            CHARGED_STONE = new IndustrialRevivalItemStack("CHARGED_STONE", Material.EMERALD);
            CARBON = new IndustrialRevivalItemStack("CARBON", Material.COAL_BLOCK);
            LED = new IndustrialRevivalItemStack("LED", Material.WHITE_CONCRETE);
            GAS = new IndustrialRevivalItemStack("GAS", Material.GLASS_BOTTLE);
            BORAX = new IndustrialRevivalItemStack("BORAX", Material.QUARTZ);
            SILICA = new IndustrialRevivalItemStack("SILICA", Material.FIREWORK_STAR);
            BATTERY_AA = new IndustrialRevivalItemStack("BATTERY_AA", Material.IRON_INGOT);
            NANOANTENNA = new IndustrialRevivalItemStack("NANOANTENNA", Material.COAL);
            RECONFIGURABLE_NANOANTENNA = new IndustrialRevivalItemStack("RECONFIGURABLE_NANOANTENNA", Material.COAL);
            LIQUID_CRYSTAL_POLYMERS = new IndustrialRevivalItemStack("LIQUID_CRYSTAL_POLYMERS", Material.WATER_BUCKET);
            GRAPHENE = new IndustrialRevivalItemStack("GRAPHENE", Material.BLACK_CONCRETE_POWDER);
            MOLYBDENUM_DISULFIDE = new IndustrialRevivalItemStack("MOLYBDENUM_DISULFIDE", Material.WHITE_CONCRETE_POWDER);
            GRAPHENE_OPTICAL_MODULATOR = new IndustrialRevivalItemStack("GRAPHENE_OPTICAL_MODULATOR", Material.BLACK_CONCRETE_POWDER);
            HOLOGRAPHIC_PLATE = new IndustrialRevivalItemStack("HOLOGRAPHIC_PLATE", Material.IRON_INGOT);
            INDIUM_TIN_OXIDE = new IndustrialRevivalItemStack("INDIUM_TIN_OXIDE", Material.IRON_INGOT);
            POLYIMIDE = new IndustrialRevivalItemStack("POLYIMIDE", Material.IRON_INGOT);
            POLYETHYLENE_TEREPHTHALATE = new IndustrialRevivalItemStack("POLYETHYLENE_TEREPHTHALATE", Material.IRON_INGOT);
            PHOTONIC = new IndustrialRevivalItemStack("PHOTONIC", Material.IRON_INGOT);
            NEGATIVE_INDEX_MATERIAL = new IndustrialRevivalItemStack("NEGATIVE_INDEX_MATERIAL", Material.BLACK_STAINED_GLASS_PANE);
            FREQUENCY_SELECTIVE_SURFACE = new IndustrialRevivalItemStack("FREQUENCY_SELECTIVE_SURFACE", Material.WHITE_CARPET);
            MICRO_ELECTRO_MECHANICAL_SYSTEM = new IndustrialRevivalItemStack("MICRO_ELECTRO_MECHANICAL_SYSTEM", Material.SKELETON_SKULL);
            EFFICIENT_SOLAR_MATERIALS = new IndustrialRevivalItemStack("EFFICIENT_SOLAR_MATERIALS", Material.DAYLIGHT_DETECTOR);
            HOLOGRAPHIC_OPTICAL_ELEMENT = new IndustrialRevivalItemStack("HOLOGRAPHIC_OPTICAL_ELEMENT", Material.IRON_INGOT);
            DIGITAL_MICROMIRROR_DEVICE = new IndustrialRevivalItemStack("DIGITAL_MICROMIRROR_DEVICE", Material.CHAIN);
            COHERENT_LIGHT_SOURCE = new IndustrialRevivalItemStack("COHERENT_LIGHT_SOURCE", Material.LIGHT);
            WAVEFRONT_SENSOR = new IndustrialRevivalItemStack("WAVEFRONT_SENSOR", Material.SCULK_SENSOR);
            QUANTUM_ENTANGLEMENT_LIGHT_SOURCE = new IndustrialRevivalItemStack("QUANTUM_ENTANGLEMENT_LIGHT_SOURCE", Material.SHROOMLIGHT);
            STRUCTURAL_COLOR_HOLOGRAPHY = new IndustrialRevivalItemStack("STRUCTURAL_COLOR_HOLOGRAPHY", Material.IRON_INGOT);
            ELECTRON_BEAM_LITHOGRAPHY_MACHINE = new IndustrialRevivalItemStack("ELECTRON_BEAM_LITHOGRAPHY_MACHINE", Material.IRON_BLOCK);
            ATOMIC_LAYER_DEPOSITION_MACHINE = new IndustrialRevivalItemStack("ATOMIC_LAYER_DEPOSITION_MACHINE", Material.BLUE_GLAZED_TERRACOTTA);
            POLYMERIAZTION_REACTOR = new IndustrialRevivalItemStack("POLYMERIAZTION_REACTOR", Material.RED_CONCRETE);
            PRESSURE_CHAMBER = new IndustrialRevivalItemStack("PRESSURE_CHAMBER", Material.GRAY_GLAZED_TERRACOTTA);
            RAW_COPPER_FOIL = new IndustrialRevivalItemStack("RAW_COPPER_FOIL", Material.PAPER);
            POLISHED_COPPER_FOIL = new IndustrialRevivalItemStack("POLISHED_COPPER_FOIL", Material.PAPER);
            FLAWLESS_COPPER_FOIL = new IndustrialRevivalItemStack("FLAWLESS_COPPER_FOIL", Material.PAPER);
            RAW_TIN_FOIL = new IndustrialRevivalItemStack("RAW_TIN_FOIL", Material.PAPER);
            POLISHED_TIN_FOIL = new IndustrialRevivalItemStack("POLISHED_TIN_FOIL", Material.PAPER);
            FLAWLESS_TIN_FOIL = new IndustrialRevivalItemStack("FLAWLESS_TIN_FOIL", Material.PAPER);
            RAW_ZINC_FOIL = new IndustrialRevivalItemStack("RAW_ZINC_FOIL", Material.PAPER);
            POLISHED_ZINC_FOIL = new IndustrialRevivalItemStack("POLISHED_ZINC_FOIL", Material.PAPER);
            FLAWLESS_ZINC_FOIL = new IndustrialRevivalItemStack("FLAWLESS_ZINC_FOIL", Material.PAPER);
            RAW_LEAD_FOIL = new IndustrialRevivalItemStack("RAW_LEAD_FOIL", Material.PAPER);
            POLISHED_LEAD_FOIL = new IndustrialRevivalItemStack("POLISHED_LEAD_FOIL", Material.PAPER);
            FLAWLESS_LEAD_FOIL = new IndustrialRevivalItemStack("FLAWLESS_LEAD_FOIL", Material.PAPER);
            RAW_TUNGSTEN_FOIL = new IndustrialRevivalItemStack("RAW_TUNGSTEN_FOIL", Material.PAPER);
            POLISHED_TUNGSTEN_FOIL = new IndustrialRevivalItemStack("POLISHED_TUNGSTEN_FOIL", Material.PAPER);
            FLAWLESS_TUNGSTEN_FOIL = new IndustrialRevivalItemStack("FLAWLESS_TUNGSTEN_FOIL", Material.PAPER);
            RAW_MAGNESIUM_FOIL = new IndustrialRevivalItemStack("RAW_MAGNESIUM_FOIL", Material.PAPER);
            POLISHED_MAGNESIUM_FOIL = new IndustrialRevivalItemStack("POLISHED_MAGNESIUM_FOIL", Material.PAPER);
            FLAWLESS_MAGNESIUM_FOIL = new IndustrialRevivalItemStack("FLAWLESS_MAGNESIUM_FOIL", Material.PAPER);
            RAW_ALUMINIUM_FOIL = new IndustrialRevivalItemStack("RAW_ALUMINIUM_FOIL", Material.PAPER);
            POLISHED_ALUMINIUM_FOIL = new IndustrialRevivalItemStack("POLISHED_ALUMINIUM_FOIL", Material.PAPER);
            FLAWLESS_ALUMINIUM_FOIL = new IndustrialRevivalItemStack("FLAWLESS_ALUMINIUM_FOIL", Material.PAPER);
            RAW_CHROMIUM_FOIL = new IndustrialRevivalItemStack("RAW_CHROMIUM_FOIL", Material.PAPER);
            POLISHED_CHROMIUM_FOIL = new IndustrialRevivalItemStack("POLISHED_CHROMIUM_FOIL", Material.PAPER);
            FLAWLESS_CHROMIUM_FOIL = new IndustrialRevivalItemStack("FLAWLESS_CHROMIUM_FOIL", Material.PAPER);
            RAW_COBALT_FOIL = new IndustrialRevivalItemStack("RAW_COBALT_FOIL", Material.PAPER);
            POLISHED_COBALT_FOIL = new IndustrialRevivalItemStack("POLISHED_COBALT_FOIL", Material.PAPER);
            FLAWLESS_COBALT_FOIL = new IndustrialRevivalItemStack("FLAWLESS_COBALT_FOIL", Material.PAPER);
            RAW_NICKEL_FOIL = new IndustrialRevivalItemStack("RAW_NICKEL_FOIL", Material.PAPER);
            POLISHED_NICKEL_FOIL = new IndustrialRevivalItemStack("POLISHED_NICKEL_FOIL", Material.PAPER);
            FLAWLESS_NICKEL_FOIL = new IndustrialRevivalItemStack("FLAWLESS_NICKEL_FOIL", Material.PAPER);
            RAW_IRON_FOIL = new IndustrialRevivalItemStack("RAW_IRON_FOIL", Material.PAPER);
            POLISHED_IRON_FOIL = new IndustrialRevivalItemStack("POLISHED_IRON_FOIL", Material.PAPER);
            FLAWLESS_IRON_FOIL = new IndustrialRevivalItemStack("FLAWLESS_IRON_FOIL", Material.PAPER);
            VACUUM_BOTTLE = new IndustrialRevivalItemStack("VACUUM_BOTTLE", Material.GLASS_BOTTLE);
            METHANE_BOTTLE = new IndustrialRevivalItemStack("METHANE_BOTTLE", Material.GLASS_BOTTLE);
            HEATED_COPPER_FOIL = new IndustrialRevivalItemStack("HEATED_COPPER_FOIL", Material.PAPER);
            HYDROGEN = new IndustrialRevivalItemStack("HYDROGEN", Material.GLASS_BOTTLE);
            OXYGEN = new IndustrialRevivalItemStack("OXYGEN", Material.GLASS_BOTTLE);
            NITROGEN = new IndustrialRevivalItemStack("NITROGEN", Material.GLASS_BOTTLE);
            HELIUM = new IndustrialRevivalItemStack("HELIUM", Material.GLASS_BOTTLE);
            LITHIUM = new IndustrialRevivalItemStack("LITHIUM", Material.WHITE_DYE);
            BERYLLIUM = new IndustrialRevivalItemStack("BERYLLIUM", Material.RAW_IRON);
            BORON = new IndustrialRevivalItemStack("BORON", Material.QUARTZ);
            RAW_GRAPHENE = new IndustrialRevivalItemStack("RAW_GRAPHENE", Material.BLACK_WOOL);
            DEIONIZED_WATER = new IndustrialRevivalItemStack("DEIONIZED_WATER", Material.WATER_BUCKET);
            GRAPHENE_THIN_FILM = new IndustrialRevivalItemStack("GRAPHENE_THIN_FILM", Material.BLACK_BANNER);
            POTASSIUM_FERROCYANIDE_SOLUTION = new IndustrialRevivalItemStack("POTASSIUM_FERROCYANIDE_SOLUTION", Material.GLASS_BOTTLE);
            STEAM_BOTTLE = new IndustrialRevivalItemStack("STEAM_BOTTLE", Material.GLASS_BOTTLE);
            PURE_WATER_BOTTLE = new IndustrialRevivalItemStack("PURE_WATER_BOTTLE", Material.WATER_BUCKET);
            POLYMETHYL_METHACRYLATE = new IndustrialRevivalItemStack("POLYMETHYL_METHACRYLATE", Material.GRAY_DYE);
            CLEANED_GRAPHENE_THIN_FILM = new IndustrialRevivalItemStack("CLEANED_GRAPHENE_THIN_FILM", Material.BLACK_CARPET);
            GRAPHENE_FILM_COATED_WITH_POLYMETHYL_METHACRYLATE = new IndustrialRevivalItemStack("GRAPHENE_FILM_COATED_WITH_POLYMETHYL_METHACRYLATE", Material.GRAY_CARPET);
            DIRTY_GRAPHENE = new IndustrialRevivalItemStack("DIRTY_GRAPHENE", Material.BLACK_DYE);
            AUTO_GRIND = new IndustrialRevivalItemStack("AUTO_GRIND", Material.DISPENSER);
            CHARGER = new IndustrialRevivalItemStack("CHARGER", Material.CRAFTING_TABLE);
            CHEMICAL_VAPOR_DEPOSITION_REACTION_CHAMBER = new IndustrialRevivalItemStack("CHEMICAL_VAPOR_DEPOSITION_REACTION_CHAMBER", Material.PINK_STAINED_GLASS);
            COLDER = new IndustrialRevivalItemStack("COLDER", Material.LIGHT_BLUE_STAINED_GLASS);
            ELECTROLYTIC_MACHINE = new IndustrialRevivalItemStack("ELECTROLYTIC_MACHINE", Material.GRINDSTONE);
            HEATER = new IndustrialRevivalItemStack("HEATER", Material.RED_STAINED_GLASS);
            HOT_PRESS = new IndustrialRevivalItemStack("HOT_PRESS", Material.PISTON);
            INDUCTION_COOKER =   new IndustrialRevivalItemStack("INDUCTION_COOKER", Material.BLAST_FURNACE);
            INDUSTRIAL_FURNACE =   new IndustrialRevivalItemStack("INDUSTRIAL_FURNACE", Material.FURNACE);
            POLISHER = new IndustrialRevivalItemStack("POLISHER", Material.POLISHED_DIORITE);
            PRESSING_MACHINE = new IndustrialRevivalItemStack("PRESSING_MACHINE", Material.NOTE_BLOCK);
            PRESSURE_COOKER = new IndustrialRevivalItemStack("PRESSURE_COOKER", Material.SMOKER);
            REACTION_CHAMBER = new IndustrialRevivalItemStack("REACTION_CHAMBER", Material.GLASS);
            SPIN_COATER = new IndustrialRevivalItemStack("SPIN_COATER", Material.LOOM);
            VACUUM_EXTRACTOR = new IndustrialRevivalItemStack("VACUUM_EXTRACTOR", Material.BLACK_STAINED_GLASS);
            WASHER = new IndustrialRevivalItemStack("WASHER", Material.CAULDRON);
            WELDING_ROD_MACHINE = new IndustrialRevivalItemStack("WELDING_ROD_MACHINE", Material.STONECUTTER);
        }
    }

    public static class IRRecipes {
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
            null,
            null,
            null,
            null,
            new CustomItemStack(
                    Material.PAPER,
                    IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(null, "recipe.slag")),
            null,
            null,
            null,
            null
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

        public static final ItemStack[] RECONFIGURABLE_NANOANTENNA =
                new ItemStack[] {IRItemStacks.GRAPHENE, null, null, null, null, null, null, null, null};
    }
}
