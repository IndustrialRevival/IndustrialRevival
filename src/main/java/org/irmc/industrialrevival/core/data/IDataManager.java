package org.irmc.industrialrevival.core.data;

import java.sql.SQLException;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.core.data.object.BlockRecord;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public sealed interface IDataManager permits AbstractDataManager {
    @Nullable GuideSettings getGuideSettings(@NotNull String playerName);

    void saveGuideSettings(@NotNull String playerName, @NotNull GuideSettings settings);

    void handleBlockPlacing(Location loc, String machineId);

    void handleBlockBreaking(Location loc);

    /**
     * Get the research status of a player as a JsonObject.
     *
     * @param playerName the player name
     * @return the research status as a JsonObject, or an empty JsonObject if the player has no research status
     */
    @NotNull YamlConfiguration getResearchStatus(String playerName);

    void saveResearchStatus(String playerName, YamlConfiguration researchStatus);

    @NotNull YamlConfiguration getBlockData(@NotNull Location location);

    void updateBlockData(@NotNull Location location, @NotNull BlockRecord r);

    List<BlockRecord> getAllBlockRecords();

    ItemStack getMenuItem(Location location, int slot);

    void createTables() throws SQLException;

    void close();
}
