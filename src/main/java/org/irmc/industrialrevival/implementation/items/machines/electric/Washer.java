package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItems;

public class Washer extends ElectricMachine {
    public Washer() {
        super();

        addRecipe(80, 4000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.PURE_WATER_BOTTLE, IndustrialRevivalItems.IRItemStacks.POLISHED_COPPER_FOIL},
                IndustrialRevivalItems.IRItemStacks.FLAWLESS_COPPER_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.PURE_WATER_BOTTLE, IndustrialRevivalItems.IRItemStacks.POLISHED_TIN_FOIL},
                IndustrialRevivalItems.IRItemStacks.FLAWLESS_TIN_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.PURE_WATER_BOTTLE, IndustrialRevivalItems.IRItemStacks.POLISHED_ZINC_FOIL},
                IndustrialRevivalItems.IRItemStacks.FLAWLESS_ZINC_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.PURE_WATER_BOTTLE, IndustrialRevivalItems.IRItemStacks.POLISHED_LEAD_FOIL},
                IndustrialRevivalItems.IRItemStacks.FLAWLESS_LEAD_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.PURE_WATER_BOTTLE, IndustrialRevivalItems.IRItemStacks.POLISHED_TUNGSTEN_FOIL},
                IndustrialRevivalItems.IRItemStacks.FLAWLESS_TUNGSTEN_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.PURE_WATER_BOTTLE, IndustrialRevivalItems.IRItemStacks.POLISHED_MAGNESIUM_FOIL},
                IndustrialRevivalItems.IRItemStacks.FLAWLESS_MAGNESIUM_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.PURE_WATER_BOTTLE, IndustrialRevivalItems.IRItemStacks.POLISHED_ALUMINIUM_FOIL},
                IndustrialRevivalItems.IRItemStacks.FLAWLESS_ALUMINIUM_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.PURE_WATER_BOTTLE, IndustrialRevivalItems.IRItemStacks.POLISHED_CHROMIUM_FOIL},
                IndustrialRevivalItems.IRItemStacks.FLAWLESS_CHROMIUM_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.PURE_WATER_BOTTLE, IndustrialRevivalItems.IRItemStacks.POLISHED_COBALT_FOIL},
                IndustrialRevivalItems.IRItemStacks.FLAWLESS_COBALT_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.PURE_WATER_BOTTLE, IndustrialRevivalItems.IRItemStacks.POLISHED_NICKEL_FOIL},
                IndustrialRevivalItems.IRItemStacks.FLAWLESS_NICKEL_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.PURE_WATER_BOTTLE, IndustrialRevivalItems.IRItemStacks.POLISHED_IRON_FOIL},
                IndustrialRevivalItems.IRItemStacks.FLAWLESS_IRON_FOIL);
        addRecipe(80, 4000,
                new ItemStack[]{IndustrialRevivalItems.IRItemStacks.DEIONIZED_WATER, IndustrialRevivalItems.IRItemStacks.GRAPHENE_THIN_FILM},
                IndustrialRevivalItems.IRItemStacks.CLEANED_GRAPHENE_THIN_FILM);
    }
}
