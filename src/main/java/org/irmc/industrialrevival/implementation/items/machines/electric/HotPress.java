package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IRItems;

public class HotPress extends ElectricMachine {
    public HotPress() {
        super();
        addRecipe(50, 8000,
                new ItemStack[]{IRItems.IRItemStacks.GRAPHENE_FILM_COATED_WITH_POLYMETHYL_METHACRYLATE, IRItems.IRItemStacks.POLYETHYLENE_TEREPHTHALATE},
                IRItems.IRItemStacks.DIRTY_GRAPHENE);
        addRecipe(50, 4000,
                IRItems.IRItemStacks.DIRTY_GRAPHENE,
                IRItems.IRItemStacks.GRAPHENE);
    }
}
