package org.irmc.industrialrevival.api.items.groups;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
import org.irmc.industrialrevival.core.utils.CleanedItemGetter;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.guide.SurvivalGuideImplementation;

public abstract class ItemGroup {
    @Getter
    private final NamespacedKey key;

    @Getter
    private final ItemStack icon;

    private final List<IndustrialRevivalItem> items = new LinkedList<>();
    boolean locked = false;

    @Getter
    private int tier;

    protected ItemGroup(NamespacedKey key, ItemStack icon) {
        this.key = key;
        this.icon = icon;
        this.tier = 3;
    }

    protected ItemGroup(NamespacedKey key, ItemStack icon, int tier) {
        this.key = key;
        this.icon = icon;
        this.tier = tier;
    }

    public void onClicked(Player p, SimpleMenu sm, int page) {
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
                            CleanedItemGetter.getCleanedItem(item.getItem()),
                            (slot, player, item1, menu, clickType) -> {
                                guide.onItemClicked(player, item, clickType);
                                return false;
                            });
                }
            }
        } else {
            onlyPageOne = true;
        }

        for (int b : Constants.BOARDER_SLOT) {
            sm.setItem(b, Constants.BACKGROUND_ITEM);
        }

        ItemStack backButton = Constants.BACK_BUTTON.apply(p);
        sm.setItem(2, backButton, ((slot, player, item, menu, clickType) -> {
            guide.open(player);
            return false;
        }));

        ItemStack previousButton = Constants.PREVIOUS_BUTTON.apply(p);
        SimpleMenu.ClickHandler previousClickHandler = (slot, player, item, menu, clickType) -> {
            guide.onGroupClicked(player, this, page - 1);
            return false;
        };

        if (page == 1) {
            previousButton.setType(Material.BLACK_STAINED_GLASS_PANE);
            previousButton.editMeta(m -> m.displayName(Component.space()));
            previousClickHandler = SimpleMenu.ClickHandler.DEFAULT;
        }

        sm.setItem(47, previousButton, previousClickHandler);

        ItemStack nextButton = Constants.NEXT_BUTTON.apply(p);
        SimpleMenu.ClickHandler nextClickHandler = (slot, player, item, menu, clickType) -> {
            guide.onGroupClicked(player, this, page + 1);
            return false;
        };

        if (onlyPageOne) {
            nextButton.setType(Material.BLACK_STAINED_GLASS_PANE);
            nextButton.editMeta(m -> m.displayName(Component.space()));
            nextClickHandler = SimpleMenu.ClickHandler.DEFAULT;
        }

        sm.setItem(51, nextButton, nextClickHandler);

        ItemStack searchButton = Constants.SEARCH_BUTTON.apply(p);
        sm.setItem(6, searchButton, SimpleMenu.ClickHandler.DEFAULT); // do nothing now

        sm.setSize(54);

        GuideHistory history = PlayerProfile.getOrRequestProfile(p.getName()).getGuideHistory();
        history.addItemGroup(this, page);

        sm.open(p);
    }

    public List<IndustrialRevivalItem> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(IndustrialRevivalItem item) {
        if (locked) {
            throw new IllegalStateException("the item group is locked");
        }

        this.items.add(item);
    }

    public void register() {
        this.locked = true;

        IndustrialRevival.getInstance().getRegistry().getItemGroups().put(key, this);
    }

    public final boolean isRegistered() {
        return IndustrialRevival.getInstance().getRegistry().getItemGroups().containsKey(key);
    }

    public void setTier(int tier) {
        this.tier = tier;

        resort();
    }

    private void resort() {
        IndustrialRevival.getInstance().getRegistry().resortItemGroups();
    }
}
