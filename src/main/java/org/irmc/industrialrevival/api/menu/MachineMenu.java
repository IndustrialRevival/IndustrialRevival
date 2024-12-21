package org.irmc.industrialrevival.api.menu;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.irmc.industrialrevival.api.objects.CustomItemStack;
import org.irmc.industrialrevival.api.objects.ItemStackReference;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@SuppressWarnings({"deprecation", "unused"})
@Getter
public class MachineMenu extends SimpleMenu {
    private final Location location;
    private final MachineMenuPreset preset;

    public MachineMenu(@NotNull Location location, @NotNull MachineMenuPreset preset) {
        super(preset.getTitle());
        this.location = location;
        this.preset = preset;
    }

    @NotNull
    public static String getProgressBar(int remainingTicks, int total) {
        StringBuilder sb = new StringBuilder();
        float percentage = Math.round((total - remainingTicks) * 100.0F / total * 100.0F / 100.0F);

        sb.append(getColorFromPercentage(percentage));

        int rest = 16;
        for (int i = (int) percentage; i >= 5; i = i - 5) {
            sb.append(':');
            rest--;
        }

        sb.append("&7");
        sb.append(":".repeat(Math.max(0, rest)));

        sb.append(" - ").append(percentage).append('%');
        return ChatColor.translateAlternateColorCodes('&', sb.toString());
    }

    public static Component getProgressBarComponent(int remainingTicks, int total) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(getProgressBar(remainingTicks, total));
    }

    private static short getDurability(@NotNull ItemStack item, int remainingTicks, int total) {
        return (short) ((item.getType().getMaxDurability() / total) * remainingTicks);
    }

    @NotNull
    public static ChatColor getColorFromPercentage(@Range(from = 0, to = 100) float percentage) {
        if (percentage < 16.0F) {
            return ChatColor.DARK_RED;
        }
        if (percentage < 32.0F) {
            return ChatColor.RED;
        }
        if (percentage < 48.0F) {
            return ChatColor.GOLD;
        }
        if (percentage < 64.0F) {
            return ChatColor.YELLOW;
        }
        if (percentage < 80.0F) {
            return ChatColor.DARK_GREEN;
        }

        return ChatColor.GREEN;
    }

    @NotNull
    public static String getRemainingTime(int seconds) {
        String remainingTime = "";

        int minutes = (int) (seconds / 60L);

        if (minutes > 0) {
            remainingTime += minutes + "m ";
        }

        seconds -= minutes * 60;
        return remainingTime + seconds + "s";
    }

    public void setSize(@Range(from = 9, to = 54) int size) {
        throw new UnsupportedOperationException("Cannot set size of a machine menu");
    }

    public void setTitle(@NotNull Component title) {
        throw new UnsupportedOperationException("Cannot set title of a machine menu");
    }

    public boolean hasViewer() {
        return !getInventory().getViewers().isEmpty();
    }

    @NotNull
    public List<HumanEntity> getViewers() {
        return getInventory().getViewers();
    }

    public void consumeSlot(@Range(from = 0, to = 53) int slot, int amount) {
        ItemStack item = getItem(slot);
        if (item != null && item.getAmount() > 0) {
            item.setAmount(item.getAmount() - amount);
            if (item.getAmount() <= 0) {
                setItem(slot, null);
            }
        }
    }

    public void consumeSlot(@Range(from = 0, to = 53) int... slot) {
        for (int s : slot) {
            setItem(s, null);
        }
    }

    public int consumeItem(@NotNull ItemStack item, @Range(from = 0, to = 53) int... slots) {
        if (item.getType().isAir()) {
            return 0;
        }

        int consumedCount = 0;

        for (int slot : slots) {
            ItemStack itemInSlot = getItem(slot);
            if (itemInSlot != null && ItemUtils.isItemSimilar(itemInSlot, item)) {
                int canConsume = Math.min(itemInSlot.getAmount(), item.getAmount());
                itemInSlot.setAmount(itemInSlot.getAmount() - canConsume);
                item.setAmount(item.getAmount() - canConsume);
                consumedCount += canConsume;
                if (item.getAmount() == 0) {
                    break;
                }
            }
        }

        return consumedCount;
    }

    public int consumeAllItem(@NotNull ItemStack item) {
        return consumeItem(item, IntStream.range(0, getSize()).toArray());
    }

    public int consumeItem(@NotNull ItemStackReference itemRef, int amount, @Range(from = 0, to = 53) int... slots) {
        int consumedCount = 0;
        for (int slot : slots) {
            ItemStack itemInSlot = getItem(slot);
            if (itemInSlot != null && itemInSlot.getType() != Material.AIR && itemRef.itemsMatch(itemInSlot)) {
                int canConsume = Math.min(itemInSlot.getAmount(), amount);
                itemInSlot.setAmount(itemInSlot.getAmount() - canConsume);
                amount -= canConsume;
                consumedCount += canConsume;
                if (amount <= 0) {
                    break;
                }
            }
        }
        return consumedCount;
    }

    @CanIgnoreReturnValue
    public int consumeItem(@NotNull Map<ItemStackReference, Integer> itemRefMap, @Range(from = 0, to = 53) int... slots) {
        int consumedCount = 0;
        for (ItemStackReference itemRef : itemRefMap.keySet()) {
            int amount = itemRefMap.get(itemRef);
            consumedCount += consumeItem(itemRef, amount, slots);
        }

        return consumedCount;
    }

    public void setProgressItem(@Range(from = 0, to = 53) int slot, int remainingTicks, int totalTicks, @NotNull ItemStack progressBarItem) {
        if (!this.hasViewer()) {
            return;
        }

        ItemStack item = progressBarItem.clone();
        ItemMeta im = item.getItemMeta();
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        if (im instanceof Damageable damageable) {
            damageable.setDamage(getDurability(item, remainingTicks, totalTicks));
        }

        im.setDisplayName(" ");
        im.setLore(Arrays.asList(
                getProgressBar(remainingTicks, totalTicks), "", ChatColor.GRAY + getRemainingTime(remainingTicks / 2)));
        item.setItemMeta(im);

        setItem(slot, item);
    }

    @Nullable
    public ItemStack pushItem(@NotNull ItemStack item, @Range(from = 0, to = 53) int... slots) {
        if (item == null || item.getType() == Material.AIR) {
            throw new IllegalArgumentException("Cannot push null or AIR");
        }

        int leftAmount = item.getAmount();

        for (int slot : slots) {
            if (leftAmount <= 0) {
                break;
            }

            ItemStack existing = getItem(slot);

            if (existing == null || existing.getType() == Material.AIR) {
                int received = Math.min(leftAmount, item.getMaxStackSize());
                setItem(slot, ItemUtils.cloneItem(item, received));
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

    @NotNull
    public Map<ItemStack, Integer> pushItem(@NotNull ItemStack[] items, @Range(from = 0, to = 53) int... slots) {
        if (items == null || items.length == 0) {
            throw new IllegalArgumentException("Cannot push null or empty array");
        }

        List<ItemStack> listItems = new ArrayList<>();
        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR) {
                listItems.add(item);
            }
        }

        return pushItem(listItems, slots);
    }

    @NotNull
    public Map<ItemStack, Integer> pushItem(@NotNull List<ItemStack> items, @Range(from = 0, to = 53) int... slots) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Cannot push null or empty list");
        }

        Map<ItemStack, Integer> itemMap = new HashMap<>();
        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR) {
                ItemStack leftOver = pushItem(item, slots);
                if (leftOver != null) {
                    itemMap.put(leftOver, itemMap.getOrDefault(leftOver, 0) + leftOver.getAmount());
                }
            }
        }

        return itemMap;
    }

    @NotNull
    @CanIgnoreReturnValue
    public Map<ItemStack, Integer> pushItem(@NotNull Map<ItemStack, Integer> items, @Range(from = 0, to = 53) int... slots) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Cannot push null or empty map");
        }

        List<ItemStack> listItems = new ArrayList<>();
        for (ItemStack item : items.keySet()) {
            if (item != null && item.getType() != Material.AIR) {
                listItems.add(ItemUtils.cloneItem(item, items.get(item)));
            }
        }

        return pushItem(listItems, slots);
    }

    public boolean fits(@NotNull ItemStack item, @Range(from = 0, to = 53) int... slots) {
        if (item.getType() == Material.AIR) {
            return true;
        }

        int incoming = item.getAmount();
        for (int slot : slots) {
            ItemStack stack = getItem(slot);

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

    public boolean fits(@NotNull ItemStack[] items, @Range(from = 0, to = 53) int... slots) {
        if (items == null || items.length == 0) {
            return false;
        }

        List<ItemStack> listItems = new ArrayList<>();
        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR) {
                listItems.add(item.clone());
            }
        }

        return fits(listItems, slots);
    }

    public boolean fits(@NotNull List<ItemStack> items, @Range(from = 0, to = 53) int... slots) {
        if (items.isEmpty()) {
            return false;
        }

        List<ItemStack> cloneMenu = new ArrayList<>();
        for (int i = 0; i < 54; i++) {
            cloneMenu.add(null);
        }

        for (int slot : slots) {
            ItemStack stack = getItem(slot);
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

    public boolean fits(@NotNull Map<ItemStack, Integer> items, @Range(from = 0, to = 53) int... slots) {
        if (items.isEmpty()) {
            return false;
        }

        List<ItemStack> listItems = new ArrayList<>();
        for (ItemStack item : items.keySet()) {
            if (item != null && item.getType() != Material.AIR) {
                listItems.add(ItemUtils.cloneItem(item, items.get(item)));
            }
        }

        return fits(listItems, slots);
    }

    @NotNull
    public Block getBlock() {
        return location.getBlock();
    }

    @Override
    @NotNull
    public MenuCloseHandler getCloseHandler() {
        return preset.getCloseHandler();
    }

    @Override
    @NotNull
    public MenuOpenHandler getOpenHandler() {
        return preset.getOpenHandler();
    }

    @Override
    @NotNull
    public ClickHandler getClickHandler(@Range(from = 0, to = 53) int slot) {
        return preset.getClickHandler(slot);
    }
}
