package org.irmc.industrialrevival.api.machines;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetComponent;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.machines.recipes.MachineRecipes;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

/**
 * ElectricGenerator is a machine that generates energy.
 */
public class ElectricGenerator extends AbstractMachine implements EnergyNetComponent {
    final long capacity;
    final long powerPerTick;
    public ElectricGenerator(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineRecipes machineRecipes, long capacity, long powerPerTick) {
        super(group, itemStack, recipeType, recipe, machineRecipes);
        this.capacity = capacity;
        this.powerPerTick = powerPerTick;
    }

    @Override
    public long getCapacity() {
        return capacity;
    }

    public long getPowerPerTick() {
        return powerPerTick;
    }

    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    @Override
    protected void preRegister() throws Exception {
        addItemHandlers(
                new BlockTicker() {
                    @Override
                    public void onTick(Block block, MachineMenuPreset menuPreset, IRBlockData data) {
                        tick(block, menuPreset, data.getMachineMenu());
                    }
                }
        );
        super.preRegister();
    }

    protected void tick(Block block, MachineMenuPreset menuPreset, MachineMenu menu) {
        // TODO: implement tick logic
    }
}
