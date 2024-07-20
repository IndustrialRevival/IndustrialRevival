package org.irmc.industrialrevival.core.guide;

import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.core.guide.impl.SurvivalGuideImplementation;

import java.util.List;

public class GuideHistory {
    private List<GuideEntry<?>> entries;

    public GuideHistory() {}

    public void recordItemGroup(Player player, ItemGroup itemGroup, int page) {
        //TODO implement

    }

    public void goBack(Player player) {
        if (entries.isEmpty()) {
            return;
        }

        SurvivalGuideImplementation guide = SurvivalGuideImplementation.INSTANCE;

        GuideEntry<?> lastEntry = entries.get(entries.size() - 2);
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
