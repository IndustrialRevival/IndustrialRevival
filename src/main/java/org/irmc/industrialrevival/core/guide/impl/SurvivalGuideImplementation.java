package org.irmc.industrialrevival.core.guide.impl;

import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;
import org.irmc.industrialrevival.core.player.PlayerProfile;
import org.irmc.industrialrevival.core.utils.Constants;

public enum SurvivalGuideImplementation implements IRGuideImplementation {
    INSTANCE;

    private static final int[] RECIPE_SLOT = {3,4,5, 12,13,14, 21,22,23};

    @Override
    public void open(Player p) {

    }

    @Override
    public void onItemClicked(Player player, IndustrialRevivalItem item) {
        SimpleMenu smp = new SimpleMenu(IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(player, Constants.GUIDE_TITLE_KEY));

    }

    @Override
    public void onGroupClicked(Player player, ItemGroup group) {
        group.onClick(player, 0, this);
    }

    @Override
    public void goBack(Player player) {
        PlayerProfile profile = PlayerProfile.getProfile(player);
        if (profile != null) {
            GuideHistory history = profile.getGuideHistory();
            history.goBack();
        }
    }
}
