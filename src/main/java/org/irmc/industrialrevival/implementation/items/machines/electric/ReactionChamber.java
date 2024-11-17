package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IndustrialRevivalItems;

public class ReactionChamber extends ElectricMachine {
    public ReactionChamber() {
        super();
        addRecipe(400, 100,
                IndustrialRevivalItems.IRItemStacks.RAW_GRAPHENE,
                IndustrialRevivalItems.IRItemStacks.GRAPHENE_THIN_FILM);
    }
}
