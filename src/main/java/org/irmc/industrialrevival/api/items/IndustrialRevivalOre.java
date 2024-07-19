package org.irmc.industrialrevival.api.items;

import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.groups.ItemGroup;
import org.irmc.industrialrevival.api.items.attributes.ChancedItem;
import org.irmc.industrialrevival.api.recipes.RecipeType;
import org.jetbrains.annotations.Range;

public class IndustrialRevivalOre extends IndustrialRevivalItem implements ChancedItem {
    private final double chance;

    public IndustrialRevivalOre(
            ItemGroup group, IndustrialRevivalItemStack itemStack, ItemStack oreBlock, double chance) {
        super(group, itemStack, RecipeType.MINE, new ItemStack[] {null, null, null, null, oreBlock});

        this.chance = chance;
    }

    @Override
    public @Range(from = 0, to = 100) double getChance() {
        return chance;
    }
}
