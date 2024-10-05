package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.objects.CustomItemStack;

@SuppressWarnings({"deprecation", "unused"})
@UtilityClass
public class MenuUtil {
    public static final ItemStack BACKGROUND = new CustomItemStack(
            Material.BLACK_STAINED_GLASS_PANE,
            "",
            ""
    ).toPureItemStack();

    public static final ItemStack INPUT_BORDER = new CustomItemStack(
            Material.BLUE_STAINED_GLASS_PANE,
            "",
            ""
    ).toPureItemStack();

    public static final ItemStack OUTPUT_BORDER = new CustomItemStack(
            Material.ORANGE_STAINED_GLASS_PANE,
            "",
            ""
    ).toPureItemStack();

    public static final ItemStack CLICKER_BORDER = new CustomItemStack(
            Material.YELLOW_STAINED_GLASS_PANE,
            "",
            ""
    ).toPureItemStack();

    public static final ItemStack CONFIRM = new CustomItemStack(
            Material.LIME_STAINED_GLASS_PANE,
            ChatColor.GREEN + "Confirm",
            ChatColor.GRAY + "Click to confirm."
    ).toPureItemStack();

    public static final ItemStack CANCEL = new CustomItemStack(
            Material.RED_STAINED_GLASS_PANE,
            ChatColor.RED + "Cancel",
            ChatColor.GRAY + "Click to cancel."
    ).toPureItemStack();
}
