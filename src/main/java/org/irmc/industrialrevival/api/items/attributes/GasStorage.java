package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.pigeonlib.objects.percentage.PositiveHundredPercentage;

public interface GasStorage extends ChemReactable {

    CatheterInsertionMethod getCatheterInsertionMethod(ItemStack item);

    void setCatheterInsertionMethod(CatheterInsertionMethod catheterInsertionMethod, ItemStack item);

    PositiveHundredPercentage getGasStorageCapacity(ItemStack item);

    void setGasStorageCapacity(PositiveHundredPercentage gasStorageCapacity, ItemStack item);

    void insertGas(ItemStack item, ChemReactable gas);

    enum CatheterInsertionMethod {
        NONE,
        UP,
        DOWN,
        WATER_INSIDE_UP,
        WATER_INSIDE_DOWN
    }
}
