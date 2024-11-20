package org.irmc.industrialrevival.implementation.items;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.recipes.CraftMethod;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.irmc.industrialrevival.implementation.items.debug.Container;
import org.irmc.industrialrevival.implementation.items.debug.DebugHead;
import org.irmc.industrialrevival.implementation.items.debug.Debugger;
import org.irmc.industrialrevival.implementation.items.debug.Empty;

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

    public static void setup() {
        EMPTY.register();
        CONTAINER.register();
        DEBUG_HEAD.register();
        DEBUGGER.register();
    }
}
