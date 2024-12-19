package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.elements.ElementProportion;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.irmc.industrialrevival.api.elements.ReactResult;

public interface ChemReactable {
    /**
     * Returns the element proportions of the item.
     *
     * @return the element proportions of the item.
     */
    ElementProportion[] getElementProportions();

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
    boolean canReact(ElementType.ReactCondition[] conditions, ChemReactable... other);

    /**
     * Reacts two or more items.
     *
     * @param other the other item(s) to react with.
     * @return the result of the reaction.
     */
    ReactResult react(ElementType.ReactCondition[] conditions, ChemReactable... other);

    /**
     * Checks if the item is a gas or gas storage.
     *
     * @return true if the item is a gas or gas storage that stores a type of gas, false otherwise.
     */
    boolean isGasOrGasStorage();
}
