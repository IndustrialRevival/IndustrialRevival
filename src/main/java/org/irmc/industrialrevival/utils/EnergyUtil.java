package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;

/**
 * This utility class provides methods for managing energy-related operations for items in the IndustrialRevival plugin.
 * It includes functionalities to get, set, add, and take energy from items, as well as to check if an item has enough energy.
 *
 * @author balugaq
 */
@UtilityClass
public class EnergyUtil {
    private static final NamespacedKey ENERGY_KEY = KeyUtil.customKey("item_energy");

    /**
     * Retrieves the energy value stored in the specified ItemStack.
     *
     * @param stack The ItemStack to retrieve the energy from.
     * @return The energy value as a double. Returns 0 if no energy is stored.
     */
    public static double getItemEnergy(ItemStack stack) {
        return PersistentDataAPI.getDouble(stack.getItemMeta(), ENERGY_KEY, 0);
    }

    /**
     * Sets the energy value for the specified ItemStack.
     *
     * @param stack The ItemStack to set the energy for.
     * @param energy The energy value to set.
     */
    public static void setItemEnergy(ItemStack stack, double energy) {
        PersistentDataAPI.setDouble(stack.getItemMeta(), ENERGY_KEY, energy);
    }

    /**
     * Adds the specified amount of energy to the ItemStack.
     *
     * @param stack The ItemStack to add energy to.
     * @param energy The amount of energy to add.
     */
    public static void addItemEnergy(ItemStack stack, double energy) {
        setItemEnergy(stack, getItemEnergy(stack) + energy);
    }

    /**
     * Takes the specified amount of energy from the ItemStack.
     * If the ItemStack has less energy than the specified amount, the energy is set to 0.
     *
     * @param stack The ItemStack to take energy from.
     * @param energy The amount of energy to take.
     */
    public static void takeItemEnergy(ItemStack stack, double energy) {
        if (getItemEnergy(stack) < energy) {
            setItemEnergy(stack, 0);
        }
        setItemEnergy(stack, getItemEnergy(stack) - energy);
    }

    /**
     * Checks if the ItemStack has enough energy for the specified amount.
     *
     * @param stack The ItemStack to check.
     * @param energy The amount of energy to check against.
     * @return True if the ItemStack has enough energy, false otherwise.
     */
    public static boolean hasEnoughEnergy(ItemStack stack, double energy) {
        return getItemEnergy(stack) >= energy;
    }
}
