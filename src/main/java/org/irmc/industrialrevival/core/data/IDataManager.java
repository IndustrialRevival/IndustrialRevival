package org.irmc.industrialrevival.core.data;

import org.irmc.industrialrevival.core.guide.GuideSettings;

public interface IDataManager {
    void connect(String url, String username, String password);

    void close();

    GuideSettings getGuideSettings(String playerName);

    void saveGuideSettings(String playerName, GuideSettings settings);
}
