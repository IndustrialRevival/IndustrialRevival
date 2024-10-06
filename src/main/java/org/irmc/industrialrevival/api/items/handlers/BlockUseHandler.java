package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface BlockUseHandler extends ItemHandler {
    /**
     * Called when a player right-clicks on a block with the item.
     *
     * @param event the {@link PlayerInteractEvent} was triggered
     */
    void onRightClick(@NotNull PlayerInteractEvent event);

    @Override
    default IncompatibleItemHandlerException isCompatible(@NotNull IndustrialRevivalItem item) {
        if (!item.getItem().getType().isBlock()) {
            return new IncompatibleItemHandlerException(
                    "Only materials that are blocks can have a block use handler", item.getId());
        }

        return null;
    }
}
