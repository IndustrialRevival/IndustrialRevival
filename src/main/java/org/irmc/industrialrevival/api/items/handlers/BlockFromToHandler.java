package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.Material;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.NotPlaceable;
import org.irmc.industrialrevival.api.objects.events.vanilla.IRBlockFromToEvent;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface BlockFromToHandler extends ItemHandler {
    void onBlockFromTo(IRBlockFromToEvent event);

    @Override
    default IncompatibleItemHandlerException isCompatible(@NotNull IndustrialRevivalItem item) {
        Material material = item.getIcon().getType();
        if (material != Material.LAVA_BUCKET && material != Material.WATER_BUCKET && material != Material.DRAGON_EGG) {
            return new IncompatibleItemHandlerException("Item must be liquid or dragon egg", item.getId());
        }

        if (item instanceof NotPlaceable) {
            return new IncompatibleItemHandlerException("This item cannot be placed", item.getId());
        }
        return null;
    }

    default @NotNull Class<? extends ItemHandler> getIdentifier() {
        return BlockFromToHandler.class;
    }
}
