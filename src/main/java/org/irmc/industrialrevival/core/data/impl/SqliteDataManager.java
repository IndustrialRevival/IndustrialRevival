package org.irmc.industrialrevival.core.data.impl;

import org.irmc.industrialrevival.core.data.AbstractDataManager;

import java.io.File;

public final class SqliteDataManager extends AbstractDataManager {
    public SqliteDataManager(File storageFile) {
        super("jdbc:sqlite://" + storageFile.getAbsolutePath(), null, null);
    }
}
