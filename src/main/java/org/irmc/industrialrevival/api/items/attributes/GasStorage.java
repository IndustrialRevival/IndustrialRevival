package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.pigeonlib.objects.percentage.PositiveHundredPercentage;

public interface GasStorage extends ChemReactable {

    CatheterInsertionMethod getCatheterInsertionMethod();

    void setCatheterInsertionMethod(CatheterInsertionMethod catheterInsertionMethod);

    PositiveHundredPercentage getGasStorageCapacity();

    enum CatheterInsertionMethod {
        NONE,
        UP,
        DOWN,
        WATER_INSIDE_UP,
        WATER_INSIDE_DOWN
    }
}
