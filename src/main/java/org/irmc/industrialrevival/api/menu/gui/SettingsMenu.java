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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsMenu extends PageableMenu<PlayerSettings<?>> {
    public final MatrixMenuDrawer customDrawer = new MatrixMenuDrawer(54)
                .addLine("BTBBBBBSB")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addLine("iiiiiiiii")
                .addLine("BPBBBBBNB")
                .addExplain("B", "Background", MenuUtil.BACKGROUND, ClickHandler.DEFAULT)
                .addExplain("T", "Settings", GuideUtil.getSettingsButton(getPlayer()), GuideUtil::openSettings)
            .addExplain("S", "Search", GuideUtil.getSearchButton(getPlayer()), GuideUtil::openSearch)
            .addExplain("P", "Previous Page", getPreviousPageButton(), getPreviousPageClickHandler())
            .addExplain("N", "Next Page", getNextPageButton(), getNextPageClickHandler());

    public SettingsMenu(Player p) {
        this(Component.text("设置", TextColor.color(0x4abfa0)), p, 1, new ArrayList<>(PlayerProfile.getProfile(p).getGuideSettings().getSettings().values()), new HashMap<>());
    }

    public SettingsMenu(Component title, Player p, int currentPage, List<PlayerSettings<?>> settings, Map<Integer, PageableMenu<PlayerSettings<?>>> pages) {
        super(title, p, currentPage, settings, pages);
        drawer = customDrawer;

        ClickHandler clickHandler = (p2 ,i, s, m, t) -> {
            int[] slots = drawer.getCharPositions('i');
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
            return false;
        };

        List<PlayerSettings<?>> cropped = crop(currentPage);
        for (var item : cropped) {
            if (!insertFirstEmpty(getDisplayItem0(p, item), clickHandler, drawer.getCharPositions('i'))) {
                break;
            }
        }
        GuideUtil.addToHistory(PlayerProfile.getProfile(p).getGuideHistory(), this);
    }

    @Warning(reason = "Not implemented, use getDisplayItem0(Player, PlayerSettings) instead.")
    @Override
    public ItemStack getDisplayItem(PlayerSettings<?> item) {
        return null;
    }

    @Override
    public PageableMenu<PlayerSettings<?>> newMenu(PageableMenu<PlayerSettings<?>> menu, int newPage) {
        return new SettingsMenu(getTitle(), getPlayer(), newPage, menu.getItems(), menu.getPages());
    }
}
