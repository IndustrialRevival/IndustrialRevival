package org.irmc.industrialrevival.api.machines;

import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetComponent;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.menu.IRInventoryHolder;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.menu.MachineMenuPreset;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

/**
 * ElectricSolarGenerator is a generator that need light to generate energy.
 */
public class ElectricSolarGenerator extends IndustrialRevivalItem implements IRInventoryHolder, EnergyNetComponent {
    final MachineMenu menu;
    private @Getter final long capacity;
    private @Getter final long energyPerTick;
    private @Getter final byte lightLevel;
    public ElectricSolarGenerator(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, MachineMenu menu, long capacity, long energyPerTick, byte lightLevel) {
        super(group, itemStack, recipeType, recipe);
        this.menu = menu;
        this.capacity = capacity;
        this.energyPerTick = energyPerTick;
        this.lightLevel = lightLevel;
    }

    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    @Override
    @Nonnull
    public Inventory getInventory() {
        return menu.getInventory();
    }

    @Override
    protected void preRegister() throws Exception {
        addItemHandlers(
                new BlockTicker() {
                    @Override
                    public void onTick(Block block, MachineMenuPreset menu, IRBlockData data) {
                        tick(block, data.getMachineMenu());
                    }
                }
        );
        super.preRegister();
    }

    protected void tick(Block block, MachineMenu menu) {
        // TODO: implement electric solar generator ticking
    }
}
