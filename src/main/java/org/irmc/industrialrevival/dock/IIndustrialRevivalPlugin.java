package org.irmc.industrialrevival.dock;

import org.irmc.industrialrevival.api.IndustrialRevivalAddon;
import org.irmc.industrialrevival.core.services.IRRegistry;
import org.irmc.pigeonlib.language.LanguageManager;

/**
 * This interface is for the IndustrialRevival plugin.
 */
public interface IIndustrialRevivalPlugin extends IndustrialRevivalAddon {
    IRRegistry getRegistry();

    LanguageManager getLanguageManager();
}
