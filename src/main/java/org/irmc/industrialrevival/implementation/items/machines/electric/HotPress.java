package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItems;

public class HotPress extends ElectricMachine {
    public HotPress() {
        super();
        addRecipe(50, 8000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.GRAPHENE_FILM_COATED_WITH_POLYMETHYL_METHACRYLATE, IndustrialRevivalItems.IRItemStacks.POLYETHYLENE_TEREPHTHALATE},
                IndustrialRevivalItems.IRItemStacks.DIRTY_GRAPHENE);
        addRecipe(50, 4000,
                IndustrialRevivalItems.IRItemStacks.DIRTY_GRAPHENE,
                IndustrialRevivalItems.IRItemStacks.GRAPHENE);
    }
}
