package org.irmc.industrialrevival.api.items;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.Smeltery;
import org.irmc.industrialrevival.api.elements.TinkerType;
import org.irmc.industrialrevival.api.elements.TinkerTypes;
import org.jetbrains.annotations.Range;

public class ElementOre extends ElementItem {
    @Override
    public @Range(from = 0, to = Smeltery.MAX_FUEL_CAPACITY) int getMeltingPoint(ItemStack itemStack) {
        return (int) getElementType().getMeltingPoint() / 10 * 9;
    }

    @Override
    public int getFuelUse(ItemStack itemStack) {
        return (int) getElementType().getMeltingPoint() / 10 * 9;
    }

    public TinkerType getTinkerType(ItemStack itemStack) {
        return TinkerTypes.ORE;
    }
}
