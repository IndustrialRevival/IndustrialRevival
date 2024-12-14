package org.irmc.industrialrevival.api.items.handlers;

import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRItemKillEntityEvent;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ItemKillEntityHandler extends ItemHandler {
    void onKill(IRItemKillEntityEvent event);

    @Override
    default IncompatibleItemHandlerException isCompatible(@NotNull IndustrialRevivalItem item) {
        if (!item.getItem().getItemStack().getType().isItem()) {
            return new IncompatibleItemHandlerException(
                    "Only materials that are items can have a use item kill entity handler", item.getId());
        }

        return null;
    }

    default @NotNull Class<? extends ItemHandler> getIdentifier() {
        return ItemKillEntityHandler.class;
    }
}
