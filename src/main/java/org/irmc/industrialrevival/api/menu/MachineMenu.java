package org.irmc.industrialrevival.api.menu;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.irmc.industrialrevival.core.utils.ItemUtils;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SuppressWarnings({"deprecation", "unused"})
@Getter
public class MachineMenu extends SimpleMenu {
    // private final IRBlockData blockData;
    private final Location location;
    private final MachineMenuPreset preset;

    public MachineMenu(Location location, MachineMenuPreset preset) {
        super(preset.getTitle());

        /*
        IRBlockData blockData = IndustrialRevival.getInstance().getBlockDataService().getBlockData(location);

        if (blockData == null) {
            throw new IllegalArgumentException("Block data not found at location " + location.toString());
        }

        this.blockData = blockData;
         */

        this.location = location;
        this.preset = preset;
    }

    public void setSize(int size) {
        throw new UnsupportedOperationException("Cannot set size of a machine menu");
    }

    public void setTitle(Component title) {
        throw new UnsupportedOperationException("Cannot set title of a machine menu");
    }

    public boolean hasViewer() {
        return !getInventory().getViewers().isEmpty();
    }

    public List<HumanEntity> getViewers() {
        return getInventory().getViewers();
    }

    public void consumeSlot(int... slot) {
        for (int s : slot) {
            setItem(s, null);
        }
    }

    public int consumeItem(ItemStack item, int... slots) {
        if (item == null || item.getType().isAir()) {
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

    public int consumeItem(ItemStack item) {
        return consumeItem(item, IntStream.range(0, getSize()).toArray());
    }

    public void setProgressItem(int slot, int remainingTicks, int totalTicks, ItemStack progressBarItem) {
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
        im.setLore(Arrays.asList(getProgressBar(remainingTicks, totalTicks), "", ChatColor.GRAY + getTimeLeft(remainingTicks / 2)));
        item.setItemMeta(im);

        setItem(slot, item);
    }

    @Nonnull
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

    private static short getDurability(@Nonnull ItemStack item, int remainingTicks, int total) {
        return (short) ((item.getType().getMaxDurability() / total) * remainingTicks);
    }

    @Nonnull
    public static ChatColor getColorFromPercentage(float percentage) {
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

    @Nonnull
    public static String getTimeLeft(int seconds) {
        String timeleft = "";

        int minutes = (int) (seconds / 60L);

        if (minutes > 0) {
            timeleft += minutes + "m ";
        }

        seconds -= minutes * 60;
        return timeleft + seconds + "s";
    }

    public ItemStack pushItem(ItemStack item, int... slots) {
        if (item == null || item.getType().isAir()) {
            return null;
        }

        for (int slot : slots) {
            ItemStack itemInSlot = getItem(slot);
            if (itemInSlot == null || itemInSlot.getType().isAir()) {
                int canPush = Math.min(item.getAmount(), item.getMaxStackSize());
                setItem(slot, ItemUtils.cloneItem(item, canPush));
                item.setAmount(item.getAmount() - canPush);
                if (item.getAmount() == 0) {
                    break;
                }
            } else if (ItemUtils.isItemSimilar(itemInSlot, item)) {
                int canPush = Math.min(item.getAmount(), Math.max(0, itemInSlot.getMaxStackSize() - itemInSlot.getAmount()));
                itemInSlot.setAmount(itemInSlot.getAmount() + canPush);
                item.setAmount(item.getAmount() - canPush);
                if (item.getAmount() == 0) {
                    break;
                }
            }
        }

        if (item.getAmount() == 0) {
            return null;
        }

        return item;
    }

    public Block getBlock() {
        return location.getBlock();
    }
}
