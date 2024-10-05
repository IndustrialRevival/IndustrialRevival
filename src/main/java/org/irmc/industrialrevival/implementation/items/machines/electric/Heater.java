package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IRItems;

public class Heater extends ElectricMachine {
    public Heater() {
        super();

        addRecipe(240, 357000,
                new ItemStack[]{new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.GLASS_BOTTLE)},
                IRItems.IRItemStacks.STEAM_BOTTLE);
    }
}
