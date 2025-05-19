package org.irmc.industrialrevival.api.menu.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

/**
 * @author lijinhong11
 */
@FunctionalInterface
public interface ClickHandler {
    /**
     * Don't move my item!
     */
    ClickHandler DEFAULT = (_, _, _, _, _) -> false;

    /**
     * Click handler that does nothing.
     */
    ClickHandler NOPE = (_, _, _, _, _) -> true;

    /**
     * Called when an item in the machine menu is clicked.
     *
     * @param player      the player who clicked the item
     * @param clickedItem the clicked item
     * @param clickedSlot the slot where the item was clicked
     * @param clickedMenu the machine menu where the item was clicked
     * @param clickType   the click type
     * @return false if the click should be canceled, true otherwise
     */
    boolean onClick(
            @NotNull Player player,
            @Nullable ItemStack clickedItem,
            @Range(from = 0, to = 53) int clickedSlot,
            @NotNull SimpleMenu clickedMenu,
            @NotNull ClickType clickType);
}
