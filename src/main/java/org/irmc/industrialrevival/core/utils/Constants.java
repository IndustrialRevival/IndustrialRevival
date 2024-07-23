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
    public static final String GUIDE_CHEAT_KEY = "guide.cheat_title";

    public static final File STORAGE_FOLDER = new File(
            IndustrialRevival.getInstance().getDataFolder().getParentFile().getParentFile(), "irstorage");

    public static final NamespacedKey ITEM_ID_KEY = new NamespacedKey(IndustrialRevival.getInstance(), "ir_item_id");

    public static final ItemStack BACKGROUND_ITEM =
            new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ").setCustomModel(10000);

    public static final Function<Player, ItemStack> BACK_BUTTON = p -> new CustomItemStack(
            Material.ENCHANTED_BOOK,
            IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, GUIDE_BACK_KEY));
}
