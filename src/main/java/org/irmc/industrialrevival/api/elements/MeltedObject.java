package org.irmc.industrialrevival.api.elements;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
public class MeltedObject {
    private final @NotNull MeltedType type;
    @Setter
    private int amount;

    public MeltedObject(@NotNull MeltedType type, int amount) {
        this.type = type;
        this.amount = amount;
    }
}
