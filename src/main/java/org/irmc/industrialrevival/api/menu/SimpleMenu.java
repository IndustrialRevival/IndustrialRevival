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
import org.irmc.industrialrevival.core.listeners.MachineMenuListener;
import org.irmc.industrialrevival.utils.Debug;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

public class SimpleMenu implements IRInventoryHolder {
    protected final Set<Player> viewers = new HashSet<>();
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
    private MenuCloseHandler closeHandler = (player, menu) -> {
    };

    @Getter
    private MenuOpenHandler openHandler = (player, menu) -> {
    };

    @Getter
    private OutsideClickHandler outsideClickHandler = (player, menu) -> {
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
        setSize(drawer.getSize());
        int i = 0;
        for (String line : drawer.getMatrix()) {
            int j = 0;
            for (char slotSymbol : line.toCharArray()) {
                int slot = i * 9 + j;
                if (drawer.getCharMap().containsKey(slotSymbol)) {
                    ItemStack itemStack = drawer.getCharMap().get(slotSymbol);
                    if (MenuUtil.isBackground(itemStack)) {
                        setItem(slot, itemStack, ClickHandler.DEFAULT);
                    } else {
                        setItem(slot, itemStack, Optional.ofNullable(drawer.getClickHandlerMap().get(slotSymbol)).orElse(ClickHandler.DEFAULT));
                    }
                } else if (drawer.getClickHandlerMap().containsKey(slotSymbol)) {
                    setItem(slot, null, drawer.getClickHandlerMap().get(slotSymbol));
                }
                j += 1;
            }
            i += 1;
        }
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
        if (slot < 0 || slot >= 54) {
            Debug.severe("Invalid slot: " + slot);
            Debug.stackTraceManually();
            return;
        }

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

        markDirty();
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
        int maxValue = slots.stream().sorted().toList().reversed().get(0);

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
            if (viewers.contains(p)) {
                continue;
            }
            p.openInventory(getInventory());
            viewers.add(p);
            getOpenHandler().onOpen(p, this);
        }
    }

    public void close(@NotNull Player... players) {
        for (Player p : players) {
            if (!viewers.contains(p)) {
                continue;
            }
            p.closeInventory();
            viewers.remove(p);
            getCloseHandler().onClose(p, this);
        }
    }

    public List<Player> getViewers() {
        return List.copyOf(this.viewers);
    }

    public boolean hasViewer() {
        return !getViewers().isEmpty();
    }

    public void addViewer(@NotNull Player player) {
        viewers.add(player);
    }

    public void removeViewer(@NotNull Player player) {
        viewers.remove(player);
    }

    public void setOutsideClickHandler(@NotNull OutsideClickHandler outsideClickHandler) {
        Preconditions.checkNotNull(outsideClickHandler, "Outside click handler cannot be null");

        this.outsideClickHandler = outsideClickHandler;
    }

    @FunctionalInterface
    public interface ClickHandler {
        ClickHandler DEFAULT = (_, _, _, _, _) -> false;
        ClickHandler ACCEPT_ALL = (_, _, _, _, _) -> true;

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
        void onClose(@NotNull Player player, @NotNull SimpleMenu menu);
    }

    @FunctionalInterface
    public interface MenuOpenHandler {
        void onOpen(@NotNull Player player, @NotNull SimpleMenu menu);
    }

    @FunctionalInterface
    public interface OutsideClickHandler {
        void onClick(@NotNull InventoryClickEvent event, @NotNull SimpleMenu menu);
    }
}
