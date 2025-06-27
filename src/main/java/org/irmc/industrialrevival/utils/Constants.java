package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.objects.enums.GuideMode;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.pigeonlib.items.CustomItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * This class contains constant values used throughout the IndustrialRevival plugin.
 * It is structured into nested classes to organize related constants.
 *
 * @author balugaq
 * @author lijinhong11
 * @since 1.0
 */
@UtilityClass
public class Constants {

    /**
     * This class contains NamespacedKey constants used for PersistentDataContainer (PDC) keys.
     */
    public static final class ItemStackKeys {
        /**
         * Key for storing the item ID in PDC.
         */
        public static final NamespacedKey ITEM_ID_KEY = KeyUtil.customKey("ir_item_id");

        /**
         * Key for storing the radiation level in PDC.
         */
        public static final NamespacedKey RADIATION_LEVEL_KEY = KeyUtil.customKey("ir_radiation_level");

        /**
         * Key for identifying guide items in PDC.
         */
        public static final NamespacedKey GUIDE_ITEM_KEY = KeyUtil.customKey("ir_guide_item");

        /**
         * Key for storing the cleaned item ID in PDC.
         */
        public static final NamespacedKey CLEANED_IR_ITEM_ID = KeyUtil.customKey("cleaned_ir_item_id");

        /**
         * Key for storing the language preference in PDC.
         */
        public static final NamespacedKey LANGUAGE_KEY = KeyUtil.customKey("ir_language");
    }

    /**
     * This class contains predefined ItemStack constants used in the plugin.
     */
    public static final class ItemStacks {
        /**
         * A black stained-glass pane used as a background item in GUIs.
         */
        public static final ItemStack BACKGROUND_ITEM =
                new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ").setCustomModel(19990).getBukkit();

        /**
         * The guide book item used in survival mode.
         */
        public static final ItemStack GUIDE_BOOK_ITEM = new CustomItemStack(
                Material.ENCHANTED_BOOK,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(null, "guide.book_item_name"))
                .setCustomModel(19999)
                .setPDCData(ItemStackKeys.GUIDE_ITEM_KEY, PersistentDataType.STRING, GuideMode.SURVIVAL.name())
                .getBukkit();

        /**
         * The guide book item used in cheat mode.
         */
        public static final ItemStack CHEAT_GUIDE_BOOK_ITEM = new CustomItemStack(
                Material.ENCHANTED_BOOK,
                IRDock.getPlugin()
                        .getLanguageManager()
                        .getMsgComponent(null, "guide.cheat_book_item_name"))
                .setCustomModel(20000)
                .setPDCData(ItemStackKeys.GUIDE_ITEM_KEY, PersistentDataType.STRING, GuideMode.CHEAT.name())
                .getBukkit();
    }

    /**
     * This class contains button-related constants, represented as functions that generate ItemStacks.
     */
    public static final class Buttons {
        /**
         * Function to generate a back button for the guide GUI.
         */
        public static final Function<Player, ItemStack> BACK_BUTTON = p -> new CustomItemStack(
                Material.ENCHANTED_BOOK,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, Keys.GUIDE_BACK_KEY),
                IRDock.getPlugin().getLanguageManager().getMsgComponentList(p, "guide.back_lore"))
                .setCustomModel(19990)
                .getBukkit();

        /**
         * Function to generate a settings button for the guide GUI.
         */
        public static final Function<Player, ItemStack> SETTING_BUTTON = p -> new CustomItemStack(
                Material.REPEATER,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, Keys.GUIDE_SETTINGS_KEY))
                .setCustomModel(19990)
                .getBukkit();

        /**
         * Function to generate a bookmark button for the guide GUI.
         */
        public static final Function<Player, ItemStack> BOOKMARK_BUTTON = p -> new CustomItemStack(
                Material.WRITABLE_BOOK,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, Keys.GUIDE_BOOKMARKS_KEY))
                .setCustomModel(19991)
                .getBukkit();

        /**
         * Function to generate an "add to bookmark" button for the guide GUI.
         */
        public static final Function<Player, ItemStack> ADD_TO_BOOKMARK_BUTTON = p -> new CustomItemStack(
                Material.PAPER,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, "guide.add_to_bookmarks"))
                .setCustomModel(19992)
                .getBukkit();

        /**
         * Function to generate a search button for the guide GUI.
         */
        public static final Function<Player, ItemStack> SEARCH_BUTTON = p -> new CustomItemStack(
                Material.COMPASS,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, Keys.GUIDE_SEARCH_KEY))
                .setCustomModel(19993)
                .getBukkit();

        /**
         * Function to generate a previous button for the guide GUI.
         */
        public static final Function<Player, ItemStack> PREVIOUS_BUTTON = p -> new CustomItemStack(
                Material.LIME_STAINED_GLASS_PANE,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, Keys.GUIDE_PREVIOUS_KEY))
                .setCustomModel(19994)
                .getBukkit();

        /**
         * Function to generate a next button for the guide GUI.
         */
        public static final Function<Player, ItemStack> NEXT_BUTTON = p -> new CustomItemStack(
                Material.LIME_STAINED_GLASS_PANE,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, Keys.GUIDE_NEXT_KEY))
                .setCustomModel(19995)
                .getBukkit();

        /**
         * Function to generate a "previous one" button for the guide GUI.
         */
        public static final Function<Player, ItemStack> PREVIOUS_ONE_BUTTON = p -> new CustomItemStack(
                Material.LIME_STAINED_GLASS_PANE,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, Keys.GUIDE_PREVIOUS_ONE_KEY))
                .setCustomModel(19996)
                .getBukkit();

        /**
         * Function to generate a wiki page button for the guide GUI.
         */
        public static final Function<Player, ItemStack> WIKI_PAGE_BUTTON = p -> new CustomItemStack(
                Material.BOOK,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, "guide.wiki_page_button"))
                .setCustomModel(19998)
                .getBukkit();

        /**
         * Function to generate a "next one" button for the guide GUI.
         */
        public static final Function<Player, ItemStack> NEXT_ONE_BUTTON = p -> new CustomItemStack(
                Material.LIME_STAINED_GLASS_PANE,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, Keys.GUIDE_NEXT_ONE_KEY))
                .setCustomModel(19997)
                .getBukkit();

        /**
         * Function to generate a history button for the guide GUI.
         */
        public static final Function<Player, ItemStack> HISTORY_BUTTON = p -> new CustomItemStack(
                Material.CLOCK,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, "guide.history_button"))
                .setCustomModel(19997)
                .getBukkit();

        /**
         * Function to generate a language button for the guide GUI.
         */
        public static final Function<Player, ItemStack> LANGUAGE_BUTTON = p -> new CustomItemStack(
                Material.BOOK,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, "guide.language_button"))
                .setCustomModel(19998)
                .getBukkit();

        /**
         * Function to generate a guide mode switch button for the guide GUI.
         */
        public static final BiFunction<Player, IRGuideImplementation, ItemStack> GUIDE_MODE_SWITCH_BUTTON = (p, impl) -> new CustomItemStack(
                Material.COMPASS,
                IRDock.getPlugin().getLanguageManager().getMsgComponent(p, impl.getGuideMode() == GuideMode.SURVIVAL ? "guide.guide_mode_survival_button" : "guide.guide_mode_cheat_button"))
                .setCustomModel(19993)
                .getBukkit();
    }

    /**
     * This class contains key constants used for language and guide-related messages.
     */
    public static final class Keys {
        //<editor-fold desc="Guide">
        public static final String GUIDE_TITLE_KEY = "guide.title";
        public static final String GUIDE_BACK_KEY = "guide.back";
        public static final String GUIDE_CHEAT_KEY = "guide.cheat_title";
        public static final String GUIDE_CHEAT_MODE_NO_PERMISSION_KEY = "guide.cheat_mode_no_permission";
        public static final String GUIDE_SETTINGS_KEY = "guide.settings";
        public static final String GUIDE_BOOKMARKS_KEY = "guide.bookmarks";
        public static final String GUIDE_ADD_TO_BOOKMARKS_KEY = "guide.add_to_bookmarks";
        public static final String GUIDE_SEARCH_KEY = "guide.search";
        public static final String GUIDE_PREVIOUS_KEY = "guide.previous";
        public static final String GUIDE_NEXT_KEY = "guide.next";
        public static final String GUIDE_PREVIOUS_ONE_KEY = "guide.previous_one";
        public static final String GUIDE_NEXT_ONE_KEY = "guide.next_one";
        //</editor-fold>

        //<editor-fold desc="Block data keys">
        public static final String ENERGY_CHARGE_KEY = "energy_charge";
        //</editor-fold>
    }

    /**
     * This class contains permission constants used for command and feature access control.
     */
    public static final class Permissions {
        public static final String BYPASS_ALL = "industrialrevival.admin";
        public static final String COMMAND_HELP = "industrialrevival.cmd.help";
        public static final String COMMAND_GUIDE = "industrialrevival.cmd.guide";
        public static final String COMMAND_CHEAT = "industrialrevival.cmd.cheat";
        public static final String COMMAND_RELOAD = "industrialrevival.cmd.reload";
        public static final String COMMAND_INFO = "industrialrevival.cmd.info";
        public static final String COMMAND_GIVE = "industrialrevival.cmd.give";
        public static final String COMMAND_TIMINGS = "industrialrevival.cmd.timings";
        public static final String COMMAND_CHEMISTRY = "industrialrevival.cmd.chemistry";
    }

    /**
     * This class contains file path constants used for storage and error reporting.
     */
    public static final class Files {
        //<editor-fold desc="Folders">
        /**
         * The folder for storing IndustrialRevival-related data.
         */
        public static final File STORAGE_FOLDER = new File(IRDock.getPlugin().getDataFolder().getParentFile().getParentFile(), "irstorage");

        /**
         * The folder for storing error reports.
         */
        public static final File ERROR_REPORTS_FOLDER = new File(IRDock.getPlugin().getDataFolder(), "error-reports");

        /**
         * The folder for storing language files.
         */
        public static final File LANGUAGES_FOLDER = new File(IRDock.getPlugin().getDataFolder(), "languages");
        //</editor-fold>

        //<editor-fold desc="Files">
        /**
         * The file for storing item settings.
         */
        public static final File ITEM_SETTINGS_FILE = new File(IRDock.getPlugin().getDataFolder(), "items-settings.yml");

        /**
         * The file for storing all the block / player data for SQLite
         */
        public static final File SQLITE_DB_FILE = new File(Constants.Files.STORAGE_FOLDER, "database.db");
        //</editor-fold>
    }

    /**
     * This class contains miscellaneous constants used in the plugin.
     */
    public static final class Misc {
        /**
         * The URL for the IndustrialRevival wiki.
         */
        public static final String WIKI_URL = "https://ir.hiworldmc.com/";
    }

    /**
     * This class contains constants related to the guide GUI layout.
     */
    public static final class Guide {
        /**
         * The slot indices for the guide group borders.
         */
        public static final int[] GUIDE_GROUP_BORDERS = {0, 1, 3, 4, 5, 7, 8, 45, 46, 48, 49, 50, 52, 53};

        /**
         * The slot indices for the guide recipe slots.
         */
        public static final int[] GUIDE_RECIPE_SLOTS = {12, 13, 14, 21, 22, 23, 30, 31, 32};
    }

    /**
     * This class contains constants related to language support in the plugin.
     */
    public static final class Languages {
        /**
         * The list of supported languages.
         */
        public static final List<String> SUPPORTED_LANGUAGES = new ArrayList<>();

        /**
         * The default language for the plugin.
         */
        public static final String DEFAULT_LANGUAGE = "zh-CN";
        /**
         * A map of locale to texture hashcodes.
         */
        private static final Map<String, String> textures = new HashMap<>();
        /**
         * A map of locale to language button ItemStacks.
         */
        private static final Map<String, ItemStack> languages = new HashMap<>();

        static {
            SUPPORTED_LANGUAGES.add("zh-CN");
        }

        static {
            textures.put("zh-CN", "7f9bc035cdc80f1ab5e1198f29f3ad3fdd2b42d9a69aeb64de990681800b98dc");
            textures.put("zh-TW", "702a4afb2e1e2e3a1894a8b74272f95cfa994ce53907f9ac140bd3c932f9f");
            textures.put("en-GB", "879d99d9c46474e2713a7e84a95e4ce7e8ff8ea4d164413a592e4435d2c6f9dc");
            textures.put("en-US", "cd91456877f54bf1ace251e4cee40dba597d2cc40362cb8f4ed711e50b0be5b3");
        }

        static {
            languages.put("zh-CN", getLanguageButton(Locale.SIMPLIFIED_CHINESE));
            languages.put("zh-TW", getLanguageButton(Locale.TRADITIONAL_CHINESE));
            languages.put("en-GB", getLanguageButton(Locale.UK));
            languages.put("en-US", getLanguageButton(Locale.US));
        }

        private static ItemStack getLanguageButton(Locale locale) {
            CustomItemStack cis = new CustomItemStack(Material.PLAYER_HEAD);
            cis.setPDCData(ItemStackKeys.LANGUAGE_KEY, PersistentDataType.STRING, locale.getLanguage());
            // todo: add texture
            return cis.getBukkit();
        }

        /**
         * Returns a map of language buttons.
         *
         * @return A map of locale to language button ItemStacks.
         */
        public static Map<String, ItemStack> getLanguageButtons() {
            return new HashMap<>(languages);
        }

        /**
         * Returns a map of textures for each language.
         *
         * @return A map of locale to texture hashcodes.
         */
        public static Map<String, String> getTextures() {
            return new HashMap<>(textures);
        }
    }
}
