package org.irmc.industrialrevival.api.items.groups;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Comparator;
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
import org.irmc.industrialrevival.implementation.guide.SurvivalGuideImplementation;

@Getter
public class NestedItemGroup extends ItemGroup {
    private final List<SubItemGroup> subItemGroups = new ArrayList<>();

    public NestedItemGroup(NamespacedKey key, ItemStack icon) {
        super(key, icon);
    }

    public NestedItemGroup(NamespacedKey key, ItemStack icon, int tier) {
        super(key, icon, tier);
    }

    public final void addItem(IndustrialRevivalItem item) {
        throw new UnsupportedOperationException("Nested item groups can only contain sub item groups");
    }

    public void onClicked(Player p, SimpleMenu sm, int page) {
        boolean onlyPageOne = false;
        IRGuideImplementation guide = SurvivalGuideImplementation.INSTANCE;

        if (!getItems().isEmpty()) {
            List<List<SubItemGroup>> items =
                    Lists.partition(subItemGroups.stream().toList(), 36);

            if (items.size() == 1) {
                onlyPageOne = true;
            }

            List<SubItemGroup> itemList = items.get(page - 1);

            for (int i = 9; i < 36; i++) {
                if ((i - 9) >= itemList.size()) {
                    break;
                }

                SubItemGroup item = itemList.get(i - 9);
                if (item != null) {
                    sm.setItem(
                            i,
                            CleanedItemGetter.getCleanedItem(item.getIcon()),
                            (slot, player, item1, menu, clickType) -> {
                                guide.onGroupClicked(player, item, 1);
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

    final void addSubItemGroup(SubItemGroup group) {
        if (locked) {
            throw new IllegalStateException("Cannot add sub item groups to a locked nested item group");
        }

        subItemGroups.add(group);

        tryResort();
    }

    final void tryResort() {
        List<SubItemGroup> sorted = subItemGroups.stream()
                .sorted(Comparator.comparingInt(ItemGroup::getTier))
                .toList();
        subItemGroups.clear();
        subItemGroups.addAll(sorted);
    }
}
