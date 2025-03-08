package org.irmc.industrialrevival.api.elements.compounds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.irmc.industrialrevival.api.elements.ElementType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@Getter
@AllArgsConstructor
@ParametersAreNonnullByDefault
public class Element extends Compound {
    private final @NotNull ElementType element;
}
