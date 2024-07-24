package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;

public interface ItemHandler {
    default IncompatibleItemHandlerException isCompatible(IndustrialRevivalItem item) {
        return null;
    }
}
