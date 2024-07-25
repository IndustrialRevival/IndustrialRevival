package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.events.PlayerRightClickEvent;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;

@FunctionalInterface
public interface BlockUseHandler extends ItemHandler {

    void onRightClick(PlayerInteractEvent e);

    @Override
    default IncompatibleItemHandlerException isCompatible(IndustrialRevivalItem item) {
        if (!item.getItem().getType().isBlock()) {
            return new IncompatibleItemHandlerException(
                    "Only materials that are blocks can have a block use handler", item.getId());
        }

        return null;
    }
}
