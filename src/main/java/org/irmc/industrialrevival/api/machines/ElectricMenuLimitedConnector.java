package org.irmc.industrialrevival.api.machines;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetComponent;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.menu.IRInventoryHolder;
import org.irmc.industrialrevival.api.menu.MachineMenu;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

/**
 * ElectricMenuLimitedConnector is a limited connector with menu.
 */
public class ElectricMenuLimitedConnector extends IndustrialRevivalItem implements EnergyNetComponent {
    private final MachineMenu menu;
    private final long limit;
    public ElectricMenuLimitedConnector(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineMenu menu, long limit) {
        super(group, itemStack, recipeType, recipe);
        this.menu = menu;
        this.limit = limit;
    }

    @Override
    public long getCapacity() {
        return limit;
    }

    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.CONNECTOR;
    }
}
