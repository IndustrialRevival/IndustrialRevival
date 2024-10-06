package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.api.objects.enums.ItemFlow;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

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

    @NotNull
    public static Map<ItemStack, Integer> getMenuItemsByItemFlow(@NotNull MachineMenu menu, @NotNull ItemFlow itemFlow, @Nullable ItemStack itemStack) {
        final int[] slots = menu.getPreset().getSlotsByItemFlow(itemFlow, itemStack);
        final Map<ItemStack, Integer> items = new HashMap<>();
        for (int slot : slots) {
            final ItemStack itemInSlot = menu.getItem(slot);
            if (itemInSlot != null && itemInSlot.getType() != Material.AIR) {
                items.merge(itemInSlot, itemInSlot.getAmount(), Integer::sum);
            }
        }
        return items;
    }

    @NotNull
    public static Map<ItemStack, Integer> getMenuItemsByItemFlow(@NotNull SimpleMenu menu, @NotNull ItemFlow itemFlow, @Nullable ItemStack itemStack) {
        if (menu instanceof MachineMenu machineMenu) {
            return getMenuItemsByItemFlow(machineMenu, itemFlow, itemStack);
        } else {
            return new HashMap<>();
        }
    }

    public static boolean isBackground(@NotNull ItemStack itemStack) {
        if (ItemUtils.isItemSimilar(itemStack, BACKGROUND)) {
            return true;
        }

        if (ItemUtils.isItemSimilar(itemStack, INPUT_BORDER)) {
            return true;
        }

        if (ItemUtils.isItemSimilar(itemStack, OUTPUT_BORDER)) {
            return true;
        }

        if (ItemUtils.isItemSimilar(itemStack, CLICKER_BORDER)) {
            return true;
        }

        if (ItemUtils.isItemSimilar(itemStack, CONFIRM)) {
            return true;
        }

        if (ItemUtils.isItemSimilar(itemStack, CANCEL)) {
            return true;
        }

        return false;
    }
}
