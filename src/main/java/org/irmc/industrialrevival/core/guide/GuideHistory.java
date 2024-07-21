package org.irmc.industrialrevival.core.guide;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.core.guide.impl.SurvivalGuideImplementation;

public class GuideHistory {
    private final String playerName;
    private final List<GuideEntry<?>> entries;

    public GuideHistory(String playerName) {
        this.entries = new ArrayList<>();
        this.playerName = playerName;
    }

    public void openItemGroup(ItemGroup itemGroup, int page) {
        GuideEntry<ItemGroup> entry = new GuideEntry<>(itemGroup);
        entry.setPage(page);
        entries.add(entry);
    }

    public void goBack() {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            return;
        }

        if (entries.isEmpty()) {
            return;
        }

        SurvivalGuideImplementation guide = SurvivalGuideImplementation.INSTANCE;

        int index = entries.size() - 2;
        if (index < 0) {
            return;
        }

        GuideEntry<?> lastEntry = entries.get(index);
        if (lastEntry != null) {
            if (lastEntry.isGroup()) {
                GuideEntry<ItemGroup> theGroupEntry = (GuideEntry<ItemGroup>) lastEntry;
                guide.onGroupClicked(player, theGroupEntry.getContent());
            } else if (lastEntry.isItem()) {
                GuideEntry<IndustrialRevivalItem> theItemEntry = (GuideEntry<IndustrialRevivalItem>) lastEntry;
                guide.onItemClicked(player, theItemEntry.getContent());
            }

            entries.add(new GuideEntry<>(lastEntry.getContent()));
        }
    }
}
