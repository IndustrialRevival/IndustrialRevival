package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;
import org.jetbrains.annotations.NotNull;

public interface ItemHandler {
    default IncompatibleItemHandlerException isCompatible(@NotNull IndustrialRevivalItem item) {
        return null;
    }
}
