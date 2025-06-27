package org.irmc.industrialrevival.api.menu.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.irmc.industrialrevival.utils.MenuUtil;
import org.irmc.pigeonlib.chat.ChatInput;
import org.w3c.dom.Text;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class SearchMenu extends PageableMenu<IndustrialRevivalItem> {
    private final String searchTerm;

    public static void openSearch(Player player, Consumer<SearchMenu> call) {
        player.closeInventory();
        player.sendMessage(Component.text("搜索: ", TextColor.color(0xb0f05f)));
        ChatInput.waitForPlayer(IRDock.getPlugin(), player, s -> {
            call.accept(new SearchMenu(getTitle(s), s, player, PlayerProfile.getProfile(player), 1));
        });
    }

    public SearchMenu(Component title, String searchTerm, Player player, PlayerProfile playerProfile, int currentPage) {
        super(title, player, playerProfile, currentPage, searchItems(player, searchTerm), new HashMap<>());
        this.searchTerm = searchTerm;
        drawer.addExplain("i", "Item");
        List<IndustrialRevivalItem> cropped = crop(currentPage);
        for (var item : cropped) {
            if (!insertFirstEmpty(getDisplayItemInSearch0(item), drawer.getCharPositions('i'))) {
                break;
            }
        }

        GuideUtil.addToHistory(playerProfile.getGuideHistory(), this);
    }

    public static Component getTitle(String searchTerm) {
        return Component.text("正在搜索: " + searchTerm, TextColor.color(0xa0f05f));
    }

    public static List<IndustrialRevivalItem> searchItems(Player player, String searchTerm) {
        var c = Component.text(searchTerm);
        List<IndustrialRevivalItem> items = new ArrayList<>();
        for (var item : IRRegistry.getInstance().getItems().values()) {
            if (item.isDisabled()) {
                continue;
            }

            if (item.getItemName().contains(c)) {
                items.add(item);
            }
        }

        return items;
    }

    @Override
    public PageableMenu<IndustrialRevivalItem> newMenu(PageableMenu<IndustrialRevivalItem> menu, int newPage) {
        return new SearchMenu(menu.getTitle(), searchTerm, menu.getPlayer(), menu.getPlayerProfile(), newPage);
    }

    public ItemStack getDisplayItem(IndustrialRevivalItem item) {
        return PageableMenu.getDisplayItem0(item);
    }

    @Nonnull
    public MatrixMenuDrawer newDrawer() {
        this.drawer = new MatrixMenuDrawer(54)
                .addLine("BbBBBBBSB")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addLine("BPBBBBBNB");

        return drawer;
    }

    @Nonnull
    public MatrixMenuDrawer explainDrawer(MatrixMenuDrawer matrixMenuDrawer) {
        return matrixMenuDrawer
                .addExplain("B", "Background", MenuUtil.BACKGROUND, ClickHandler.DEFAULT)
                .addExplain("T", "Settings", GuideUtil.getSettingsButton(getPlayer()), GuideUtil::openSettings)
                .addExplain("S", "Search", GuideUtil.getSearchButton(getPlayer()), GuideUtil::openSearch)
                .addExplain("b", "Back", GuideUtil.getBackButton(getPlayer()), GuideUtil::backHistory)
                .addExplain("P", "Previous Page", getPreviousPageButton(), getPreviousPageClickHandler())
                .addExplain("N", "Next Page", getNextPageButton(), getNextPageClickHandler());
    }
}
