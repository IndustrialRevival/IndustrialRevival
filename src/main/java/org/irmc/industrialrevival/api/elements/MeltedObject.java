package org.irmc.industrialrevival.api.elements;

import lombok.Getter;

@Getter
public class MeltedObject {
    private final MeltedType type;
    private final int amount;

    public MeltedObject(MeltedType type, int amount) {
        this.type = type;
        this.amount = amount;
    }
}
