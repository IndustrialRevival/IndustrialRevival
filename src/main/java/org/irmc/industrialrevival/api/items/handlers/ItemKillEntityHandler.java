package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ItemKillEntityHandler extends ItemHandler {
    void onKill(@NotNull EntityDeathEvent event, @NotNull Player killer, @NotNull IndustrialRevivalItem item);
    @Override
    default IncompatibleItemHandlerException isCompatible(@NotNull IndustrialRevivalItem item) {
        if (!item.getItem().getType().isItem()) {
            return new IncompatibleItemHandlerException(
                    "Only materials that are items can have a use item kill entity handler", item.getId());
        }

        return null;
    }
}
