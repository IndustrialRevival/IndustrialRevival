package org.irmc.industrialrevival.core.data;

public final class MysqlDataManager extends AbstractDataManager {
    public MysqlDataManager(String url, String username, String password) {
        super("com.mysql.cj.jdbc.Driver", url, username, password);
    }
}
