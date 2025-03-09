package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.elements.compounds.ChemicalCompound;
import org.irmc.industrialrevival.api.elements.reaction.ReactResult;

public interface ChemReactable extends ItemAttribute {
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

    /**
     * Reacts two or more items.
     *
     * @param other the other item(s) to react with.
     * @return the result of the reaction.
     */
    ReactResult react(ReactCondition[] conditions, ChemReactable... other);
}
