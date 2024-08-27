package org.irmc.industrialrevival.api.items;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.attributes.Radiation;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

public class RadiativeItem extends IndustrialRevivalItem implements Radiation {
    private RadiationLevel radiationLevel;

    public RadiativeItem(
            @NotNull ItemGroup group,
            @NotNull IndustrialRevivalItemStack itemStack,
            @NotNull RecipeType recipeType,
            @NotNull ItemStack[] recipe) {
        super(group, itemStack, recipeType, recipe);
    }

    @Override
    public RadiationLevel getRadiationLevel() {
        return radiationLevel;
    }

    @Override
    public void setRadiationLevel(RadiationLevel radiationLevel) {
        this.radiationLevel = radiationLevel;
    }
}
