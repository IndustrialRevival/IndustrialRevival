package org.irmc.industrialrevival.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.RecipeDisplayItem;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.menu.gui.SearchMenu;
import org.irmc.industrialrevival.api.menu.gui.SettingsMenu;
import org.irmc.industrialrevival.api.menu.gui.SimpleRecipeDisplayMenu;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.pigeonlib.items.CustomItemStack;

/**
 * @author balugaq
 */
public class GuideUtil {
    public static void addToHistory(GuideHistory guideHistory, SimpleMenu simpleMenu) {
        guideHistory.addMenu(simpleMenu);
    }

    public static ItemStack getSettingsButton() {
        return new CustomItemStack(
                Material.COMPASS,
                "Settings",
                "nothing"
        ).getBukkit();
    }

    /**
     * Compatible with {@link ClickHandler}
     */
    public static boolean openSettings(Player player, ItemStack itemStack, int slot, SimpleMenu menu, ClickType clickType) {
        return openSettings(player);
    }

    /**
     * Opens the settings menu
     * @param player The player
     * @return false
     */
    public static boolean openSettings(Player player) {
        new SettingsMenu(player).open(player);
        return false;
    }

    public static ItemStack getSearchButton() {
        return new CustomItemStack(
                Material.HOPPER,
                "Search items...",
                "nothing"
        ).getBukkit();
    }

    /**
     * Compatible with {@link ClickHandler}
     */
    public static boolean openSearch(Player player, ItemStack itemStack, int slot, SimpleMenu menu, ClickType clickType) {
        return openSearch(player);
    }

    /**
     * Opens the search menu
     * @param player The player
     * @return false
     */
    public static boolean openSearch(Player player) {
        new SearchMenu(player, menu -> menu.open(player));
        return false;
    }

    /**
     * Compatible with {@link ClickHandler}
     */
    public static boolean lookup(Player player, ItemStack itemStack, int slot, SimpleMenu menu, ClickType clickType) {
        return lookup(player, itemStack);
    }

    public static boolean lookup(Player player, ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return false;
        }

        var ir = IndustrialRevivalItem.getByItem(itemStack);
        if (ir instanceof RecipeDisplayItem rdi) {
            openComplexRecipeDisplayMenu(player, itemStack, ir, rdi);
        }
        else if (ir != null) {
            openSimpleRecipeDisplayMenu(player, itemStack, ir);
        }
        else {
            openVanillaRecipeDisplayMenu(player, itemStack);
        }

        return false;
    }

    public static void openComplexRecipeDisplayMenu(Player player, ItemStack itemStack, IndustrialRevivalItem ir, RecipeDisplayItem rdi) {
        // todo:
    }

    public static void openSimpleRecipeDisplayMenu(Player player, ItemStack itemStack, IndustrialRevivalItem ir) {
        new SimpleRecipeDisplayMenu(player, ir).open(player);
    }

    public static void openVanillaRecipeDisplayMenu(Player player, ItemStack itemStack) {
        // todo:
    }
}
