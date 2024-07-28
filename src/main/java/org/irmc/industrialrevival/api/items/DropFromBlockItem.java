package org.irmc.industrialrevival.api.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.attributes.BlockDropItem;
import org.irmc.industrialrevival.api.items.groups.ItemGroup;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.Range;

public class DropFromBlockItem extends IndustrialRevivalItem implements BlockDropItem {
    private final Material dropBlock;
    private final int dropAmount;
    private final double chance;

    public DropFromBlockItem(ItemGroup group, IndustrialRevivalItemStack itemStack, RecipeType recipeType, ItemStack[] recipe, Material dropBlock, int dropAmount, double chance) {
        super(group, itemStack, recipeType, recipe);

        this.dropBlock = dropBlock;
        this.dropAmount = dropAmount;
        this.chance = chance;
    }

    @Override
    public Material dropBlock() {
        return dropBlock;
    }

    @Override
    public int dropAmount() {
        return dropAmount;
    }

    @Override
    public @Range(from = 0, to = 100) double getChance() {
        return chance;
    }
}
