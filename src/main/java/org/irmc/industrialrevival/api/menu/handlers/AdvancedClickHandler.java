package org.irmc.industrialrevival.api.menu.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

/**
 * @author lijinhong11
 */
@FunctionalInterface
public interface AdvancedClickHandler extends ClickHandler {
    /**
     * Called when an item in the machine menu is clicked.
     *
     * @param player      the player who clicked the item
     * @param clickedItem the clicked item
     * @param clickedSlot the slot where the item was clicked
     * @param clickedMenu the machine menu where the item was clicked
     * @param clickType   the click type
     * @return always false, use {@link #onClick(Player, ItemStack, int, SimpleMenu, ClickType, InventoryClickEvent)}
     */

    @Override
    default boolean onClick(
            @NotNull Player player,
            @Nullable ItemStack clickedItem,
            @Range(from = 0, to = 53) int clickedSlot,
            @NotNull SimpleMenu clickedMenu,
            @NotNull ClickType clickType) {
        return false;
    }

    boolean onClick(
            @NotNull Player player,
            @Nullable ItemStack item,
            @Range(from = 0, to = 53) int slot,
            @NotNull SimpleMenu menu,
            @NotNull ClickType clickType,
            @NotNull InventoryClickEvent event);
}
