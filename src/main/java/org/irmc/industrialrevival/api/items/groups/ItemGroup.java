package org.irmc.industrialrevival.api.items.groups;

import com.google.common.collect.Lists;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.Displayable;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.menu.gui.GroupMenu;
import org.irmc.industrialrevival.api.menu.gui.PageableMenu;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.guide.SurvivalGuideImplementation;
import org.irmc.industrialrevival.utils.CleanedItemGetter;
import org.irmc.industrialrevival.utils.Constants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * An ItemGroup is a collection of items that can be displayed in a guide menu.
 *
 * @author lijinhong11
 * @author balugaq
 * @see NormalItemGroup
 * @see NestedItemGroup
 * @see SubItemGroup
 * @see TreeItemGroup
 */
public abstract class ItemGroup implements Displayable<ItemGroup> {
    @Getter
    private final NamespacedKey key;
    @Getter
    private final ItemStack icon;

    private final List<IndustrialRevivalItem> items = new LinkedList<>();

    boolean locked = false;

    @Getter
    private int tier;
    @Getter
    private boolean onlyVisibleByAdmins = false;

    @Getter
    private final Function<Player, GroupMenu> menuGenerator;

    /**
     * Creates a new ItemGroup with the given key and icon.
     *
     * @param key  the key of the item group
     * @param icon the icon of the item group
     */
    protected ItemGroup(@NotNull Function<Player, GroupMenu> menuGenerator, @NotNull NamespacedKey key, @NotNull ItemStack icon) {
        this(menuGenerator, key, icon, 3);
    }

    /**
     * Creates a new ItemGroup with the given key, icon, and tier.
     *
     * @param key  the key of the item group
     * @param icon the icon of the item group
     * @param tier the tier of the item group, default is 3
     * @apiNote the lower the tier is, the higher the priority of the item group is
     */
    protected ItemGroup(@Nullable Function<Player, GroupMenu> menuGenerator, @NotNull NamespacedKey key, @NotNull ItemStack icon, int tier) {
        if (menuGenerator == null) {
            this.menuGenerator = p -> new GroupMenu(p, this);
        } else {
            this.menuGenerator = menuGenerator;
        }
        this.key = key;
        this.icon = icon;
        this.tier = tier;
    }

    /**
     * Creates a new ItemGroup with the given key and icon.
     *
     * @param key  the key of the item group
     * @param icon the icon of the item group
     */
    protected ItemGroup(@NotNull NamespacedKey key, @NotNull ItemStack icon) {
        this(key, icon, 3);
    }

    /**
     * Creates a new ItemGroup with the given key, icon, and tier.
     *
     * @param key  the key of the item group
     * @param icon the icon of the item group
     * @param tier the tier of the item group, default is 3
     * @apiNote the lower the tier is, the higher the priority of the item group is
     */
    protected ItemGroup(@NotNull NamespacedKey key, @NotNull ItemStack icon, int tier) {
        this(null, key, icon, tier);
    }

    /**
     * Checks if the given item is allowed to be added to this item group.
     *
     * @param item the item to be added
     * @return true if the item is allowed to be added, false otherwise
     */
    public boolean allowedItem(@NotNull IndustrialRevivalItem item) {
        return true;
    }

    /**
     * Opens the guide menu for this item group.
     *
     * @param p    the player who opened the guide menu
     * @param sm   the SimpleMenu instance to be used for the guide menu
     * @param page the page number of the guide menu to be opened
     */
    public void onClicked(@NotNull Player p, @NotNull SimpleMenu sm, int page) {
        boolean onlyPageOne = false;
        IRGuideImplementation guide = SurvivalGuideImplementation.INSTANCE;

        if (!getItems().isEmpty()) {
            List<List<IndustrialRevivalItem>> items =
                    Lists.partition(getItems().stream().toList(), 36);

            if (items.size() == 1) {
                onlyPageOne = true;
            }

            List<IndustrialRevivalItem> itemList = items.get(page - 1);

            for (int i = 9; i < 36; i++) {
                if ((i - 9) >= itemList.size()) {
                    break;
                }

                IndustrialRevivalItem item = itemList.get(i - 9);
                if (item != null) {
                    sm.setItem(
                            i,
                            CleanedItemGetter.clean(item.getIcon()),
                            (player, clickedItem, slot, menu, clickType) -> {
                                guide.onItemClicked(player, item, clickType);
                                return false;
                            });
                }
            }
        } else {
            onlyPageOne = true;
        }

        for (int b : Constants.Guide.GUIDE_GROUP_BORDERS) {
            sm.setItem(b, Constants.ItemStacks.BACKGROUND_ITEM);
        }

        ItemStack backButton = Constants.Buttons.BACK_BUTTON.apply(p);
        sm.setItem(2, backButton, ((player, item, slot, menu, clickType) -> {
            guide.open(player);
            return false;
        }));

        ItemStack previousButton = Constants.Buttons.PREVIOUS_BUTTON.apply(p);
        ClickHandler previousClickHandler = (player, item, slot, menu, clickType) -> {
            guide.onGroupClicked(player, this, page - 1);
            return false;
        };

        if (page == 1) {
            previousButton.setType(Material.BLACK_STAINED_GLASS_PANE);
            previousButton.editMeta(m -> m.displayName(Component.space()));
            previousClickHandler = ClickHandler.DEFAULT;
        }

        sm.setItem(47, previousButton, previousClickHandler);

        ItemStack nextButton = Constants.Buttons.NEXT_BUTTON.apply(p);
        ClickHandler nextClickHandler = (player, item, slot, menu, clickType) -> {
            guide.onGroupClicked(player, this, page + 1);
            return false;
        };

        if (onlyPageOne) {
            nextButton.setType(Material.BLACK_STAINED_GLASS_PANE);
            nextButton.editMeta(m -> m.displayName(Component.space()));
            nextClickHandler = ClickHandler.DEFAULT;
        }

        sm.setItem(51, nextButton, nextClickHandler);

        ItemStack searchButton = Constants.Buttons.SEARCH_BUTTON.apply(p);
        sm.setItem(6, searchButton, ClickHandler.DEFAULT); // do nothing now

        sm.setSize(54);

        GuideHistory history = PlayerProfile.getOrRequestProfile(p.getName()).getGuideHistory();
        history.addItemGroup(this, page);

        sm.open(p);
    }

    /**
     * Returns a list of all the items in this item group.
     *
     * @return a list of all the items in this item group
     */
    @NotNull
    public List<IndustrialRevivalItem> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Adds an item to this item group.
     *
     * @param item the item to be added
     */
    public void addItem(@NotNull IndustrialRevivalItem item) {
        if (allowedItem(item)) {
            this.items.add(item);
        } else {
            // not allowed item
            IRDock.getPlugin().getLogger().warning(MessageFormat.format(
                    "Item {0} (From addon {1}) is not allowed to be added to group {2} (From addon {3})",
                    item.getItemName(),
                    item.getAddon().getPlugin().getName(),
                    this.getClass().getSimpleName(),
                    this.getKey().getKey()));
        }
    }

    /**
     * Registers this item group.
     *
     * @throws IllegalStateException if the item group is already registered
     */
    public void register() {
        if (isRegistered()) {
            throw new IllegalStateException("the item group is already registered");
        }

        locked = true;

        IRDock.getPlugin().getRegistry().registerItemGroup(this);

        resort();
    }

    /**
     * Checks if this item group is registered.
     *
     * @return true if the item group is registered, false otherwise
     */
    public final boolean isRegistered() {
        return IRDock.getPlugin().getRegistry().getItemGroups().containsKey(key);
    }

    /**
     * Sets the tier of this item group.
     *
     * @param tier the tier of this item group
     */
    public void setTier(int tier) {
        checkLocked();
        this.tier = tier;
    }

    /**
     * Sets if this item group is only visible by admins.
     *
     * @param onlyVisibleByAdmins if this item group is only visible by admins
     * @apiNote if true, players without the admin permission will not see this item group in the guide menu
     */
    public void setOnlyVisibleByAdmins(boolean onlyVisibleByAdmins) {
        checkLocked();
        this.onlyVisibleByAdmins = onlyVisibleByAdmins;
    }

    /**
     * Resorts the item groups. An internal method
     */
    void resort() {
        IRDock.getPlugin().getRegistry().resortItemGroups();
    }

    /**
     * Checks if the item group is locked.
     */
    private void checkLocked() {
        if (locked) {
            throw new IllegalStateException("the item group is locked");
        }
    }

    @Override
    public ItemStack getDisplayItem(ItemGroup itemGroup) {
        return PageableMenu.getDisplayItem0(itemGroup);
    }
}
