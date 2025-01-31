package org.irmc.industrialrevival.api.items;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.items.attributes.Meltable;
import org.irmc.industrialrevival.api.elements.MeltedType;
import org.irmc.industrialrevival.api.elements.Smeltery;
import org.irmc.industrialrevival.api.elements.melt_types.OreMeltedType;
import org.jetbrains.annotations.Range;

@Getter
public class ElementItem extends IndustrialRevivalItem implements Meltable {
    private ElementType elementType;
    public ElementItem setElementType(ElementType elementType) {
        this.elementType = elementType;
        return this;
    }

    @Override
    public MeltedType getMeltedType(ItemStack itemStack) {
        return OreMeltedType.of(elementType);
    }

    @Override
    public @Range(from = 0, to = Smeltery.MAX_FUEL_CAPACITY) int getMeltingPoint(ItemStack itemStack) {
        return (int) elementType.getMeltingPoint();
    }

    @Override
    public int getFuelUse(ItemStack itemStack) {
        return (int) elementType.getMeltingPoint();
    }
}
