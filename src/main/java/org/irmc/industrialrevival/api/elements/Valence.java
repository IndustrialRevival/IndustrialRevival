package org.irmc.industrialrevival.api.elements;

import org.jetbrains.annotations.NotNull;

public record Valence(int... valences) {
    public static @NotNull Valence of(int... valences) {
        return new Valence(valences);
    }
}
