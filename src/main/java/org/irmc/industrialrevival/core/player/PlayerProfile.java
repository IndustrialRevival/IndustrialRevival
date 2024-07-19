package org.irmc.industrialrevival.core.player;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.items.Research;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.irmc.industrialrevival.core.message.MessageReplacement;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public class PlayerProfile {
    @Getter
    private final Player player;

    @Getter
    private final GuideHistory guideHistory;

    @Getter
    private final GuideSettings guideSettings;

    private final Map<NamespacedKey, Boolean> researchStatus = new HashMap<>();

    public boolean hasResearched(NamespacedKey key) {
        return researchStatus.getOrDefault(key, false);
    }

    public void research(NamespacedKey key) {
        if (hasResearched(key)) {
            return;
        }

        Research research = Research.getResearch(key);
        if (research == null) {
            return;
        }

        if (player.getExpToLevel() < research.getRequiredExpLevel()) {
            IndustrialRevival.getInstance().getLanguageManager().sendMessage(player, "research.not_enough_exp");
            return;
        }

        player.giveExpLevels(-research.getRequiredExpLevel());
        researchStatus.put(key, true);

        IndustrialRevival.getInstance()
                .getLanguageManager()
                .sendMessage(player, "research.success", new MessageReplacement("%name%", research.getName()));
    }

    @Nullable
    public static PlayerProfile getProfile(Player player) {
        return null;
    }
}
