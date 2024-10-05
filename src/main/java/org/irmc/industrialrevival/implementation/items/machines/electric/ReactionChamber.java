package org.irmc.industrialrevival.implementation.items.machines.electric;

import org.irmc.industrialrevival.api.machines.ElectricMachine;
import org.irmc.industrialrevival.implementation.items.IRItems;

public class ReactionChamber extends ElectricMachine {
    public ReactionChamber() {
        super();
        addRecipe(400, 100,
                IRItems.IRItemStacks.RAW_GRAPHENE,
                IRItems.IRItemStacks.GRAPHENE_THIN_FILM);
    }
}
