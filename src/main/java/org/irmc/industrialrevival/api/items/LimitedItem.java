package org.irmc.industrialrevival.api.items;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.attributes.Limited;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.NotNull;

public class LimitedItem extends IndustrialRevivalItem implements Limited {
    private int limit;
    private int countLeft;

    public LimitedItem(
            @NotNull ItemGroup group,
            @NotNull IndustrialRevivalItemStack itemStack,
            @NotNull RecipeType recipeType,
            @NotNull ItemStack[] recipe,
            int limit) {
        super(group, itemStack, recipeType, recipe);
        this.limit = limit;
        this.countLeft = limit;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public int getCountLeft() {
        return countLeft;
    }

    @Override
    public void setCountLeft(int countLeft) {
        this.countLeft = countLeft;
    }
}
