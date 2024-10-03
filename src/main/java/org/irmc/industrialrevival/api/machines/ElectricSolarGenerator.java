package org.irmc.industrialrevival.api.machines;

import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetProvider;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * ElectricSolarGenerator is a generator that need light to generate energy.
 */
@Getter
public abstract class ElectricSolarGenerator extends IndustrialRevivalItem implements EnergyNetProvider {
    private final long capacity;
    private final long dayEnergyPerTick;
    private final long nightEnergyPerTick;
    private final byte lightLevel;

    public ElectricSolarGenerator(
            @NotNull ItemGroup group,
            @NotNull IndustrialRevivalItemStack itemStack,
            @NotNull RecipeType recipeType,
            @NotNull ItemStack[] recipe,
            long capacity,
            long dayEnergyPerTick,
            long nightEnergyPerTick,
            byte lightLevel) {
        super(group, itemStack, recipeType, recipe);
        this.capacity = capacity;
        this.dayEnergyPerTick = dayEnergyPerTick;
        this.nightEnergyPerTick = nightEnergyPerTick;
        this.lightLevel = lightLevel;
    }

    @Override
    public void preRegister() throws Exception {
        super.preRegister();
    }

    public long getEnergyProduction(Block block, @Nullable MachineMenu menu) {
        if (block.getLightFromSky() >= lightLevel) {
            return dayEnergyPerTick;
        } else {
            return nightEnergyPerTick;
        }
    }
}
