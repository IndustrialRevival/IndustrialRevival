package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.api.objects.enums.GuideMode;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.io.File;
import java.util.function.Function;

@UtilityClass
@SuppressWarnings({"unused"})
public class Constants {
    public static final class ItemStackKeys {
        public static final NamespacedKey ITEM_ID_KEY = KeyUtil.customKey("ir_item_id");
        public static final NamespacedKey RADIATION_LEVEL_KEY = KeyUtil.customKey("ir_radiation_level");
        public static final NamespacedKey GUIDE_ITEM_KEY = KeyUtil.customKey("ir_guide_item");
        public static final NamespacedKey CLEANED_IR_ITEM_ID = KeyUtil.customKey("cleaned_ir_item_id");
    }
    public static final class ItemStacks {
        public static final ItemStack BACKGROUND_ITEM =
                new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ").setCustomModel(19990);
        public static final ItemStack GUIDE_BOOK_ITEM = new CustomItemStack(
                Material.ENCHANTED_BOOK,
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(null, "guide.book_item_name"))
                .setCustomModel(19999)
                .setPDCData(ItemStackKeys.GUIDE_ITEM_KEY, PersistentDataType.STRING, GuideMode.SURVIVAL.name());
        public static final ItemStack CHEAT_GUIDE_BOOK_ITEM = new CustomItemStack(
                Material.ENCHANTED_BOOK,
                IndustrialRevival.getInstance()
                        .getLanguageManager()
                        .getMsgComponent(null, "guide.cheat_book_item_name"))
                .setCustomModel(20000)
                .setPDCData(ItemStackKeys.GUIDE_ITEM_KEY, PersistentDataType.STRING, GuideMode.CHEAT.name());
    }
    public static final class Buttons {
        public static final Function<Player, ItemStack> BACK_BUTTON = p -> new CustomItemStack(
                Material.ENCHANTED_BOOK,
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Keys.GUIDE_BACK_KEY),
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponentList(p, "guide.back_lore"))
                .setCustomModel(19990);
        public static final Function<Player, ItemStack> SETTING_BUTTON = p -> new CustomItemStack(
                Material.REPEATER,
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Keys.GUIDE_SETTINGS_KEY))
                .setCustomModel(19990);
        public static final Function<Player, ItemStack> BOOKMARK_BUTTON = p -> new CustomItemStack(
                Material.WRITABLE_BOOK,
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Keys.GUIDE_BOOKMARKS_KEY))
                .setCustomModel(19991);
        public static final Function<Player, ItemStack> ADD_TO_BOOKMARK_BUTTON = p -> new CustomItemStack(
                Material.PAPER,
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, "guide.add_to_bookmarks"))
                .setCustomModel(19992);
        public static final Function<Player, ItemStack> SEARCH_BUTTON = p -> new CustomItemStack(
                Material.COMPASS,
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Keys.GUIDE_SEARCH_KEY))
                .setCustomModel(19993);
        public static final Function<Player, ItemStack> PREVIOUS_BUTTON = p -> new CustomItemStack(
                Material.LIME_STAINED_GLASS_PANE,
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Keys.GUIDE_PREVIOUS_KEY))
                .setCustomModel(19994);
        public static final Function<Player, ItemStack> NEXT_BUTTON = p -> new CustomItemStack(
                Material.LIME_STAINED_GLASS_PANE,
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Keys.GUIDE_NEXT_KEY))
                .setCustomModel(19995);
        public static final Function<Player, ItemStack> PREVIOUS_ONE_BUTTON = p -> new CustomItemStack(
                Material.LIME_STAINED_GLASS_PANE,
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Keys.GUIDE_PREVIOUS_ONE_KEY))
                .setCustomModel(19996);
        public static final Function<Player, ItemStack> WIKI_PAGE_BUTTON = p -> new CustomItemStack(
                Material.BOOK,
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, "guide.wiki_page_button"))
                .setCustomModel(19998);
        public static final Function<Player, ItemStack> NEXT_ONE_BUTTON = p -> new CustomItemStack(
                Material.LIME_STAINED_GLASS_PANE,
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Keys.GUIDE_NEXT_ONE_KEY))
                .setCustomModel(19997);
    }
    public static final class Keys {
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
        public static final String ENERGY_CHARGE_KEY = "energy_charge";
    }

    public static final class Permissions {
        public static final String BYPASS_ALL = "industrialrevival.admin";
        public static final String COMMAND_HELP = "industrialrevival.cmd.help";
        public static final String COMMAND_GUIDE = "industrialrevival.cmd.guide";
        public static final String COMMAND_CHEAT = "industrialrevival.cmd.cheat";
        public static final String COMMAND_RELOAD = "industrialrevival.cmd.reload";
        public static final String COMMAND_INFO = "industrialrevival.cmd.info";
        public static final String COMMAND_GIVE = "industrialrevival.cmd.give";
        public static final String COMMAND_TIMINGS = "industrialrevival.cmd.timings";
    }
    public static final class Files {
        // server_folder/irstorage
        public static final File STORAGE_FOLDER = new File(IndustrialRevival.getInstance().getDataFolder().getParentFile().getParentFile(), "irstorage");
        // server_folder/plugins/IndustrialRevival/error-reports
        public static final File ERROR_REPORTS_FOLDER = new File(IndustrialRevival.getInstance().getDataFolder(), "error-reports");
    }

    public static final class Misc {
        public static final String WIKI_URL = "https://ir.hiworldmc.com/";
    }
    public static final class Guide {
        public static final int[] GUIDE_GROUP_BORDERS = {0, 1, 3, 4, 5, 7, 8, 45, 46, 48, 49, 50, 52, 53};
        public static final int[] GUIDE_RECIPE_SLOTS = {12, 13, 14, 21, 22, 23, 30, 31, 32};
    }

}
