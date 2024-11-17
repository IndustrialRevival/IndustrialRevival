package org.irmc.industrialrevival.implementation.items;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.recipes.CraftMethod;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.groups.IRItemGroups;
import org.irmc.industrialrevival.implementation.items.debug.Debugger;

public class IndustrialRevivalItemSetup {
    public static final IndustrialRevival INSTANCE = IndustrialRevival.getInstance();
    public static final ItemStack[] EMPTY_RECIPE = new ItemStack[] {};

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
        DEBUGGER.register();
    }
}
