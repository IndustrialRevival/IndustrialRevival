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

/*
 * ElectricMachine is a BasicMachine that consumes energy.
 */
public class ElectricMachine extends BasicMachine implements EnergyNetComponent {
    final long capacity;
    public ElectricMachine(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineRecipes machineRecipes, long capacity) {
        super(group, itemStack, recipeType, recipe, machineRecipes);
        this.capacity = capacity;
    }

    @Override
    public long getCapacity() {
        return capacity;
    }

    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    protected void preRegister() throws Exception {
        addItemHandlers(
          // 找个时间在idea上面改，在这改不方便
<<<<<< master
                (BlockTicker) (block, menu, data) -> tick(block, data.getMachineMenu())
======
                new BlockTicker() {
                    @Override
                    public void onTick(Block block, MachineMenuPreset menuPreset, IRBlockData data) {
                        tick(block, menuPreset, data.getMachineMenu());
                    }
                }
>>>>>> master
        );
        super.preRegister();
    }

    protected void tick(Block block, MachineMenuPreset menuPreset, MachineMenu menu) {
        // TODO: implement tick logic
    }
}
