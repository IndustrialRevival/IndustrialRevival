package org.irmc.industrialrevival.api.items.groups;

import com.google.common.collect.Lists;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.guide.SurvivalGuideImplementation;
import org.irmc.industrialrevival.utils.CleanedItemGetter;
import org.irmc.industrialrevival.utils.Constants;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class ItemGroup {
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

    protected ItemGroup(@NotNull NamespacedKey key, @NotNull ItemStack icon) {
        this.key = key;
        this.icon = icon;
        this.tier = 3;
    }

    protected ItemGroup(@NotNull NamespacedKey key, @NotNull ItemStack icon, int tier) {
        this.key = key;
        this.icon = icon;
        this.tier = tier;
    }

    public boolean allowedItem(IndustrialRevivalItem item) {
        return true;
    }

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
                            CleanedItemGetter.getCleanedItem(item.getItem().getItemStack()),
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
        SimpleMenu.ClickHandler previousClickHandler = (player, item, slot, menu, clickType) -> {
            guide.onGroupClicked(player, this, page - 1);
            return false;
        };

        if (page == 1) {
            previousButton.setType(Material.BLACK_STAINED_GLASS_PANE);
            previousButton.editMeta(m -> m.displayName(Component.space()));
            previousClickHandler = SimpleMenu.ClickHandler.DEFAULT;
        }

        sm.setItem(47, previousButton, previousClickHandler);

        ItemStack nextButton = Constants.Buttons.NEXT_BUTTON.apply(p);
        SimpleMenu.ClickHandler nextClickHandler = (player, item, slot, menu, clickType) -> {
            guide.onGroupClicked(player, this, page + 1);
            return false;
        };

        if (onlyPageOne) {
            nextButton.setType(Material.BLACK_STAINED_GLASS_PANE);
            nextButton.editMeta(m -> m.displayName(Component.space()));
            nextClickHandler = SimpleMenu.ClickHandler.DEFAULT;
        }

        sm.setItem(51, nextButton, nextClickHandler);

        ItemStack searchButton = Constants.Buttons.SEARCH_BUTTON.apply(p);
        sm.setItem(6, searchButton, SimpleMenu.ClickHandler.DEFAULT); // do nothing now

        sm.setSize(54);

        GuideHistory history = PlayerProfile.getOrRequestProfile(p.getName()).getGuideHistory();
        history.addItemGroup(this, page);

        sm.open(p);
    }

    @NotNull
    public List<IndustrialRevivalItem> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(@NotNull IndustrialRevivalItem item) {
        if (allowedItem(item)) {
            this.items.add(item);
        } else {
            IndustrialRevival.getInstance().getLogger().warning(MessageFormat.format(
                    "Item {0} (From addon {1}) is not allowed to be added to group {2} (From addon {3})",
                    item.getItemName(),
                    item.getAddon().getPlugin().getName(),
                    this.getClass().getSimpleName(),
                    this.getKey().getKey()));
        }
    }

    public void register() {
        if (isRegistered()) {
            throw new IllegalStateException("the item group is already registered");
        }

        locked = true;

        IndustrialRevival.getInstance().getRegistry().registerItemGroup(this);

        resort();
    }

    public final boolean isRegistered() {
        return IndustrialRevival.getInstance().getRegistry().getItemGroups().containsKey(key);
    }

    public void setTier(int tier) {
        checkLocked();
        this.tier = tier;
    }

    public void setOnlyVisibleByAdmins(boolean onlyVisibleByAdmins) {
        checkLocked();
        this.onlyVisibleByAdmins = onlyVisibleByAdmins;
    }

    private void resort() {
        IndustrialRevival.getInstance().getRegistry().resortItemGroups();
    }

    private void checkLocked() {
        if (locked) {
            throw new IllegalStateException("the item group is locked");
        }
    }
}
