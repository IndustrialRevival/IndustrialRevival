package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.MeltedType;
import org.irmc.industrialrevival.api.elements.Smeltery;
import org.jetbrains.annotations.Range;

public interface Meltable extends ItemAttribute {
    MeltedType getMeltedType(ItemStack itemStack);
    @Range(from = 0, to = Smeltery.MAX_FUEL_CAPACITY) int getMeltingPoint(ItemStack itemStack);
    int getFuelUse(ItemStack itemStack);
}
