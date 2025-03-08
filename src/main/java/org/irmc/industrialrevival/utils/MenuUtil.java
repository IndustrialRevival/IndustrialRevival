package org.irmc.industrialrevival.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.irmc.industrialrevival.api.machines.process.IOperation;
import org.irmc.industrialrevival.api.machines.process.MachineOperation;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
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

/**
 * This utility class provides methods for managing menus and item interactions in the IndustrialRevival plugin.
 * It includes functionalities for drawing menus, handling item flows, pushing items into menus, and checking if items fit into specific slots.
 *
 * @author balugaq
 */
@SuppressWarnings({"deprecation", "unused"})
@UtilityClass
public class MenuUtil {
    /**
     * A predefined matrix menu drawer for classic-style menus.
     */
    public static final MatrixMenuDrawer CLASSIC_MENU = new MatrixMenuDrawer(54)
            .addLine("BBBBBBBBB")
            .addLine("IIIIPOOOO")
            .addLine("IiiIBOooO")
            .addLine("IiiIBOooO")
            .addLine("IIIIBOOOO")
            .addLine("BBBBBBBBB");

    /**
     * A background item used in menus.
     */
    public static final ItemStack BACKGROUND = new CustomItemStack(
            Material.BLACK_STAINED_GLASS_PANE,
            "",
            ""
    ).toPureItemStack();

    /**
     * An input border item used in menus.
     */
    public static final ItemStack INPUT_BORDER = new CustomItemStack(
            Material.BLUE_STAINED_GLASS_PANE,
            "",
            ""
    ).toPureItemStack();

    /**
     * An output border item used in menus.
     */
    public static final ItemStack OUTPUT_BORDER = new CustomItemStack(
            Material.ORANGE_STAINED_GLASS_PANE,
            "",
            ""
    ).toPureItemStack();

    /**
     * A clicker border item used in menus.
     */
    public static final ItemStack CLICKER_BORDER = new CustomItemStack(
            Material.YELLOW_STAINED_GLASS_PANE,
            "",
            ""
    ).toPureItemStack();

    /**
     * A confirm button item used in menus.
     */
    public static final ItemStack CONFIRM = new CustomItemStack(
            Material.LIME_STAINED_GLASS_PANE,
            ChatColor.GREEN + "Confirm",
            ChatColor.GRAY + "Click to confirm."
    ).toPureItemStack();

    /**
     * A cancel button item used in menus.
     */
    public static final ItemStack CANCEL = new CustomItemStack(
            Material.RED_STAINED_GLASS_PANE,
            ChatColor.RED + "Cancel",
            ChatColor.GRAY + "Click to cancel."
    ).toPureItemStack();

    /**
     * Retrieves items from a menu based on the specified item flow and item stack.
     *
     * @param menu      The menu to retrieve items from.
     * @param itemFlow  The item flow to filter by.
     * @param itemStack The item stack to filter by.
     * @return A map of item stacks and their amounts.
     */
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

    /**
     * Retrieves items from a simple menu based on the specified item flow and item stack.
     *
     * @param menu      The menu to retrieve items from.
     * @param itemFlow  The item flow to filter by.
     * @param itemStack The item stack to filter by.
     * @return A map of item stacks and their amounts.
     */
    @NotNull
    public static Map<ItemStack, Integer> getMenuItemsByItemFlow(@NotNull SimpleMenu menu, @NotNull ItemFlow itemFlow, @Nullable ItemStack itemStack) {
        if (menu instanceof MachineMenu machineMenu) {
            return getMenuItemsByItemFlow(machineMenu, itemFlow, itemStack);
        } else {
            return new HashMap<>();
        }
    }

    /**
     * Checks if an item stack is a background item.
     *
     * @param itemStack The item stack to check.
     * @return True if the item stack is a background item, false otherwise.
     */
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

    /**
     * Pushes an item into a menu at the specified slots.
     *
     * @param simpleMenu The menu to push the item into.
     * @param item       The item to push.
     * @param slots      The slots to push the item into.
     * @return The remaining item stack if it couldn't be fully pushed, or null if it was fully pushed.
     */
    @Nullable
    public static ItemStack pushItem(@Nonnull SimpleMenu simpleMenu, @Nonnull ItemStack item, int... slots) {
        if (item == null || item.getType() == Material.AIR) {
            throw new IllegalArgumentException("Cannot push null or AIR");
        }

        int leftAmount = item.getAmount();

        for (int slot : slots) {
            if (leftAmount <= 0) {
                break;
            }

            ItemStack existing = simpleMenu.getItem(slot);

            if (existing == null || existing.getType() == Material.AIR) {
                int received = Math.min(leftAmount, item.getMaxStackSize());
                simpleMenu.setItem(slot, ItemUtils.cloneItem(item, received));
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

    /**
     * Pushes multiple items into a menu at the specified slots.
     *
     * @param simpleMenu The menu to push the items into.
     * @param items      The items to push.
     * @param slots      The slots to push the items into.
     * @return A map of remaining item stacks and their amounts.
     */
    @Nonnull
    public static Map<ItemStack, Integer> pushItem(@Nonnull SimpleMenu simpleMenu, @Nonnull ItemStack[] items, int... slots) {
        if (items == null || items.length == 0) {
            throw new IllegalArgumentException("Cannot push null or empty array");
        }

        List<ItemStack> listItems = new ArrayList<>();
        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR) {
                listItems.add(item);
            }
        }

        return pushItem(simpleMenu, listItems, slots);
    }

    /**
     * Pushes a list of item stacks into the specified slots of a SimpleMenu.
     * If the items cannot be fully pushed into the slots, the remaining items are returned in a map.
     *
     * @param simpleMenu The SimpleMenu to push the items into.
     * @param items      The list of item stacks to push.
     * @param slots      The slots in the menu where the items should be pushed.
     * @return A map of remaining item stacks and their amounts if they couldn't be fully pushed, otherwise an empty map.
     * @throws IllegalArgumentException If the items list is null or empty.
     */
    @Nonnull
    public static Map<ItemStack, Integer> pushItem(@Nonnull SimpleMenu simpleMenu, @Nonnull List<ItemStack> items, int... slots) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Cannot push null or empty list");
        }

        Map<ItemStack, Integer> itemMap = new HashMap<>();
        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR) {
                ItemStack leftOver = pushItem(simpleMenu, item, slots);
                if (leftOver != null) {
                    itemMap.put(leftOver, itemMap.getOrDefault(leftOver, 0) + leftOver.getAmount());
                }
            }
        }

        return itemMap;
    }

    /**
     * Pushes a map of item stacks into the specified slots of a SimpleMenu.
     * The map contains item stacks as keys and their amounts as values.
     * If the items cannot be fully pushed into the slots, the remaining items are returned in a map.
     *
     * @param simpleMenu The SimpleMenu to push the items into.
     * @param items      The map of item stacks and their amounts to push.
     * @param slots      The slots in the menu where the items should be pushed.
     * @return A map of remaining item stacks and their amounts if they couldn't be fully pushed, otherwise an empty map.
     * @throws IllegalArgumentException If the items map is null or empty.
     */
    @Nonnull
    public static Map<ItemStack, Integer> pushItem(@Nonnull SimpleMenu simpleMenu, @Nonnull Map<ItemStack, Integer> items, int... slots) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Cannot push null or empty map");
        }

        List<ItemStack> listItems = new ArrayList<>();
        for (Map.Entry<ItemStack, Integer> entry : items.entrySet()) {
            ItemStack item = entry.getKey();
            if (item != null && item.getType() != Material.AIR) {
                listItems.add(item.asQuantity(entry.getValue()));
            }
        }

        return pushItem(simpleMenu, listItems, slots);
    }

    /**
     * Checks if an item stack can fit into the specified slots of a SimpleMenu.
     *
     * @param simpleMenu The SimpleMenu to check.
     * @param item       The item stack to check.
     * @param slots      The slots in the menu to check.
     * @return True if the item can fit into the slots, false otherwise.
     */
    public static boolean fits(@Nonnull SimpleMenu simpleMenu, @Nonnull ItemStack item, int... slots) {
        if (item.getType() == Material.AIR) {
            return true;
        }

        int incoming = item.getAmount();
        for (int slot : slots) {
            ItemStack stack = simpleMenu.getItem(slot);

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

    /**
     * Checks if an array of item stacks can fit into the specified slots of a SimpleMenu.
     *
     * @param simpleMenu The SimpleMenu to check.
     * @param items      The array of item stacks to check.
     * @param slots      The slots in the menu to check.
     * @return True if all items can fit into the slots, false otherwise.
     */
    public static boolean fits(@Nonnull SimpleMenu simpleMenu, @Nonnull ItemStack[] items, int... slots) {
        if (items.length == 0) {
            return false;
        }

        List<ItemStack> listItems = new ArrayList<>();
        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR) {
                listItems.add(item.clone());
            }
        }

        return fits(simpleMenu, listItems, slots);
    }

    /**
     * Checks if a list of item stacks can fit into the specified slots of a SimpleMenu.
     *
     * @param simpleMenu The SimpleMenu to check.
     * @param items      The list of item stacks to check.
     * @param slots      The slots in the menu to check.
     * @return True if all items can fit into the slots, false otherwise.
     */
    public static boolean fits(@Nonnull SimpleMenu simpleMenu, @Nonnull List<ItemStack> items, int... slots) {
        if (items.isEmpty()) {
            return false;
        }

        List<ItemStack> cloneMenu = new ArrayList<>();
        for (int i = 0; i < 54; i++) {
            cloneMenu.add(null);
        }

        for (int slot : slots) {
            ItemStack stack = simpleMenu.getItem(slot);
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

    /**
     * Checks if a map of item stacks can fit into the specified slots of a SimpleMenu.
     * @param simpleMenu The SimpleMenu to check.
     * @param items      The map of item stacks and their amounts to check.
     * @param slots      The slots in the menu to check.
     * @return True if all items can fit into the slots, false otherwise.
     */
    public static boolean fits(@Nonnull SimpleMenu simpleMenu, @Nonnull Map<ItemStack, Integer> items, int... slots) {
        List<ItemStack> listItems = new ArrayList<>();
        for (Map.Entry<ItemStack, Integer> entry : items.entrySet()) {
            ItemStack item = entry.getKey();
            if (item != null && item.getType() != Material.AIR) {
                listItems.add(item.asQuantity(entry.getValue()));
            }
        }
        return fits(simpleMenu, listItems, slots);
    }

    /**
     * Gets a progress bar item for the specified operation.
     * @param material  The material of the progress bar.
     * @param operation The operation to generate the progress.
     * @return The progress bar item.
     */
    public static ItemStack getProgressBar(@Nonnull Material material, @Nonnull IOperation operation) {
        int current = operation.getCurrentProgress();
        int max = operation.getTotalProgress();
        int currentPercentage = (int) (current * 100.0f / max);
        String lore = "" + ChatColor.GREEN;
        for (int i = 0; i < currentPercentage / 10; i++) {
            lore += "=";
        }
        lore += ChatColor.GRAY;
        for (int i = 0; i < (100 - currentPercentage) / 10; i++) {
            lore += "=";
        }
        lore += " " + current + " / " + max + " (" + currentPercentage + "%)";

        ItemStack itemStack = new CustomItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        if (meta instanceof Damageable damageable) {
            damageable.setDamage(damageable.getMaxDamage() * currentPercentage / 100);
        }
        itemStack.setItemMeta(meta);
        itemStack.setLore(List.of(lore));
        return itemStack;
    }
}
