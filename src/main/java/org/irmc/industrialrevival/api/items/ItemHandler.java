package org.irmc.industrialrevival.api.items;

public interface ItemHandler {
    default boolean isCompatible(IndustrialRevivalItem item) {
        return true;
    }
}
