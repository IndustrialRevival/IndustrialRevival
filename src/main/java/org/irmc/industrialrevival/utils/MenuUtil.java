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

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        return ItemUtils.isItemSimilar(itemStack, CANCEL);
    }

    @Nullable
    public static ItemStack pushItem(@Nonnull MachineMenu machineMenu, @Nonnull ItemStack item, int... slots) {
        if (item == null || item.getType() == Material.AIR) {
            throw new IllegalArgumentException("Cannot push null or AIR");
        }

        int leftAmount = item.getAmount();

        for (int slot : slots) {
            if (leftAmount <= 0) {
                break;
            }

            ItemStack existing = machineMenu.getItem(slot);

            if (existing == null || existing.getType() == Material.AIR) {
                int received = Math.min(leftAmount, item.getMaxStackSize());
                machineMenu.setItem(slot, ItemUtils.cloneItem(item, received));
                leftAmount -= received;
                item.setAmount(Math.max(0, leftAmount));
            } else {
                int existingAmount = existing.getAmount();
                if (existingAmount >= item.getMaxStackSize()) {
                    continue;
                }

                if (!ItemUtils.isItemSimilar(item, existing)) {
                    continue;
                }

                int received = Math.max(0, Math.min(item.getMaxStackSize() - existingAmount, leftAmount));
                leftAmount -= received;
                existing.setAmount(existingAmount + received);
                item.setAmount(leftAmount);
            }
        }

        if (leftAmount > 0) {
            return new CustomItemStack(item, leftAmount);
        } else {
            return null;
        }
    }

    @Nonnull
    public static Map<ItemStack, Integer> pushItem(@Nonnull MachineMenu machineMenu, @Nonnull ItemStack[] items, int... slots) {
        if (items == null || items.length == 0) {
            throw new IllegalArgumentException("Cannot push null or empty array");
        }

        List<ItemStack> listItems = new ArrayList<>();
        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR) {
                listItems.add(item);
            }
        }

        return pushItem(machineMenu, listItems, slots);
    }

    @Nonnull
    public static Map<ItemStack, Integer> pushItem(@Nonnull MachineMenu machineMenu, @Nonnull List<ItemStack> items, int... slots) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Cannot push null or empty list");
        }

        Map<ItemStack, Integer> itemMap = new HashMap<>();
        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR) {
                ItemStack leftOver = pushItem(machineMenu, item, slots);
                if (leftOver != null) {
                    itemMap.put(leftOver, itemMap.getOrDefault(leftOver, 0) + leftOver.getAmount());
                }
            }
        }

        return itemMap;
    }

    public static boolean fits(@Nonnull MachineMenu machineMenu, @Nonnull ItemStack item, int... slots) {
        if (item.getType() == Material.AIR) {
            return true;
        }

        int incoming = item.getAmount();
        for (int slot : slots) {
            ItemStack stack = machineMenu.getItem(slot);

            if (stack == null || stack.getType() == Material.AIR) {
                incoming -= item.getMaxStackSize();
            } else if (stack.getMaxStackSize() > stack.getAmount() && ItemUtils.isItemSimilar(item, stack)) {
                incoming -= stack.getMaxStackSize() - stack.getAmount();
            }

            if (incoming <= 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean fits(@Nonnull MachineMenu machineMenu, @Nonnull ItemStack[] items, int... slots) {
        if (items.length == 0) {
            return false;
        }

        List<ItemStack> listItems = new ArrayList<>();
        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR) {
                listItems.add(item.clone());
            }
        }

        return fits(machineMenu, listItems, slots);
    }

    public static boolean fits(@Nonnull MachineMenu machineMenu, @Nonnull List<ItemStack> items, int... slots) {
        if (items.isEmpty()) {
            return false;
        }

        List<ItemStack> cloneMenu = new ArrayList<>();
        for (int i = 0; i < 54; i++) {
            cloneMenu.add(null);
        }

        for (int slot : slots) {
            ItemStack stack = machineMenu.getItem(slot);
            if (stack != null && stack.getType() != Material.AIR) {
                cloneMenu.set(slot, stack.clone());
            } else {
                cloneMenu.set(slot, null);
            }
        }

        for (ItemStack rawItem : items) {
            ItemStack item = rawItem.clone();
            int leftAmount = item.getAmount();
            for (int slot : slots) {
                if (leftAmount <= 0) {
                    break;
                }

                ItemStack existing = cloneMenu.get(slot);

                if (existing == null || existing.getType() == Material.AIR) {
                    int received = Math.min(leftAmount, item.getMaxStackSize());
                    cloneMenu.set(slot, ItemUtils.cloneItem(item, leftAmount));
                    leftAmount -= received;
                    item.setAmount(Math.max(0, leftAmount));
                } else {
                    int existingAmount = existing.getAmount();
                    if (existingAmount >= item.getMaxStackSize()) {
                        continue;
                    }

                    if (!ItemUtils.isItemSimilar(item, existing)) {
                        continue;
                    }

                    int received = Math.max(0, Math.min(item.getMaxStackSize() - existingAmount, leftAmount));
                    leftAmount -= received;
                    existing.setAmount(existingAmount + received);
                    item.setAmount(leftAmount);
                }
            }

            if (leftAmount > 0) {
                return false;
            }
        }

        return true;
    }
}
