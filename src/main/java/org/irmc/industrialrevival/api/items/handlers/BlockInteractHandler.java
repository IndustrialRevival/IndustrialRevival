package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerInteractIRBlockEvent;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface BlockInteractHandler extends ItemHandler {
    /**
     * Called when a player clicks on a block with the item.
     *
     * @param event the {@link PlayerInteractEvent} was triggered
     */
    void onBlockUse(PlayerInteractIRBlockEvent event);

    @Override
    default IncompatibleItemHandlerException isCompatible(@NotNull IndustrialRevivalItem item) {
        if (!item.getItem().getItemStack().getType().isBlock()) {
            return new IncompatibleItemHandlerException(
                    "Only materials that are blocks can have a block use handler", item.getId());
        }
        if (item instanceof NotPlaceable) {
            return new IncompatibleItemHandlerException("This item cannot be placed", item.getId());
        }
        return null;
    }

    default Class<? extends ItemHandler> getIdentifier() {
        return BlockInteractHandler.class;
    }
}
