package org.irmc.industrialrevival.core.utils;

import java.io.File;
import java.util.function.Function;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.core.IndustrialRevival;

public class Constants {
    public static final String GUIDE_TITLE_KEY = "guide.title";
    public static final String GUIDE_BACK_KEY = "guide.back";
    public static final String GUIDE_NEXT_KEY = "guide.next";
    public static final String GUIDE_PREVIOUS_KEY = "guide.previous";
    public static final String GUIDE_CHEAT_KEY = "guide.cheat_title";
    public static final String GUIDE_SETTINGS_KEY = "guide.settings";
    public static final String GUIDE_BOOKMARKS_KEY = "guide.bookmarks";
    public static final String GUIDE_SEARCH_KEY = "guide.search";

    public static final File STORAGE_FOLDER = new File(
            IndustrialRevival.getInstance().getDataFolder().getParentFile().getParentFile(), "irstorage");

    public static final NamespacedKey ITEM_ID_KEY = new NamespacedKey(IndustrialRevival.getInstance(), "ir_item_id");

    public static final ItemStack BACKGROUND_ITEM =
            new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ").setCustomModel(19990);

    public static final Function<Player, ItemStack> BACK_BUTTON = p -> new CustomItemStack(
            Material.ENCHANTED_BOOK,
            IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, GUIDE_BACK_KEY)
    ).setCustomModel(19991);

    public static final Function<Player, ItemStack> SETTING_BUTTON = p -> new CustomItemStack(
        Material.ENCHANTED_BOOK,
        IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, GUIDE_SETTINGS_KEY)
    ).setCustomModel(19992);

    public static final Function<Player, ItemStack> BOOKMARK_BUTTON = p -> new CustomItemStack(
        Material.WRITABLE_BOOK,
        IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, GUIDE_BOOKMARKS_KEY)
    ).setCustomModel(19993);

    public static final Function<Player, ItemStack> SEARCH_BUTTON = p -> new CustomItemStack(
        Material.COMPASS,
        IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, GUIDE_SEARCH_KEY)
    ).setCustomModel(19994);

    public static final Function<Player, ItemStack> PREVIOUS_BUTTON = p -> new CustomItemStack(
            Material.LIME_STAINED_GLASS_PANE,
            IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, GUIDE_PREVIOUS_KEY)
    ).setCustomModel(19995);

    public static final Function<Player, ItemStack> NEXT_BUTTON = p -> new CustomItemStack(
            Material.LIME_STAINED_GLASS_PANE,
            IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, GUIDE_NEXT_KEY)
    ).setCustomModel(19996);
}
