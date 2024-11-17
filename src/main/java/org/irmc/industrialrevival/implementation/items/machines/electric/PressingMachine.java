package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItems;

public class PressingMachine extends ElectricMachine {
    public PressingMachine() {
        super();

        addRecipe(20, 1000,
                new ItemStack(Material.COPPER_INGOT),
                IndustrialRevivalItems.IRItemStacks.RAW_COPPER_FOIL);
        addRecipe(20, 1000,
                IndustrialRevivalItems.IRItemStacks.TIN,
                IndustrialRevivalItems.IRItemStacks.RAW_TIN_FOIL);
        addRecipe(20, 1000,
                IndustrialRevivalItems.IRItemStacks.ZINC,
                IndustrialRevivalItems.IRItemStacks.RAW_ZINC_FOIL);
        addRecipe(20, 1000,
                IndustrialRevivalItems.IRItemStacks.LEAD,
                IndustrialRevivalItems.IRItemStacks.RAW_LEAD_FOIL);
        addRecipe(20, 1000,
                IndustrialRevivalItems.IRItemStacks.TUNGSTEN,
                IndustrialRevivalItems.IRItemStacks.RAW_TUNGSTEN_FOIL);
        addRecipe(20, 1000,
                IndustrialRevivalItems.IRItemStacks.MAGNESIUM,
                IndustrialRevivalItems.IRItemStacks.RAW_MAGNESIUM_FOIL);
        addRecipe(20, 1000,
                IndustrialRevivalItems.IRItemStacks.ALUMINIUM,
                IndustrialRevivalItems.IRItemStacks.RAW_ALUMINIUM_FOIL);
        addRecipe(20, 1000,
                IndustrialRevivalItems.IRItemStacks.CHROMIUM,
                IndustrialRevivalItems.IRItemStacks.RAW_CHROMIUM_FOIL);
        addRecipe(20, 1000,
                IndustrialRevivalItems.IRItemStacks.COBALT,
                IndustrialRevivalItems.IRItemStacks.RAW_COBALT_FOIL);
        addRecipe(20, 1000,
                IndustrialRevivalItems.IRItemStacks.NICKEL,
                IndustrialRevivalItems.IRItemStacks.RAW_NICKEL_FOIL);
        addRecipe(20, 1000,
                new ItemStack(Material.IRON_INGOT),
                IndustrialRevivalItems.IRItemStacks.RAW_IRON_FOIL);
    }
}
