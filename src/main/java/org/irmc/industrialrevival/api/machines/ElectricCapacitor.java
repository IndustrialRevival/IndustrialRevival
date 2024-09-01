package org.irmc.industrialrevival.api.machines;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItemStack;
import org.irmc.industrialrevival.api.items.attributes.EnergyNetComponent;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.objects.enums.EnergyNetComponentType;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

/**
 * ElectricCapacitor is an item that can be used to store energy.
 */
public abstract class ElectricCapacitor extends IndustrialRevivalItem implements EnergyNetComponent {
    private @Getter final long capacity;

    public ElectricCapacitor(
            @NotNull ItemGroup group,
            @NotNull IndustrialRevivalItemStack itemStack,
            @NotNull RecipeType recipeType,
            @NotNull ItemStack[] recipe,
            long capacity) {
        super(group, itemStack, recipeType, recipe);
        this.capacity = capacity;
    }

    @Override
    public EnergyNetComponentType getComponentType() {
        return EnergyNetComponentType.CAPACITOR;
    }
}
