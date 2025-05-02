package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.pigeonlib.objects.percentage.PositiveHundredPercentage;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;

public interface GasStorage extends ChemReactable, SimpleDataContainer<PositiveHundredPercentage> {
    public static final NamespacedKey GAS_STORAGE_CAPACITY_KEY = KeyUtil.customKey("gas_storage_capacity");

    CatheterInsertionMethod getCatheterInsertionMethod(ItemStack item);

    void setCatheterInsertionMethod(CatheterInsertionMethod catheterInsertionMethod, ItemStack item);

    @Override
    default PositiveHundredPercentage getData(ItemStack item) {
        return PositiveHundredPercentage.fromDoublePercentage(PersistentDataAPI.getOrDefault(item.getItemMeta(), GAS_STORAGE_CAPACITY_KEY, PersistentDataType.DOUBLE, 0.0));
    }

    @Override
    default void setData(ItemStack item, PositiveHundredPercentage gasStorageCapacity) {
        item.editMeta(meta -> PersistentDataAPI.set(meta, GAS_STORAGE_CAPACITY_KEY, PersistentDataType.DOUBLE, gasStorageCapacity.getDecimalValue()));
    }

    void insertGas(ItemStack item, ChemReactable gas);

    enum CatheterInsertionMethod {
        NONE,
        UP,
        DOWN,
        WATER_INSIDE_UP,
        WATER_INSIDE_DOWN
    }
}
