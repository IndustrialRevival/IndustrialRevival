package org.irmc.industrialrevival.api.items;

import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;

public interface ItemHandler {
    default IncompatibleItemHandlerException isCompatible(IndustrialRevivalItem item) {
        return null;
    }
}
