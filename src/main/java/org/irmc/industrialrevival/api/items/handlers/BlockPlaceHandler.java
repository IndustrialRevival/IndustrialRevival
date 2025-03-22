package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockPlaceEvent;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface BlockPlaceHandler extends ItemHandler {
    void onBlockPlace(IRBlockPlaceEvent event);

    @Override
    default IncompatibleItemHandlerException isCompatible(@NotNull IndustrialRevivalItem item) {
        if (!ItemUtils.isActualBlock(item.getIcon().getType())) {
            return new IncompatibleItemHandlerException("Only actual blocks can be placed", item.getId());
        }
        if (item instanceof NotPlaceable) {
            return new IncompatibleItemHandlerException("This item cannot be placed", item.getId());
        }
        return null;
    }

    default @NotNull Class<? extends ItemHandler> getIdentifier() {
        return BlockPlaceHandler.class;
    }
}
