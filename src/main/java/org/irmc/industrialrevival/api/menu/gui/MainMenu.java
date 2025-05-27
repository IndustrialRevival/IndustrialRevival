package org.irmc.industrialrevival.api.menu.gui;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.utils.DataUtil;
import org.irmc.industrialrevival.utils.GuideUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainMenu extends PageableMenu<ItemGroup> {
    public MainMenu(Player player) {
        this(Component.text("Main Menu"), player, 1, getDisplayableItemGroups(player), new HashMap<>());
    }

    public MainMenu(Component title, Player player, int currentPage, List<ItemGroup> items, Map<Integer, PageableMenu<ItemGroup>> pages) {
        super(title, player, currentPage, items, pages);
        drawer.addExplain("i", "Item group");

        ClickHandler clickHandler = (p, i, s, m, t) -> {
            var n = NamespacedKey.fromString(DataUtil.getPDC(i.getItemMeta(), PageableMenu.GROUP_KEY));
            if (n != null) {
                var group = IRRegistry.getInstance().getItemGroups().get(n);
                if (group != null) {
                    group.getMenuGenerator().apply(p).open(p);
                }
            }
            return false;
        };

        List<ItemGroup> cropped = crop(currentPage);
        for (var item : cropped) {
            if (!insertFirstEmpty(getDisplayItem(item), clickHandler, drawer.getCharPositions('i'))) {
                break;
            }
        }


        GuideUtil.addToHistory(PlayerProfile.getProfile(player).getGuideHistory(), this);
    }

    @Override
    public PageableMenu<ItemGroup> newMenu(PageableMenu<ItemGroup> menu, int newPage) {
        return new MainMenu(menu.getTitle(), menu.getPlayer(), newPage, menu.getItems(), menu.getPages());
    }

    public static List<ItemGroup> getDisplayableItemGroups(Player player) {
        List<ItemGroup> itemGroups = new ArrayList<>();
        for (var i : IRRegistry.getInstance().getItemGroups().values()) {
            if (!i.isOnlyVisibleByAdmins() || player.isOp()) {
                itemGroups.add(i);
            }
        }

        return itemGroups;
    }
}
