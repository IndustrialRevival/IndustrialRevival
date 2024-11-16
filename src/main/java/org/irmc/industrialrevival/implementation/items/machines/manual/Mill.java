package org.irmc.industrialrevival.implementation.items.machines.manual;

import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.handlers.BlockInteractHandler;
import org.irmc.industrialrevival.api.machines.AbstractMachine;
import org.irmc.industrialrevival.api.objects.events.vanilla.PlayerInteractIRBlockEvent;

public class Mill extends AbstractMachine {
    @Override
    public void preRegister() throws Exception {
        super.preRegister();
        addItemHandlers(new BlockInteractHandler() {
            /**
             * Called when a player clicks on a block with the item.
             *
             * @param event the {@link PlayerInteractEvent} was triggered
             */
            @Override
            public void onBlockUse(PlayerInteractIRBlockEvent event) {
                // todo: implement mill logic
            }
        });
    }
}
