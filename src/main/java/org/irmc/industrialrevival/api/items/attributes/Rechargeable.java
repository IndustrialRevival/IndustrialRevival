package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.utils.EnergyUtil;

/**
 * This interface defines an item that can be charge or discharge its energy. <br>
 */
public interface Rechargeable extends ItemAttribute, SimpleDataContainer<Double> {
    double getEnergyCapacity();

    @Override
    default Double getData(ItemStack item) {
        return EnergyUtil.getItemEnergy(item);
    }

    @Override
    default void setData(ItemStack item, Double energy) {
        EnergyUtil.setItemEnergy(item, energy);
    }

    default void addItemEnergy(ItemStack item, double energy) {
        EnergyUtil.addItemEnergy(item, energy);
        onRecharge(item, energy);
    }

    default void takeItemEnergy(ItemStack item, double energy) {
        EnergyUtil.takeItemEnergy(item, energy);
        onEnergyTaken(item, energy);
    }

    default boolean hasEnoughEnergy(ItemStack item, double energy) {
        return EnergyUtil.hasEnoughEnergy(item, energy);
    }

    default void onRecharge(ItemStack item, double energy) {
    }

    void onEnergyTaken(ItemStack item, double energy);
}
