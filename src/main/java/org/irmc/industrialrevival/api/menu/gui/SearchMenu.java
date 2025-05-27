package org.irmc.industrialrevival.api.menu.gui;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.irmc.pigeonlib.chat.ChatInput;
import org.irmc.pigeonlib.items.CustomItemStack;
import org.irmc.pigeonlib.items.ItemUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SearchMenu extends PageableMenu<IndustrialRevivalItem> {
    private final String searchTerm;

    public SearchMenu(Player player, Consumer<SearchMenu> call) {
        super();
        this.searchTerm = null;
        ChatInput.waitForPlayer(IndustrialRevival.getInstance(), player, s -> {
            call.accept(new SearchMenu(getTitle(s), s, player, 1));
        });
    }

    public SearchMenu(Component title, String searchTerm, Player player, int currentPage) {
        super(title, player, currentPage, searchItems(player, searchTerm), new HashMap<>());
        this.searchTerm = searchTerm;
        drawer.addExplain("i", "Item");
        List<IndustrialRevivalItem> cropped = crop(currentPage);
        for (var item : cropped) {
            if (!insertFirstEmpty(getDisplayItem(item), drawer.getCharPositions('i'))) {
                break;
            }
        }

        GuideUtil.addToHistory(PlayerProfile.getProfile(player).getGuideHistory(), this);
    }

    public static Component getTitle(String searchTerm) {
        return Component.text("Searching: " + searchTerm);
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
        return new SearchMenu(menu.getTitle(), searchTerm, menu.getPlayer(), newPage);
    }
}
