package org.irmc.industrialrevival.core.data;

import java.sql.SQLException;

public final class MysqlDataManager extends AbstractDataManager {
    public MysqlDataManager(String url, String username, String password) throws SQLException {
        super("com.mysql.cj.jdbc.Driver", url, username, password);

        createTables();
    }
}
