package org.irmc.industrialrevival.api.machines;

import lombok.Getter;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetProvider;
import org.irmc.industrialrevival.api.items.collection.ItemDictionary;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.NotNull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * ElectricGenerator is a machine that generates energy.
 */
public abstract class AbstractElectricGenerator extends AbstractMachine implements EnergyNetProvider {
    @Getter
    private long capacity;

    @Override
    public AbstractElectricGenerator setItemGroup(@NotNull ItemGroup group) {
        super.setItemGroup(group);
        return this;
    }

    @Override
    public AbstractElectricGenerator setItemStack(@NotNull IndustrialRevivalItemStack itemStack) {
        super.setItemStack(itemStack);
        return this;
    }

    @Override
    public AbstractElectricGenerator addCraftMethod(@NotNull CraftMethodHandler handler) {
        super.addCraftMethod(handler);
        return this;
    }

    @Override
    public AbstractElectricGenerator setWikiText(@NotNull String wikiText) {
        super.setWikiText(wikiText);
        return this;
    }

    @Override
    public AbstractElectricGenerator setDisabledInWorld(@NotNull World world, boolean disabled) {
        super.setDisabledInWorld(world, disabled);
        return this;
    }

    @Override
    public AbstractElectricGenerator setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    @Override
    public AbstractElectricGenerator addItemDictionary(@NotNull ItemDictionary dictionary) {
        super.addItemDictionary(dictionary);
        return this;
    }

    public AbstractElectricGenerator setCapacity(long capacity) {
        checkRegistered();
        this.capacity = capacity;
        return this;
    }

    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetProvider.super.getComponentType();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    protected void preRegister() throws Exception {
        addItemHandlers((BlockTicker) (block, menu, data) -> tick(block, menu));
        super.preRegister();
    }

    protected abstract void tick(Block block, MachineMenu menu);

}
