package org.irmc.industrialrevival.api.items.handlers;

import org.bukkit.event.block.BlockBreakEvent;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface BlockBreakHandler extends ItemHandler {
    void onBlockBreak(@NotNull BlockBreakEvent event);

    @Override
    default IncompatibleItemHandlerException isCompatible(IndustrialRevivalItem item) {
        if (!ItemUtils.isActualBlock(item.getItem().getType())) {
            return new IncompatibleItemHandlerException("Only actual blocks can be breaked", item.getId());
        }
        return null;
    }
}
