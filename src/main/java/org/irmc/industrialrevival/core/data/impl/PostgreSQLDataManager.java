package org.irmc.industrialrevival.core.data.impl;

import org.irmc.industrialrevival.core.data.AbstractDataManager;

public final class PostgreSQLDataManager extends AbstractDataManager {
    public PostgreSQLDataManager(String url, String username, String password) {
        super("jdbc:postgresql://" + url + "/industrialrevival", username, password);
    }
}
