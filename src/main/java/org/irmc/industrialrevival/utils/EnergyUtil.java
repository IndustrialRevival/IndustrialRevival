package org.irmc.industrialrevival.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.IndustrialRevival;

public class EnergyUtil {
    private static final NamespacedKey ENERGY_KEY = new NamespacedKey(IndustrialRevival.getInstance(), "item_energy");

    private EnergyUtil() {}

    public static double getItemEnergy(ItemStack stack) {
        return PersistentDataAPI.getDouble(stack.getItemMeta(), ENERGY_KEY, 0);
    }

    public static void setItemEnergy(ItemStack stack, double energy) {
        PersistentDataAPI.setDouble(stack.getItemMeta(), ENERGY_KEY, energy);
    }

    public static void addItemEnergy(ItemStack stack, double energy) {
        setItemEnergy(stack, getItemEnergy(stack) + energy);
    }

    public static void takeItemEnergy(ItemStack stack, double energy) {
        if (getItemEnergy(stack) < energy) {
            setItemEnergy(stack, 0);
        }
        setItemEnergy(stack, getItemEnergy(stack) - energy);
    }

    public static boolean hasEnoughEnergy(ItemStack stack, double energy) {
        return getItemEnergy(stack) >= energy;
    }
}
