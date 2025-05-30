package org.irmc.industrialrevival.api.menu.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Warning;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.menu.MatrixMenuDrawer;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.api.player.PlayerSettings;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.irmc.industrialrevival.utils.MenuUtil;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsMenu extends PageableMenu<PlayerSettings<?>> {
    public SettingsMenu(Player player) {
        this(player, 1);
    }

    public SettingsMenu(Player p, int page) {
        this(Component.text("设置", TextColor.color(0x4abfa0)), p, PlayerProfile.getProfile(p), page, new ArrayList<>(PlayerProfile.getProfile(p).getGuideSettings().getSettings().values()), new HashMap<>());
    }

    public SettingsMenu(Component title, Player p, PlayerProfile playerProfile, int currentPage, List<PlayerSettings<?>> settings, Map<Integer, PageableMenu<PlayerSettings<?>>> pages) {
        super(title, p, playerProfile, currentPage, settings, pages);

        ClickHandler clickHandler = (p2 ,i, s, m, t) -> {
            int[] slots = getDrawer().getCharPositions('i');
            int index = 0;
            for (var s2 : slots) {
                if (s2 != s) {
                    index++;
                } else {
                    break;
                }
            }

            PlayerSettings<?> settings2 = getItems().get(index * getCurrentPage());
            settings2.onClick(p2, i, s, m, t);
            GuideUtil.removeLastHistory(playerProfile);
            GuideUtil.openSettings(p2, currentPage);
            return false;
        };

        List<PlayerSettings<?>> cropped = crop(currentPage);
        for (var item : cropped) {
            if (!insertFirstEmpty(getDisplayItem0(p, playerProfile, item), clickHandler, getDrawer().getCharPositions('i'))) {
                break;
            }
        }

        GuideUtil.addToHistory(playerProfile.getGuideHistory(), this);
    }

    @Warning(reason = "Not implemented, use getDisplayItem0(Player, PlayerSettings) instead.")
    @Override
    public ItemStack getDisplayItem(PlayerSettings<?> item) {
        return null;
    }

    @Nonnull
    public MatrixMenuDrawer newDrawer() {
        this.drawer = new MatrixMenuDrawer(54)
                .addLine("bTBBBBBSB")
                .addLine("BiiiiiiiB")
                .addLine("BiiiiiiiB")
                .addLine("BiiiiiiiB")
                .addLine("BiiiiiiiB")
                .addLine("BPBBBBBNB");

        return drawer;
    }

    @Nonnull
    public MatrixMenuDrawer explainDrawer(MatrixMenuDrawer matrixMenuDrawer) {
        return matrixMenuDrawer
                .addExplain("B", "Background", MenuUtil.BACKGROUND, ClickHandler.DEFAULT)
                .addExplain("T", "Settings", GuideUtil.getSettingsButton(getPlayer()), GuideUtil::openSettings)
                .addExplain("S", "Search", GuideUtil.getSearchButton(getPlayer()), GuideUtil::openSearch)
                .addExplain("b", "Back", GuideUtil.getBackButton(getPlayer()), ((player, clickedItem, clickedSlot, clickedMenu, clickType) -> {
                    GuideUtil.backHistory(player, clickedItem, clickedSlot, clickedMenu, clickType);
                    GuideUtil.backHistory(player, clickedItem, clickedSlot, clickedMenu, clickType);
                    return false;
                }))
                .addExplain("P", "Previous Page", getPreviousPageButton(), getPreviousPageClickHandler())
                .addExplain("N", "Next Page", getNextPageButton(), getNextPageClickHandler());
    }

    @Override
    public PageableMenu<PlayerSettings<?>> newMenu(PageableMenu<PlayerSettings<?>> menu, int newPage) {
        return new SettingsMenu(menu.getTitle(), menu.getPlayer(), menu.getPlayerProfile(), newPage, menu.getItems(), menu.getPages());
    }
}
