package org.irmc.industrialrevival.api.elements.reaction;

import org.irmc.industrialrevival.api.items.attributes.ChemReactable;

import javax.annotation.Nullable;

/**
 * The result of a chemical reaction.
 * @param sediments        The sediments created by the reaction.
 * @param gases            The gases created by the reaction.
 * @param otherReactables  The other reactables involved in the reaction.
 */
public record ReactResult(@Nullable Sediment sediments, @Nullable Gas gases, ChemReactable... otherReactables) {
    /**
     * The sediment created by the reaction.
     * @param reactables The reactables involved in the reaction.
     */
    public record Sediment(ChemReactable... reactables) { }

    /**
     * The gas created by the reaction.
     * @param reactables The reactables involved in the reaction.
     */
    public record Gas(ChemReactable... reactables) { }
}
