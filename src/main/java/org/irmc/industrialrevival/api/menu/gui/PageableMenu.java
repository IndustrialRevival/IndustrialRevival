package org.irmc.industrialrevival.api.menu.gui;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.api.player.PlayerSettings;
import org.irmc.industrialrevival.api.recipes.RecipeContent;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.irmc.industrialrevival.utils.KeyUtil;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.irmc.pigeonlib.items.CustomItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public abstract class PageableMenu<T> extends SimpleMenu implements Pageable {
    public MatrixMenuDrawer drawer = getDrawer();

    private final Player player;
    private final int currentPage;
    private final List<T> items;
    private final Map<Integer, PageableMenu<T>> pages;

    /**
     * Temp page constructor, just for passing the compiler
     */
    public PageableMenu() {
        super("");
        this.player = null;
        this.currentPage = -1;
        this.items = null;
        this.pages = null;
    }

    public PageableMenu(Component title, Player player, int currentPage, List<T> items, Map<Integer, PageableMenu<T>> pages) {
        super(title);

        this.player = player;
        this.currentPage = currentPage;
        this.items = items;
        this.pages = pages;

        addMenuDrawer(getDrawer());
        recordPage(currentPage);
    }

    public PageableMenu(PageableMenu<T> menu) {
        super(menu.getTitle());

        this.player = menu.player;
        this.currentPage = menu.currentPage;
        this.items = menu.items;
        this.pages = menu.pages;
    }

    public void recordPage(int page) {
        this.pages.put(page, this);
    }

    @Nullable
    public PageableMenu<T> getByPage(int page) {
        if (pages.containsKey(page)) {
            return pages.get(page);
        } else if (page <= maxPage()) {
            PageableMenu<T> newMenu = newMenu(this, page);
            pages.put(page, newMenu);
            return newMenu;
        } else {
            return null;
        }
    }

    public ItemStack getPreviousPageButton() {
        return MenuUtil.getPreviousButton(player, currentPage, maxPage());
    }

    public ItemStack getNextPageButton() {
        return MenuUtil.getNextButton(player, currentPage, maxPage());
    }

    public ClickHandler getPreviousPageClickHandler() {
        return (player, _, _, _, _) -> {
            previousPage(player, PlayerProfile.getProfile(player), currentPage);
            return true;
        };
    }

    public ClickHandler getNextPageClickHandler() {
        return (player, _, _, _, _) -> {
            nextPage(player, PlayerProfile.getProfile(player), currentPage);
            return true;
        };
    }

    @Override
    public void previousPage(Player player, PlayerProfile profile, int currentPage) {
        if (currentPage > 1) {
            getByPage(currentPage - 1).open(player);
        }
    }

    @Override
    public void nextPage(Player player, PlayerProfile profile, int currentPage) {
        if (currentPage <= maxPage()) {
            getByPage(currentPage + 1).open(player);
        }
    }

    public int objsLength() {
        return getDrawer().getCharPositions("i").length;
    }

    public int maxPage() {
        return (int) Math.ceil((double) items.size() / objsLength());
    }

    public List<T> crop(int page) {
        int start = Math.max(0, page - 1) * objsLength();
        int end = Math.min(start + objsLength(), items.size());
        return items.subList(start, end);
    }

    public abstract ItemStack getDisplayItem(T item);

    public static ItemStack getDisplayItem(IndustrialRevivalItem item) {
        var icon = item.getIcon();
        var meta = icon.getItemMeta();
        if (meta == null) {
            return null;
        }

        List<Component> lore = new ArrayList<>();
        var e = meta.lore();
        if (e != null) {
            lore.addAll(e);
        }

        if (item.getGroup().size() == 1) {
            var group = item.getGroup().stream().findFirst().get();
            lore.add(Component.text().append(Component.text(item.getAddon().getName(), TextColor.color(0xea645d))).append(Component.text(" - ", TextColor.color(0x7f7f7f))).append(group.getIcon().displayName()).build());
        } else {
            lore.add(Component.text(item.getAddon().getName(), TextColor.color(0xea645d)));
            for (var group : item.getGroup()) {
                lore.add(Component.text().append(Component.text(" - ", TextColor.color(0x7f7f7f))).append(group.getIcon().displayName()).build());
            }
        }
        lore.add(Component.text("Click to lookup", TextColor.color(0x4abfa0)));

        return new CustomItemStack(icon)
                .lore(lore)
                .setPDCData(ITEM_KEY, PersistentDataType.STRING, item.getKey().toString())
                .getBukkit();
    }

    public static NamespacedKey GROUP_KEY = KeyUtil.customKey("group");
    public static NamespacedKey ITEM_KEY = KeyUtil.customKey("item");

    public static ItemStack getDisplayItem(ItemGroup group) {
        return new CustomItemStack(group.getIcon())
                .lore(List.of(Component.text("Click to lookup", TextColor.color(0x4abfa0))))
                .setPDCData(GROUP_KEY, PersistentDataType.STRING, group.getKey().toString())
                .getBukkit();
    }

    public static <K> ItemStack getDisplayItem(Player player, PlayerSettings<K> clazz) {
        return clazz.getIcon().apply(PlayerProfile.getProfile(player).getGuideSettings(clazz));
    }

    public MatrixMenuDrawer getDrawer() {
        if (drawer != null) {
            return drawer;
        }

        return newDrawer();
    }

    public MatrixMenuDrawer newDrawer() {
        return new MatrixMenuDrawer(54)
                .addLine("BTBBBBBSB")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addLine("BPBBBBBNB")
                .addExplain("B", "Background", MenuUtil.BACKGROUND, ClickHandler.DEFAULT)
                .addExplain("T", "Settings", GuideUtil.getSettingsButton(getPlayer()), GuideUtil::openSettings)
                .addExplain("S", "Search", GuideUtil.getSearchButton(getPlayer()), GuideUtil::openSearch)
                .addExplain("b", "Back", GuideUtil.getBackButton(getPlayer()), GuideUtil::backHistory)
                .addExplain("P", "Previous Page", getPreviousPageButton(), getPreviousPageClickHandler())
                .addExplain("N", "Next Page", getNextPageButton(), getNextPageClickHandler());
    }

    public abstract PageableMenu<T> newMenu(PageableMenu<T> menu, int newPage);
}
