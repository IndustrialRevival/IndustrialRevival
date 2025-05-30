package org.irmc.industrialrevival.api.recipes.methods;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.DropFromBlockItem;
import org.irmc.industrialrevival.api.recipes.RecipeType;

/**
 * @author baluagq
 */
@Getter
public class BlockDropMethod implements ProduceMethod {
    private final Material blockType;
    private final int dropAmount;
    private final double chance;
    private final ItemStack itemToDrop;

    public BlockDropMethod(Material blockType, int dropAmount, double chance, ItemStack itemToDrop) {
        this.blockType = blockType;
        this.dropAmount = dropAmount;
        this.chance = chance;
        this.itemToDrop = itemToDrop;
    }

    public BlockDropMethod(Material blockType, int dropAmount, double chance, DropFromBlockItem itemToDrop) {
        this.blockType = blockType;
        this.dropAmount = dropAmount;
        this.chance = chance;
        this.itemToDrop = itemToDrop.getIcon();
    }

    public static BlockDropMethod of(Material blockType, int dropAmount, double chance, ItemStack itemToDrop) {
        return new BlockDropMethod(blockType, dropAmount, chance, itemToDrop);
    }

    public static BlockDropMethod of(Material blockType, int dropAmount, double chance, DropFromBlockItem itemToDrop) {
        return new BlockDropMethod(blockType, dropAmount, chance, itemToDrop);
    }

    @Override
    public RecipeType getRecipeType() {
        return RecipeType.BLOCK_DROP;
    }

    @Override
    public ItemStack[] getIngredients() {
        return new ItemStack[0];
    }

    @Override
    public ItemStack[] getOutput() {
        return new ItemStack[]{itemToDrop};
    }
}
