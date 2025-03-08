package org.irmc.industrialrevival.api.elements.compounds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@Getter
@AllArgsConstructor
@ParametersAreNonnullByDefault
public class Chemical extends Compound {
    private final @NotNull ChemicalCompound compound;
}
