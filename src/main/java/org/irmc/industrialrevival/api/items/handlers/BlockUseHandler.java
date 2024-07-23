package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.ItemHandler;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;

@FunctionalInterface
public interface BlockUseHandler extends ItemHandler {

    void onRightClick();

    @Override
    default IncompatibleItemHandlerException isCompatible(IndustrialRevivalItem item) {
        if (!item.getItem().getType().isBlock()) {
            return new IncompatibleItemHandlerException(
                    "Only materials that are blocks can have a block use handler", item.getId());
        }

        return null;
    }
}
