package org.irmc.industrialrevival.core.implemention.menus;

import net.kyori.adventure.text.Component;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;

public abstract class AbstractMenuPreset extends MachineMenuPreset {
    public AbstractMenuPreset(String id, Component title) {
        super(id, title);

        setupItems();

        register();
    }

    protected abstract void setupItems();
}
