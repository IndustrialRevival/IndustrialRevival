package org.irmc.industrialrevival.core.data.impl;

import org.irmc.industrialrevival.core.data.AbstractDataManager;

public final class MysqlDataManager extends AbstractDataManager {
    public MysqlDataManager(String url, String username, String password) {
        super("jdbc:mysql://" + url + "/industrialrevival", username, password);
    }
}
