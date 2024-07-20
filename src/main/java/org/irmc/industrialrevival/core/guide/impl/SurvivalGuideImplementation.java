package org.irmc.industrialrevival.core.guide.impl;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.utils.Constants;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SurvivalGuideImplementation implements IRGuideImplementation {
    public static final SurvivalGuideImplementation INSTANCE = new SurvivalGuideImplementation();

    private static final int[] RECIPE_SLOT = {3,4,5, 12,13,14, 21,22,23};

    @Override
    public void open(Player p) {
        SimpleMenu sm = new SimpleMenu(IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Constants.GUIDE_TITLE_KEY));
        setupGuideMenu(sm);
        sm.open(p);
    }

    @Override
    public void onItemClicked(Player p, IndustrialRevivalItem item) {
        SimpleMenu sm = new SimpleMenu(IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(p, Constants.GUIDE_TITLE_KEY));

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
            history.goBack(player);
        }
    }

    private void setupGuideMenu(SimpleMenu sm) {
        Collection<ItemGroup> groups = IndustrialRevival.getInstance().getRegistry().getItemGroups().values();

    }
}
