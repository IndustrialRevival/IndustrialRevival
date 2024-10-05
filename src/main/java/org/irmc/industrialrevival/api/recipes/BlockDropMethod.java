package org.irmc.industrialrevival.api.recipes;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.DropFromBlockItem;

@Getter
public class BlockDropMethod implements ProduceMethod {
    private final Material dropFromBlock;
    private final int amount;
    private final int chance;
    private final ItemStack itemToDrop;

    public BlockDropMethod(Material dropFromBlock, int amount, int chance, ItemStack itemToDrop) {
        this.dropFromBlock = dropFromBlock;
        this.amount = amount;
        this.chance = chance;
        this.itemToDrop = itemToDrop;
    }

    public BlockDropMethod(Material dropFromBlock, int amount, int chance, DropFromBlockItem itemToDrop) {
        this.dropFromBlock = dropFromBlock;
        this.amount = amount;
        this.chance = chance;
        this.itemToDrop = itemToDrop.getItem();
    }
}
