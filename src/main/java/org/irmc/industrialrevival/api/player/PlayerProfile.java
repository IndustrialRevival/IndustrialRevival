package org.irmc.industrialrevival.api.player;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class PlayerProfile {
    @Getter
    private final String playerName;

    @Getter
    private final UUID playerUUID;

    @Getter
    private final GuideHistory guideHistory;

    @Getter
    private final GuideSettings guideSettings;

    private final Map<NamespacedKey, Boolean> researchStatus;

    protected PlayerProfile(
            String playerName,
            UUID playerUUID,
            GuideSettings guideSettings,
            Map<NamespacedKey, Boolean> researchStatus) {
        this.playerName = playerName;
        this.playerUUID = playerUUID;
        this.guideHistory = new GuideHistory(playerName);
        this.guideSettings = Objects.requireNonNullElse(guideSettings, GuideSettings.DEFAULT_SETTINGS);
        this.researchStatus = researchStatus;
    }

    @Nullable
    public static PlayerProfile getProfile(String playerName) {
        return IndustrialRevival.getInstance().getRegistry().getPlayerProfiles().get(playerName);
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

    @NotNull
    @CanIgnoreReturnValue
    public static PlayerProfile getOrRequestProfile(String name) {
        if (IndustrialRevival.getInstance().getRegistry().getPlayerProfiles().containsKey(name)) {
            return IndustrialRevival.getInstance()
                    .getRegistry()
                    .getPlayerProfiles()
                    .get(name);
        }

        OfflinePlayer player = Bukkit.getOfflinePlayer(name);

        UUID playerUUID = player.getUniqueId();

        GuideSettings guideSettings =
                IndustrialRevival.getInstance().getDataManager().getGuideSettings(name);

        Map<NamespacedKey, Boolean> researchStatus = new HashMap<>();
        ConfigurationSection researchStatusYml =
                IndustrialRevival.getInstance().getDataManager().getResearchStatus(name);

        researchStatusYml.getKeys(false).forEach(entry -> {
            NamespacedKey key = NamespacedKey.fromString(entry);
            boolean value = researchStatusYml.getBoolean(entry);
            researchStatus.put(key, value);
        });

        PlayerProfile profile = new PlayerProfile(name, playerUUID, guideSettings, researchStatus);
        IndustrialRevival.getInstance().getRegistry().getPlayerProfiles().put(name, profile);

        return profile;
    }

    public boolean hasResearched(NamespacedKey key) {
        return researchStatus.getOrDefault(key, false);
    }
}
