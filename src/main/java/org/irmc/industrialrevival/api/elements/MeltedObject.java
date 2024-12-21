package org.irmc.industrialrevival.api.elements;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MeltedObject {
    private final MeltedType type;
    @Setter
    private int amount;

    public MeltedObject(MeltedType type, int amount) {
        this.type = type;
        this.amount = amount;
    }
}
