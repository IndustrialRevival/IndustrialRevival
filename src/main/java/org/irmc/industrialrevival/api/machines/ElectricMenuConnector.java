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
 * ElectricMenuConnector is a connector with menu.
 */
public class ElectricMenuConnector extends IndustrialRevivalItem implements IRInventoryHolder, EnergyNetComponent {
    private final MachineMenu menu;
    public ElectricMenuConnector(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, @NotNull MachineMenu menu) {
        super(group, itemStack, recipeType, recipe);
        this.menu = menu;
    }

    @Override
    public long getCapacity() {
        return 0;
    }

    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.CONNECTOR;
    }

    @Override
    @NotNull
    public Inventory getInventory() {
        return menu.getInventory();
    }
}
