package org.irmc.industrialrevival.api.items;

public interface ItemAttribute {
    default boolean isCompatible(IndustrialRevivalItem item) {
        return true;
    }
}
