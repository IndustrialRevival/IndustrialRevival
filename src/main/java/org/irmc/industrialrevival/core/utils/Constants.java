package org.irmc.industrialrevival.core.utils;

import java.io.File;
import org.bukkit.Material;
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
    public static final ItemStack BACKGROUND_ITEM =
            new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ").setCustomModel(10000);
}
