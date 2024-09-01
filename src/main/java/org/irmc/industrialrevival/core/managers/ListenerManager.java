package org.irmc.industrialrevival.core.managers;

import org.irmc.industrialrevival.core.listeners.AbstractIRListener;
import org.irmc.industrialrevival.core.listeners.DisabledItemListener;
import org.irmc.industrialrevival.core.listeners.DropListener;
import org.irmc.industrialrevival.core.listeners.GuideListener;
import org.irmc.industrialrevival.core.listeners.InteractListener;
import org.irmc.industrialrevival.core.listeners.ItemHandlerListener;
import org.irmc.industrialrevival.core.listeners.LimitedItemListener;
import org.irmc.industrialrevival.core.listeners.MachineMenuListener;
import org.irmc.industrialrevival.core.listeners.NotPlaceableListener;
import org.irmc.industrialrevival.core.listeners.PlayerJoinListener;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {
    List<AbstractIRListener> listeners = new ArrayList<>();
    public ListenerManager() {}

    public void loadAll() {
        listeners.add(new DisabledItemListener());
        listeners.add(new DropListener());
        listeners.add(new GuideListener());
        listeners.add(new InteractListener());
        listeners.add(new ItemHandlerListener());
        listeners.add(new LimitedItemListener());
        listeners.add(new MachineMenuListener());
        listeners.add(new NotPlaceableListener());
        listeners.add(new PlayerJoinListener());
    }

    public void setupAll() {
        if (listeners.isEmpty()) {
            loadAll();
        }
        listeners.forEach(AbstractIRListener::register);
    }
}
