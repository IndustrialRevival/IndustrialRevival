package org.irmc.industrialrevival.core.data;

import java.io.File;

public final class SqliteDataManager extends AbstractDataManager {
    public SqliteDataManager(File storageFile) {
        super("org.sqlite.JDBC", "jdbc:sqlite:" + storageFile.getAbsolutePath(), null, null);
    }
}
