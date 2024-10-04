package org.irmc.industrialrevival.implementation.items.generators.manual;

import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetProvider;
import org.irmc.industrialrevival.api.machines.AbstractElectricGenerator;
import org.irmc.industrialrevival.api.objects.enums.GeneratorType;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO: 玩家尝试打开机器菜单时，发电1j （或许是一个很好的新手发电机）
public class HandGenerator extends AbstractElectricGenerator implements EnergyNetProvider {

    @Override
    public GeneratorType getGeneratorType() {
        return GeneratorType.MANUAL;
    }

    @Override
    public long getEnergyProduction(@NotNull Block block, @Nullable MachineMenu menu) {
        return 1;
    }

    @Override
    protected void tick(Block block, MachineMenu menu) {

    }

    @Override
    public void preRegister() {
        // addItemHandlers()
    }
}
