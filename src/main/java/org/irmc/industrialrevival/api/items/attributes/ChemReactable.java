package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.Keyed;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.ElementProportion;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

public interface ChemReactable extends ItemAttribute, Keyed {
    /**
     * Returns the element proportions of the item.
     *
     * @return the element proportions of the item.
     */
    ElementProportion[] getElementProportions(ItemStack item);

    /**
     * Returns the quality of each item. (Unit: grams)
     *
     * @return the quality of each item.
     */
    int getMass(ItemStack item);

    /**
     * Reacts two or more items.
     *
     * @param item the item itself to react with.
     * @param conditions the conditions for the reaction.
     * @param other the other item(s) to react with.
     * @return the result of the reaction.
     */
    ReactResult react(ItemStack item, ElementType.ReactCondition[] conditions, ChemReactable... other);

    default void registerReactable() {
        IndustrialRevival.getInstance().getRegistry().getChemReactables().put(this.getKey(), this);
    }
}
