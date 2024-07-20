package org.irmc.industrialrevival.core.data;

import org.irmc.industrialrevival.core.guide.GuideSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IDataManager {
    void connect(String url, String username, String password);

    void close();

    @Nullable
    GuideSettings getGuideSettings(@NotNull String playerName);

    void saveGuideSettings(@NotNull String playerName, @NotNull GuideSettings settings);
}
