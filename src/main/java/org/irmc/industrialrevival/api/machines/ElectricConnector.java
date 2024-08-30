package org.irmc.industrialrevival.api.machines;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetComponent;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

/**
 * ElectricConnector is an item that can be used to connect machines.
 */
public abstract class ElectricConnector extends IndustrialRevivalItem implements EnergyNetComponent {
    public ElectricConnector(
            @NotNull ItemGroup group,
            @NotNull IndustrialRevivalItemStack itemStack,
            @NotNull RecipeType recipeType,
            @NotNull ItemStack[] recipe) {
        super(group, itemStack, recipeType, recipe);
    }

    @Override
    public long getCapacity() {
        return 0;
    }

    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.CONNECTOR;
    }
}
