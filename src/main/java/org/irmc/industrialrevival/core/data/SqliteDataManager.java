package org.irmc.industrialrevival.core.data;

import java.io.File;

public final class SqliteDataManager extends AbstractDataManager {
    public SqliteDataManager(File storageFile) {
        super("jdbc:sqlite:" + storageFile.getAbsolutePath(), null, null);
    }
}
