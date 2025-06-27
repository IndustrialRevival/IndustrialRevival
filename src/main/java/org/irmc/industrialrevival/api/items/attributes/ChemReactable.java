package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.elements.reaction.ReactHelper;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;
import org.irmc.industrialrevival.api.machines.process.Environment;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.pigeonlib.pdc.PersistentDataAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public interface ChemReactable extends ItemAttribute, Keyed, ComplexDataContainer.DataContainer2<Double, ChemicalCompound> {
    NamespacedKey CHEMICAL_COMPOUND_KEY = KeyUtil.customKey("chemical_compound");
    NamespacedKey MASS_KEY = KeyUtil.customKey("mass");

    /**
     * Returns the reactable instance for the given chemical compound.
     *
     * @param compound the chemical compound to get the item for.
     * @return the reactable instance for the given chemical compound.
     */
    static @Nullable ChemReactable getByCompound(@NotNull ChemicalCompound compound) {
        for (var entry : IRRegistry.getInstance().getBindingCompounds().entrySet()) {
            if (entry.getValue().equals(compound)) {
                return entry.getKey();
            }
        }

        return null;
    }

    @Override
    default ChemicalCompound getData2(ItemStack itemStack) {
        return getChemicalCompound(itemStack);
    }

    /**
     * Returns the chemical compound of the item.
     *
     * @param itemStack the item stack to get the chemical compound from.
     * @return the chemical compound of the item.
     */
    default @NotNull ChemicalCompound getChemicalCompound(@NotNull ItemStack itemStack) {
        return ChemicalCompound.forName(PersistentDataAPI.get(itemStack.getItemMeta(), CHEMICAL_COMPOUND_KEY, PersistentDataType.STRING));
    }

    @Override
    default void setData2(ItemStack itemStack, ChemicalCompound data) {
        setChemicalCompound(itemStack, data);
    }

    default void setChemicalCompound(@NotNull ItemStack itemStack, ChemicalCompound data) {
        itemStack.editMeta(meta -> {
            PersistentDataAPI.set(meta, CHEMICAL_COMPOUND_KEY, PersistentDataType.STRING, data.getName());
        });
    }

    @Override
    default Double getData1(ItemStack itemStack) {
        return getMass(itemStack);
    }

    /**
     * Returns the quality of each item. (Unit: grams)
     *
     * @return the quality of each item.
     */

    default double getMass(@NotNull ItemStack itemStack) {
        return PersistentDataAPI.getOrDefault(itemStack.getItemMeta(), MASS_KEY, PersistentDataType.DOUBLE, 0D);
    }

    @Override
    default void setData1(ItemStack itemStack, Double data) {
        setMass(itemStack, data);
    }

    default void setMass(@NotNull ItemStack itemStack, Double data) {
        itemStack.editMeta(meta -> {
            PersistentDataAPI.set(meta, MASS_KEY, PersistentDataType.DOUBLE, data);
        });
    }

    default @NotNull ReactResult react(@NotNull Environment environment, @NotNull Set<ReactCondition> conditions, @NotNull List<ItemStack> reactItems) {
        return ReactHelper.react(environment, conditions, reactItems);
    }

    /**
     * Registers the item as a reactable.
     */
    default void registerReactable() {
        IRDock.getPlugin().getRegistry().registerChemicalReactable(this);
    }

    /**
     * Returns true if the item is a catalyst for the given condition.
     *
     * @param condition the condition to check.
     * @return true if the item is a catalyst for the given condition, false otherwise.
     */
    default boolean isCatalyst(@NotNull ReactCondition condition) {
        return false;
    }

    /**
     * Binds the item to the given chemical compound.
     *
     * @param compound the chemical compound to bind to.
     */
    default void bind(@NotNull ChemicalCompound compound) {
        IRDock.getPlugin().getRegistry().bindChemicalCompound(this, compound);
    }
}
