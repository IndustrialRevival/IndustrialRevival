package org.irmc.industrialrevival.api.elements;

import org.irmc.industrialrevival.api.items.attributes.ChemReactable;

import javax.annotation.Nullable;

public record ReactResult(@Nullable Sediment sediments,@Nullable Gas gases, ChemReactable... otherReactables) {
    public record Sediment(ChemReactable... reactables) { }
    public record Gas(ChemReactable... reactables) { }
}
