package org.irmc.industrialrevival.api.menu;

import com.google.common.base.Preconditions;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
import java.util.stream.IntStream;

public class SimpleMenu implements IRInventoryHolder {
    protected final Map<Integer, ClickHandler> clickHandlers;
    protected Map<Integer, ClickHandler> getClickHandlers() {
        return clickHandlers;
    }

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

        this.clickHandlers = new HashMap<>();
    }

    public SimpleMenu(MachineMenuPreset preset) {
        this.title = preset.getTitle();
        this.clickHandlers = preset.getClickHandlers();
        this.size = preset.getSize();
        this.closeHandler = preset.getCloseHandler();
        this.openHandler = preset.getOpenHandler();
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
        setItem(slot, itemStack, null);
    }

    public void setItem(@Range(from = 0, to = 53) int slot, @Nullable ItemStack itemStack, @Nullable ClickHandler clickHandler) {
        if (slot < 0 || slot >= 54) {
            Debug.severe("Invalid slot: " + slot);
            Debug.stackTraceManually();
            return;
        }

        if (itemStack == null) {
            this.clickHandlers.remove(slot);

            return;
        }

        this.getInventory().setItem(slot, itemStack);
        if (clickHandler != null) {
            this.clickHandlers.put(slot, clickHandler);
        }
    }

    public void setTitle(@NotNull Component title) {
        this.title = title;
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
    }

    private void setupInventory() {
        Debug.stackTraceManually();
        if (this.inventory == null) {
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
        }
    }

    @Nullable
    public ItemStack getItem(@Range(from = 0, to = 53) int slot) {
        return this.inventory.getItem(slot);
    }

    /**
     * Gets the contents of the machine menu as an array of ItemStacks.(Not the actual inventory's
     * contents)
     *
     * @return the contents of the machine menu as an array of ItemStacks
     */
    @NotNull
    public ItemStack[] getMenuContents() {
        return this.getInventory().getContents();
    }

    public int[] getEmptySlots() {
        return IntStream.range(0, getSize())
                .filter(i -> {
                    ItemStack item = this.getItem(i);
                    return (item == null || item.getType() == Material.AIR)
                            && (!clickHandlers.containsKey(i) || clickHandlers.get(i) == ClickHandler.ACCEPT_ALL);
                })
                .toArray();
    }

    @Nullable
    public ClickHandler getClickHandler(@Range(from = 0, to = 53) int slot) {
        return this.clickHandlers.getOrDefault(slot, ClickHandler.DEFAULT);
    }

    public void setClickHandler(@Range(from = 0, to = 53) int slot, @NotNull ClickHandler clickHandler) {
        this.clickHandlers.put(slot, clickHandler);
    }

    @Override
    @NotNull
    public Inventory getInventory() {
        if (this.inventory == null) {
            setupInventory();
        }

        return this.inventory;
    }

    public int lastNonnullSlot() {
        for (int i = 53; i >= 0; i--) {
            if (getItem(i) != null) {
                return i;
            }
        }

        return -1;
    }

    private int calculateInventorySize() {
        int index = lastNonnullSlot();
        if (index == -1) {
            return 54;
        }

        return (index / 9 + 1) * 9;
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
            getCloseHandler().onClose(p, this);
        }
    }

    public List<Player> getViewers() {
        return getInventory().getViewers().stream().map(h -> (Player) h).toList();
    }

    public boolean hasViewer() {
        return !getViewers().isEmpty();
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
