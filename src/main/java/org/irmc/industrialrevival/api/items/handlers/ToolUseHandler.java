package org.irmc.industrialrevival.api.items.handlers;

import java.util.List;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.exceptions.IncompatibleItemHandlerException;

@FunctionalInterface
public interface ToolUseHandler extends ItemHandler {
    /**
     * Called when a tool is used on a block.
     * <br/>
     * <b>Note: block drop item doesn't add to the drops list.</b>
     *
     * @param event     the {@link BlockBreakEvent} that was fired
     * @param tool  the {@link ItemStack} that was used as a tool
     * @param drops the list of {@link ItemStack}s that will be dropped by the block
     */
    void onToolUse(BlockBreakEvent event, ItemStack tool, List<ItemStack> drops);

    @Override
    default IncompatibleItemHandlerException isCompatible(IndustrialRevivalItem item) {
        if (!item.getItem().getType().isItem()) {
            return new IncompatibleItemHandlerException(
                    "Only materials that are items can have a block use handler", item.getId());
        }

        return null;
    }
}
