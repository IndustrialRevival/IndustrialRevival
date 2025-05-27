package org.irmc.industrialrevival.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.attributes.RecipeDisplayItem;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.menu.gui.MainMenu;
import org.irmc.industrialrevival.api.menu.gui.SearchMenu;
import org.irmc.industrialrevival.api.menu.gui.SettingsMenu;
import org.irmc.industrialrevival.api.menu.gui.SimpleRecipeDisplayMenu;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.pigeonlib.items.CustomItemStack;

/**
 * @author balugaq
 */
public class GuideUtil {
    public static final NamespacedKey WIKI_KEY = KeyUtil.customKey("wiki");
    public static void openMainMenu(Player player) {
        new MainMenu(player).open(player);
    }

    public static void addToHistory(GuideHistory guideHistory, SimpleMenu simpleMenu) {
        guideHistory.addMenu(simpleMenu);
    }

    public static ItemStack getSettingsButton(Player player) {
        return new CustomItemStack(
                Material.COMPASS,
                "Settings",
                "nothing"
        ).getBukkit();
    }

    public static ItemStack getWikiButton(String s) {
        return new CustomItemStack(
                Material.KNOWLEDGE_BOOK,
                "Wiki",
                "nothing"
        )
                .setPDCData(WIKI_KEY, PersistentDataType.STRING, s)
                .getBukkit();
    }

    /**
     * Compatible with {@link ClickHandler}
     */
    public static boolean openWiki(Player player, ItemStack itemStack, int slot, SimpleMenu menu, ClickType clickType) {
        return openWiki(player, itemStack);
    }

    /**
     * Opens the wiki page
     * @param player The player
     * @param itemStack The item
     * @return false
     */
    public static boolean openWiki(Player player, ItemStack itemStack) {
        String url = Constants.Misc.WIKI_URL + DataUtil.getPDC(itemStack.getItemMeta(), WIKI_KEY);
        ClickEvent clickEvent = ClickEvent.openUrl(url);
        Component text = IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(player, "misc.wiki_page");
        text = text.clickEvent(clickEvent);

        Component finalText = text;
        player.sendMessage(finalText);
        return false;
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

    public static ItemStack getSearchButton(Player player) {
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

    public static ItemStack getBackButton(Player player) {
        return new CustomItemStack(
                Material.ENCHANTED_BOOK,
                "Back",
                "nothing"
        ).getBukkit();
    }

    /**
     * Compatible with {@link ClickHandler}
     */
    public static boolean backHistory(Player player, ItemStack itemStack, int slot, SimpleMenu menu, ClickType clickType) {
        return backHistory(player);
    }

    /**
     * Goes back in the history
     * @param player The player
     * @return false
     */
    public static boolean backHistory(Player player) {
        PlayerProfile.getProfile(player).getGuideHistory().goBack();
        return false;
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
        if (ir != null) {
            lookup(player, ir, itemStack);
        } else {
            openVanillaRecipeDisplayMenu(player, itemStack);
        }

        return false;
    }

    public static boolean lookup(Player player, IndustrialRevivalItem ir, ItemStack itemStack) {
        if (ir instanceof RecipeDisplayItem rdi) {
            openComplexRecipeDisplayMenu(player, itemStack, ir, rdi);
        } else if (ir != null) {
            openSimpleRecipeDisplayMenu(player, itemStack, ir);
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
