package org.irmc.industrialrevival.api.items.attributes;

public interface GasStorage extends ChemReactable {

    CatheterInsertionMethod getCatheterInsertionMethod();

    enum CatheterInsertionMethod {
        NONE,
        UP,
        DOWN,
        WATER_INSIDE
    }
}
