package org.irmc.industrialrevival.api.items.attributes;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;

public interface ItemAttribute {
    default boolean isCompatible(IndustrialRevivalItem item) {
        return true;
    }
}
