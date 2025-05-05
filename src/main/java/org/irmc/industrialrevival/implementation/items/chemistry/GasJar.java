package org.irmc.industrialrevival.implementation.items.chemistry;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;
import org.irmc.industrialrevival.api.items.ElementItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.ChemReactable;
import org.irmc.industrialrevival.api.items.attributes.GasStorage;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.pigeonlib.objects.percentage.PositiveHundredPercentage;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GasJar extends ElementItem implements GasStorage {
    private static final NamespacedKey CATHER_KEY = KeyUtil.customKey("cather_insertion_method");

    private static final NamespacedKey ITEM_KEY = KeyUtil.customKey("gas_jar");

    @Getter
    private PositiveHundredPercentage capacity;

    public GasJar setCapacity(PositiveHundredPercentage capacity) {
        this.capacity = capacity;
        return this;
    }

    @Override
    public CatheterInsertionMethod getCatheterInsertionMethod(ItemStack item) {
        return CatheterInsertionMethod.values()[PersistentDataAPI.getOrDefault(item.getItemMeta(), CATHER_KEY, PersistentDataType.INTEGER, 0)];
    }

    @Override
    public void setCatheterInsertionMethod(CatheterInsertionMethod catheterInsertionMethod, ItemStack item) {
        PersistentDataAPI.set(item.getItemMeta(), CATHER_KEY, PersistentDataType.INTEGER, catheterInsertionMethod.ordinal());
    }

    @Override
    public void insertGas(ItemStack item, ChemReactable gas) {
        PersistentDataAPI.set(item.getItemMeta(), ChemReactable.CHEMICAL_COMPOUND_KEY, PersistentDataType.STRING, gas.getKey().toString());
    }

    /**
     * Returns the chemical compound of the item.
     *
     * @param itemStack the item stack to get the chemical compound from.
     * @return the chemical compound of the item.
     */
    @Override
    public @NotNull ChemicalCompound getChemicalCompound(@NotNull ItemStack itemStack) {
        String key = PersistentDataAPI.getOrDefault(itemStack.getItemMeta(), ChemReactable.CHEMICAL_COMPOUND_KEY, PersistentDataType.STRING, "");
        if (key.isEmpty()) {
            return null;
        }

        return IndustrialRevival.getInstance().getRegistry().getChemReactables().get(NamespacedKey.fromString(key)).getChemicalCompound(itemStack);
    }

    @Override
    public double getMass(@NotNull ItemStack item) {
        String key = PersistentDataAPI.getOrDefault(item.getItemMeta(), ChemReactable.CHEMICAL_COMPOUND_KEY, PersistentDataType.STRING, "");
        if (key.isEmpty()) {
            return 0;
        }

        ChemReactable reactable = IndustrialRevival.getInstance().getRegistry().getChemReactables().get(NamespacedKey.fromString(key));
        if (reactable == null) {
            return 0;
        }

        return reactable.getMass(item); // gas will host the mass
    }


    @Override
    public @NotNull NamespacedKey getKey() {
        return ITEM_KEY;
    }

    @Override
    public GasJar register() {
        super.register();
        registerReactable();
        return this;
    }
}
