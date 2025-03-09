package org.irmc.industrialrevival.api.elements.reaction;

import org.irmc.industrialrevival.api.items.attributes.ChemReactable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * The result of a chemical reaction.
 *
 * @param sediments       The sediments created by the reaction.
 * @param gases           The gases created by the reaction.
 * @param otherReactables The other reactables involved in the reaction.
 * @author lijinhong11
 */
public record ReactResult(@Nullable Sediment sediments, @Nullable Gas gases,
                          @NotNull ChemReactable @NotNull ... otherReactables) {
    public static final ReactResult FAILED = new ReactResult(null, null);

    /**
     * The sediment created by the reaction.
     *
     * @param reactables The reactables involved in the reaction.
     */
    public record Sediment(@NotNull ChemReactable @NotNull ... reactables) {
    }

    /**
     * The gas created by the reaction.
     *
     * @param reactables The reactables involved in the reaction.
     */
    public record Gas(@NotNull ChemReactable @NotNull ... reactables) {
    }
}
