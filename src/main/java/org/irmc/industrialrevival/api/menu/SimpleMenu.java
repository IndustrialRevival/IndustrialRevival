package org.irmc.industrialrevival.api.menu;

import com.google.common.base.Preconditions;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.utils.Debug;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

public class SimpleMenu implements IRInventoryHolder {
    protected final Map<Integer, ItemStack> slots;
    protected Map<Integer, ItemStack> getSlots() {
        return slots;
    }
    protected final Map<Integer, ClickHandler> clickHandlers;
    protected Map<Integer, ClickHandler> getClickHandlers() {
        return clickHandlers;
    }

    @Getter
    protected boolean dirty;

    protected int size = -1;

    @Getter
    protected Component title;

    private Inventory inventory;

    @Getter
    private MenuCloseHandler closeHandler = (player) -> {
    };

    @Getter
    private MenuOpenHandler openHandler = (player, menu) -> {
    };

    public SimpleMenu(@NotNull String title) {
        this(Component.text(title));
    }

    public SimpleMenu(@NotNull Component title) {
        this.title = title;

        this.slots = new HashMap<>();
        this.clickHandlers = new HashMap<>();
    }

    public SimpleMenu(MachineMenuPreset preset) {
        this.title = preset.getTitle();
        this.slots = preset.getSlots();
        this.clickHandlers = preset.getClickHandlers();
        this.size = preset.getSize();
        this.closeHandler = preset.getCloseHandler();
        this.openHandler = preset.getOpenHandler();
        this.dirty = true;
    }

    public void addMenuDrawer(@NotNull MatrixMenuDrawer drawer) {
        Debug.log("Called SimpleMenu.addMenuDrawer(MatrixMenuDrawer)");
        Debug.log("Adding menu drawer: " + getTitle());
        Debug.log("Size: " + drawer.getSize());
        setSize(drawer.getSize());
        int i = 0;
        for (String line : drawer.getMatrix()) {
            int j = 0;
            for (char slotSymbol : line.toCharArray()) {
                int slot = i * 9 + j;
                if (drawer.getCharMap().containsKey(slotSymbol)) {
                    ItemStack itemStack = drawer.getCharMap().get(slotSymbol);
                    if (MenuUtil.isBackground(itemStack)) {
                        Debug.log("Setting background slot: " + slot);
                        setItem(slot, MenuUtil.BACKGROUND, ClickHandler.DEFAULT);
                    } else {
                        Debug.log("Setting item slot: " + slot + " with symbol: " + slotSymbol);
                        setItem(slot, itemStack, Optional.ofNullable(drawer.getClickHandlerMap().get(slotSymbol)).orElse(ClickHandler.DEFAULT));
                    }
                } else if (drawer.getClickHandlerMap().containsKey(slotSymbol)) {
                    Debug.log("Setting click handler slot: " + slot + " with symbol: " + slotSymbol);
                    setItem(slot, null, drawer.getClickHandlerMap().get(slotSymbol));
                }
                j += 1;
            }
            i += 1;
        }

        Debug.log("Size: " + this.size);
        Debug.log("Slots size: " + this.slots.size());
        Debug.log("Click handlers size: " + this.clickHandlers.size());
        Debug.log("End SimpleMenu.addMenuDrawer(MatrixMenuDrawer)");
    }

    public void setItem(@Nullable ItemStack item, @NotNull ClickHandler clickHandler, @Range(from = 0, to = 53) int... slots) {
        for (int slot : slots) {
            setItem(slot, item, clickHandler);
        }
    }

    public void setItem(@Range(from = 0, to = 53) int slot, @Nullable ItemStack itemStack) {
        setItem(slot, itemStack, ClickHandler.DEFAULT);
    }

    public void setItem(@Range(from = 0, to = 53) int slot, @Nullable ItemStack itemStack, @NotNull ClickHandler clickHandler) {
        Debug.log("Called SimpleMenu.setItem(int, ItemStack, ClickHandler)");
        if (slot < 0 || slot >= 54) {
            Debug.severe("Invalid slot: " + slot);
            Debug.stackTraceManually();
            return;
        }

        Debug.log("Setting slot " + slot + " to " + ItemUtils.getDisplayName(itemStack));
        if (itemStack == null) {
            this.slots.remove(slot);
            this.clickHandlers.remove(slot);

            if (this.slots.get(slot) == null && this.clickHandlers.get(slot) == null) {
                markDirty();
            }

            return;
        }

        this.slots.put(slot, itemStack);
        this.clickHandlers.put(slot, clickHandler);
        Debug.log("Slots size: " + this.slots.size());

        markDirty();
        Debug.log("End call");
    }

    public void setTitle(@NotNull Component title) {
        this.title = title;

        markDirty();
    }

    public int getSize() {
        setupInventory();
        return this.inventory.getSize();
    }

    public void setSize(@Range(from = 9, to = 54) int size) {
        Preconditions.checkArgument(size > 0, "Size must be greater than 0");
        Preconditions.checkArgument(size <= 54, "Size must be less than or equal to 54");
        Preconditions.checkArgument(size % 9 == 0, "Size must be a multiple of 9");

        this.size = size;
        markDirty();
    }

    private void setupInventory() {
        if (this.inventory == null || this.dirty) {
            Debug.log("Creating inventory");
            Debug.log("Current Size: " + this.size);
            if (this.size == -1) {
                this.size = calculateInventorySize();
            }

            Debug.log("Final Size: " + this.size);
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

    @Nullable
    public ItemStack getItem(@Range(from = 0, to = 53) int slot) {
        return this.slots.get(slot);
    }

    /**
     * Gets the contents of the machine menu as an array of ItemStacks.(Not the actual inventory's
     * contents)
     *
     * @return the contents of the machine menu as an array of ItemStacks
     */
    @NotNull
    public ItemStack[] getMenuContents() {
        return this.slots.values().toArray(new ItemStack[0]);
    }

    public int[] getSelectedSlots() {
        return this.slots.keySet().stream().mapToInt(i -> i).toArray();
    }

    public int[] getEmptySlots() {
        return IntStream.range(0, getSize())
                .filter(i -> !this.slots.containsKey(i)
                        && (!clickHandlers.containsKey(i) || clickHandlers.get(i) == ClickHandler.ACCEPT_ALL))
                .toArray();
    }

    @Nullable
    public ClickHandler getClickHandler(@Range(from = 0, to = 53) int slot) {
        return this.clickHandlers.getOrDefault(slot, ClickHandler.DEFAULT);
    }

    public void setClickHandler(@Range(from = 0, to = 53) int slot, @NotNull ClickHandler clickHandler) {
        this.clickHandlers.put(slot, clickHandler);
    }

    private void markDirty() {
        this.dirty = true;
    }

    @Override
    @NotNull
    public Inventory getInventory() {
        if (this.inventory == null || this.dirty) {
            setupInventory();
        }

        return this.inventory;
    }

    private int calculateInventorySize() {
        Set<Integer> slots = this.slots.keySet();
        Debug.log("Slots: " + slots.size());
        int maxValue = slots.stream().sorted().toList().reversed().get(0);
        Debug.log("Max value: " + maxValue);

        return (maxValue % 9 + 1) == 0 ? maxValue + 1 : (maxValue / 9 + 1) * 9;
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
            p.openInventory(getInventory());
            getOpenHandler().onOpen(p, this);
        }
    }

    public void close(@NotNull Player... players) {
        for (Player p : players) {
            p.closeInventory();
            getCloseHandler().onClose(p);
        }
    }

    public boolean hasViewer() {
        return !inventory.getViewers().isEmpty();
    }

    @FunctionalInterface
    public interface ClickHandler {
        ClickHandler DEFAULT = (player, clickedItem, clickedSlot, clickedMenu, clickType) -> false;
        ClickHandler ACCEPT_ALL = (player, clickedItem, clickedSlot, clickedMenu, clickType) -> true;

        /**
         * Called when an item in the machine menu is clicked.
         *
         * @param player      the player who clicked the item
         * @param clickedItem the clicked item
         * @param clickedSlot the slot where the item was clicked
         * @param clickedMenu the machine menu where the item was clicked
         * @param clickType   the click type
         * @return false if the click should be canceled, true otherwise
         */
        boolean onClick(
                @NotNull Player player,
                @Nullable ItemStack clickedItem,
                @Range(from = 0, to = 53) int clickedSlot,
                @NotNull SimpleMenu clickedMenu,
                @NotNull ClickType clickType);
    }

    @FunctionalInterface
    public interface AdvancedClickHandler extends ClickHandler {
        /**
         * Called when an item in the machine menu is clicked.
         *
         * @param player      the player who clicked the item
         * @param clickedItem the clicked item
         * @param clickedSlot the slot where the item was clicked
         * @param clickedMenu the machine menu where the item was clicked
         * @param clickType   the click type
         * @return always false, use {@link #onClick(Player, ItemStack, int, SimpleMenu, ClickType, InventoryClickEvent)}
         */

        @Override
        default boolean onClick(
                @NotNull Player player,
                @Nullable ItemStack clickedItem,
                @Range(from = 0, to = 53) int clickedSlot,
                @NotNull SimpleMenu clickedMenu,
                @NotNull ClickType clickType) {
            return false;
        }

        boolean onClick(
                @NotNull Player player,
                @Nullable ItemStack item,
                @Range(from = 0, to = 53) int slot,
                @NotNull SimpleMenu menu,
                @NotNull ClickType clickType,
                @NotNull InventoryClickEvent event);
    }

    @FunctionalInterface
    public interface MenuCloseHandler {
        void onClose(@NotNull Player player);
    }

    @FunctionalInterface
    public interface MenuOpenHandler {
        void onOpen(@NotNull Player player, @NotNull SimpleMenu menu);
    }
}
