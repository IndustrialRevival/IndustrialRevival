package org.irmc.industrialrevival.implementation.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.melt.OreMeltedType;
import org.irmc.industrialrevival.api.elements.tinker.TinkerTypes;
import org.irmc.industrialrevival.api.items.TinkerModelItem;
import org.irmc.industrialrevival.api.items.TinkerProductItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.machines.BasicMachine;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipe;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.api.recipes.methods.CraftMethod;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.irmc.industrialrevival.implementation.items.chemistry.ElectrolyticMachine;
import org.irmc.industrialrevival.implementation.items.chemistry.OperationTable;
import org.irmc.industrialrevival.implementation.items.debug.Container;
import org.irmc.industrialrevival.implementation.items.debug.DebugHead;
import org.irmc.industrialrevival.implementation.items.debug.Debugger;
import org.irmc.industrialrevival.implementation.items.debug.Empty;
import org.irmc.industrialrevival.implementation.items.register.ChemicalCompoundSetup;
import org.irmc.industrialrevival.implementation.items.register.ElementOreDusts;
import org.irmc.industrialrevival.implementation.items.register.ElementOres;
import org.irmc.industrialrevival.implementation.items.register.GasJars;
import org.irmc.industrialrevival.implementation.multiblock.BlastFurnace;
import org.irmc.industrialrevival.implementation.multiblock.BlastSmeltery;
import org.irmc.industrialrevival.implementation.multiblock.CokeOven;
import org.irmc.industrialrevival.implementation.multiblock.EarthFurnace;
import org.irmc.industrialrevival.implementation.multiblock.ProfessionalLaboratory;
import org.irmc.industrialrevival.implementation.multiblock.ResearchTable;
import org.irmc.industrialrevival.implementation.multiblock.WoodenPress;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.jetbrains.annotations.NotNull;

/**
 * This class is responsible for registering all the items of Industrial Revival.
 * <p>
 * Here's an example of how to build an item:
 *
 * <p>
 * If an item is a normal item, it should be built as:
 * <pre>{@code
 * public static final MySpecialItem my_special_item = new MySpecialItem()
 *     .addon(my_addon)                          // my_addon -> {@link IndustrialRevivalAddon }
 *     .itemGroup(my_item_group)                 // my_item_group -> {@link ItemGroup }
 *     .icon(my_item_stack)                      // my_item_stack -> {@link ItemStack }
 *     .recipe(item -> new CraftMethod(          // recipe method, see more {@link CraftMethod}
 *              RecipeType.NULL,                 // recipe type, can be any of the types in {@link RecipeType } or a custom recipe type
 *              new ItemStack[] {},              // input items, can be empty but CANNOT be null
 *              item                             // item is a pure itemstack which be used in {@code setIcon }
 *          ))
 *     .cast(MySpecialItem.class)                // this method can be used to return the instance of {@code MySpecialItem }
 *     .doOtherThingsInMySpecialItem();          // this method should return the instance of {@code MySpecialItem }
 * }</pre>
 * <p>
 * If an item is a multiblock, it should be built as:
 * <pre>{@code
 * public static final MyMultiblock my_multiblock = new MyMultiblock(namespacedKey) // namespacedKey is the key of the multiblock, see more {@link KeyUtil}
 *     .addon(my_addon)                          // my_addon -> {@link IndustrialRevivalAddon }
 *     .itemGroup(my_item_group)                 // my_item_group -> {@link ItemGroup }
 *     .icon(my_item_stack)                      // my_item_stack -> {@link ItemStack }
 *     .recipe(item -> new CraftMethod(          // recipe method, see more {@link CraftMethod}
 *              RecipeType.MULTIBLOCK,           // recipe type, can only be MULTIBLOCK
 *              new ItemStack[] {},              // input items, can be anything but CANNOT be null
 *              item                             // item is a pure itemstack which be used in {@code setIcon }
 *          ))
 *     .cast(MyMultiblock.class)                 // this method can be used to return the instance of {@code MyMultiblock }
 *     .doOtherThingsInMyMultiblock();           // this method should return the instance of {@code MyMultiblock }
 * }</pre>
 * <p>
 * Then items should be registered as:
 * <pre>{@code
 * my_item.register();
 * my_multiblock.register();
 * }</pre>
 * <p>
 * Registering items when building items are okay, too.
 * {@code new MyItem().register()}
 * But it's better to register them in a separate method.
 * {@code registerItems()}
 *
 * @author balugaq
 */
public class IndustrialRevivalItemSetup {
    public static final IndustrialRevival INSTANCE = IndustrialRevival.getInstance();
    public static final ItemStack[] EMPTY_RECIPE = new ItemStack[]{};

    public static final Empty EMPTY = new Empty()
            .itemGroup(IRItemGroups.DEBUG)
            .id("empty")
            .icon(IndustrialRevivalItems.EMPTY)
            .recipe(item -> new CraftMethod(
                    RecipeType.NULL,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(Empty.class);

    public static final Container CONTAINER = new Container()
            .itemGroup(IRItemGroups.DEBUG)
            .id("container")
            .icon(IndustrialRevivalItems.CONTAINER)
            .recipe(item -> new CraftMethod(
                    RecipeType.NULL,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(Container.class);

    public static final DebugHead DEBUG_HEAD = new DebugHead()
            .itemGroup(IRItemGroups.DEBUG)
            .id("debug_head")
            .icon(IndustrialRevivalItems.DEBUG_HEAD)
            .recipe(item -> new CraftMethod(
                    RecipeType.NULL,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(DebugHead.class);

    public static final Debugger DEBUGGER = new Debugger()
            .itemGroup(IRItemGroups.DEBUG)
            .id("debugger")
            .icon(IndustrialRevivalItems.DEBUGGER)
            .recipe(item -> new CraftMethod(
                    RecipeType.NULL,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(Debugger.class);
    public static final BlastFurnace BLAST_FURNACE = new BlastFurnace(KeyUtil.customKey("blast_furnace"))
            .itemGroup(IRItemGroups.MULTIBLOCK)
            .id("blast_furnace")
            .icon(IndustrialRevivalItems.BLAST_FURNACE)
            .recipe(item -> new CraftMethod(
                    RecipeType.MULTIBLOCK,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(BlastFurnace.class);
    public static final CokeOven COKE_OVEN = new CokeOven(KeyUtil.customKey("coke_oven"))
            .itemGroup(IRItemGroups.MULTIBLOCK)
            .id("coke_oven")
            .icon(IndustrialRevivalItems.COKE_OVEN)
            .recipe(item -> new CraftMethod(
                    RecipeType.MULTIBLOCK,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(CokeOven.class);
    public static final EarthFurnace EARTH_FURNACE = new EarthFurnace(KeyUtil.customKey("earth_furnace"))
            .itemGroup(IRItemGroups.MULTIBLOCK)
            .id("earth_furnace")
            .icon(IndustrialRevivalItems.EARTH_FURNACE)
            .recipe(item -> new CraftMethod(
                    RecipeType.MULTIBLOCK,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(EarthFurnace.class);
    public static final WoodenPress WOODEN_PRESS = new WoodenPress(KeyUtil.customKey("wooden_press"))
            .itemGroup(IRItemGroups.MULTIBLOCK)
            .id("wooden_press")
            .icon(IndustrialRevivalItems.WOODEN_PRESS)
            .recipe(item -> new CraftMethod(
                    RecipeType.MULTIBLOCK,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(WoodenPress.class);
    public static final ResearchTable RESEARCH_TABLE = new ResearchTable(KeyUtil.customKey("research_table"))
            .itemGroup(IRItemGroups.MULTIBLOCK)
            .id("research_table")
            .icon(IndustrialRevivalItems.RESEARCH_TABLE)
            .recipe(item -> new CraftMethod(
                    RecipeType.MULTIBLOCK,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(ResearchTable.class);
    public static final ProfessionalLaboratory PROFESSIONAL_LABORATORY = new ProfessionalLaboratory(KeyUtil.customKey("professional_laboratory"))
            .itemGroup(IRItemGroups.MULTIBLOCK)
            .id("professional_laboratory")
            .icon(IndustrialRevivalItems.PROFESSIONAL_LABORATORY)
            .recipe(item -> new CraftMethod(
                    RecipeType.MULTIBLOCK,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(ProfessionalLaboratory.class);

    public static final BlastSmeltery BLAST_SMELTERY = new BlastSmeltery(KeyUtil.customKey("blast_smeltery"))
            .itemGroup(IRItemGroups.MULTIBLOCK)
            .id("blast_smeltery")
            .icon(IndustrialRevivalItems.BLAST_SMELTERY)
            .recipe(item -> new CraftMethod(
                    RecipeType.MULTIBLOCK,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(BlastSmeltery.class);

    public static final TinkerModelItem TEST_MODEL = new TinkerModelItem()
            .tinkerType(TinkerTypes.BLOCK)
            .itemGroup(IRItemGroups.DEBUG)
            .id("test_model")
            .icon(IndustrialRevivalItems.TEST_MODEL)
            .cast(TinkerModelItem.class);

    public static final TinkerProductItem TEST_PRODUCT = new TinkerProductItem()
            .meltedType(OreMeltedType.of(ElementType.Fe))
            .tinkerType(TinkerTypes.BLOCK)
            .itemGroup(IRItemGroups.DEBUG)
            .id("test_product")
            .icon(IndustrialRevivalItems.TEST_PRODUCT)
            .cast(TinkerProductItem.class);

    public static final BasicMachine TEST_MACHINE = new BasicMachine() {
        @Override
        public @NotNull Component getItemName() {
            return Component.text("Test Machine");
        }
    }
            .itemGroup(IRItemGroups.DEBUG)
            .id("test_machine")
            .icon(IndustrialRevivalItems.TEST_MACHINE)
            .cast(BasicMachine.class)
            .addRecipe(new MachineRecipe(10, 1, new ItemStack(Material.STONE), new ItemStack(Material.DIAMOND)))
            .cast(BasicMachine.class);

    public static final ElectrolyticMachine ELECTROLYTIC_MACHINE = new ElectrolyticMachine() {
        @Override
        public @NotNull Component getItemName() {
            return Component.text("Electrolytic Machine");
        }
    }
            .itemGroup(IRItemGroups.DEBUG)
            .id("electrolytic_machine")
            .icon(IndustrialRevivalItems.ELECTROLYTIC_MACHINE)
            .cast(ElectrolyticMachine.class);

    public static final OperationTable OPERATION_TABLE = new OperationTable() {
        @Override
        public @NotNull Component getItemName() {
            return Component.text("Operation Table");
        }
    }
            .itemGroup(IRItemGroups.DEBUG)
            .id("operation_table")
            .icon(IndustrialRevivalItems.OPERATION_TABLE)
            .cast(OperationTable.class);

    public static void setup() {
        EMPTY.register();
        CONTAINER.register();
        DEBUG_HEAD.register();
        DEBUGGER.register();

        BLAST_FURNACE.register();
        COKE_OVEN.register();
        EARTH_FURNACE.register();
        WOODEN_PRESS.register();
        RESEARCH_TABLE.register();
        PROFESSIONAL_LABORATORY.register();
        BLAST_SMELTERY.register();
        TEST_MODEL.register();
        TEST_PRODUCT.register();
        ElementOres.setup();
        ElementOreDusts.setup();
        GasJars.setup();
        ChemicalCompoundSetup.setup();
        TEST_MACHINE.register();
        ELECTROLYTIC_MACHINE.register();
        OPERATION_TABLE.register();
    }
}
