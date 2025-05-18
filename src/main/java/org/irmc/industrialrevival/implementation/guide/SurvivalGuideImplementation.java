package org.irmc.industrialrevival.implementation.guide;

import com.google.common.collect.Lists;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.objects.enums.GuideMode;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.api.recipes.methods.ProduceMethod;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.pigeonlib.chat.ChatInput;
import org.irmc.pigeonlib.items.ItemUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurvivalGuideImplementation implements IRGuideImplementation {
    public static final SurvivalGuideImplementation INSTANCE = new SurvivalGuideImplementation();

    private static final NamespacedKey BOOKMARK_KEY = KeyUtil.customKey("bookmark_group");
    private static final NamespacedKey HISTORY_KEY = KeyUtil.customKey("history_group");

    private final Map<String, BookMarkGroup> bookmarks;
    private final Map<String, Integer> pageMap;

    SurvivalGuideImplementation() {
        bookmarks = new HashMap<>();
        pageMap = new HashMap<>();
    }

    @Override
    public void open(Player p) {
        SimpleMenu sm = new SimpleMenu(
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Constants.Keys.GUIDE_TITLE_KEY));
        setupGuideMenu(p, sm);

        PlayerProfile.getOrRequestProfile(p.getName()).getGuideHistory().guideOpen(this, 1);

        sm.open(p);
    }

    @Override
    public void onItemClicked(Player p, IndustrialRevivalItem item, ClickType clickType) {
        SimpleMenu sm = new SimpleMenu(IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Constants.Keys.GUIDE_TITLE_KEY));

        List<ProduceMethod> produceMethods = item.getProduceMethods();
        if (produceMethods == null || produceMethods.isEmpty()) {
            return;
        }

        produceMethods.getFirst().getRecipeType().getRecipeDisplay().display(p, sm, item);

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
        SimpleMenu sm = new SimpleMenu(ItemUtils.getDisplayName(group.getIcon()));

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

    @Override
    public GuideMode getGuideMode() {
        return GuideMode.SURVIVAL;
    }

    public void addBookmark(Player p, IndustrialRevivalItem item) {
        BookMarkGroup group = bookmarks.computeIfAbsent(p.getName(), k -> new BookMarkGroup(p));
        group.addItem(item);
    }

    public void removeBookmark(Player p, IndustrialRevivalItem item) {
        BookMarkGroup group = bookmarks.get(p.getName());
        if (group != null) {
            group.removeItem(item);
        }
    }

    void setupGuideMenu(Player p, SimpleMenu sm) {
        List<ItemGroup> groups = new ArrayList<>(
                IndustrialRevival.getInstance().getRegistry().getItemGroups().values());

        sm.setSize(54);

        for (int b : Constants.Guide.GUIDE_GROUP_BORDERS) {
            sm.setItem(b, Constants.ItemStacks.BACKGROUND_ITEM);
        }

        ItemStack settingButton = Constants.Buttons.SETTING_BUTTON.apply(p);
        sm.setItem(2, settingButton, ClickHandler.DEFAULT); // do nothing now

        ItemStack searchButton = Constants.Buttons.SEARCH_BUTTON.apply(p);
        sm.setItem(6, searchButton, ClickHandler.DEFAULT); // do nothing now

        int page = pageMap.getOrDefault(p.getName(), 1);

        ItemStack previousButton = Constants.Buttons.PREVIOUS_BUTTON.apply(p);
        ClickHandler previousClickHandler = (player, _, _, _, _) -> {
            pageMap.put(p.getName(), page - 1);
            SimpleMenu previousPage = new SimpleMenu(IndustrialRevival.getInstance()
                    .getLanguageManager()
                    .getMsgComponent(player, Constants.Keys.GUIDE_TITLE_KEY));
            setupGuideMenu(player, previousPage);
            previousPage.open(player);
            return false;
        };

        if (page == 1) {
            previousButton.setType(Material.BLACK_STAINED_GLASS_PANE);
            previousButton.editMeta(m -> m.displayName(Component.space()));
            previousClickHandler = ClickHandler.DEFAULT;
        }

        sm.setItem(47, previousButton, previousClickHandler);

        groups.add(0, bookmarks.computeIfAbsent(p.getName(), k -> new BookMarkGroup(p)));
        List<List<ItemGroup>> partition = Lists.partition(groups.stream().toList(), 36);

        ItemStack nextButton = Constants.Buttons.NEXT_BUTTON.apply(p);
        ClickHandler nextClickHandler = (player, slot, item, menu, clickType) -> {
            pageMap.put(p.getName(), page + 1);
            SimpleMenu nextPage = new SimpleMenu(IndustrialRevival.getInstance()
                    .getLanguageManager()
                    .getMsgComponent(player, Constants.Keys.GUIDE_TITLE_KEY));
            setupGuideMenu(player, nextPage);
            nextPage.open(player);
            return false;
        };

        if (page == partition.size()) {
            nextButton.setType(Material.BLACK_STAINED_GLASS_PANE);
            nextButton.editMeta(m -> m.displayName(Component.space()));
            nextClickHandler = ClickHandler.DEFAULT;
        }

        sm.setItem(51, nextButton, nextClickHandler);

        List<ItemGroup> groupList = partition.get(page - 1);
        for (int i = 9; i < 45; i++) {
            int index = i - 9;
            ItemGroup group = index < groupList.size() ? groupList.get(index) : null;
            if (group != null) {
                sm.setItem(i, group.getIcon(), (player, slot, item, menu, clickType) -> {
                    onGroupClicked(player, group);
                    return false;
                });
            }
        }

        sm.setItem(6, Constants.Buttons.SEARCH_BUTTON.apply(p), (player, item, slot, menu, clickType) -> {
            player.closeInventory();
            IndustrialRevival.getInstance().getLanguageManager().sendMessage(player, "guide.type_search");
            SearchGUI.request(player, this);
            return false;
        });
    }

    private static class BookMarkGroup extends ItemGroup {
        public BookMarkGroup(Player p) {
            super(BOOKMARK_KEY, Constants.Buttons.BOOKMARK_BUTTON.apply(p));
        }

        @Override
        public void addItem(@NotNull IndustrialRevivalItem item) {
            if (getItems().contains(item)) {
                return;
            }

            super.addItem(item);
        }

        @Override
        public void register() {
        }

        public void removeItem(IndustrialRevivalItem item) {
            getItems().remove(item);
        }
    }

    private static class HistoryGroup extends ItemGroup {
        public HistoryGroup(Player p) {
            super(HISTORY_KEY, Constants.Buttons.HISTORY_BUTTON.apply(p));
        }

        @Override
        public void addItem(@NotNull IndustrialRevivalItem item) {
        }

        @Override
        public void register() {
        }
    }

    public static class LanguageGUI {
        private final Player p;
        private final IRGuideImplementation impl;

        public LanguageGUI(Player p, IRGuideImplementation impl) {
            this.p = p;
            this.impl = impl;
        }

        public static void request(Player player, IRGuideImplementation impl) {
            LanguageGUI languageGUI = new LanguageGUI(player, impl);
            languageGUI.showMenu();
        }

        /**
         * @see Constants.Languages#getLanguageButtons()
         */
        public void showMenu() {
            // todo: implement language selection
        }
    }

    public static class SettingsGUI {
        private static final int[] SETTING_BUTTON = {
                9, 10, 11, 12, 13, 14, 15, 16, 17,
                18, 19, 20, 21, 22, 23, 24, 25, 26,
                27, 28, 29, 30, 31, 32, 33, 34, 35,
                36, 37, 38, 39, 40, 41, 42, 43, 44
        };
        private final Player p;
        private final IRGuideImplementation impl;
        private int pointer = 0;

        private SettingsGUI(Player p, IRGuideImplementation impl) {
            this.p = p;
            this.impl = impl;
        }

        public static void request(Player player, IRGuideImplementation impl) {
            SettingsGUI settingsGUI = new SettingsGUI(player, impl);
            settingsGUI.showMenu();
        }

        public void showMenu() {
            SimpleMenu sm = createMenu();

            sm.setItem(SETTING_BUTTON[pointer++], Constants.Buttons.LANGUAGE_BUTTON.apply(p), (player, _, _, _, _) -> {
                LanguageGUI.request(player, impl);
                return false;
            });

            // todo: change to permission check
            if (p.isOp()) {
                sm.setItem(SETTING_BUTTON[pointer++], Constants.Buttons.GUIDE_MODE_SWITCH_BUTTON.apply(p, impl), (player, _, _, _, _) -> {
                    // todo: ^ same
                    if (player.isOp()) {
                        player.closeInventory(InventoryCloseEvent.Reason.PLUGIN);
                        if (impl instanceof CheatGuideImplementation) {
                            SurvivalGuideImplementation.INSTANCE.open(player);
                        } else if (impl instanceof SurvivalGuideImplementation) {
                            CheatGuideImplementation.INSTANCE.open(player);
                        }
                    }
                    return false;
                });
            }

            sm.open(p);
        }

        private SimpleMenu createMenu() {
            SimpleMenu sm = new SimpleMenu(IndustrialRevival.getInstance()
                    .getLanguageManager()
                    .getMsgComponent(p, Constants.Keys.GUIDE_TITLE_KEY));
            for (int slot : Constants.Guide.GUIDE_GROUP_BORDERS) {
                sm.setItem(slot, Constants.ItemStacks.BACKGROUND_ITEM, ClickHandler.DEFAULT);
            }

            sm.setItem(2, Constants.Buttons.BACK_BUTTON.apply(p), (player, _, _, _, _) -> {
                impl.goBack(player);
                return false;
            });

            sm.setItem(6, Constants.ItemStacks.BACKGROUND_ITEM, ClickHandler.DEFAULT);

            sm.setSize(54);

            return sm;
        }
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
                    implementation.goBack(player);
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

            ItemStack previousButton = Constants.Buttons.PREVIOUS_BUTTON.apply(player);
            ClickHandler previousClickHandler = (_, _, _, _, _) -> {
                showResults(page - 1);
                return false;
            };

            if (page == 1) {
                previousButton.setType(Material.BLACK_STAINED_GLASS_PANE);
                previousButton.editMeta(m -> m.displayName(Component.space()));
                previousClickHandler = ClickHandler.DEFAULT;
            }

            ItemStack nextButton = Constants.Buttons.NEXT_BUTTON.apply(player);
            ClickHandler nextClickHandler = (_, _, _, _, _) -> {
                showResults(page + 1);
                return false;
            };

            // not the actual page, actually page - 1 = last page index
            if (partition.isEmpty() || partition.size() == 1) {
                nextButton.setType(Material.BLACK_STAINED_GLASS_PANE);
                nextButton.editMeta(m -> m.displayName(Component.space()));
                nextClickHandler = ClickHandler.DEFAULT;
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
                ItemStack stack = item.getItemStack().clone();
                sm.setItem(startIndex, stack, (player, _, _, _, clickType) -> {
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
                    .getMsgComponent(player, Constants.Keys.GUIDE_TITLE_KEY));
            for (int slot : Constants.Guide.GUIDE_GROUP_BORDERS) {
                sm.setItem(slot, Constants.ItemStacks.BACKGROUND_ITEM, ClickHandler.DEFAULT);
            }

            sm.setItem(2, Constants.Buttons.BACK_BUTTON.apply(player), (player, _, _, _, _) -> {
                implementation.goBack(player);
                return false;
            });

            sm.setItem(6, Constants.ItemStacks.BACKGROUND_ITEM, ClickHandler.DEFAULT);

            sm.setSize(54);

            return sm;
        }
    }
}
