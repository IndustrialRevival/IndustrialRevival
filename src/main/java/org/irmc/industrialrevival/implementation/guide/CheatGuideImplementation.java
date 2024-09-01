package org.irmc.industrialrevival.implementation.guide;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.pigeonlib.items.ItemUtils;

public class CheatGuideImplementation extends SurvivalGuideImplementation {
    public static final CheatGuideImplementation INSTANCE = new CheatGuideImplementation();

    CheatGuideImplementation() {}

    @Override
    public void open(Player p) {
        SimpleMenu sm = new SimpleMenu(
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Constants.GUIDE_CHEAT_KEY));
        setupGuideMenu(p, sm);
        if (p.isOp()) {
            sm.open(p);
        } else {
            p.sendMessage(IndustrialRevival.getInstance()
                    .getLanguageManager()
                    .getMsg(p, Constants.GUIDE_CHEAT_MODE_NO_PERMISSION_KEY));
        }
    }

    @Override
    public void onItemClicked(Player player, IndustrialRevivalItem item, ClickType clickType) {
        ItemStack itemStack = item.getItem().clone();
        int amount = itemStack.getAmount();
        if (clickType.isRightClick()) {
            amount = Math.max(itemStack.getMaxStackSize(), itemStack.getAmount());
        }
        player.getInventory().addItem(ItemUtils.cloneItem(itemStack, amount));
    }
}
