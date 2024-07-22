package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.ItemAttribute;
import org.irmc.industrialrevival.utils.EnergyUtil;

public interface Rechargeable extends ItemAttribute {
    double getEnergyCapacity();

    default double getItemEnergy(ItemStack item) {
        return EnergyUtil.getItemEnergy(item);
    }

    default void setItemEnergy(ItemStack item, long energy) {
        EnergyUtil.setItemEnergy(item, energy);
    }

    default void addItemEnergy(ItemStack item, double energy) {
        EnergyUtil.addItemEnergy(item, energy);
    }

    default void takeItemEnergy(ItemStack item, double energy) {
        EnergyUtil.takeItemEnergy(item, energy);
    }

    default boolean hasEnoughEnergy(ItemStack item, double energy) {
        return EnergyUtil.hasEnoughEnergy(item, energy);
    }
}
