package org.irmc.industrialrevival.implementation.guide;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.industrialrevival.core.utils.KeyUtil;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.pigeonlib.chat.ChatInput;

public class SurvivalGuideImplementation implements IRGuideImplementation {
    public static final SurvivalGuideImplementation INSTANCE = new SurvivalGuideImplementation();

    private static final NamespacedKey BOOKMARK_KEY = KeyUtil.customKey("bookmark_group");
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

        PlayerProfile.getOrRequestProfile(p.getName()).getGuideHistory().guideOpen(this, 1);

        sm.open(p);
    }

    @Override
    public void onItemClicked(Player p, IndustrialRevivalItem item, ClickType clickType) {
        SimpleMenu sm = new SimpleMenu(
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Constants.GUIDE_TITLE_KEY));

        item.getRecipeType().getRecipeDisplay().display(p, sm, item);

        sm.open(p);

        PlayerProfile profile = PlayerProfile.getOrRequestProfile(p.getName());
        profile.getGuideHistory().addItem(item);
    }

    @Override
    public void onGroupClicked(Player player, ItemGroup group) {
        onGroupClicked(player, group, 1);
    }

    @Override
    public void onGroupClicked(Player p, ItemGroup group, int page) {
        SimpleMenu sm = new SimpleMenu(group.getIcon().getItemMeta().displayName());

        sm.setSize(54);

        group.onClicked(p, sm, page);
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

    public void addBookmark(Player p, IndustrialRevivalItem item) {
        BookMarkGroup group = bookmarks.computeIfAbsent(p.getName(), k -> new BookMarkGroup(BOOKMARK_KEY, p));
        group.addItem(item);
    }

    void setupGuideMenu(Player p, SimpleMenu sm) {
        List<ItemGroup> groups = new ArrayList<>(
                IndustrialRevival.getInstance().getRegistry().getItemGroups().values());

        sm.setSize(54);

        for (int b : Constants.BOARDER_SLOT) {
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
            SimpleMenu previousPage = new SimpleMenu(IndustrialRevival.getInstance()
                    .getLanguageManager()
                    .getMsgComponent(player, Constants.GUIDE_TITLE_KEY));
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

        groups.add(0, bookmarks.computeIfAbsent(p.getName(), k -> new BookMarkGroup(BOOKMARK_KEY, p)));
        List<List<ItemGroup>> partition = Lists.partition(groups.stream().toList(), 36);

        ItemStack nextButton = Constants.NEXT_BUTTON.apply(p);
        SimpleMenu.ClickHandler nextClickHandler = (slot, player, item, menu, clickType) -> {
            pageMap.put(p.getName(), page + 1);
            SimpleMenu nextPage = new SimpleMenu(IndustrialRevival.getInstance()
                    .getLanguageManager()
                    .getMsgComponent(player, Constants.GUIDE_TITLE_KEY));
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

        List<ItemGroup> groupList = partition.get(page - 1);
        for (int i = 9; i < 45; i++) {
            int index = i - 9;
            ItemGroup group = index < groupList.size() ? groupList.get(index) : null;
            if (group != null) {
                sm.setItem(i, group.getIcon(), (slot, player, item, menu, clickType) -> {
                    onGroupClicked(player, group);
                    return false;
                });
            }
        }

        sm.setItem(6, Constants.SEARCH_BUTTON.apply(p), (slot, player, item, menu, clickType) -> {
            player.closeInventory();
            IndustrialRevival.getInstance().getLanguageManager().sendMessage(player, "guide.type_search");
            SearchGUI.request(player, this);
            return false;
        });
    }

    private static class BookMarkGroup extends ItemGroup {
        public BookMarkGroup(NamespacedKey key, Player p) {
            super(key, Constants.BOOKMARK_BUTTON.apply(p));
        }

        @Override
        public void addItem(IndustrialRevivalItem item) {
            if (getItems().contains(item)) {
                return;
            }

            super.addItem(item);
        }

        @Override
        public void register() {}
    }

    public static class SearchGUI {
        private final Player player;
        private final String searchTerm;
        private final IRGuideImplementation implementation;

        private SearchGUI(Player player, String searchTerm, IRGuideImplementation implementation) {
            this.player = player;
            this.searchTerm = searchTerm;
            this.implementation = implementation;
        }

        public static void request(Player player, IRGuideImplementation implementation) {
            IndustrialRevival.getInstance().getLanguageManager().sendMessage(player, "guide.type_search");
            ChatInput.waitForPlayer(IndustrialRevival.getInstance(), player, s -> {
                if (s.equalsIgnoreCase("#cancel")) {
                    return;
                }

                SearchGUI searchGUI = new SearchGUI(player, s, implementation);
                searchGUI.showResults(1);
            });
        }

        public void showResults(int page) {
            SimpleMenu sm = createMenu();

            List<IndustrialRevivalItem> searchResults =
                    IndustrialRevival.getInstance().getRegistry().searchItems(searchTerm);
            List<List<IndustrialRevivalItem>> partition = Lists.partition(searchResults, 36);

            ItemStack previousButton = Constants.PREVIOUS_BUTTON.apply(player);
            SimpleMenu.ClickHandler previousClickHandler = (slot, player, item, menu, clickType) -> {
                showResults(page - 1);
                return false;
            };

            if (page == 1) {
                previousButton.setType(Material.BLACK_STAINED_GLASS_PANE);
                previousButton.editMeta(m -> m.displayName(Component.space()));
                previousClickHandler = SimpleMenu.ClickHandler.DEFAULT;
            }

            ItemStack nextButton = Constants.NEXT_BUTTON.apply(player);
            SimpleMenu.ClickHandler nextClickHandler = (slot, player, item, menu, clickType) -> {
                showResults(page + 1);
                return false;
            };

            // not the actual page, actually page - 1 = last page index
            if (partition.isEmpty() || partition.size() == 1) {
                nextButton.setType(Material.BLACK_STAINED_GLASS_PANE);
                nextButton.editMeta(m -> m.displayName(Component.space()));
                nextClickHandler = SimpleMenu.ClickHandler.DEFAULT;
            }

            sm.setItem(47, previousButton, previousClickHandler);
            sm.setItem(51, nextButton, nextClickHandler);

            if (page > partition.size() || page < 1) {
                sm.open(player);
                return;
            }

            List<IndustrialRevivalItem> items = partition.get(page - 1);

            int startIndex = 9;
            for (IndustrialRevivalItem item : items) {
                ItemStack stack = item.getItem().clone();
                sm.setItem(startIndex, stack, (slot, player, item1, menu, clickType) -> {
                    implementation.onItemClicked(player, item, clickType);
                    return false;
                });
                startIndex++;
            }

            PlayerProfile profile = PlayerProfile.getOrRequestProfile(player.getName());
            GuideHistory guideHistory = profile.getGuideHistory();
            guideHistory.addSearchGUI(this);

            sm.open(player);
        }

        private SimpleMenu createMenu() {
            SimpleMenu sm = new SimpleMenu(IndustrialRevival.getInstance()
                    .getLanguageManager()
                    .getMsgComponent(player, Constants.GUIDE_TITLE_KEY));
            for (int slot : Constants.BOARDER_SLOT) {
                sm.setItem(slot, Constants.BACKGROUND_ITEM, SimpleMenu.ClickHandler.DEFAULT);
            }

            sm.setItem(2, Constants.BACK_BUTTON.apply(player), (slot, player, item, menu, clickType) -> {
                implementation.goBack(player);
                return false;
            });

            sm.setItem(6, Constants.BACKGROUND_ITEM, SimpleMenu.ClickHandler.DEFAULT);

            sm.setSize(54);

            return sm;
        }
    }
}
