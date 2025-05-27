package org.irmc.industrialrevival.api.menu.gui;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.handlers.ClickHandler;
import org.irmc.industrialrevival.api.objects.enums.GuideMode;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.api.player.PlayerSettings;
import org.irmc.industrialrevival.core.guide.GuideSettings;
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
        this(itemGroup.getIcon().displayName(), player, 1, itemGroup.getItems(), new HashMap<>());
        this.itemGroup = itemGroup;
    }

    public GroupMenu(Component title, Player player, int currentPage, List<IndustrialRevivalItem> items, Map<Integer, PageableMenu<IndustrialRevivalItem>> pages) {
        super(title, player, currentPage, items, pages);
        drawer.addExplain("i", "Item");

        ClickHandler clickHandler = (p, i, s, m, t) -> {
            var guideMode = PlayerProfile.getProfile(p).getGuideSettings(GuideSettings.GUIDE_MODE);

            if (i == null) {
                return false;
            }

            var n = NamespacedKey.fromString(DataUtil.getPDC(i.getItemMeta(), PageableMenu.ITEM_KEY, PersistentDataType.STRING));
            if (n != null) {
                var item = IRRegistry.getInstance().getItems().get(n);
                if (item != null) {
                    if (guideMode == GuideMode.CHEAT && p.isOp()) {
                        tryGiveItem(p, item, i, t.isRightClick() ? i.getMaxStackSize() : 1);
                    } else {
                        GuideUtil.lookup(player, item, i);
                    }
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

    @Override
    public PageableMenu<IndustrialRevivalItem> newMenu(PageableMenu<IndustrialRevivalItem> menu, int newPage) {
        return new GroupMenu(menu.getTitle(), menu.getPlayer(), newPage, menu.getItems(), menu.getPages());
    }

    public static void tryGiveItem(Player player, IndustrialRevivalItem ir, ItemStack itemStack, int amount) {
        player.getInventory().addItem(ir.getIcon().asQuantity(amount));
        player.sendMessage(Component.text("已获取 " + amount + "x ").append(ir.getIcon().displayName()));
    }

    public ItemStack getDisplayItem(IndustrialRevivalItem item) {
        return PageableMenu.getDisplayItem0(item);
    }
}
