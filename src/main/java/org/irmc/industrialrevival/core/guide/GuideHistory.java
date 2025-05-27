package org.irmc.industrialrevival.core.guide;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.implementation.guide.SurvivalGuideImplementation;

import java.util.ArrayList;
import java.util.List;

public class GuideHistory {
    private final String playerName;
    private final List<GuideEntry<?>> entries;

    public GuideHistory(String playerName) {
        this.entries = new ArrayList<>();
        this.playerName = playerName;
    }

    public void guideOpen(SurvivalGuideImplementation guide, int page) {
        GuideEntry<SurvivalGuideImplementation> entry = new GuideEntry<>(guide);
        entry.setPage(page);
        entries.add(entry);
    }

    public void addMenu(SimpleMenu menu) {
        entries.add(GuideEntry.warp(menu));
    }

    @Deprecated
    public void addItemGroup(ItemGroup itemGroup, int page) {
        GuideEntry<ItemGroup> entry = new GuideEntry<>(itemGroup);
        entry.setPage(page);
        entries.add(entry);
    }

    @Deprecated
    public void addItem(IndustrialRevivalItem item) {
        GuideEntry<IndustrialRevivalItem> entry = new GuideEntry<>(item);
        entries.add(entry);
    }

    @Deprecated
    public void addSearchGUI(SurvivalGuideImplementation.SearchGUI searchGUI) {
        GuideEntry<SurvivalGuideImplementation.SearchGUI> entry = new GuideEntry<>(searchGUI);
        entries.add(entry);
    }

    public void goBack() {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            return;
        }

        SurvivalGuideImplementation guide = SurvivalGuideImplementation.INSTANCE;
        if (entries.isEmpty()) {
            guide.open(player);
            return;
        }

        int index = entries.size() - 2;
        if (index < 0) {
            return;
        }

        GuideEntry<?> lastEntry = entries.get(index);
        if (lastEntry != null) {
            if (lastEntry.isGroup()) {
                int page = lastEntry.getPage();
                GuideEntry<ItemGroup> theGroupEntry = (GuideEntry<ItemGroup>) lastEntry;
                guide.onGroupClicked(player, theGroupEntry.getContent(), page);
            } else if (lastEntry.isItem()) {
                GuideEntry<IndustrialRevivalItem> theItemEntry = (GuideEntry<IndustrialRevivalItem>) lastEntry;
                guide.onItemClicked(player, theItemEntry.getContent(), ClickType.UNKNOWN);
            } else if (lastEntry.isGuide()) {
                guide.open(player);
            } else if (lastEntry.isSearch()) {
                SurvivalGuideImplementation.SearchGUI searchGUI =
                        (SurvivalGuideImplementation.SearchGUI) lastEntry.getContent();
                searchGUI.showResults(1);
            } else if (lastEntry.isMenu()) {
                ((SimpleMenu) lastEntry.getContent()).open(player);
            }

            entries.add(lastEntry);
        }
    }
}
