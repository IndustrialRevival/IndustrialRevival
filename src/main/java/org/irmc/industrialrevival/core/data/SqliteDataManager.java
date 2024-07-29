package org.irmc.industrialrevival.core.data;

import java.io.File;
import java.sql.SQLException;

public final class SqliteDataManager extends AbstractDataManager {
    public SqliteDataManager(File storageFile) throws SQLException {
        super("org.sqlite.JDBC", "jdbc:sqlite:" + storageFile.getAbsolutePath(), null, null);

        createTables();
    }
}
