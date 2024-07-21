package org.irmc.industrialrevival.api.player;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.irmc.industrialrevival.core.utils.Constants;
import org.jetbrains.annotations.NotNull;
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

    private final Map<NamespacedKey, Boolean> researchStatus;

    protected PlayerProfile(
            String playerName,
            UUID playerUUID,
            GuideSettings guideSettings,
            Map<NamespacedKey, Boolean> researchStatus) {
        this.playerName = playerName;
        this.playerUUID = playerUUID;
        this.guideHistory = new GuideHistory(playerName);
        this.guideSettings = guideSettings;
        this.researchStatus = researchStatus;
    }

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

    @Nullable public static PlayerProfile getProfile(String playerName) {
        return IndustrialRevival.getInstance().getRegistry().getPlayerProfiles().get(playerName);
    }

    @NotNull @CanIgnoreReturnValue
    public static PlayerProfile requestProfile(String name) {
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

        // try to get data from file
        if (player.hasPlayedBefore()) {
            File playerDataFile = new File(Constants.STORAGE_FOLDER, "playerdata/" + playerUUID + ".json");
            if (playerDataFile.exists()) {
                try {
                    String data = Files.readString(playerDataFile.toPath());
                    JsonElement ele = JsonParser.parseString(data);
                    JsonObject obj = ele.getAsJsonObject();
                    JsonObject researchStatusObj = obj.getAsJsonObject("researchStatus");
                    researchStatusObj.asMap().forEach((k, v) -> {
                        NamespacedKey key = NamespacedKey.fromString(k);
                        Boolean value = v.getAsBoolean();
                        researchStatus.put(key, value);
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                playerDataFile.mkdirs();
                try {
                    playerDataFile.createNewFile();
                    Files.writeString(playerDataFile.toPath(), "{}"); // avoid errors from json reading lol
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        PlayerProfile profile = new PlayerProfile(name, playerUUID, guideSettings, researchStatus);
        IndustrialRevival.getInstance().getRegistry().getPlayerProfiles().put(name, profile);

        return profile;
    }
}
