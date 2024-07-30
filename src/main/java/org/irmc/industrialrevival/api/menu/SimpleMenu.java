package org.irmc.industrialrevival.api.menu;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleMenu implements IRInventoryHolder {
    private final Map<Integer, ItemStack> slots;
    private final Map<Integer, ClickHandler> clickHandlers;

    private boolean dirty;

    private int size = -1;

    @Getter
    private Component title;

    private Inventory inventory;

    @Getter
    private MenuCloseHandler closeHandler = (player) -> {};

    @Getter
    private MenuOpenHandler openHandler = (player, menu) -> {};

    public SimpleMenu(Component title) {
        this.title = title;

        this.slots = new HashMap<>();
        this.clickHandlers = new HashMap<>();
    }

    public void setItem(ItemStack item, ClickHandler clickHandler, int... slots) {
        for (int slot : slots) {
            setItem(slot, item, clickHandler);
        }
    }

    public void setItem(int slot, ItemStack itemStack) {
        setItem(slot, itemStack, ClickHandler.DEFAULT);
    }

    public void setItem(int slot, ItemStack itemStack, ClickHandler clickHandler) {
        if (slot < 0 || slot >= 54) {
            throw new IllegalArgumentException("Invalid slot: " + slot);
        }

        if (itemStack == null) {
            if (this.slots.containsKey(slot)) {
                this.slots.remove(slot);
            }

            if (this.clickHandlers.containsKey(slot)) {
                this.clickHandlers.remove(slot);
            }

            if (this.slots.get(slot) == null && this.clickHandlers.get(slot) == null) {
                markDirty();
            }
            return;
        }

        this.slots.put(slot, itemStack);
        this.clickHandlers.put(slot, clickHandler);

        markDirty();
    }

    public void setTitle(Component title) {
        this.title = title;

        markDirty();
    }

    public int getSize() {
        setupInventory();
        return this.inventory.getSize();
    }

    public void setSize(int size) {
        Preconditions.checkArgument(size > 0, "Size must be greater than 0");
        Preconditions.checkArgument(size <= 54, "Size must be less than or equal to 54");
        Preconditions.checkArgument(size % 9 == 0, "Size must be a multiple of 9");

        this.size = size;
        markDirty();
    }

    private void setupInventory() {
        if (this.inventory == null || this.dirty) {
            if (this.size == -1) {
                this.size = calculateInventorySize();
            }

            this.inventory = Bukkit.createInventory(this, this.size, getTitle());
            for (int i = 0; i < this.size; i++) {
                ItemStack item = getItem(i);
                if (item == null) {
                    continue;
                }
                this.inventory.setItem(i, item);
            }

            this.dirty = false;
        }
    }

    @Nullable public ItemStack getItem(int slot) {
        return this.slots.get(slot);
    }

    /**
     * Gets the contents of the machine menu as an array of ItemStacks.(Not the actual inventory's
     * contents)
     *
     * @return the contents of the machine menu as an array of ItemStacks
     */
    public ItemStack[] getMenuContents() {
        return this.slots.values().toArray(new ItemStack[0]);
    }

    public int[] getSelectedSlots() {
        return this.slots.keySet().stream().mapToInt(i -> i).toArray();
    }

    public int[] getEmptySlots() {
        return IntStream.range(0, getSize())
                .filter(i -> !this.slots.containsKey(i)
                        && (!clickHandlers.containsKey(i) || clickHandlers.get(i) == ClickHandler.NO_ACTION))
                .toArray();
    }

    public ClickHandler getClickHandler(int slot) {
        return this.clickHandlers.getOrDefault(slot, ClickHandler.DEFAULT);
    }

    public void setClickHandler(int slot, ClickHandler clickHandler) {
        this.clickHandlers.put(slot, clickHandler);
    }

    private void markDirty() {
        this.dirty = true;
    }

    @Override
    public @NotNull Inventory getInventory() {
        if (this.inventory == null || this.dirty) {
            setupInventory();
        }

        return this.inventory;
    }

    private int calculateInventorySize() {
        Set<Integer> slots = this.slots.keySet();
        int maxValue = slots.stream().sorted().findFirst().orElse(0);

        return ((maxValue - 1) / 9 + 1) * 9;
    }

    public void setCloseHandler(@NotNull MenuCloseHandler closeHandler) {
        Preconditions.checkNotNull(closeHandler, "Close handler cannot be null");

        this.closeHandler = closeHandler;
    }

    public void setOpenHandler(@NotNull MenuOpenHandler openHandler) {
        Preconditions.checkNotNull(openHandler, "Open handler cannot be null");

        this.openHandler = openHandler;
    }

    public void open(@NotNull Player... players) {
        for (Player p : players) {
            p.closeInventory();

            p.openInventory(getInventory());
        }
    }

    @FunctionalInterface
    public interface ClickHandler {
        ClickHandler DEFAULT = (slot, player, item, menu, clickType) -> false;
        ClickHandler NO_ACTION = (slot, player, item, menu, clickType) -> true;

        /**
         * Called when an item in the machine menu is clicked.
         *
         * @param slot the slot of the clicked item
         * @param player the player who clicked the item
         * @param item the clicked item
         * @param menu the machine menu
         * @param clickType the click type
         * @return false if the click should be canceled, true otherwise
         */
        boolean onClick(
                int slot,
                @NotNull Player player,
                @Nullable ItemStack item,
                @NotNull SimpleMenu menu,
                @NotNull ClickType clickType);
    }

    @FunctionalInterface
    public interface AdvancedClickHandler extends ClickHandler {
        /**
         * Called when an item in the machine menu is clicked.
         *
         * @param slot the slot of the clicked item
         * @param item the clicked item
         * @param menu the machine menu
         * @param clickType the click type
         * @return always false because there's another method for advanced click handling
         */
        @Override
        default boolean onClick(
                int slot,
                @NotNull Player player,
                @Nullable ItemStack item,
                @NotNull SimpleMenu menu,
                @NotNull ClickType clickType) {
            return false;
        }

        boolean onClick(
                int slot,
                @NotNull Player player,
                @Nullable ItemStack item,
                SimpleMenu menu,
                ClickType clickType,
                InventoryClickEvent event);
    }

    @FunctionalInterface
    public interface MenuCloseHandler {
        void onClose(Player player);
    }

    @FunctionalInterface
    public interface MenuOpenHandler {
        void onOpen(Player player, SimpleMenu menu);
    }
}
