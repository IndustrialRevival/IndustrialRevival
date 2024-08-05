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
 * ElectricLimitedConnector is a limited connector.
 */
public class ElectricLimitedConnector extends IndustrialRevivalItem implements EnergyNetComponent {
    private final long limit;
    public ElectricLimitedConnector(@NotNull ItemGroup group, @NotNull IndustrialRevivalItemStack itemStack, @NotNull RecipeType recipeType, @NotNull ItemStack[] recipe, long limit) {
        super(group, itemStack, recipeType, recipe);
        this.limit = limit;
    }

    @Override
    public long getCapacity() {
        return limit;
    }

    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.LIMITED_CONNECTOR;
    }
}
