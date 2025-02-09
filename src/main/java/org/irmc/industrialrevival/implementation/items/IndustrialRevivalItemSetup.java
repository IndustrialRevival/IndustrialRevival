package org.irmc.industrialrevival.implementation.items;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.api.elements.TinkerType;
import org.irmc.industrialrevival.api.elements.TinkerTypes;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.TinkerModelItem;
import org.irmc.industrialrevival.api.items.TinkerProductItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.recipes.CraftMethod;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.irmc.industrialrevival.implementation.items.debug.Container;
import org.irmc.industrialrevival.implementation.items.debug.DebugHead;
import org.irmc.industrialrevival.implementation.items.debug.Debugger;
import org.irmc.industrialrevival.implementation.items.debug.Empty;
import org.irmc.industrialrevival.implementation.items.register.ElementOres;
import org.irmc.industrialrevival.implementation.multiblock.BlastFurnace;
import org.irmc.industrialrevival.implementation.multiblock.BlastSmeltery;
import org.irmc.industrialrevival.implementation.multiblock.CokeOven;
import org.irmc.industrialrevival.implementation.multiblock.EarthFurnace;
import org.irmc.industrialrevival.implementation.multiblock.ProfessionalLaboratory;
import org.irmc.industrialrevival.implementation.multiblock.ResearchTable;
import org.irmc.industrialrevival.implementation.multiblock.WoodenPress;
import org.irmc.industrialrevival.utils.KeyUtil;

/**
 * This class is responsible for registering all the items of Industrial Revival.
 * <p>
 * Here's an example of how to build an item:
 *
 * <p>
 * If an item is a normal item, it should be built as:
 * <pre>{@code
 * public static final MySpecialItem my_special_item = new MySpecialItem()
 *     .setAddon(my_addon)                       // my_addon -> {@link IndustrialRevivalAddon }
 *     .addItemGroup(my_item_group)              // my_item_group -> {@link ItemGroup }
 *     .setItemStack(my_item_stack)              // my_item_stack -> {@link IndustrialRevivalItemStack }
 *     .addCraftMethod(item -> new CraftMethod(  // recipe method, see more {@link CraftMethod}
 *              RecipeType.NULL,                 // recipe type, can be any of the types in {@link RecipeType } or a custom recipe type
 *              new ItemStack[] {},              // input items, can be empty but CANNOT be null
 *              item                             // item is a pure itemstack which be used in {@code setItemStack }
 *          ))
 *     .cast(MySpecialItem.class)                // this method can be used to return the instance of {@code MySpecialItem }
 *     .doOtherThingsInMySpecialItem();          // this method should return the instance of {@code MySpecialItem }
 * }</pre>
 *
 * If an item is a multiblock, it should be built as:
 * <pre>{@code
 * public static final MyMultiblock my_multiblock = new MyMultiblock(namespacedKey) // namespacedKey is the key of the multiblock, see more {@link KeyUtil}
 *     .setAddon(my_addon)                       // my_addon -> {@link IndustrialRevivalAddon }
 *     .addItemGroup(my_item_group)              // my_item_group -> {@link ItemGroup }
 *     .setItemStack(my_item_stack)              // my_item_stack -> {@link IndustrialRevivalItemStack }
 *     .addCraftMethod(item -> new CraftMethod(  // recipe method, see more {@link CraftMethod}
 *              RecipeType.MULTIBLOCK,           // recipe type, can only be MULTIBLOCK
 *              new ItemStack[] {},              // input items, can be anything but CANNOT be null
 *              item                             // item is a pure itemstack which be used in {@code setItemStack }
 *          ))
 *     .cast(MyMultiblock.class)                 // this method can be used to return the instance of {@code MyMultiblock }
 *     .doOtherThingsInMyMultiblock();           // this method should return the instance of {@code MyMultiblock }
 * }</pre>
 *
 * Then items should be registered as:
 * <pre>{@code
 * my_item.register();
 * my_multiblock.register();
 * }</pre>
 *
 * Registering items when building items are okay, too.
 * {@code new MyItem().register()}
 * But it's better to register them in a separate method.
 * {@code registerItems()}
 *
 * @author balugaq
 */
public class IndustrialRevivalItemSetup {
    public static final IndustrialRevival INSTANCE = IndustrialRevival.getInstance();
    public static final ItemStack[] EMPTY_RECIPE = new ItemStack[] {};

    public static final Empty EMPTY = new Empty()
            .setAddon(INSTANCE)
            .addItemGroup(IRItemGroups.DEBUG)
            .setItemStack(IndustrialRevivalItems.EMPTY)
            .addCraftMethod(item -> new CraftMethod(
                    RecipeType.NULL,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(Empty.class);

    public static final Container CONTAINER = new Container()
            .setAddon(INSTANCE)
            .addItemGroup(IRItemGroups.DEBUG)
            .setItemStack(IndustrialRevivalItems.CONTAINER)
            .addCraftMethod(item -> new CraftMethod(
                    RecipeType.NULL,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(Container.class);

    public static final DebugHead DEBUG_HEAD = new DebugHead()
            .setAddon(INSTANCE)
            .addItemGroup(IRItemGroups.DEBUG)
            .setItemStack(IndustrialRevivalItems.DEBUG_HEAD)
            .addCraftMethod(item -> new CraftMethod(
                    RecipeType.NULL,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(DebugHead.class);

    public static final Debugger DEBUGGER = new Debugger()
            .setAddon(INSTANCE)
            .addItemGroup(IRItemGroups.DEBUG)
            .setItemStack(IndustrialRevivalItems.DEBUGGER)
            .addCraftMethod(item -> new CraftMethod(
                    RecipeType.NULL,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(Debugger.class);
    public static final BlastFurnace BLAST_FURNACE = new BlastFurnace(KeyUtil.customKey("blast_furnace"))
            .setAddon(INSTANCE)
            .addItemGroup(IRItemGroups.MULTIBLOCK)
            .setItemStack(IndustrialRevivalItems.BLAST_FURNACE)
            .addCraftMethod(item -> new CraftMethod(
                    RecipeType.MULTIBLOCK,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(BlastFurnace.class);
    public static final CokeOven COKE_OVEN = new CokeOven(KeyUtil.customKey("coke_oven"))
            .setAddon(INSTANCE)
            .addItemGroup(IRItemGroups.MULTIBLOCK)
            .setItemStack(IndustrialRevivalItems.COKE_OVEN)
            .addCraftMethod(item -> new CraftMethod(
                    RecipeType.MULTIBLOCK,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(CokeOven.class);
    public static final EarthFurnace EARTH_FURNACE = new EarthFurnace(KeyUtil.customKey("earth_furnace"))
            .setAddon(INSTANCE)
            .addItemGroup(IRItemGroups.MULTIBLOCK)
            .setItemStack(IndustrialRevivalItems.EARTH_FURNACE)
            .addCraftMethod(item -> new CraftMethod(
                    RecipeType.MULTIBLOCK,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(EarthFurnace.class);
    public static final WoodenPress WOODEN_PRESS = new WoodenPress(KeyUtil.customKey("wooden_press"))
            .setAddon(INSTANCE)
            .addItemGroup(IRItemGroups.MULTIBLOCK)
            .setItemStack(IndustrialRevivalItems.WOODEN_PRESS)
            .addCraftMethod(item -> new CraftMethod(
                    RecipeType.MULTIBLOCK,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(WoodenPress.class);
    public static final ResearchTable RESEARCH_TABLE = new ResearchTable(KeyUtil.customKey("research_table"))
            .setAddon(INSTANCE)
            .addItemGroup(IRItemGroups.MULTIBLOCK)
            .setItemStack(IndustrialRevivalItems.RESEARCH_TABLE)
            .addCraftMethod(item -> new CraftMethod(
                    RecipeType.MULTIBLOCK,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(ResearchTable.class);
    public static final ProfessionalLaboratory PROFESSIONAL_LABORATORY = new ProfessionalLaboratory(KeyUtil.customKey("professional_laboratory"))
            .setAddon(INSTANCE)
            .addItemGroup(IRItemGroups.MULTIBLOCK)
            .setItemStack(IndustrialRevivalItems.PROFESSIONAL_LABORATORY)
            .addCraftMethod(item -> new CraftMethod(
                    RecipeType.MULTIBLOCK,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(ProfessionalLaboratory.class);

    public static final BlastSmeltery BLAST_SMELTERY = new BlastSmeltery(KeyUtil.customKey("blast_smeltery"))
            .setAddon(INSTANCE)
            .addItemGroup(IRItemGroups.MULTIBLOCK)
            .setItemStack(IndustrialRevivalItems.BLAST_SMELTERY)
            .addCraftMethod(item -> new CraftMethod(
                    RecipeType.MULTIBLOCK,
                    EMPTY_RECIPE,
                    item
            ))
            .cast(BlastSmeltery.class);

    public static final TinkerModelItem TEST_MODEL = new TinkerModelItem()
            .setTinkerType(TinkerTypes.BLOCK)
            .setAddon(INSTANCE)
            .addItemGroup(IRItemGroups.DEBUG)
            .setItemStack(IndustrialRevivalItems.TEST_MODEL)
            .cast(TinkerModelItem.class);

    public static final TinkerProductItem TEST_PRODUCT = new TinkerProductItem()
            .setTinkerType(TinkerTypes.BLOCK)
            .setAddon(INSTANCE)
            .addItemGroup(IRItemGroups.DEBUG)
            .setItemStack(IndustrialRevivalItems.TEST_PRODUCT)
            .cast(TinkerProductItem.class);

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
        ElementOres.register();
    }
}
