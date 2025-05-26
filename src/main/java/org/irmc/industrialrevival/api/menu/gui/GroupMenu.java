package org.irmc.industrialrevival.api.menu.gui;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.industrialrevival.utils.DataUtil;
import org.irmc.industrialrevival.utils.GuideUtil;
import org.irmc.pigeonlib.items.ItemUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class GroupMenu extends PageableMenu<IndustrialRevivalItem> {
    private ItemGroup itemGroup;
    public GroupMenu(Player player, ItemGroup itemGroup) {
        this(itemGroup.getIcon().getItemMeta().getDisplayName(), player, 1, itemGroup.getItems(), new HashMap<>());
        this.itemGroup = itemGroup;
    }

    public GroupMenu(String title, Player player, int currentPage, List<IndustrialRevivalItem> items, Map<Integer, PageableMenu<IndustrialRevivalItem>> pages) {
        super(title, player, currentPage, items, pages);
        drawer.addExplain("i", "Item");

        ClickHandler clickHandler = (p, i, s, m, t) -> {
            var n = NamespacedKey.fromString(DataUtil.getPDC(i.getItemMeta(), PageableMenu.ITEM_KEY));
            if (n != null) {
                var item = IRRegistry.getInstance().getItems().get(n);
                if (item != null) {

                }
            }
            return false;
        };

        List<IndustrialRevivalItem> cropped = crop(currentPage);
        for (var item : cropped) {
            if (!insertFirstEmpty(getDisplayItem(item), clickHandler, drawer.getCharPositions('i'))) {
                break;
            }
        }

        GuideUtil.addToHistory(PlayerProfile.getProfile(player).getGuideHistory(), this);
    }
}
