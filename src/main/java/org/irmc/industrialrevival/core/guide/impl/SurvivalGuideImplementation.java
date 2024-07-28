package org.irmc.industrialrevival.core.guide.impl;

import com.google.common.collect.Lists;

import java.util.*;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.industrialrevival.core.utils.ItemUtils;

public class SurvivalGuideImplementation implements IRGuideImplementation {
    public static final SurvivalGuideImplementation INSTANCE = new SurvivalGuideImplementation();

    private static final int[] RECIPE_SLOT = {3, 4, 5, 12, 13, 14, 21, 22, 23};
    private static final int[] BOARDER_SLOT = {0, 1, 3, 4, 5, 7, 8, 45, 46, 48, 49, 50, 52, 53};

    private static final NamespacedKey BOOKMARK_KEY = new NamespacedKey(IndustrialRevival.getInstance(), "bookmark_group");

    private final Map<String, BookMarkGroup> bookmarks;
    private final Map<String, Integer> pageMap;

    SurvivalGuideImplementation() {
        bookmarks = new HashMap<>();
        pageMap = new HashMap<>();
    }

    @Override
    public void open(Player p) {
        SimpleMenu sm = new SimpleMenu(
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Constants.GUIDE_TITLE_KEY));
        setupGuideMenu(p, sm);
        sm.open(p);

        PlayerProfile.getOrRequestProfile(p.getName()).getGuideHistory().guideOpen(this, 1);
    }

    @Override
    public void onItemClicked(Player p, IndustrialRevivalItem item) {
        SimpleMenu sm = new SimpleMenu(
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Constants.GUIDE_TITLE_KEY));
        ItemStack[] recipe = item.getRecipe();
        for (int i = 0; i < RECIPE_SLOT.length; i++) {
            sm.setItem(RECIPE_SLOT[i], ItemUtils.getCleanedItem(recipe[i]));
        }

        sm.setItem(0, Constants.BACK_BUTTON.apply(p), (slot, player, i, menu, clickType) -> {
            goBack(player);
            return false;
        });

        sm.setItem(16, item.getItem(), SimpleMenu.ClickHandler.DEFAULT);

        sm.setSize(54);
        sm.open(p);
    }

    @Override
    public void onGroupClicked(Player player, ItemGroup group) {
        onGroupClicked(player, group, 1);
    }

    @Override
    public void onGroupClicked(Player p, ItemGroup group, int page) {
        SimpleMenu sm = new SimpleMenu(
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Constants.GUIDE_TITLE_KEY));

        if (!group.getItems().isEmpty()) {
            List<List<IndustrialRevivalItem>> items =
                    Lists.partition(group.getItems().stream().toList(), 36);
            List<IndustrialRevivalItem> itemList = items.get(page - 1);

            for (int i = 9; i < 36; i++) {
                IndustrialRevivalItem item = itemList.get(i - 9);
                if (item != null) {
                    sm.setItem(i, ItemUtils.getCleanedItem(item.getItem()), (slot, player, item1, menu, clickType) -> {
                        onItemClicked(player, item);
                        return false;
                    });
                }
            }
        }

        for (int b : BOARDER_SLOT) {
            sm.setItem(b, Constants.BACKGROUND_ITEM);
        }

        ItemStack backButton = Constants.BACK_BUTTON.apply(p);
        sm.setItem(2, backButton, ((slot, player, item, menu, clickType) -> {
            goBack(player);
            return false;
        })); // do nothing now

        ItemStack searchButton = Constants.SEARCH_BUTTON.apply(p);
        sm.setItem(6, searchButton, SimpleMenu.ClickHandler.DEFAULT); // do nothing now

        sm.setSize(54);

        sm.open(p);

        GuideHistory history = PlayerProfile.getOrRequestProfile(p.getName()).getGuideHistory();
        history.addItemGroup(group, page);
    }

    @Override
    public void goBack(Player player) {
        PlayerProfile profile = PlayerProfile.getProfile(player.getName());
        if (profile != null) {
            GuideHistory history = profile.getGuideHistory();
            history.goBack();
        } else {
            PlayerProfile.getOrRequestProfile(player.getName());
            goBack(player);
        }
    }

    void setupGuideMenu(Player p, SimpleMenu sm) {
        Collection<ItemGroup> groups =
                IndustrialRevival.getInstance().getRegistry().getItemGroups().values();

        sm.setSize(54);

        for (int b : BOARDER_SLOT) {
            sm.setItem(b, Constants.BACKGROUND_ITEM);
        }

        ItemStack settingButton = Constants.SETTING_BUTTON.apply(p);
        sm.setItem(2, settingButton, SimpleMenu.ClickHandler.DEFAULT); // do nothing now

        ItemStack searchButton = Constants.SEARCH_BUTTON.apply(p);
        sm.setItem(6, searchButton, SimpleMenu.ClickHandler.DEFAULT); // do nothing now

        int page = pageMap.getOrDefault(p.getName(), 1);

        ItemStack previousButton = Constants.PREVIOUS_BUTTON.apply(p);
        SimpleMenu.ClickHandler previousClickHandler = (slot, player, item, menu, clickType) -> {
            pageMap.put(p.getName(), page - 1);
            SimpleMenu previousPage = new SimpleMenu(IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(player, Constants.GUIDE_TITLE_KEY));
            setupGuideMenu(player, previousPage);
            previousPage.open(player);
            return false;
        };

        if (page == 1) {
            previousButton.setType(Material.BLACK_STAINED_GLASS_PANE);
            previousButton.editMeta(m -> m.displayName(Component.space()));
            previousClickHandler = SimpleMenu.ClickHandler.DEFAULT;
        }

        sm.setItem(47, previousButton, previousClickHandler);

        int partSize = page == 1 ? 35 : 36;
        List<List<ItemGroup>> partition = Lists.partition(groups.stream().toList(), partSize);

        ItemStack nextButton = Constants.NEXT_BUTTON.apply(p);
        SimpleMenu.ClickHandler nextClickHandler = (slot, player, item, menu, clickType) -> {
            pageMap.put(p.getName(), page + 1);
            SimpleMenu nextPage = new SimpleMenu(IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(player, Constants.GUIDE_TITLE_KEY));
            setupGuideMenu(player, nextPage);
            nextPage.open(player);
            return false;
        };

        if (page == partition.size()) {
            nextButton.setType(Material.BLACK_STAINED_GLASS_PANE);
            nextButton.editMeta(m -> m.displayName(Component.space()));
            nextClickHandler = SimpleMenu.ClickHandler.DEFAULT;
        }

        sm.setItem(51, nextButton, nextClickHandler);

        sm.setItem(9, bookmarks.computeIfAbsent(p.getName(), k -> new BookMarkGroup(BOOKMARK_KEY, p)).getIcon());

        List<ItemGroup> groupList = partition.get(page - 1);
        for (int i = 10; i < partSize + 9; i++) {
            int index = i - 10;
            ItemGroup group = index < groupList.size() ? groupList.get(index) : null;
            if (group != null) {
                sm.setItem(i, group.getIcon(), (slot, player, item, menu, clickType) -> {
                    onGroupClicked(player, group);
                    return false;
                });
            }
        }
    }

    private static class BookMarkGroup extends ItemGroup {
        public BookMarkGroup(NamespacedKey key, Player p) {
            super(key, Constants.BOOKMARK_BUTTON.apply(p));
        }

        @Override
        public void register() {}
    }
}
