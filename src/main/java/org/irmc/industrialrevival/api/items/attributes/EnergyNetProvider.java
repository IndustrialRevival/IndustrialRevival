package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;
import org.irmc.industrialrevival.api.objects.enums.GeneratorType;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.industrialrevival.utils.DataUtil;
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;

/**
 * This interface defines a generator can provide energy to an energy network.<br>
 * <br>
 * <b>Note: </b> use {@link EnergyNetComponent} if the machine not provides energy to the network.
 */
public interface EnergyNetProvider extends EnergyNetComponent {
    GeneratorType getGeneratorType();

    /**
     * This method should return the type of the energy net component.
     *
     * @return the type of the energy net component
     */
    @Override
    default EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    /**
     * This method can be called by the machine's tick to add energy production to the network.
     *
     * @param location         the machine's location
     * @param energyProduction the amount of energy production to add to the network
     * @return the total energy production added to the network
     */
    default long addEnergyProduction(@NotNull Location location, long energyProduction) {
        long existingEnergyProduction = 0;
        final String dataExistingEnergyProduction = DataUtil.getData(location, Constants.ENERGY_CHARGE_KEY);
        if (dataExistingEnergyProduction != null) {
            try {
                existingEnergyProduction = Long.parseLong(dataExistingEnergyProduction);
            } catch (NumberFormatException ignored) {
            }
        }
        long capacity = getCapacity();
        long charged = Math.min(capacity - existingEnergyProduction, energyProduction);
        long finalEnergy = existingEnergyProduction + charged;
        DataUtil.setData(location, Constants.ENERGY_CHARGE_KEY, String.valueOf(finalEnergy));
        return charged;
    }

    long getEnergyProduction(@NotNull Block block, @Nullable MachineMenu menu);
}
