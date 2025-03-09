package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.bukkit.Keyed;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.elements.ElementProportion;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

public interface ChemReactable extends ItemAttribute, Keyed {
    /**
     * Returns the chemical compound of the item.
     *
     * @return the chemical compound of the item.
     */
    ChemicalCompound getChemicalCompound();

    /**
     * Returns the quality of each item. (Unit: grams)
     *
     * @return the quality of each item.
     */

    int getMass();

    /**
     * Checks if two or more items can react.
     * @param other the other item(s) to react with.
     * @return true if the items can react, false otherwise.
     */
    boolean canReact(ReactCondition[] conditions, ChemReactable... other);
  
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
