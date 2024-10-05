package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IRItems;

public class VacuumExtractor extends ElectricMachine {
    public VacuumExtractor() {
        super();

        addRecipe(60, 20000,
                new ItemStack(Material.GLASS_BOTTLE),
                IRItems.IRItemStacks.VACUUM_BOTTLE);
    }
}
