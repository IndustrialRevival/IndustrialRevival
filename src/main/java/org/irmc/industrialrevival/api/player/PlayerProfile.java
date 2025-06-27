package org.irmc.industrialrevival.api.player;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.irmc.industrialrevival.api.objects.enums.GuideMode;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Contains information related to {@link Player} and Industrial Revival.
 *
 * @author lijinhong11
 * @since 1.0
 */
public class PlayerProfile {
    @Getter
    private final String playerName;

    @Getter
    private final UUID playerUUID;

    @Getter
    private final GuideHistory guideHistory;

    @Getter
    private final GuideSettings guideSettings;

    public <T> T getGuideSettings(PlayerSettings<T> clazz) {
        return getGuideSettings().getPlayerSettings(clazz).get();
    }

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
    public static PlayerProfile getProfile(Player player) {
        return getProfile(player.getName());
    }

    @Nullable
    public static PlayerProfile getProfile(String playerName) {
        return IRDock.getPlugin().getRegistry().getPlayerProfiles().get(playerName);
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
            IRDock.getPlugin().getLanguageManager().sendMessage(player, "research.not_enough_exp");
            return;
        }

        player.giveExpLevels(-research.getRequiredExpLevel());
        researchStatus.put(key, true);

        IRDock.getPlugin()
                .getLanguageManager()
                .sendMessage(player, "research.success", new MessageReplacement("%name%", research.getName()));
    }
     */

    @NotNull
    @CanIgnoreReturnValue
    public static PlayerProfile getOrRequestProfile(String name) {
        if (IRDock.getPlugin().getRegistry().getPlayerProfiles().containsKey(name)) {
            return IRDock.getPlugin()
                    .getRegistry()
                    .getPlayerProfiles()
                    .get(name);
        }

        OfflinePlayer player = Bukkit.getOfflinePlayer(name);

        UUID playerUUID = player.getUniqueId();

        GuideSettings guideSettings = GuideSettings.DEFAULT_SETTINGS;
        //IRDock.getPlugin().getDataManager().getGuideSettings(name);

        Map<NamespacedKey, Boolean> researchStatus = new HashMap<>();
        ConfigurationSection researchStatusYml = new YamlConfiguration();
        //IRDock.getPlugin().getDataManager().getResearchStatus(name);

        researchStatusYml.getKeys(false).forEach(entry -> {
            NamespacedKey key = NamespacedKey.fromString(entry);
            boolean value = researchStatusYml.getBoolean(entry);
            researchStatus.put(key, value);
        });

        PlayerProfile profile = new PlayerProfile(name, playerUUID, guideSettings, researchStatus);
        IRDock.getPlugin().getRegistry().getPlayerProfiles().put(name, profile);

        return profile;
    }

    public boolean hasResearched(NamespacedKey key) {
        return researchStatus.getOrDefault(key, false);
    }
}
