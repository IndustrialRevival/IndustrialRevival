package org.irmc.industrialrevival.api.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.jetbrains.annotations.Nullable;

public class PlayerProfile {
    @Getter
    private final String playerName;

    @Getter
    private final UUID playerUUID;

    @Getter
    private final GuideHistory guideHistory;

    @Getter
    private final GuideSettings guideSettings;

    protected PlayerProfile(String playerName, UUID playerUUID, GuideSettings guideSettings) {
        this.playerName = playerName;
        this.playerUUID = playerUUID;
        this.guideHistory = new GuideHistory();
        this.guideSettings = guideSettings;
    }

    private final Map<NamespacedKey, Boolean> researchStatus = new HashMap<>();

    public boolean hasResearched(NamespacedKey key) {
        return researchStatus.getOrDefault(key, false);
    }

    /*
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
     */

    @Nullable
    public static PlayerProfile getProfile(Player player) {
        return null;
    }
}
