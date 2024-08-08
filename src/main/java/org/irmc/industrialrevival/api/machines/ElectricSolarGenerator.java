package org.irmc.industrialrevival.api.machines;

import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetComponent;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

/**
 * ElectricSolarGenerator is a generator that need light to generate energy.
 */
@Getter
public abstract class ElectricSolarGenerator extends IndustrialRevivalItem implements EnergyNetComponent {
    private final long capacity;
    private final long dayEnergyPerTick;
    private final long nightEnergyPerTick;
    private final byte lightLevel;

    public ElectricSolarGenerator(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, long capacity, long dayEnergyPerTick, long nightEnergyPerTick, byte lightLevel) {
        super(group, itemStack, recipeType, recipe);
        this.capacity = capacity;
        this.dayEnergyPerTick = dayEnergyPerTick;
        this.nightEnergyPerTick = nightEnergyPerTick;
        this.lightLevel = lightLevel;
    }

    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    @Override
    protected void preRegister() throws Exception {
        addItemHandlers(
          // 找个时间在idea上面改，在这改不方便
                (BlockTicker) (block, menu, data) -> tick(block, menu)
        );
        super.preRegister();
    }

    protected void tick(Block block, MachineMenu menu) {
        if (block.getLightFromSky() >= lightLevel) {
            // TODO: implement tick logic
        }
    }
}
