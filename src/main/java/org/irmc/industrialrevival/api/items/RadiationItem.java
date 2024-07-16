package org.irmc.industrialrevival.api.items;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.attributes.Radiation;
import org.irmc.industrialrevival.api.objects.enums.RadiationLevel;
import org.irmc.industrialrevival.api.recipes.RecipeType;

public class RadiationItem extends IndustrialRevivalItem implements Radiation {
    private final RadiationLevel level;

    public RadiationItem(ItemGroup group, IndustrialRevivalItemStack itemStack, RecipeType recipeType, ItemStack[] recipe, RadiationLevel level) {
        super(group, itemStack, recipeType, recipe);

        this.level = level;
    }

    @Override
    public RadiationLevel getRadiationLevel() {
        return level;
    }
}
