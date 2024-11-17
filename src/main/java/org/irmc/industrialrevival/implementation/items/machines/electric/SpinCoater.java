package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItems;

public class SpinCoater extends ElectricMachine {
    public SpinCoater() {
        super();

        addRecipe(80, 1000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.CLEANED_GRAPHENE_THIN_FILM, IndustrialRevivalItems.IRItemStacks.POLYMETHYL_METHACRYLATE},
                IndustrialRevivalItems.IRItemStacks.GRAPHENE_FILM_COATED_WITH_POLYMETHYL_METHACRYLATE);
    }
}
