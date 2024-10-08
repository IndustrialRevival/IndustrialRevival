package org.irmc.industrialrevival.implementation.items.machines.manual;

import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.handlers.BlockInteractHandler;
import org.irmc.industrialrevival.api.machines.AbstractMachine;

public class Mill extends AbstractMachine {
    @Override
    public void preRegister() throws Exception {
        super.preRegister();
        addItemHandlers(new BlockInteractHandler() {
            @Override
            public void onBlockUse(PlayerInteractEvent event) {
                // TODO
            }
        });
    }
}
