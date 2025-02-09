package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.TinkerType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

public interface TinkerProduct extends ItemAttribute {
    TinkerType getTinkerType();
    ItemStack getProduct();
    IndustrialRevivalItem getIRItem();
}
