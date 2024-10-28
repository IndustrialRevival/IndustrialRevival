package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRItemInteractEvent;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ItemInteractHandler extends ItemHandler {
    void onInteract(@NotNull IRItemInteractEvent event);

    @Override
    default IncompatibleItemHandlerException isCompatible(@NotNull IndustrialRevivalItem item) {
        if (!item.getItem().getType().isItem()) {
            return new IncompatibleItemHandlerException(
                    "Only materials that are items can have a use item interact handler", item.getId());
        }

        return null;
    }
}
