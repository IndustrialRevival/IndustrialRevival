package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.Keyed;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ChemReactable extends ItemAttribute, Keyed {
    /**
     * Returns the reactable instance for the given chemical compound.
     * @param compound the chemical compound to get the item for.
     * @return the reactable instance for the given chemical compound.
     */
    // todo: implement
    static @Nullable ChemReactable getByCompound(@NotNull ChemicalCompound compound) {
        return null;
    }

    /**
     * Returns the chemical compound of the item.
     *
     * @param itemStack the item stack to get the chemical compound from.
     * @return the chemical compound of the item.
     */
    @NotNull ChemicalCompound getChemicalCompound(@NotNull ItemStack itemStack);

    /**
     * Returns the quality of each item. (Unit: grams)
     *
     * @return the quality of each item.
     */

    int getMass(@NotNull ItemStack itemStack);

    /**
     * Reacts two or more items.
     *
     * @param item the item itself to react with.
     * @param conditions the conditions for the reaction.
     * @param other the other item(s) to react with.
     * @return the result of the reaction.
     */

    @NotNull ReactResult react(@NotNull ItemStack item, @NotNull ReactCondition[] conditions, @NotNull ChemReactable... other);

    /**
     * Registers the item as a reactable.
     */
    default void registerReactable() {
        IndustrialRevival.getInstance().getRegistry().getChemReactables().put(this.getKey(), this);
    }

    /**
     * Returns true if the item is a catalyst for the given condition.
     * @param condition the condition to check.
     * @return true if the item is a catalyst for the given condition, false otherwise.
     */
    default boolean isCatalyst(@NotNull ReactCondition condition) {
        return false;
    }
}
