package org.irmc.industrialrevival.implementation.items.generators.manual;

import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetProvider;
import org.irmc.industrialrevival.api.items.handlers.BlockUseHandler;
import org.irmc.industrialrevival.api.machines.ElectricManualGenerator;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.utils.DataUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HandGenerator extends ElectricManualGenerator implements EnergyNetProvider {

    @Override
    public long getEnergyProduction(@NotNull Block block, @Nullable MachineMenu menu) {
        return 1;
    }

    @Override
    public void preRegister() throws Exception {
        super.preRegister();
        addItemHandlers(new BlockUseHandler() {
            @Override
            public void onRightClick(PlayerInteractEvent event) {
                Block block = event.getClickedBlock();
                if (block == null) {
                    return;
                }
                MachineMenu menu = DataUtil.getMachineMenu(block);
                addEnergyProduction(block.getLocation(), getEnergyProduction(block, menu));
            }
        });
    }
}
