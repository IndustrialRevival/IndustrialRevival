package org.irmc.industrialrevival.core.data;

import java.sql.SQLException;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.irmc.industrialrevival.core.data.mapper.GuideSettingsMapper;
import org.irmc.industrialrevival.core.data.mapper.ResearchStatusMapper;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IDataManager {
    void connect(String url, String username, String password) throws SQLException;

    void close();

    @Nullable GuideSettings getGuideSettings(@NotNull String playerName);

    void saveGuideSettings(@NotNull String playerName, @NotNull GuideSettings settings);

    /**
     * Get the research status of a player as a JsonObject.
     * @param playerName the player name
     * @return the research status as a JsonObject, or an empty JsonObject if the player has no research status
     */
    @NotNull YamlConfiguration getResearchStatus(String playerName);

    void saveResearchStatus(String playerName, YamlConfiguration researchStatus);

    default Configuration newMybatisConfiguration(Environment environment) {
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(GuideSettingsMapper.class);
        configuration.addMapper(ResearchStatusMapper.class);

        return configuration;
    }
}
