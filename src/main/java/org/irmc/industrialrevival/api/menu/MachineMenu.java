package org.irmc.industrialrevival.api.menu;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.utils.ItemUtils;

import java.util.List;
import java.util.stream.IntStream;

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
}
