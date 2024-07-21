package org.irmc.industrialrevival.core.data;

import java.sql.SQLException;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.irmc.industrialrevival.core.data.mapper.typehandler.UUIDTypeHandler;
import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IDataManager {
    void connect(String url, String username, String password) throws SQLException;

    void close();

    @Nullable GuideSettings getGuideSettings(@NotNull String playerName);

    void saveGuideSettings(@NotNull String playerName, @NotNull GuideSettings settings);

    default Configuration newMybatisConfiguration(Environment environment) {
        Configuration configuration = new Configuration(environment);
        configuration.getTypeHandlerRegistry().register(UUIDTypeHandler.class);

        return configuration;
    }
}
