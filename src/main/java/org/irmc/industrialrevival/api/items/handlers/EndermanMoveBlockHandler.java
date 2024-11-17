package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.objects.events.vanilla.BlockExplodeIRBlockEvent;
import org.irmc.industrialrevival.api.objects.events.vanilla.EndermanMoveIRBlockEvent;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface EndermanMoveBlockHandler extends ItemHandler {
    void onEndermanMoveBlock(@NotNull EndermanMoveIRBlockEvent event);

    @Override
    default IncompatibleItemHandlerException isCompatible(@NotNull IndustrialRevivalItem item) {
        if (!ItemUtils.isActualBlock(item.getItem().getType())) {
            return new IncompatibleItemHandlerException("Only actual blocks can be broken", item.getId());
        }
        if (item instanceof NotPlaceable) {
            return new IncompatibleItemHandlerException("This item cannot be placed", item.getId());
        }
        return null;
    }
    default Class<? extends ItemHandler> getIdentifier() {
        return EndermanMoveBlockHandler.class;
    }
}
