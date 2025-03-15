package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.Smeltery;
import org.irmc.industrialrevival.api.elements.melt.MeltedType;
import org.irmc.industrialrevival.api.elements.tinker.TinkerType;
import org.jetbrains.annotations.Range;

public interface Meltable extends ItemAttribute {
    TinkerType getTinkerType(ItemStack itemStack);
    MeltedType getMeltedType(ItemStack itemStack);
    @Range(from = 0, to = Smeltery.MAX_FUEL_CAPACITY) int getMeltingPoint(ItemStack itemStack);
    int getFuelUse(ItemStack itemStack);
}
